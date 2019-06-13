/*
 * Decompiled with CFR 0.139.
 * 
 * Could not load the following classes:
 *  question1.Cotisant
 *  question1.SoldeDebiteurException
 *  question1.Visiteur
 */
package question1;

import question1.Cotisant;
import question1.SoldeDebiteurException;
import question1.Visiteur;

public class Contributeur
extends Cotisant
implements Cloneable {
    private int solde;

    public Contributeur(String nom, int somme) {
        super(nom);
        if (somme < 0) {
            throw new RuntimeException("La somme ne peut pas etre negative");
        }
        this.solde = somme;
    }

    public int solde() {
        return this.solde;
    }

    public int nombreDeCotisants() {
        return 1;
    }

    public void debit(int somme) throws SoldeDebiteurException {
        if (somme < 0) {
            throw new RuntimeException("La somme ne peut pas etre negative");
        }
        if (this.solde < somme) {
            throw new SoldeDebiteurException("solde<somme");
        }
        this.solde -= somme;
    }

    public void credit(int somme) {
        if (somme < 0) {
            throw new RuntimeException("La somme ne peut pas etre negative");
        }
        this.solde += somme;
    }

    public void affecterSolde(int somme) {
        try {
            this.debit(this.solde());
            this.credit(somme);
        }
        catch (SoldeDebiteurException sde) {
            this.solde = somme;
        }
    }

    public <T> T accepter(Visiteur<T> visiteur) {
        return (T)visiteur.visite(this);
    }

    public String toString() {
        return "<Contributeur : " + this.nom + "," + this.solde + ">";
    }

    public void setState(Cotisant c) {
        this.solde = c.solde();
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
