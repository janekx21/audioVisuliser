import ddf.minim.*;
import ddf.minim.analysis.*;
import ddf.minim.effects.*;
import ddf.minim.signals.*;
import ddf.minim.spi.*;
import ddf.minim.ugens.*;

public Minim minim;
public AudioPlayer s;

float sliceH;
int numFrames;
int i = 0;

boolean fileShosen = false;

void setup() {
  size(500, 500);
  minim = new Minim(this);


  sliceH = height/2;

  background(0);
  stroke(0);
  noFill();
  smooth();
  strokeWeight(5);
  ellipseMode(RADIUS);
  colorMode(HSB);
  selectInput("Wähle eine Sound Datei", "fileSelect");
}

void draw() {

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

void fileSelect(File selected) {
  if (selected != null) {
    s = minim.loadFile(selected.getPath());
    numFrames = s.mix.size();
    s.play();
    fileShosen = true;
  } else {
    Object[] optionen = {"Das versuche ich nochmal", "ne Schüss"};
    int anser = javax.swing.JOptionPane.showOptionDialog(null, "Keine Musik Datei Selektiert", "ACHTUNG", javax.swing.JOptionPane.INFORMATION_MESSAGE, javax.swing.JOptionPane.WARNING_MESSAGE, null, optionen, optionen[0]);
    if (anser == 0) {
      selectInput("Selektire eine Musik Datei", "fileSelect");
    } else {
      exit();
    }
  }
}
