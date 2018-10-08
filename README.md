"# Java_object_oreinted_design_MVC" 

Animation of Shapes                                                    October-November 2017
	Developed Java Swing (GUI) programming of animation player for users to select shapes, 
pause, restart and loop of animation; control speed of playing by playback progress bar; and output SVG file
	Applied model-view-controller (MVC) architecture, principles of object-oriented programming,
 Java design patterns, and listener-event models. Utilized JUnit Test Framework to do unit tests

You can download the hw09.jar file in resource and run it in command line
It will show you the visual windows to run the program

Jar command samples :

1. our hybrid view : java -jar hw09.jar -if buildings.txt -iv interactive -o out -speed 50
2. our text view : java -jar hw09.jar -if buildings.txt -iv text -o out -speed 50
3. our svg view : java -jar hw09.jar -if buildings.txt -iv svg -o TestSvg.svg -speed 50
4. our visual view : java -jar hw09.jar -if buildings.txt -iv visual -o out -speed 50
5. provider hybrid view: java -jar hw09.jar -if buildings.txt -iv provider -o out -speed 50
6. provider visual view: java -jar hw09.jar -if buildings.txt -iv providerVisual -o out -speed 50
7. other two provider views we test output string and make them works correctly in
   TestProvider class
8. Hybrid view layers: java -jar hw09.jar -if buildings_layer.txt -iv interactive -o out -speed 20

Tips: for our hybrid views, please select shapes firstly and click start. Later, you
 can choose new shapes and click restart. For provider hybrid view, you can choose shapes
  any time and they will disappear and appear immediately.  Any output file should be
 in the resource (the jar file location), which is default path. For our hybrid view, you can
 choose any path for svg file


