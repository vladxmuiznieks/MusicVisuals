package example;

import c18511793.VladsVisual;
import processing.core.*;

// This is an example of a visual that renders the waveform
public class WaveForm
{
    MyVisual mv;
    float cy = 0;

    public WaveForm(VladsVisual vladsVisual)
    {
        this.mv = vladsVisual;
        cy = this.mv.height / 2;
    }

    public void render()
    {
        mv.colorMode(PApplet.HSB);
        for(int i = 0 ; i < mv.getAudioBuffer().size() ; i ++)
        {
            mv.stroke(
                PApplet.map(i, 0, mv.getAudioBuffer().size(), 0, 255)
                , 255
                , 255
            );

            mv.line(i, cy, i, cy + cy * mv.getAudioBuffer().get(i));
        }
    }
}