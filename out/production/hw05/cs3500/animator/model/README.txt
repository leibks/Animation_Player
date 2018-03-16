Homework 5 – readme.txt

This README file explains my design about animation model
including purpose description of all interfaces and classes.

------ Model Part ------
Interface ModelOperations:
	    This is an interface of my animation model, which support various kinds of 2D shapes and
	their animations (eg: move, change color, and so on). Several common methods for its
	implementation class.

Class BasicModel:
	    This is a class of my animation model, which implements the interface ModelOperations
	and inherit all public methods. This model contains shapes and animations information.
	It is allowed to add shapes, delete shapes, and add animations, show state of model by string
	and provide model information to the views and users.


------ Shape Part ------
Abstract Class AbstractShape:
	    This is an abstract class of 2D shapes in the model, which contains all common attributes
	of all shapes and methods to manipulate shapes. It can provide some useful data of shape
	for others to retrieve. It can show shape’s information by string.

Class Rectangle:
	    This is a subclass of shape which represents one rectangle in 2D, extends AbstractShape
	class and inherited common fields and methods.
	    Two extra fields: width(double) – width of rectangle
	and height(double) – height of rectangle.

Class Oval:
	    This is a subclass of shape which represents one oval in 2D, extends AbstractShape
	class and inherited common fields and methods.
	    Two extra fields: xRadius(double) – x radius of oval
	and yRadius (double) – y radius of oval.


------ Animation Command Part ------
Abstract Class AbstractAnimation:
	    This is an abstract class of animation commands in the model, which contains all common
	attribute and methods for animations. It can provide useful data of animation for others
	to retrieve. It can show animation’s information by string.

Class ChangeColorAnimation:
	    This is a subclass of animation which represent changing color of one shape during a time
	interval. It extends AbstractShape class and inherit its animation common fields and methods.
	    Two extra fields are changeFrom(ColorInfor) -- color change from it
	and changeTo(ColorInfor) -- color change to it.

Class ChangeScaleAnimation:
	    This is a subclass of animation which represent changing scale of one shape during atime
	interval. It extends AbstractShape class and inherit its animation common fields and methods.
	    Two extra fields are valueChange(ArrayList<Double>) -- value change from it and
	valueChangeTo(ArrayList<Double>) -- value change to it.

Class MoveAnimation:
	    This is a subclass of animation which represent move one shape positions during a time
	 interval. It extends AbstractShape class and inherit its animation common fields and methods.
	    Two extra fields are moveFrom(Position2D) – position move from it
	 and moveTo(Position2D) – position move to it.


------ Enum Part ------
Enum ShapeType:
	    Includes types of various kinds of 2D shapes in animation model.
	Now, it has rectangle and oval.

Enum AnimationType:
	    Includes types of animations commands in animation model.
	Now, it has Move, ChangeColor, and ChangeScale.


------ Tool Part ------
Class ColorInfo:
	    This is a class of containing color three information red, green, and blue
	by float numbers. The value of every part is between 0.0 to 1.0.

Class Position2D:
	This is a class of position in 2D. It contains X position and Y position in 2D.
