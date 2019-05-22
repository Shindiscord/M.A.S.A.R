package UI;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class MasarSprite{
    SpriteSheet sheet;
    private int iteration;
    private int nb_sprites;
    private int width;
    private int height;

    public MasarSprite(String ref, int width, int height, int nb_sprites){
        try {
            this.sheet = new SpriteSheet(ref, width, height);
        } catch( SlickException e) {
            e.printStackTrace();
        }
        this.width = width;
        this.height = height;
        this.nb_sprites = nb_sprites;
        this.iteration = 0;
    }

    public void drawButtonImage(float x, float y, boolean mouseIsOver){
        if(!mouseIsOver)
            sheet.getSubImage(1, 0).draw(x-(float)this.width/2, y-(float)this.height/2);
        else
            sheet.getSubImage(0,0).draw(x-(float)this.width/2, y-(float)this.height/2);
    }

    public void drawNextSubimage(float x, float y){
        sheet.getSubImage(this.iteration, 0).draw(x-(float)this.width/2, y-(float)this.height/2);
        this.iteration = (this.iteration+1)%nb_sprites;
    }

    public void drawNextSubimageRotated(float x, float y, float angle){
        Image img = sheet.getSubImage(this.iteration, 0);
        img.setCenterOfRotation(this.width/2, this.height/2);
        img.setRotation(angle);
        img.draw(x-(float)this.width/2, y-(float)this.height/2);
        this.iteration = (this.iteration+1)%nb_sprites;
    }

}
