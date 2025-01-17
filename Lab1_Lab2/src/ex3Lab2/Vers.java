package ex3Lab2;

public class Vers
{
    private String vers;
    public Vers(String vers)
    {
        this.vers = vers;
    }
    public int numaraCuvinte()
    {
        String[] cuvinte = vers.trim().split("\\s+");
        return cuvinte.length;
    }
    public int numaraVocale()
    {
        int count = 0;
        for (char c : vers.toLowerCase().toCharArray())
        {
            if ("aeiou".indexOf(c) != -1) {
                count++;
            }
        }
        return count;
    }
    public boolean seTerminaCu(String terminatie)
    {
        return vers.endsWith(terminatie);
    }
    public String toUpperCase()
    {
        return vers.toUpperCase();
    }
    public String getVers()
    {
        return vers;
    }
}
