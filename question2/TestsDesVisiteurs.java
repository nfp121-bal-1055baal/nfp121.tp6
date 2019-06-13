/*
 * Decompiled with CFR 0.139.
 * 
 * Could not load the following classes:
 *  junit.framework.TestCase
 *  question1.Contributeur
 *  question1.Cotisant
 *  question1.GroupeDeContributeurs
 *  question1.Visiteur
 *  question2.SansDoublon
 */
package question2;

import junit.framework.TestCase;
import question1.Contributeur;
import question1.Cotisant;
import question1.GroupeDeContributeurs;
import question1.Visiteur;
import question2.CompositeValide;
import question2.DebitMaximal;
import question2.SansDoublon;

public class TestsDesVisiteurs
extends TestCase {
    public void testACompleter() {
        TestsDesVisiteurs.fail((String)" cette m\u00c3\u00a9thode de tests, est \u00c3\u00a0 compl\u00c3\u00a9ter, appels des trois visiteurs....");
    }

    public void testCompositeValide() {
        try {
            GroupeDeContributeurs g = new GroupeDeContributeurs("g");
            TestsDesVisiteurs.assertFalse((String)" Ce composite n'est pas valide, revoyez CompositeValide !!!", (boolean)((Boolean)g.accepter((Visiteur)new CompositeValide())));
            GroupeDeContributeurs g1 = new GroupeDeContributeurs("g1");
            g.ajouter((Cotisant)g1);
            TestsDesVisiteurs.assertFalse((String)" Ce composite n'est pas valide, revoyez CompositeValide !!!", (boolean)((Boolean)g.accepter((Visiteur)new CompositeValide())));
            g1.ajouter((Cotisant)new Contributeur("c", 100));
            TestsDesVisiteurs.assertTrue((String)" Ce composite est valide, revoyez CompositeValide !!!", (boolean)((Boolean)g.accepter((Visiteur)new CompositeValide())));
        }
        catch (Exception e) {
            TestsDesVisiteurs.fail((String)("exception inattendue !!! " + e.getMessage()));
        }
    }

    public void testTroisContributeursUnGroupe() {
        try {
            GroupeDeContributeurs g = new GroupeDeContributeurs("g");
            g.ajouter((Cotisant)new Contributeur("g_a", 100));
            g.ajouter((Cotisant)new Contributeur("g_b", 200));
            g.ajouter((Cotisant)new Contributeur("g_c", 300));
            TestsDesVisiteurs.assertTrue((String)" Ce composite est valide, revoyez CompositeValide !!!", (boolean)((Boolean)g.accepter((Visiteur)new CompositeValide())));
            TestsDesVisiteurs.assertEquals((String)" Revoyez D\u00c3\u00a9bitMaximal !!!", (Object)new Integer(100), (Object)g.accepter((Visiteur)new DebitMaximal()));
            GroupeDeContributeurs g1 = new GroupeDeContributeurs("g1");
            g.ajouter((Cotisant)g1);
            TestsDesVisiteurs.assertFalse((String)" Ce composite n'est pas valide, revoyez CompositeValide !!!", (boolean)((Boolean)g1.accepter((Visiteur)new CompositeValide())));
        }
        catch (Exception e) {
            TestsDesVisiteurs.fail((String)("exception inattendue !!! " + e.getMessage()));
        }
    }

    public void testUnContributeurUnGroupeAvecLeMemeNom() {
        try {
            GroupeDeContributeurs g = new GroupeDeContributeurs("g_a");
            g.ajouter((Cotisant)new Contributeur("g_a", 100));
            g.ajouter((Cotisant)new Contributeur("g_b", 200));
            g.ajouter((Cotisant)new Contributeur("g_c", 300));
            g.ajouter((Cotisant)new Contributeur("g_d", 80));
            TestsDesVisiteurs.assertTrue((String)" Ce composite est valide, revoyez CompositeValide !!!", (boolean)((Boolean)g.accepter((Visiteur)new CompositeValide())));
            TestsDesVisiteurs.assertFalse((String)" Ce composite a au moins un doublon, revoyez SansDoublon !!!", (boolean)((Boolean)g.accepter((Visiteur)new SansDoublon())));
        }
        catch (Exception e) {
            TestsDesVisiteurs.fail((String)("exception inattendue !!! " + e.getMessage()));
        }
    }
}
