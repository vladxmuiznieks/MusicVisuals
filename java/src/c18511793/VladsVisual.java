package c18511793;
import ddf.minim.AudioBuffer;
import ie.tudublin.*;
import processing.core.PApplet;
import processing.opengl.Texture;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;



public class VladsVisual extends Visual
{

    public void settings()
    {
      //size(1024, 500); 
        fullScreen(P3D, SPAN);
    }

    public void setup()
    {
        colorMode(HSB);
        noCursor();

        setFrameSize(256);

        startMinim();
        loadAudio("areyouready1.mp3");
        getAudioPlayer().play();
    }


    float lerpedAverage = 0;

    float radius = 300;
    float smoothedBoxSize = 0;
    float rot = 0;

    public void draw()
    {
        calculateAverageAmplitude();
        try
        {
            calculateFFT();
        }
        catch(VisualException e)
        {
            e.printStackTrace();
        }
        calculateFrequencyBands();
        background(0);
        stroke(255);
        lights();
        stroke(map(getSmoothedAmplitude(), 0, 1, 0, 255), 255, 255);

        camera(500, -500, -500, 1, 50, 50, 100, 1, 100);

        rot += getAmplitude() / 2f;
    
        rotateY(rot);
        float[] bands = getSmoothedBands();
        for(int i = 0; i < bands.length; i++)
        {
            float theta = map(i, 0, bands.length, 0, TWO_PI * 3);
            stroke(map(i,0, bands.length, 0,255), 255, 255);
            float x = sin(theta) * radius;
            float z = cos(theta) * radius;
            float h = bands[i];
            pushMatrix();
            translate(x, -h/8, z);
            rotateY(theta);
            box(100, h, 100);
            popMatrix();

            if(!mousePressed)
            {
               
                fill(105);
                pushMatrix();
                stroke(255);
                strokeWeight(1);
                beginShape();
                vertex(0, -50);
                vertex(14, -20);
                vertex(47, -15);
                vertex(23, 7);
                vertex(29, 40);
                vertex(0, 25);
                vertex(-29, 40);
                vertex(-23, 7);
                vertex(-47, -15);
                vertex(-14, -20);
                endShape(CLOSE);
                popMatrix();
                
            }
            else{
            noFill();
            pushMatrix();
            translate(-100, 0, 0);
           box(smoothedBoxSize);
            popMatrix();
            pushMatrix();
            translate(100, 0, 0);
            strokeWeight(5); 
            box(smoothedBoxSize);
            popMatrix();
            }
            
            for( int j = 0; j < bands.length; j++)
            {
                ellipse(Y, h, h / 2, Y);
                lights();
                stroke(210);
            }
        }
      float angle = 0;
    
    }
     
    
}
