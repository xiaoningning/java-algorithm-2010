// class for mirror
public class Mirror {
    // direction
    public String direction;
    //which side is reflective
    public boolean rightSide;
    public boolean leftSide;

    public Mirror(String direct) {
        if (!direct.equals("R") && !direct.equals("L")) {
            throw new RuntimeException("The " + direct + " direction of mirror is not supported.");
        }

        direction = direct;
        rightSide = true;
        leftSide = true;
    }

    public Mirror(String direct, String side) {
        this(direct);

        if (!side.equals("R") && !side.equals("L")) {
            throw new RuntimeException("The " + side + " of a mirror is not supported.");
        }
        if (side.equals("R")) {
            leftSide = false;
        }
        if (side.equals("L")) {
            rightSide = false;
        }

    }
}
