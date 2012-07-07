
public class Triangle {

    private int sideA;
    private int sideB;
    private int sideC;

    public Triangle(int a, int b, int c) {
        sideA = a;
        sideB = b;
        sideC = c;
    }

    public int triangleType() {
        if ((sideA + sideB > sideC) && (sideA + sideC > sideB) && (sideC + sideB > sideA)) {
            if ((sideA == sideB) && (sideB == sideC))
                return 3; // equilateral
            else if ((sideA == sideB) || (sideA == sideC) || (sideB == sideC))
                return 2; // isosceles
            else
                return 1; // scalene
        } else {
            return 4; // error
        }
    }

    public static void main(String[] args) {

        Triangle triangle1 = new Triangle(5, 5, 5);
        assert(3 == triangle1.triangleType());

        Triangle triangle2 = new Triangle(6, 6, 5);
        assert(2 == triangle2.triangleType());

        Triangle triangle3 = new Triangle(5, 6, 8);
        assert(1 == triangle3.triangleType());

        Triangle triangle4 = new Triangle(1, 8, 7);
        assert(4 == triangle4.triangleType());

    }
}
