/*
 * Decompiled with CFR 0.139.
 * 
 * Could not load the following classes:
 *  question1.Contributeur
 *  question1.Cotisant
 *  question1.GroupeDeContributeurs
 */
package question3;

import java.io.PrintStream;
import question1.Contributeur;
import question1.Cotisant;
import question1.GroupeDeContributeurs;

public class Memento {
    Cotisant cotisant;

    public Memento(Cotisant c) {
        try {
            if (c instanceof Contributeur) {
                System.out.println("Contributeur");
                this.cotisant = (Contributeur)((Contributeur)c).clone();
            } else if (c instanceof GroupeDeContributeurs) {
                System.out.println("GroupeContributeur " + c.toString());
                this.cotisant = (GroupeDeContributeurs)((GroupeDeContributeurs)c).clone();
            }
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public void setState(Cotisant c) {
        System.out.println("c: " + c.toString());
        System.out.println("cotisant: " + this.cotisant.toString());
        if (c instanceof Contributeur) {
            ((Contributeur)c).setState((Cotisant)((Contributeur)this.cotisant));
        } else if (c instanceof GroupeDeContributeurs) {
            ((GroupeDeContributeurs)c).setState((Cotisant)((GroupeDeContributeurs)this.cotisant));
        }
    }
}
