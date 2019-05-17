package Objects;

public class SystemLink {

    private MasarSystem sys1, sys2;

    public SystemLink(MasarSystem sys1, MasarSystem sys2) {
        this.sys1 = sys1;
        this.sys2 = sys2;
    }

    public boolean equals(SystemLink l2){
        if((sys1 == l2.getSys1() && sys2 == l2.getSys2()) || (sys1 == l2.getSys2() && sys2 == l2.getSys1()))
            return true;
        return false;
    }

    public MasarSystem getSys1() {
        return sys1;
    }

    public MasarSystem getSys2() {
        return sys2;
    }
}
