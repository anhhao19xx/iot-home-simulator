extends Node2D

export var enabled = true

func update_status():
	for node in get_children():
		if not node.is_in_group('light'):
			continue
			
		if enabled:
			node.turn_on()
		else:
			node.turn_off()

func _ready():
	update_status()

func _on_Room_Light_input_event(viewport, event, shape_idx):
	if event is InputEventMouseButton and event.button_index == BUTTON_LEFT and event.pressed:
		enabled = not enabled
		update_status()
