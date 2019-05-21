package UI;

import Objects.MasarSystem;
import backend.MasarData;
import backend.Renderable;
import org.newdawn.slick.*;

public class WindowSystem implements Renderable{

    private MasarSystem ms;
    private MasarData gameData;
    private MasarSprite planet1, planet2, planet3, window;


    public WindowSystem(MasarSystem ms, MasarData gameData){
        this.gameData = gameData;
        this.ms = ms;

        this.window = this.gameData.getPlanetImage("InfoWindow");
        this.planet1 = this.gameData.getPlanetImage("planet1");
        this.planet2 = this.gameData.getPlanetImage("planet2");
        this.planet3 = this.gameData.getPlanetImage("planet3");
    }

    public MasarSystem getMs(){return this.ms;}

    public void render(GameContainer gc, Graphics g){
        this.window.drawNextSubimage(this.ms.getX() + 50, this.ms.getY() + 120);
        this.gameData.getFont("default").drawString(this.ms.getX() - 50 , this.ms.getY() + 120, "Pop :" + this.ms.getPop() + "/" + this.ms.getMaxPop(), Color.black);
        this.gameData.getFont("default").drawString(this.ms.getX() - 50, this.ms.getY() + 140, "RPS :" + this.ms.getRPS() + "/" + this.ms.getMaxRPS(), Color.black);
        this.gameData.getFont("default").drawString(this.ms.getX() - 50, this.ms.getY() + 160, "RPS BOOST :" + this.ms.getRPS_BOOST(), Color.black);


        if(this.ms.getLevel() == 1) {
            this.planet1.drawNextSubimage(this.ms.getX() - 30, this.ms.getY() + 80);
            this.gameData.getFont("default2").drawString(this.ms.getX() - 50, this.ms.getY() + 95, "Brigitte", Color.black);
        }

        else if(this.ms.getLevel() == 2){
            this.planet1.drawNextSubimage(this.ms.getX() - 30, this.ms.getY() + 80);
            this.planet2.drawNextSubimage(this.ms.getX() + 50, this.ms.getY() + 80);
            this.gameData.getFont("default2").drawString(this.ms.getX() - 50, this.ms.getY() + 95, "Brigitte", Color.black);
            this.gameData.getFont("default2").drawString( this.ms.getX() + 30, this.ms.getY() + 95, "Pascale", Color.black);
        }

        else if (this.ms.getLevel() == 3) {
            this.planet1.drawNextSubimage(this.ms.getX() - 30, this.ms.getY() + 80);
            this.planet2.drawNextSubimage(this.ms.getX() + 50, this.ms.getY() + 80);
            this.planet3.drawNextSubimage(this.ms.getX() + 130, this.ms.getY() + 80);
            this.gameData.getFont("default2").drawString(this.ms.getX() - 50, this.ms.getY() + 95, "Brigitte", Color.black);
            this.gameData.getFont("default2").drawString(this.ms.getX() + 30, this.ms.getY() + 95, "Pascale", Color.black);
            this.gameData.getFont("default2").drawString(this.ms.getX() + 105, this.ms.getY() + 95, "Antoinette", Color.black);
        }
    }
}