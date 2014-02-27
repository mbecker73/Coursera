/*Assignment #3
My sketch is meant to be an artistic representation of butterflies flying around either in the day time or at night (the 
user can choose). The butterflies in the sketch are drawn with randomly colored triangles that mirror each other based on the 
movement of the user clicking the mouse.
Instructions:
  Backspace or Delete: Clears the Screen
  The 's' key: Saves a screenshot
  Enter: Changes between day mode and night mode
  Mouse click: Draws the "butterflies"
*/

color strokeColor = color(192, 100, 64, 0);
int mode = 0; // mode of 0 is "daytime", 1 is "nighttime"

void setup() {
  size(720, 720);
  colorMode(HSB, 360, 100, 100, 100);
  background(360);
}

void draw() {
  // style setup 
  strokeWeight(random(0, width/2));
  stroke(strokeColor);
  fill(color(random(190), random(100), random(100), random(100)));
  // if mouse pressed draws mirrored triangles
  if (mousePressed) {
    triangle(mouseX, mouseY, mouseX+random(20, 50), mouseY+random(20, 50), mouseX-random(20, 40), mouseY-random(50, 40));
    triangle(mouseY, mouseX, mouseY+random(20, 50), mouseX+random(20, 50), mouseY-random(20, 40), mouseX-random(50, 40));
  }
}

void keyReleased() {
  // clear screen
  if ( key == DELETE || key == BACKSPACE) {
    if (mode == 0) {
      background(360);
    }
    else {
      background(0);
    }
  } 
  // save screenshot 
  else if (key == 's' || key == 'S') {
    saveFrame("screenshot.png");
  } 
  // enter changes between night and day mode and also clears screen
  else if (key == ENTER) {
    if (mode == 0) {
      mode = 1;
      background(0);
    }
    else if (mode == 1){
      mode = 0;
      background(360);
    }
  }
}


