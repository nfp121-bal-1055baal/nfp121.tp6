/*
 * Decompiled with CFR 0.139.
 * 
 * Could not load the following classes:
 *  question1.Contributeur
 *  question1.Cotisant
 *  question1.GroupeDeContributeurs
 *  question1.Visiteur
 */
package question2;

import java.io.PrintStream;
import java.util.List;
import question1.Contributeur;
import question1.Cotisant;
import question1.GroupeDeContributeurs;
import question1.Visiteur;

public class CompositeValide
implements Visiteur<Boolean> {
    public Boolean visite(Contributeur c) {
        return c.solde() >= 0;
    }

    public Boolean visite(GroupeDeContributeurs g) {
        boolean res = false;
        List<Cotisant> l = g.getChildren();
        System.out.println(l.size());
        if (l.size() == 0) {
            return res;
        }
        for (Cotisant c : l) {
            if (c instanceof Contributeur) {
                res = true;
                break;
            }
            if (!(c instanceof GroupeDeContributeurs)) continue;
            res = this.visite((GroupeDeContributeurs)c);
        }
        return res;
    }
}
