package UI;

public class MenuButton {

    int posx;
    int posy;
    String ImagePath;
    String name;
    /* 0 = main menu, 1 = chapters menu, 2 = settings menu */
    private int type;


    public MenuButton(int x, int y, String IP, String nom, int type) {
        this.posx = x;
        this.posy = y;
        this.ImagePath = IP;
        this.name = nom;
        this.type = type;
    }

    public int getPosx() {
        return this.posx;
    }

    public int getPosy() {
        return this.posy;
    }

    public String getImagePath() {return this.ImagePath;}

    public String getName() {return this.name;}

    public int getType() {return this.type;}

}
