// Assignment 3

color strokeColor = color(192, 100, 64, 10);

void setup() {
  size(720, 720);
  colorMode(HSB, 360, 100, 100, 100);
  background(360);
}

void draw() {
  //style
  strokeWeight(2);
  stroke(strokeColor);
  fill(strokeColor);
  if (mousePressed) {
    ellipse(mouseX, mouseY, 40, 40);
  }
}

void keyReleased() {
  if ( key == DELETE || key == BACKSPACE) {
    background(360);
  } 
  else if (key == 's' || key == 'S') {
    saveFrame("screenshot.png");
  }
}

