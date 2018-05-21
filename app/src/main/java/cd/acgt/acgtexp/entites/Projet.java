package cd.acgt.acgtexp.entites;

/**
 * Created by Sugar on 5/18/2018
 */


public class Projet {
    int codeProjet;
    String nom;

    public Projet(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getCodeProjet() {
        return codeProjet;
    }

    public void setCodeProjet(int codeProjet) {
        this.codeProjet = codeProjet;
    }
}
