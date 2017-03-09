import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import ddf.minim.*; 
import ddf.minim.analysis.*; 
import ddf.minim.effects.*; 
import ddf.minim.signals.*; 
import ddf.minim.spi.*; 
import ddf.minim.ugens.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class musikVisuliser extends PApplet {








public Minim minim;
public AudioPlayer s;

float sliceH;
int numFrames;
int i = 0;

boolean fileShosen = false;

public void setup() {
  
  minim = new Minim(this);


  sliceH = height/2;

  background(0);
  stroke(0);
  noFill();
  
  strokeWeight(5);
  ellipseMode(RADIUS);
  colorMode(HSB);
  selectInput("W\u00e4hle eine Sound Datei", "fileSelect");
}

public void draw() {

  if (fileShosen) {
    float[] buffer = s.mix.toArray();

    float step = buffer.length/width;
    for (int i = 0; i<width; i++) {
      line(map(buffer[i], 0, 1, 0, height), i*step, 0, i*step);
    }
    //background(0);
    rect(40, 40, width-80, height-80);
    float val= map(s.mix.get(0), -1, 1, 0, width/2);
    stroke(map(s.mix.get(5), -1, 1, 0, 255), 255, random(255));
    ellipse(width/2, height/2, val, val);
    //println(s.position());
  }
}

public void fileSelect(File selected) {
  if (selected != null) {
    s = minim.loadFile(selected.getPath());
    numFrames = s.mix.size();
    s.play();
    fileShosen = true;
  } else {
    Object[] optionen = {"Das versuche ich nochmal", "ne Sch\u00fcss"};
    int anser = javax.swing.JOptionPane.showOptionDialog(null, "Keine Musik Datei Selektiert", "ACHTUNG", javax.swing.JOptionPane.INFORMATION_MESSAGE, javax.swing.JOptionPane.WARNING_MESSAGE, null, optionen, optionen[0]);
    if (anser == 0) {
      selectInput("Selektire eine Musik Datei", "fileSelect");
    } else {
      exit();
    }
  }
}

  public void settings() {  size(500, 500);  smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "musikVisuliser" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
