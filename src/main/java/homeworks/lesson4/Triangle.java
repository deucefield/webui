package homeworks.lesson4;

public class Triangle {

    public double calculateSquare(int a, int b, int c) {
        double p=(a+b+c)/2;
        double s=Math.sqrt(p*(p-a)*(p-b)*(p-c));
        return s;
    }

}
