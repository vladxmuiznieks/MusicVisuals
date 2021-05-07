package c18511793;
import ie.tudublin.*;

public class VladsVisual extends Visual
{
    public void settings()
    {
      // size(1024, 500); 
        fullScreen(P3D);
       
    }

    public void setup()
    {
        colorMode(HSB);
        noCursor();
        startMinim();
        loadAudio("areyouready.mp3");
        getAudioPlayer().play();
       // startListening();
        
    }

    public void Keypressed()
    {
        if (key == ' ')
        {
            getAudioPlayer().cue(0);
            getAudioPlayer().play();
        }
    }

    public void draw()
    {
        background(0);

       
    }
    

 
    
}
