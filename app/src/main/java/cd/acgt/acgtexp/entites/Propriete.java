package cd.acgt.acgtexp.entites;

public class Propriete {
    int codePropriete;
    public String adresse;
    public String urlPhoto1;
    public String urlPhoto2;
    public String urlPhoto3;

    public Propriete(String adresse) {
        this.adresse = adresse;
    }

    public String getAdresse() {
        return adresse;
    }

    public int getCodePropriete() {
        return codePropriete;
    }

    public void setCodePropriete(int codePropriete) {
        this.codePropriete = codePropriete;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getUrlPhoto1() {
        return urlPhoto1;
    }

    public void setUrlPhoto1(String urlPhoto1) {
        this.urlPhoto1 = urlPhoto1;
    }

    public String getUrlPhoto2() {
        return urlPhoto2;
    }

    public void setUrlPhoto2(String urlPhoto2) {
        this.urlPhoto2 = urlPhoto2;
    }

    public String getUrlPhoto3() {
        return urlPhoto3;
    }

    public void setUrlPhoto3(String urlPhoto3) {
        this.urlPhoto3 = urlPhoto3;
    }
}
