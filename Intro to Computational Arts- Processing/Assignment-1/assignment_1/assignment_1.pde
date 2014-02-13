color blue = #1395AD;
color yellow = #EECB77;
color brown = #A46738;
color red = #BB2D43;
color gold = #DE943B;
color orange = #DA5D41;

void setup() {
  size(600,490);
  // set background to light yellow
  background(#f6e8c1);
}

void draw() {
  noLoop();
   // blue line
  stroke(blue);
  fill(blue);
  rect(0,0,600,20);

  //light yellow line
  stroke(yellow);
  fill(yellow);
  rect(0,20,600,20);

  // swirl/spiral pattern
  for(int i=0; i <= 560; i=i+140){
      swirl(i,0);
  }

  // brown line
  stroke(brown);
  fill(brown);
  rect(0,160,600,20);
  
  // blue row of upward triangles
  stroke(blue);
  fill(blue);
  for(int i=0; i<600; i=i+40){
    triangle(i+0,200,i+20,180,i+40,200);
  }
  
  // light yellow zigzag pattern
  stroke(yellow);
  fill(yellow);
  for(int i=-20; i <=560; i=i+140){
      zigzag(i,0);
  }
  
  // orange zigzag pattern
  stroke(orange);
  fill(orange);
  for(int i=0; i <=560; i=i+140){
      zigzag(i,0);
  }
  
  // maroon zigzag pattern
  stroke(red);
  fill(red);
  for(int i=-100; i <=560; i=i+140){
      zigzag(i,0);
  }
 
  // blue row of downward triangles
  stroke(blue);
  fill(blue);
  for(int i=0; i<600; i=i+40){
    triangle(i+0,300,i+20,320,i+40,300);
  }
 
  
  // crossing lines
  stroke(red);
  fill(red);
  strokeWeight(10);
  triangleLines(0,0);
  
  stroke(yellow);
  fill(yellow);
  strokeWeight(10);
  triangleLines(-100,0);
  triangleLines(500,0);

  
  stroke(gold);
  fill(gold);
  strokeWeight(10);
  triangleLines(-200,0);
  triangleLines(100,0);
  
  // brown line
  stroke(brown);
  fill(brown);
  rect(0,325,600,15);
  
  // blue line
  stroke(blue);
  fill(blue);
  rect(0,450,600,20);

  //light yellow line
  stroke(yellow);
  fill(yellow);
  rect(0,470,600,20);  
  
}

// function to draw crossing lines
void triangleLines(int x, int y){
  line(x+0,y+340,x+150,y+450);
  line(x+150,y+450,x+300,y+340);
  line(x+300,y+340,x+450,y+450);
  line(x+450,y+450,x+600,y+340);
}

// function to draw zigzag pattern
void zigzag(int x, int y){
  rect(x+0,y+220,60,20);
  rect(x+40,y+220,20,60);
  rect(x+60,y+260,60,20);
  rect(x+120,y+220,20,60);
}

// function to draw swirl/spiral pattern
void swirl(int x, int y){
  stroke(gold);
  fill(gold);
  rect(x+20,y+120,100,20);
  rect(x+100,y+40,20,20);

  stroke(orange);
  fill(orange);
  rect(x+100,y+60,20,20);
  rect(x+20,y+60,60,20);

  stroke(red);
  fill(red);
  rect(x+100,y+80,20,20);
  rect(x+20,y+80,20,20);
  triangle(x+40,y+80,x+60,y+80,x+60,y+100);
  rect(x+60,y+80,20,20);

  stroke(yellow);
  fill(yellow);
  rect(x+20,y+100,20,20);
  rect(x+100,y+100,20,20);
}


