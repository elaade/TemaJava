package Lab3;

import java.lang.Math;

class Parabola
{
    private int a, b, c;
    public Parabola(int a, int b, int c)
    {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    public double[] calculeazaVarf()
    {
        double x = -b / (2.0 * a);
        double y = -((double) b * b - 4.0 * a * c) / (4.0 * a);
        return new double[]{x, y};
    }
    @Override
    public String toString()
    {
        return "f(x) = " + a + "x^2 + " + b + "x + " + c;
    }
    public static double[] calculeazaMijloc(Parabola p1, Parabola p2)
    {
        double[] varf1 = p1.calculeazaVarf();
        double[] varf2 = p2.calculeazaVarf();
        double x = (varf1[0] + varf2[0]) / 2;
        double y = (varf1[1] + varf2[1]) / 2;
        return new double[]{x, y};
    }
    public double calculeazaLungime(double[] varfAlt)
    {
        double[] varfCurent = this.calculeazaVarf();
        return Math.hypot(varfCurent[0] - varfAlt[0], varfCurent[1] - varfAlt[1]);
    }
    public static double calculeazaLungimeIntreVarfuri(Parabola p1, Parabola p2)
    {
        double[] varf1 = p1.calculeazaVarf();
        double[] varf2 = p2.calculeazaVarf();
        return Math.hypot(varf1[0] - varf2[0], varf1[1] - varf2[1]);
    }
}
