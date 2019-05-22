package UI;

import Objects.MasarSystem;
import backend.MasarData;
import backend.Renderable;
import org.newdawn.slick.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WindowSystem implements Renderable{

    private MasarSystem ms;
    private MasarData gameData;
    private MasarSprite window, planet,planet1, planet2, planet3;
    private List<String> planetList;
    private Random rand;


    public WindowSystem(MasarSystem ms, MasarData gameData){
        this.gameData = gameData;
        this.ms = ms;

        this.window = this.gameData.getPlanetImage("InfoWindow");

        this.planetList = new ArrayList<>();
        this.planetList.add("planet1");
        this.planetList.add("planet2");
        this.planetList.add("planet3");

        rand = new Random();
        this.planet1 = this.gameData.getPlanetImage(planetList.get(rand.nextInt(planetList.size())));
        this.planet2 = this.gameData.getPlanetImage(planetList.get(rand.nextInt(planetList.size())));
        this.planet3 = this.gameData.getPlanetImage(planetList.get(rand.nextInt(planetList.size())));
    }

    public MasarSystem getMs(){return this.ms;}

    public void render(GameContainer gc, Graphics g){
        this.window.drawNextSubimage(this.ms.getX() + 50, this.ms.getY() + 120);

        int pop = this.ms.getPop();
        if ( pop > 1000000 )  pop = pop/1000000;
        else if ( pop > 1000 )  pop = pop/1000;
        int popMax = this.ms.getMaxPop()/1000000;
        if ( this.getMs().getPop()/1000000 != 0 )
            this.gameData.getFont("default").drawString(this.ms.getX() - 50 , this.ms.getY() + 120, "Pop : " + pop + "M/ " + popMax + "M", Color.black);
        else if ( this.getMs().getPop()/1000 != 0 )
            this.gameData.getFont("default").drawString(this.ms.getX() - 50 , this.ms.getY() + 120, "Pop : " + pop + "K/" + popMax + "M", Color.black);
        else
            this.gameData.getFont("default").drawString(this.ms.getX() - 50 , this.ms.getY() + 120, "Pop : " + pop + " / " + popMax + "M", Color.black);


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