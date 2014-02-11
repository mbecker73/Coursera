void setup() {
  size(600,490);
  // set background to light yellow
  background(#f6e8c1);

  // blue line
  stroke(#1395ad);
  fill(#1395ad);
  rect(0,0,600,20);

  //light yellow line
  stroke(#eecb77);
  fill(#eecb77);
  rect(0,20,600,20);

  // swirl/spiral pattern
  for(int i=0; i <= 560; i=i+140){
      swirl(i,0);
  }

  // brown line
  stroke(#a46738);
  fill(#a46738);
  rect(0,160,600,20);
  
  // blue row of upward triangles
  stroke(#1395ad);
  fill(#1395ad);
  for(int i=0; i<600; i=i+40){
    triangle(i+0,200,i+20,180,i+40,200);
  }
  
  // light yellow zigzag pattern
  stroke(#eecb77);
  fill(#eecb77);
  for(int i=-20; i <=560; i=i+140){
      zigzag(i,0);
  }
  
  // orange zigzag pattern
  stroke(#da5d41);
  fill(#da5d41);
  for(int i=0; i <=560; i=i+140){
      zigzag(i,0);
  }
  
  // maroon zigzag pattern
  stroke(#bb2d43);
  fill(#bb2d43);
  for(int i=-100; i <=560; i=i+140){
      zigzag(i,0);
  }
 
  // blue row of downward triangles
  stroke(#1395ad);
  fill(#1395ad);
  for(int i=0; i<600; i=i+40){
    triangle(i+0,300,i+20,320,i+40,300);
  }
 
  
  // crossing lines
  stroke(#bb2d43);
  fill(#bb2d43);
  strokeWeight(10);
  triangleLines(0,0);
  
  stroke(#eecb77);
  fill(#eecb77);
  strokeWeight(10);
  triangleLines(-100,0);
  triangleLines(500,0);

  
  stroke(#de943b);
  fill(#de943b);
  strokeWeight(10);
  triangleLines(-200,0);
  triangleLines(100,0);
  
  // brown line
  stroke(#a46738);
  fill(#a46738);
  rect(0,325,600,15);
  
  // blue line
  stroke(#1395ad);
  fill(#1395ad);
  rect(0,450,600,20);

  //light yellow line
  stroke(#eecb77);
  fill(#eecb77);
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
  stroke(#de943b);
  fill(#de943b);
  rect(x+20,y+120,100,20);
  rect(x+100,y+40,20,20);

  stroke(#da5d41);
  fill(#da5d41);
  rect(x+100,y+60,20,20);
  rect(x+20,y+60,60,20);

  stroke(#bb2d43);
  fill(#bb2d43);
  rect(x+100,y+80,20,20);
  rect(x+20,y+80,20,20);
  triangle(x+40,y+80,x+60,y+80,x+60,y+100);
  rect(x+60,y+80,20,20);

  stroke(#eecb77);
  fill(#eecb77);
  rect(x+20,y+100,20,20);
  rect(x+100,y+100,20,20);
}
/*
void draw(){
println (mouseX +"," + mouseY);
}*/

