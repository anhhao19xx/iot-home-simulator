extends Area2D


export var enabled = false
export var id = ''

signal on_change

var old_rotation_degrees
var old_enabled = enabled

func update_status():
	if enabled:
		rotation_degrees = old_rotation_degrees - 90
	else:
		rotation_degrees = old_rotation_degrees
		
	if old_enabled != enabled:
		old_enabled = enabled
		emit_signal("on_change")

func _ready():
	old_rotation_degrees = rotation_degrees
	update_status()

func _on_Door_input_event(viewport, event, shape_idx):
	if event is InputEventMouseButton and event.button_index == BUTTON_LEFT and event.pressed:
		enabled = not enabled
		update_status()
