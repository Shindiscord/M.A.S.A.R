package UI;

import backend.Clickable;
import backend.MasarData;
import backend.Renderable;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;

public class MenuButton implements Clickable, Renderable {

    private float posx;
    private float posy;
    private MasarSprite Sprite;
    private String name;
    private MouseOverArea mouseOverArea;
    private MasarData gameData;


    public MenuButton(float x, float y, MasarData md, String ImagePath, String name) {
        this.posx = x;
        this.posy = y;
        this.gameData = md;
        this.name = name;

        Image img = null;
        try{
            img = new Image(ImagePath);
        } catch (SlickException e) {
            e.printStackTrace();
        }

        this.mouseOverArea = new MouseOverArea(this.gameData.getGameContainer(), img, 0, 0);
        this.updateLocation();

        switch (name) {
            case "Chapters" :
                Sprite = this.gameData.getButtonsImages().get("Chapters");
                break;
            case "Settings" :
                Sprite = this.gameData.getButtonsImages().get("Settings");
                break;
            case "Quit" :
                Sprite = this.gameData.getButtonsImages().get("Quit");
                break;
            case "BackLte" :
                Sprite = this.gameData.getButtonsImages().get("BackLte");
                break;
            case "Back" :
                Sprite = this.gameData.getButtonsImages().get("Back");
                break;
            case "Chapter1" :
                Sprite = this.gameData.getButtonsImages().get("Chapter1");
                break;
            case "Done" :
                Sprite = this.gameData.getButtonsImages().get("Done");
                break;
            case "PlayPause" :
                Sprite = this.gameData.getButtonsImages().get("PlayPause");
                break;
        }
    }

    public float getPosx() {
        return this.posx;
    }

    public float getPosy() {
        return this.posy;
    }

    public String getName() {return this.name;}

    @Override
    public boolean isMouseOver() {
        int mouseX = this.gameData.getGameContainer().getInput().getMouseX();
        int mouseY = this.gameData.getGameContainer().getInput().getMouseY();
        int minX = this.mouseOverArea.getX();
        int minY = this.mouseOverArea.getY();
        int maxX = this.mouseOverArea.getX() + this.mouseOverArea.getWidth();
        int maxY = this.mouseOverArea.getY() + this.mouseOverArea.getHeight();
        if(mouseX > minX && mouseX < maxX && mouseY > minY && mouseY < maxY){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void onMousePressed(int button) {
        switch (this.getName()) {
            case "Chapters":
            case "BackLte":
                this.gameData.setRoom(1);
                break;
            case "Settings":
                this.gameData.setRoom(2);
                break;
            case "Quit":
                System.exit(0);
                break;
            case "Back":
            case "Done":
                this.gameData.setRoom(0);
                break;
            case "Chapter1":
                this.gameData.setRoom(3);
                break;
            case "PlayPause":
                if(this.gameData.getCurrentRoom().getRoomType() == 2) {
                    this.gameData.setRoom(4);
                } else {
                    this.gameData.setRoom(3);
                }
                break;
        }
    }

    @Override
    public void onMouseReleased(int button, Clickable clickablePressed) {

    }

    @Override
    public void renderHitbox(GUIContext gc, Graphics g) {
        this.mouseOverArea.render(this.gameData.getGameContainer(), g);
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        this.Sprite.drawButtonImage(this.getPosx(), this.getPosy(), this.isMouseOver());
        if(this.gameData.getCurrentRoom().getRoomType() == 3) {
            TrueTypeFont font = new TrueTypeFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 50), false);
                font.drawString(560, 325, "Pause" , Color.green);
        }
    }

    public void updateLocation(){
        float x = this.getPosx() - (float)this.mouseOverArea.getWidth()/2;
        float y =  this.getPosy() - (float)this.mouseOverArea.getHeight()/2;
        mouseOverArea.setLocation(x, y);
    }
}
