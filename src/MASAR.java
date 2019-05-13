/* Main program here test*/

public class MASAR{

    private static Game g;

    public static void main(String[] args){
        g = new Game();

        boucle();
    }

    private static void boucle(){
        while(! g.fini){
            g.ubdate();
            g.render();
        }
    }

}