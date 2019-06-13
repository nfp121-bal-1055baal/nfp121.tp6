/*
 * Decompiled with CFR 0.139.
 * 
 * Could not load the following classes:
 *  question1.GroupeDeContributeurs$GroupeIterator
 *  question1.SoldeDebiteurException
 *  question1.Visiteur
 *  question2.DebitMaximal
 */
package question1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import question1.Contributeur;
import question1.Cotisant;
import question1.GroupeDeContributeurs;
import question1.SoldeDebiteurException;
import question1.Visiteur;
import question2.DebitMaximal;
import java.util.Stack;
import java.util.NoSuchElementException;

public class GroupeDeContributeurs extends Cotisant implements Iterable<Cotisant>, Cloneable {
    public List<Cotisant> liste = new ArrayList<Cotisant>();

    public GroupeDeContributeurs(String nomDuGroupe) {
        super(nomDuGroupe);
    }

    public void ajouter(Cotisant cotisant) {
        this.liste.add(cotisant);
        cotisant.setParent(this);
    }

    @Override
    public int nombreDeCotisants() {
        int nombre = 0;
        for (Cotisant c : this.liste) {
            nombre += c.nombreDeCotisants();
        }
        return nombre;
    }

    public String toString() {
        String str = new String();
        for (Cotisant c : this.liste) {
            str = (String)str + c.toString();
        }
        return str;
    }

    public List<Cotisant> getChildren() {
        return this.liste;
    }

    @Override
    public void debit(int somme) throws SoldeDebiteurException {
        if (somme < 0) {
            throw new RuntimeException("La somme ne peut pas etre negative");
        }
        if (new DebitMaximal().visite(this) < somme) {
            throw new SoldeDebiteurException("solde<somme");
        }
        for (Cotisant c : this.liste) {
            c.debit(somme);
        }
    }

    @Override
    public void credit(int somme) {
        if (somme < 0) {
            throw new RuntimeException("La somme ne peut pas etre negative");
        }
        for (Cotisant c : this.liste) {
            c.credit(somme);
        }
    }

    @Override
    public int solde() {
        int solde = 0;
        for (Cotisant c : this.liste) {
            solde += c.solde();
        }
        return solde;
    }
    @Override
    public Iterator<Cotisant> iterator() {
        return new GroupeIterator(this.liste.iterator());
    }
    
    @Override
    public <T> T accepter(Visiteur<T> visiteur) {
        return (T)visiteur.visite(this);
    }

    public void setState(Cotisant c) {
        this.liste = ((GroupeDeContributeurs)c).getChildren();
    }

    public Object clone() throws CloneNotSupportedException {
        GroupeDeContributeurs g = new GroupeDeContributeurs(this.nom);
        for (Cotisant c : this.getChildren()) {
            if (c instanceof Contributeur) {
                g.ajouter((Contributeur)((Contributeur)c).clone());
                continue;
            }
            g.ajouter((GroupeDeContributeurs)((GroupeDeContributeurs)c).clone());
        }
        return g;
    }
    private class GroupeIterator implements Iterator<Cotisant> {
        private Stack<Iterator<Cotisant>> stk = new Stack();

        public GroupeIterator(Iterator<Cotisant> iterator) {
            this.stk.push(iterator);
        }

        @Override
        public boolean hasNext() {
            if (this.stk.empty()) {
                return false;
            }
            Iterator<Cotisant> iterator = this.stk.peek();
            if (!iterator.hasNext()) {
                this.stk.pop();
                return this.hasNext();
            }
            return true;
        }

        @Override
        public Cotisant next() {
            if (this.hasNext()) {
                Iterator<Cotisant> iterator = this.stk.peek();
                Cotisant cpt = iterator.next();
                if (cpt instanceof GroupeDeContributeurs) {
                    GroupeDeContributeurs gr = (GroupeDeContributeurs)cpt;
                    this.stk.push(gr.liste.iterator());
                }
                return cpt;
            }
            throw new NoSuchElementException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
