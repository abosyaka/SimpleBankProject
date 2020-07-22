class Circle {

    double radius;

    // write methods here
    public double getLength() {
        return Math.PI * radius * 2;
    }

    public double getArea() {
        return (Math.PI * Math.pow(radius, 2d));
    }
}