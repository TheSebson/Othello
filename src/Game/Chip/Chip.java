package Game.Chip;

public class Chip {

    private boolean side;

    public Chip(boolean side){
        this.side = side;
    }

    public void flip(){
        side = !side;
    }

    public boolean isSide() {
        return side;
    }

    @Override
    public String toString() {
        return Boolean.toString( side);
    }
}
