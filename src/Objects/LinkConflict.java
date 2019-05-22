package Objects;

class LinkConflict{

    private SystemLink link;

    LinkConflict(SystemLink link) {
        this.link = link;
    }

    void update(){
        if(link.getSys1().getClan() != link.getSys2().getClan()) {
            int hitS1 = link.getSys1().getRPS() + link.getSys1().getRPS_BOOST();
            int hitS2 = link.getSys2().getRPS() + link.getSys2().getRPS_BOOST();
            link.getSys2().beHit(link.getSys1(), hitS1);
            if(link.getSys2().getClan() != MasarSystem.NEUTRAL)
                link.getSys1().beHit(link.getSys2(), hitS2);
        }
    }
}
