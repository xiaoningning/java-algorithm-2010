// used to track position in the path
public class Position {
    public int col;
    public int row;
    public String orientation;  // "H" or "V"
    public String direction;   // "+": increase step; "-": decrease step

    public Position(int c, int r, String o, String d) {
        col = c;
        row = r;
        if (!o.equals("H") && !o.equals("V")) {
            throw new RuntimeException("The " + o + " direction of mirror is not supported.");
        }
        orientation = o;
        if (!d.equals("+") && !d.equals("-")) {
            throw new RuntimeException("The " + d + " direction of movement is not supported.");
        }
        direction = d;
    }

    public String toString() {
        return "position: " + col + "x" + row + " (" + orientation + direction + ") ";
    }

    public boolean equals(Position p) {
        if (col == p.col && row == p.row
                && orientation.equals(p.orientation)
                && direction.equals(p.direction)) {
            return true;
        } else return false;
    }

}
