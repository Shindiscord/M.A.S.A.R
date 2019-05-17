package UI;

public class MenuButton {

    int posx;
    int posy;
    String ImagePath;
    String name;


    public MenuButton(int x, int y, String IP, String nom) {
        this.posx = x;
        this.posy = y;
        this.ImagePath = IP;
        this.name = nom;
    }

    public int getPosx() {
        return this.posx;
    }

    public int getPosy() {
        return this.posy;
    }

    public String getImagePath() {return this.ImagePath;}

    public String getName() {return this.name;}

}
