extends Sprite

func _ready():
	pass # Replace with function body.

func turn_on():
	$Light2D.energy = 1
	modulate.a = 1
	
func turn_off():
	$Light2D.energy = 0
	modulate.a = .3
