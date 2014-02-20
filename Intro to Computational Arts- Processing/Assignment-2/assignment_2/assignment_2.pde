// Assignment 2

float counter =0;
color[] palette = { 
  #610000, #87311F, #AC6842, #D3A368, #FBDF8E
};
// Pythagorean theorem, cool mathematical constants equality, definition of pi, einstein's famous equation, euler's equations 
String[] words = {
  "x^2+y+2=z^2", "e^iπ = −1", "π = c/d", "E=MC^2", "V-E+F=2"
};

void setup() {
  size(800, 600);
  // black background
  background(#000000);
  noLoop();
}

void draw() {
  while (counter < 120) {
    float r = random(1, 5);
    fill(palette[int(r)]);
    textSize(r*14);
    // randomly rotate the text
    rotate(random(0, 360)); 
    textAlign(CENTER);
    text(words[int(random(1, 5))], random(width), random(height));
    counter++;
  }
}

