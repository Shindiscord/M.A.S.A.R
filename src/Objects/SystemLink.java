package Objects;

public class SystemLink {

    private MasarSystem sys1, sys2;

    public SystemLink(MasarSystem sys1, MasarSystem sys2) {
        this.sys1 = sys1;
        this.sys2 = sys2;
    }

    public MasarSystem getSys1() {
        return sys1;
    }

    public MasarSystem getSys2() {
        return sys2;
    }
}