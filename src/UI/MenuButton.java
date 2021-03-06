package UI;

import backend.Clickable;
import backend.MasarData;
import backend.Renderable;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;

/* Classe implémentant les Boutons */
public class MenuButton implements Clickable, Renderable {

    private float posx;
    private float posy;
    private MasarSprite Sprite;
    private String name;
    private MouseOverArea mouseOverArea;
    private MasarData gameData;

    /* Constructeur*/
    public MenuButton(float x, float y, MasarData md, String ImagePath, String name) {
        this.posx = x;
        this.posy = y;
        this.gameData = md;
        this.name = name;

        Image img;
        try{
            img = new Image(ImagePath);
        } catch (SlickException e) {
            e.printStackTrace();
            return;
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
            case "Chapter2" :
                Sprite = this.gameData.getButtonsImages().get("Chapter2");
                break;
            case "Chapter3" :
                Sprite = this.gameData.getButtonsImages().get("Chapter3");
                break;
            case "Chapter4" :
                Sprite = this.gameData.getButtonsImages().get("Chapter4");
                break;
            case "Chapter5" :
                Sprite = this.gameData.getButtonsImages().get("Chapter5");
                break;
            case "Done" :
                Sprite = this.gameData.getButtonsImages().get("Done");
                break;
            case "PlayPause" :
                Sprite = this.gameData.getButtonsImages().get("PlayPause");
                break;
            case "Music" :
                Sprite = this.gameData.getButtonsImages().get("Music");
                break;
            case "BackEnd" :
                Sprite = this.gameData.getButtonsImages().get("BackEnd");
                break;
        }
    }

    private float getPosx() {
        return this.posx;
    }

    private float getPosy() {
        return this.posy;
    }

    private String getName() {return this.name;}

    /* Fonction detectant si la souris est au-dessus du bouton */
    @Override
    public boolean isMouseOver() {
        int mouseX = this.gameData.getGameContainer().getInput().getMouseX();
        int mouseY = this.gameData.getGameContainer().getInput().getMouseY();
        int minX = this.mouseOverArea.getX();
        int minY = this.mouseOverArea.getY();
        int maxX = this.mouseOverArea.getX() + this.mouseOverArea.getWidth();
        int maxY = this.mouseOverArea.getY() + this.mouseOverArea.getHeight();

        return (mouseX > minX && mouseX < maxX && mouseY > minY && mouseY < maxY);
    }

    /* Détection du clic souris et réaction en fonction du bouton cliqué */
    @Override
    public void onMousePressed(int button) {
        switch (this.getName()) {
            case "Chapters":
                this.gameData.setRoom(1);
                break;
            case "BackEnd":
            case "BackLte":
                this.gameData.setRoom(1);
                this.gameData.getLinkList().clear();
                this.gameData.getSystemList().clear();
                this.gameData.getWindowList().clear();
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
                this.gameData.setChapitre(1);
                this.gameData.setRoom(3);
                break;
            case "Chapter2":
                this.gameData.setChapitre(2);
                this.gameData.setRoom(3);
                break;
            case "Chapter3":
                this.gameData.setChapitre(3);
                this.gameData.setRoom(3);
                break;
            case "Chapter4":
                this.gameData.setChapitre(4);
                this.gameData.setRoom(3);
                break;
            case "Chapter5":
                this.gameData.setChapitre(5);
                this.gameData.setRoom(3);
                break;
            case "PlayPause":
                if(this.gameData.getCurrentRoom().getRoomType() == 2) {
                    this.gameData.setRoom(4);
                } else {
                    this.gameData.setRoom(3);
                }
                break;
            case "Music":
                if(this.gameData.getBackground().playing()) {
                    this.gameData.getBackground().stop();
                } else {
                    this.gameData.getBackground().loop();
                    this.gameData.getBackground().setVolume(0.2f);
                }
                break;
        }
    }

    @Override
    public void onMouseReleased(int button, Clickable clickablePressed) {

    }

    /* Fonction de debug permettant d'afficher les hitboxs des boutons */
    @Override
    public void renderHitbox(GUIContext gc, Graphics g) {
        this.mouseOverArea.render(this.gameData.getGameContainer(), g);
    }

    /* Fonction de dessin des boutons*/
    @Override
    public void render(GameContainer gc, Graphics g) {

        if (this.gameData.getCurrentRoom().getRoomNumber() == 3) {
            if (this.gameData.getBackground().playing() && this.name.equals("Music")) {
                this.Sprite.sheet.getSubImage(0, 0).draw(this.getPosx() - (float)(344/2), this.getPosy() - (float)(144/2));
            } else if (!this.gameData.getBackground().playing() && this.name.equals("Music")) {
                this.Sprite.sheet.getSubImage(1, 0).draw(this.getPosx() - (float)(344/2), this.getPosy() - (float)(144/2));
            } else {
                this.Sprite.drawButtonImage(this.getPosx(), this.getPosy(), this.isMouseOver());
            }
            return;
        }

        this.Sprite.drawButtonImage(this.getPosx(), this.getPosy(), this.isMouseOver());
        if(this.gameData.getCurrentRoom().getRoomType() == 3) {
            TrueTypeFont font = new TrueTypeFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 50), false);
            font.drawString(560, 325, "Pause" , Color.green);
        }
    }

    private void updateLocation(){
        float x = this.getPosx() - (float)this.mouseOverArea.getWidth()/2;
        float y =  this.getPosy() - (float)this.mouseOverArea.getHeight()/2;
        mouseOverArea.setLocation(x, y);
    }
}
