extends Node2D

export var url = 'ws://127.0.0.1:5000'
var ws = null
var data = null
var is_init = false

func _ready():
	_connect()

func _connect():
	ws = WebSocketClient.new()
	ws.connect("connection_established", self, "_connection_established")
	ws.connect("connection_closed", self, "_connection_closed")
	ws.connect("connection_error", self, "_connection_error")
	
	print("Connecting to " + url)
	ws.connect_to_url(url)

func _connection_established(protocol):
	print("Connection established with protocol: ", protocol)

func _connection_closed(m):
	print("Connection closed")
	print(m)

func _connection_error():
	print("Connection error")

func _process(_delta):
	if ws.get_connection_status() == ws.CONNECTION_CONNECTING || ws.get_connection_status() == ws.CONNECTION_CONNECTED:
		ws.poll()
		
	if ws.get_peer(1).is_connected_to_host():
		if ws.get_peer(1).get_available_packet_count() > 0 :
			print('Load data')
			var text = ws.get_peer(1).get_packet().get_string_from_ascii()
			data = JSON.parse(text).result
			update_data()

func get_item(id):
	for item in data:
		if item['id'] == id:
			return item
		
	return null

func update_data():
	for node in get_children():
		if not 'id' in node:
			continue
			
		var item = get_item(node['id'])
		
		if not item:
			continue
			
		if node.enabled == item['value']:
			continue
			
		node.enabled = item['value']
		node.update_status()
		
	is_init = true


func on_change():
	if ws == null or not is_init:
		return
		
	if ws.get_peer(1).is_connected_to_host():
		var is_update = false
		
		for node in get_children():
			if not 'id' in node:
				continue
				
			var item = get_item(node['id'])
			
			if not item:
				continue
				
			if node.enabled == item['value']:
				continue
				
			item['value'] = node.enabled
			is_update = true
		
		if is_update:
			print('Save data')
			var text = JSON.print(data)
			ws.get_peer(1).put_packet(text.to_utf8())
			
