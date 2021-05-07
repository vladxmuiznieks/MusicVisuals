package c18511793;
import ddf.minim.AudioBuffer;
import ie.tudublin.*;
import processing.core.PApplet;
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

    public void keyPressed()
    {

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

        camera(500, -500, -500, 1, -50, -50, 100, 1, 100);

        rot += getAmplitude() / 3.5f;

        rotateY(rot);
        float[] bands = getSmoothedBands();
        for(int i = 0; i < bands.length; i++)
        {
            float theta = map(i, 0, bands.length, 0, TWO_PI);
            stroke(map(i,0, bands.length, 0,255), 255, 255);

            float x = sin(theta) * radius;
            float z = cos(theta) * radius;
            float h = bands[i];
            pushMatrix();
            translate(x, -h/2, z);
            rotateY(theta);
            box(-50, h, -50);
            popMatrix();
        }
      float angle = 0;
    
    }
 
    
}
