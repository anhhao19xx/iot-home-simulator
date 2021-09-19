extends Node2D

export var enabled = true
export var id = ''

var old_enabled = enabled

signal on_change

func update_status():
	for node in get_children():
		if not node.is_in_group('light'):
			continue
			
		if enabled:
			node.turn_on()
		else:
			node.turn_off()
			
	if old_enabled != enabled:
		old_enabled = enabled
		emit_signal("on_change")

func _ready():
	update_status()


func _on_Room_Light_input_event(viewport, event, shape_idx):
	if event is InputEventMouseButton and event.button_index == BUTTON_LEFT and event.pressed:
		enabled = not enabled
		update_status()
