package Ex2Tema;

public class PerecheNumere
{
    private int a;
    private int b;
    PerecheNumere() {};
    PerecheNumere(int a, int b)
    {
        this.a=a;
        this.b=b;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
    @Override
    public String toString()
    {
        return "Valoarea lui a:"+a+", "+"Valoarea lui b:"+b;
    }
    public boolean Fibbonaci()
    {
        int num1=Math.min(a,b);
        int num2=Math.max(a,b);
        int fib1=0;
        int fib2=1;
        while(fib2<=num2)
        {
            if(fib1==num1 && fib2==num2)
            {
                return true;
            }
            int next=fib1+fib2;
            fib1=fib2;
            fib2=next;
        }
        return false;
    }
    public int CelMaiMicMultipluComun(int c, int d)
    {
        int max=Math.max(c,d);
        while(true)
        {
            if(max%c==0 && max%d==0)
                return max;
            max++;
        }
    }
    public boolean SumaCifrelorUnuiNumar(int e, int f)
    {
        int s=0;
        int suma=0;
        while(e>0)
        {
            int digit=e%10;
            s=s+digit;
            e=e/10;
        }
        while(f>0)
        {
            int cifra=f%10;
            suma=suma+cifra;
            f=f/10;
        }
        if(s==suma)
        {
            return true;
        }
        else
            return false;
    }
    public boolean AcelasiNumarDeCifrePare(int g, int h)
    {
        int i=0;
        int j=0;
        while(g>0)
        {
            int digit=g%10;
            if(g%2==0)
                i++;
            g=g/10;
        }
        while(h>0)
        {
            int cifra=h%10;
            if(h%2==0)
                j++;
            h=h/10;
        }
        if(i==j)
            return true;
        else return false;
    }
}
