package clase;

public class Date {
    String nume;
    String prenume;
    String parola;
    int hash;
    public Date(String nume,String prenume, String parola,int hash)
    {
        this.nume = nume;
        this.prenume = prenume;
        this.parola = parola;
        this.hash = hashCode();
    }
    public int hashCode()
    {
        int result = 7;
        result = 31 * result + nume.hashCode();
        result = 31 * result + prenume.hashCode();
        result = 31 * result + parola.hashCode();
        return result;
    }
    public String toString()
    {
        return nume + "," + prenume + "," + parola + "," + hash;
    }
}
