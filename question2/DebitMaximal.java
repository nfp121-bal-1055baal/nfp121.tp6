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

import java.util.List;
import question1.Contributeur;
import question1.Cotisant;
import question1.GroupeDeContributeurs;
import question1.Visiteur;

public class DebitMaximal
implements Visiteur<Integer> {
    public Integer visite(Contributeur c) {
        return c.solde();
    }

    public Integer visite(GroupeDeContributeurs g) {
        if (g.getChildren().size() == 0) {
            return 0;
        }
        int res = ((Cotisant)g.getChildren().get(0)).solde();
        for (int i = 1; i < g.getChildren().size(); ++i) {
            int temp = ((Cotisant)g.getChildren().get(i)).solde();
            if (temp >= res) continue;
            res = temp;
        }
        return res;
    }
}
