package Objects;

public class Conflict {

    private MasarSystem sys1, sys2;

    public Conflict(SystemLink link) {
        this.sys1 = link.getSys1();
        this.sys2 = link.getSys2();
    }

}
