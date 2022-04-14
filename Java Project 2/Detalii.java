package clase;

public class Detalii {
    String nume;
    String prenume;
    String parola;
    String adresa;
    int telefon;
    int varsta;
    int hash;
    public Detalii(int hash, String nume, String prenume, String parola, int telefon,int varsta,String adresa)
    {
        this.nume = nume;
        this.prenume = prenume;
        this.parola = parola;
        this.adresa = adresa;
        this.telefon = telefon;
        this.varsta = varsta;
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
        return hash + "," + nume + "," + prenume + "," +  parola  + "," + varsta +  "," + telefon + "," + adresa;
    }
}
