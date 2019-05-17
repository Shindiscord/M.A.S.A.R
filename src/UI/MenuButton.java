package UI;

public class MenuButton {

    int posx;
    int posy;
    String ImagePath;


    public MenuButton(int x, int y, String IP) {
        this.posx = x;
        this.posy = y;
        this.ImagePath = IP;
    }

    public int getPosx() {
        return this.posx;
    }

    public int getPosy() {
        return this.posy;
    }

    public String getImagePath() {return this.ImagePath;}

}
