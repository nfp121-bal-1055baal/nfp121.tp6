/*
 * Decompiled with CFR 0.139.
 * 
 * Could not load the following classes:
 *  question1.SoldeDebiteurException
 *  question1.Visiteur
 */
package question1;

import question1.SoldeDebiteurException;
import question1.Visiteur;

public abstract class Cotisant {
    protected String nom;
    protected Cotisant parent;

    public Cotisant(String nom) {
        this(nom, null);
    }

    public Cotisant(String nom, Cotisant parent) {
        this.nom = nom;
        this.parent = parent;
    }

    public abstract void debit(int var1) throws SoldeDebiteurException;

    public abstract void credit(int var1);

    public abstract int solde();

    public abstract int nombreDeCotisants();

    public String nom() {
        return this.nom;
    }

    public boolean equals(Object o) {
        return this.nom.equals(((Cotisant)o).nom);
    }

    public void setParent(Cotisant parent) {
        this.parent = parent;
    }

    public Cotisant getParent() {
        return this.parent;
    }

    public abstract <T> T accepter(Visiteur<T> var1);
}
