/*
 * Decompiled with CFR 0.139.
 * 
 * Could not load the following classes:
 *  junit.framework.TestCase
 *  question1.SoldeDebiteurException
 *  question1.Visiteur
 *  question1.VisiteurToString
 */
package question1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import junit.framework.TestCase;
import question1.Contributeur;
import question1.Cotisant;
import question1.GroupeDeContributeurs;
import question1.SoldeDebiteurException;
import question1.Visiteur;
import question1.VisiteurToString;

public class TestsCotisant
extends TestCase {
    public void testEnonce() {
        Contributeur c2;
        try {
            c2 = new Contributeur("a", -100);
            TestsCotisant.fail((String)"la somme allou\u00e9e lors de la cr\u00e9ation ne peut \u00eatre n\u00e9gative ???");
        }
        catch (Exception e2) {
            // empty catch block
        }
        c2 = new Contributeur("a", 100);
        TestsCotisant.assertTrue((String)" solde erron\u00e9 ??? ", (boolean)(c2.solde() == 100));
        int val = 30;
        try {
            c2.debit(30);
        }
        catch (Exception e) {
            TestsCotisant.fail((String)" aucune exception n'est attendue ici !");
        }
        TestsCotisant.assertTrue((String)" d\u00e9bit inop\u00e9rant ??? ", (boolean)(c2.solde() == 70));
        c2.credit(30);
        TestsCotisant.assertTrue((String)" cr\u00e9dit inop\u00e9rant??? ", (boolean)(c2.solde() == 100));
        try {
            c2.debit(300);
            TestsCotisant.fail((String)"SoldeDebiteurException est attendue");
        }
        catch (Exception e) {
            TestsCotisant.assertTrue((String)" SoldeDebiteurException est attendue ???", (boolean)(e instanceof SoldeDebiteurException));
        }
        GroupeDeContributeurs g = new GroupeDeContributeurs("g");
        g.ajouter(new Contributeur("a", 100));
        g.ajouter(new Contributeur("b", 50));
        g.ajouter(new Contributeur("c", 150));
        TestsCotisant.assertEquals((String)" nombre de Contributeurs ??? ", (int)3, (int)g.nombreDeCotisants());
        TestsCotisant.assertEquals((String)" solde erron\u00e9 ??? ", (int)300, (int)g.solde());
        TestsCotisant.assertEquals((String)" solde erron\u00e9 ??? ", (int)30, (int)30);
        try {
            g.debit(10);
        }
        catch (Exception e) {
            TestsCotisant.fail();
        }
        TestsCotisant.assertEquals((String)" d\u00e9bit inop\u00e9rant ??? ", (int)(300 - g.nombreDeCotisants() * 10), (int)g.solde());
        TestsCotisant.assertEquals((String)" solde erron\u00e9 ??? ", (int)270, (int)g.solde());
        try {
            g.debit(60);
            TestsCotisant.fail((String)"SoldeDebiteurException est attendue");
        }
        catch (Exception e) {
            TestsCotisant.assertTrue((String)" SoldeDebiteurException est attendue ???", (boolean)(e instanceof SoldeDebiteurException));
        }
        TestsCotisant.assertEquals((String)" solde erron\u00e9 ??? ", (int)210, (int)g.solde());
        g = new GroupeDeContributeurs("g");
        g.ajouter(new Contributeur("g_a", 100));
        g.ajouter(new Contributeur("g_b", 200));
        g.ajouter(new Contributeur("g_c", 300));
        GroupeDeContributeurs g1 = new GroupeDeContributeurs("g1");
        g1.ajouter(new Contributeur("g1_a1", 100));
        g1.ajouter(new Contributeur("g1_b1", 200));
        g.ajouter(g1);
        Iterator<Cotisant> it = g.iterator();
        TestsCotisant.assertTrue((boolean)(it.next() instanceof Contributeur));
        TestsCotisant.assertTrue((boolean)(it.next() instanceof Contributeur));
        TestsCotisant.assertTrue((boolean)(it.next() instanceof Contributeur));
        TestsCotisant.assertTrue((boolean)(it.next() instanceof GroupeDeContributeurs));
        TestsCotisant.assertTrue((boolean)(it.next() instanceof Contributeur));
        TestsCotisant.assertTrue((boolean)(it.next() instanceof Contributeur));
        try {
            it.next();
        }
        catch (Exception ex) {
            TestsCotisant.assertTrue((boolean)(ex instanceof NoSuchElementException));
        }
    }

    public void testValeurNegative() {
        try {
            Contributeur c2;
            try {
                c2 = new Contributeur("a", -100);
                TestsCotisant.fail((String)"la somme allou\u00e9e lors de la cr\u00e9ation ne peut \u00eatre n\u00e9gative ???");
            }
            catch (Exception e2) {
                // empty catch block
            }
            c2 = new Contributeur("a", 100);
            try {
                ((Cotisant)c2).debit(-300);
                TestsCotisant.fail((String)"la somme d\u00e9bit\u00e9e ne peut \u00eatre n\u00e9gative ???");
            }
            catch (Exception exception) {
                // empty catch block
            }
            try {
                ((Cotisant)c2).credit(-300);
                TestsCotisant.fail((String)"la somme cr\u00e9dit\u00e9e ne peut \u00eatre n\u00e9gative ???");
            }
            catch (Exception exception) {}
        }
        catch (Exception e) {
            TestsCotisant.fail((String)("exception inattendue !!! " + e.getMessage()));
        }
    }

    public void testIteratorSurUnGroupeDeGroupe() {
        GroupeDeContributeurs g = new GroupeDeContributeurs("g");
        g.ajouter(new Contributeur("g_a", 100));
        g.ajouter(new Contributeur("g_b", 200));
        g.ajouter(new Contributeur("g_c", 300));
        GroupeDeContributeurs g1 = new GroupeDeContributeurs("g1");
        g1.ajouter(new Contributeur("g1_a1", 100));
        g1.ajouter(new Contributeur("g1_b1", 200));
        g.ajouter(g1);
        Iterator<Cotisant> it = g.iterator();
        TestsCotisant.assertTrue((String)" est-ce le bon Cotisant ?, un Contributeur est attendu !", (boolean)(it.next() instanceof Contributeur));
        TestsCotisant.assertTrue((String)" est-ce le bon Cotisant ?, un Contributeur est attendu !", (boolean)(it.next() instanceof Contributeur));
        TestsCotisant.assertTrue((String)" est-ce le bon Cotisant ?, un Contributeur est attendu !", (boolean)(it.next() instanceof Contributeur));
        TestsCotisant.assertTrue((String)" est-ce le bon Cotisant ?, un GroupeDeContributeurs est attendu !", (boolean)(it.next() instanceof GroupeDeContributeurs));
        TestsCotisant.assertTrue((String)" est-ce le bon Cotisant ?, un Contributeur est attendu !", (boolean)(it.next() instanceof Contributeur));
        TestsCotisant.assertTrue((String)" est-ce le bon Cotisant ?, un Contributeur est attendu !", (boolean)(it.next() instanceof Contributeur));
        try {
            it.next();
        }
        catch (Exception ex) {
            TestsCotisant.assertTrue((String)" NoSuchElementException est attendu ??? ", (boolean)(ex instanceof NoSuchElementException));
        }
    }

    public void testUnContributeur() {
        Contributeur c = new Contributeur("a", 100);
        TestsCotisant.assertTrue((String)" solde erron\u00e9 ??? ", (boolean)(c.solde() == 100));
        int val = 30;
        try {
            c.debit(30);
            TestsCotisant.assertTrue((String)" d\u00e9bit inop\u00e9rant ??? ", (boolean)(c.solde() == 70));
            c.credit(30);
            TestsCotisant.assertTrue((String)" cr\u00e9dit inop\u00e9rant??? ", (boolean)(c.solde() == 100));
            try {
                c.debit(300);
                TestsCotisant.fail((String)"le solde ne peut \u00eatre n\u00e9gatif ???");
            }
            catch (Exception e) {
                TestsCotisant.assertTrue((String)" SoldeDebiteurException est attendue ???", (boolean)(e instanceof SoldeDebiteurException));
            }
            try {
                c.debit(30);
            }
            catch (Exception e) {
                TestsCotisant.fail((String)" le solde ne peut \u00eatre nul ??? ");
            }
        }
        catch (Exception e) {
            TestsCotisant.fail((String)("exception inattendue !!! " + e.getMessage()));
        }
    }

    public void testUnGroupeDeContributeurs() {
        try {
            GroupeDeContributeurs c = new GroupeDeContributeurs("c");
            c.ajouter(new Contributeur("a", 100));
            c.ajouter(new Contributeur("b", 100));
            c.ajouter(new Contributeur("c", 100));
            TestsCotisant.assertTrue((String)" nombre de Contributeurs ??? ", (boolean)(c.nombreDeCotisants() == 3));
            TestsCotisant.assertTrue((String)" solde erron\u00e9 ??? ", (boolean)(c.solde() == c.nombreDeCotisants() * 100));
            int val = 30;
            c.debit(30);
            TestsCotisant.assertTrue((String)" d\u00e9bit inop\u00e9rant ??? ", (boolean)(c.solde() == c.nombreDeCotisants() * 100 - c.nombreDeCotisants() * 30));
            c.credit(30);
            TestsCotisant.assertTrue((String)" cr\u00e9dit inop\u00e9rant??? ", (boolean)(c.solde() == c.nombreDeCotisants() * 100));
        }
        catch (Exception e) {
            TestsCotisant.fail((String)("exception inattendue !!! " + e.getMessage()));
        }
    }

    public void testPlusieursGroupesDeContributeurs() {
        try {
            GroupeDeContributeurs g0 = new GroupeDeContributeurs("g0");
            g0.ajouter(new Contributeur("a0", 100));
            TestsCotisant.assertTrue((String)" nombre de Contributeurs ??? ", (boolean)(g0.nombreDeCotisants() == 1));
            GroupeDeContributeurs g1 = new GroupeDeContributeurs("g1");
            Contributeur a1 = new Contributeur("a1", 100);
            g1.ajouter(a1);
            g1.ajouter(new Contributeur("b1", 100));
            TestsCotisant.assertTrue((String)" nombre de Contributeurs ??? ", (boolean)(g1.nombreDeCotisants() == 2));
            GroupeDeContributeurs g2 = new GroupeDeContributeurs("g2");
            g2.ajouter(new Contributeur("a", 100));
            g2.ajouter(new Contributeur("b", 100));
            g2.ajouter(new Contributeur("c", 100));
            TestsCotisant.assertTrue((String)" nombre de Contributeurs ??? ", (boolean)(g2.nombreDeCotisants() == 3));
            TestsCotisant.assertTrue((String)" nombre de Contributeurs ??? ", (boolean)(g0.nombreDeCotisants() == 1));
            g2.ajouter(g0);
            TestsCotisant.assertTrue((String)" nombre de Contributeurs ??? ", (boolean)(g2.nombreDeCotisants() == 4));
            TestsCotisant.assertTrue((String)" nombre de Contributeurs ??? ", (boolean)(g1.nombreDeCotisants() == 2));
            g2.ajouter(g1);
            TestsCotisant.assertTrue((String)" nombre de Contributeurs ??? ", (boolean)(g2.nombreDeCotisants() == 6));
            TestsCotisant.assertTrue((String)(" solde erron\u00e9 ??? " + g2.solde()), (boolean)(g2.solde() == g2.nombreDeCotisants() * 100));
            int val = 30;
            g2.debit(30);
            TestsCotisant.assertTrue((String)" d\u00e9bit inop\u00e9rant ??? ", (boolean)(g2.solde() == g2.nombreDeCotisants() * 100 - g2.nombreDeCotisants() * 30));
            g2.credit(30);
            TestsCotisant.assertTrue((String)" cr\u00e9dit inop\u00e9rant??? ", (boolean)(g2.solde() == g2.nombreDeCotisants() * 100));
        }
        catch (Exception e) {
            TestsCotisant.fail((String)("exception inattendue !!! " + e.getMessage()));
        }
    }

    public void testGetParentSurUnGroupe() {
        try {
            GroupeDeContributeurs g0 = new GroupeDeContributeurs("g0");
            Contributeur a0 = new Contributeur("a0", 100);
            g0.ajouter(a0);
            TestsCotisant.assertTrue((String)" getParent en \u00e9chec ??? ", (boolean)(g0.getParent() == null));
            TestsCotisant.assertTrue((String)" getParent en \u00e9chec ??? ", (boolean)(a0.getParent() != null));
            TestsCotisant.assertTrue((String)" getParent  en \u00e9chec ??? ", (boolean)a0.getParent().equals(g0));
            GroupeDeContributeurs g1 = new GroupeDeContributeurs("g1");
            Contributeur a1 = new Contributeur("a1", 100);
            g1.ajouter(a1);
            g1.ajouter(new Contributeur("b1", 100));
            TestsCotisant.assertTrue((String)" getParent en \u00e9chec ??? ", (boolean)(a1.getParent() != null));
            TestsCotisant.assertTrue((String)" parent incorrect ??? ", (boolean)a1.getParent().nom().equals(g1.nom()));
            GroupeDeContributeurs g2 = new GroupeDeContributeurs("g2");
            Contributeur b = new Contributeur("b", 100);
            g2.ajouter(new Contributeur("a", 100));
            g2.ajouter(b);
            g2.ajouter(new Contributeur("c", 100));
            TestsCotisant.assertTrue((String)" getParent en \u00e9chec ??? ", (boolean)(g2.getParent() == null));
            TestsCotisant.assertTrue((String)" getParent en \u00e9chec ??? ", (boolean)(b.getParent() != null));
            TestsCotisant.assertTrue((String)" parent incorrect ??? ", (boolean)b.getParent().nom().equals(g2.nom()));
        }
        catch (Exception e) {
            TestsCotisant.fail((String)("exception inattendue !!! " + e.getMessage()));
        }
    }

    public void testUnGroupeAvecUnSoldeDebiteurDuPremierContributeur() {
        try {
            Contributeur a = new Contributeur("a", 100);
            Contributeur b = new Contributeur("b", 200);
            Contributeur c = new Contributeur("c", 300);
            GroupeDeContributeurs g = new GroupeDeContributeurs("g");
            g.ajouter(a);
            g.ajouter(b);
            g.ajouter(c);
            TestsCotisant.assertTrue((String)" nombre de Contributeurs ??? ", (boolean)(g.nombreDeCotisants() == 3));
            TestsCotisant.assertTrue((String)" solde erron\u00e9 ??? ", (boolean)(g.solde() == 600));
            try {
                g.debit(120);
            }
            catch (Exception e) {
                TestsCotisant.assertTrue((String)" SoldeDebiteurException est attendue ???", (boolean)(e instanceof SoldeDebiteurException));
            }
            TestsCotisant.assertTrue((String)" \u00e0 la question3 seulement l'atomicit\u00e9 est souhait\u00e9e ", (boolean)(a.solde() == 100));
            TestsCotisant.assertTrue((String)" \u00e0 la question3 seulement l'atomicit\u00e9 est souhait\u00e9e ", (boolean)(b.solde() == 200));
            TestsCotisant.assertTrue((String)" \u00e0 la question3 seulement l'atomicit\u00e9 est souhait\u00e9e ", (boolean)(c.solde() == 300));
            TestsCotisant.assertTrue((String)" solde erron\u00e9 ??? ", (boolean)(g.solde() == 600));
        }
        catch (Exception e) {
            TestsCotisant.fail((String)("exception inattendue !!! " + e.getMessage()));
        }
    }

    public void testUnGroupeAvecUnSoldeDebiteurDuDernierContributeur() {
        try {
            Contributeur a = new Contributeur("a", 300);
            Contributeur b = new Contributeur("b", 200);
            Contributeur c = new Contributeur("c", 100);
            GroupeDeContributeurs g = new GroupeDeContributeurs("g");
            g.ajouter(a);
            g.ajouter(b);
            g.ajouter(c);
            TestsCotisant.assertTrue((String)" nombre de Contributeurs ??? ", (boolean)(g.nombreDeCotisants() == 3));
            TestsCotisant.assertTrue((String)" solde erron\u00e9 ??? ", (boolean)(g.solde() == 600));
            try {
                g.debit(120);
            }
            catch (Exception e) {
                TestsCotisant.assertTrue((String)" SoldeDebiteurException est attendue ???", (boolean)(e instanceof SoldeDebiteurException));
            }
            TestsCotisant.assertTrue((String)" \u00e0 la question3 seulement l'atomicit\u00e9 est souhait\u00e9e ", (boolean)(a.solde() == 180));
            TestsCotisant.assertTrue((String)" \u00e0 la question3 seulement l'atomicit\u00e9 est souhait\u00e9e ", (boolean)(b.solde() == 80));
            TestsCotisant.assertTrue((String)" \u00e0 la question3 seulement l'atomicit\u00e9 est souhait\u00e9e ", (boolean)(c.solde() == 100));
            TestsCotisant.assertTrue((String)" solde erron\u00e9 ??? ", (boolean)(g.solde() == 360));
        }
        catch (Exception e) {
            TestsCotisant.fail((String)("exception inattendue !!! " + e.getMessage()));
        }
    }

    public void testUnGroupeAvecTousLesSoldesDebiteurs() {
        try {
            GroupeDeContributeurs c = new GroupeDeContributeurs("c");
            c.ajouter(new Contributeur("a", 100));
            c.ajouter(new Contributeur("b", 200));
            c.ajouter(new Contributeur("c", 300));
            TestsCotisant.assertTrue((String)" nombre de Contributeurs ??? ", (boolean)(c.nombreDeCotisants() == 3));
            TestsCotisant.assertTrue((String)" solde erron\u00e9 ??? ", (boolean)(c.solde() == 600));
            int val = 30;
            c.debit(30);
            TestsCotisant.assertTrue((String)" d\u00e9bit inop\u00e9rant ??? ", (boolean)(c.solde() == 600 - c.nombreDeCotisants() * 30));
            c.credit(30);
            TestsCotisant.assertTrue((String)" cr\u00e9dit inop\u00e9rant??? ", (boolean)(c.solde() == 600));
        }
        catch (Exception e) {
            TestsCotisant.fail((String)("exception inattendue !!! " + e.getMessage()));
        }
    }

    public void testVisiteurSurUnGroupe() {
        try {
            GroupeDeContributeurs g = new GroupeDeContributeurs("g");
            g.ajouter(new Contributeur("a", 100));
            g.ajouter(new Contributeur("b", 200));
            g.ajouter(new Contributeur("c", 300));
            Iterator<Cotisant> it = g.iterator();
            TestsCotisant.assertTrue((String)" est-ce le bon Contributeur ???", (boolean)it.next().nom().equals("a"));
            TestsCotisant.assertTrue((String)" est-ce le bon Contributeur ???", (boolean)it.next().nom().equals("b"));
            TestsCotisant.assertTrue((String)" est-ce le bon Contributeur ???", (boolean)it.next().nom().equals("c"));
            TestsCotisant.assertTrue((String)" visite non conforme ? : ", (boolean)((String)g.accepter(new VisiteurToString())).equals("<Groupe g : (<Contributeur : (a, 100)><Contributeur : (b, 200)><Contributeur : (c, 300)>>"));
        }
        catch (Exception e) {
            TestsCotisant.fail((String)("exception inattendue !!! " + e.getMessage()));
        }
    }

    private List<Cotisant> enListe(Cotisant c) {
        ArrayList<Cotisant> liste = new ArrayList<Cotisant>();
        if (c instanceof GroupeDeContributeurs) {
            GroupeDeContributeurs g = (GroupeDeContributeurs)c;
            for (Cotisant co : g.getChildren()) {
                liste.addAll(this.enListe(co));
            }
        } else {
            liste.add(c);
        }
        return liste;
    }

    public void testIteratorSurUnGroupeDeGroupes() {
        try {
            GroupeDeContributeurs g = new GroupeDeContributeurs("g");
            g.ajouter(new Contributeur("g_a", 100));
            g.ajouter(new Contributeur("g_b", 200));
            g.ajouter(new Contributeur("g_c", 300));
            GroupeDeContributeurs g1 = new GroupeDeContributeurs("g1");
            g1.ajouter(new Contributeur("g1_a", 100));
            g1.ajouter(new Contributeur("g1_b", 200));
            GroupeDeContributeurs g11 = new GroupeDeContributeurs("g11");
            g11.ajouter(new Contributeur("g11_a", 100));
            g1.ajouter(g11);
            GroupeDeContributeurs g2 = new GroupeDeContributeurs("g2");
            g2.ajouter(new Contributeur("g2_a", 100));
            g2.ajouter(new Contributeur("g2_b", 200));
            g2.ajouter(new Contributeur("g2_c", 100));
            g2.ajouter(new Contributeur("g2_d", 200));
            g.ajouter(g1);
            g.ajouter(g2);
            Iterator<Cotisant> it = g.iterator();
            TestsCotisant.assertTrue((String)" est-ce le bon Cotisant ?, un Contributeur est attendu !", (boolean)(it.next() instanceof Contributeur));
            TestsCotisant.assertTrue((String)" est-ce le bon Cotisant ?, un Contributeur est attendu !", (boolean)(it.next() instanceof Contributeur));
            TestsCotisant.assertTrue((String)" est-ce le bon Cotisant ?, un Contributeur est attendu !", (boolean)(it.next() instanceof Contributeur));
            TestsCotisant.assertTrue((String)" est-ce le bon Cotisant ?, un GroupeDeContributeurs est attendu !", (boolean)(it.next() instanceof GroupeDeContributeurs));
            TestsCotisant.assertTrue((String)" est-ce le bon Cotisant ?, un Contributeur est attendu !", (boolean)(it.next() instanceof Contributeur));
            TestsCotisant.assertTrue((String)" est-ce le bon Cotisant ?, un Contributeur est attendu !", (boolean)(it.next() instanceof Contributeur));
            TestsCotisant.assertTrue((String)" est-ce le bon Cotisant ?, un GroupeDeContributeurs est attendu !", (boolean)(it.next() instanceof GroupeDeContributeurs));
            TestsCotisant.assertTrue((String)" est-ce le bon Cotisant ?, un Contributeur est attendu !", (boolean)(it.next() instanceof Contributeur));
            TestsCotisant.assertTrue((String)" est-ce le bon Cotisant ?, un GroupeDeContributeurs est attendu !", (boolean)(it.next() instanceof GroupeDeContributeurs));
            TestsCotisant.assertTrue((String)" est-ce le bon Cotisant ?, un Contributeur est attendu !", (boolean)(it.next() instanceof Contributeur));
            TestsCotisant.assertTrue((String)" est-ce le bon Cotisant ?, un Contributeur est attendu !", (boolean)(it.next() instanceof Contributeur));
            TestsCotisant.assertTrue((String)" est-ce le bon Cotisant ?, un Contributeur est attendu !", (boolean)(it.next() instanceof Contributeur));
            TestsCotisant.assertTrue((String)" est-ce le bon Cotisant ?, un Contributeur est attendu !", (boolean)(it.next() instanceof Contributeur));
            try {
                it.next();
            }
            catch (Exception ex) {
                TestsCotisant.assertTrue((String)" NoSuchElementException est attendu ??? ", (boolean)(ex instanceof NoSuchElementException));
            }
        }
        catch (Exception e) {
            TestsCotisant.fail((String)("exception inattendue !!! " + e.getMessage()));
        }
    }

    public void testGetChildrenSurUnGroupeDeGroupes() {
        try {
            GroupeDeContributeurs g = new GroupeDeContributeurs("g");
            g.ajouter(new Contributeur("g_a", 100));
            g.ajouter(new Contributeur("g_b", 200));
            g.ajouter(new Contributeur("g_c", 300));
            GroupeDeContributeurs g1 = new GroupeDeContributeurs("g1");
            g1.ajouter(new Contributeur("g1_a", 100));
            g1.ajouter(new Contributeur("g1_b", 200));
            GroupeDeContributeurs g11 = new GroupeDeContributeurs("g11");
            g11.ajouter(new Contributeur("g11_a", 100));
            g1.ajouter(g11);
            GroupeDeContributeurs g2 = new GroupeDeContributeurs("g2");
            g2.ajouter(new Contributeur("g2_a", 100));
            g2.ajouter(new Contributeur("g2_b", 200));
            g2.ajouter(new Contributeur("g2_c", 100));
            g2.ajouter(new Contributeur("g2_d", 200));
            g.ajouter(g1);
            g.ajouter(g2);
            Iterator<Cotisant> it = g.getChildren().iterator();
            TestsCotisant.assertTrue((String)" est-ce le bon Cotisant ?, un Contributeur est attendu !", (boolean)(it.next() instanceof Contributeur));
            TestsCotisant.assertTrue((String)" est-ce le bon Cotisant ?, un Contributeur est attendu !", (boolean)(it.next() instanceof Contributeur));
            TestsCotisant.assertTrue((String)" est-ce le bon Cotisant ?, un Contributeur est attendu !", (boolean)(it.next() instanceof Contributeur));
            TestsCotisant.assertTrue((String)" est-ce le bon Cotisant ?, un GroupeDeContributeurs est attendu !", (boolean)(it.next() instanceof GroupeDeContributeurs));
            TestsCotisant.assertTrue((String)" est-ce le bon Cotisant ?, un GroupeDeContributeurs est attendu !", (boolean)(it.next() instanceof GroupeDeContributeurs));
            try {
                it.next();
            }
            catch (Exception ex) {
                TestsCotisant.assertTrue((String)" NoSuchElementException est attendu ??? ", (boolean)(ex instanceof NoSuchElementException));
            }
        }
        catch (Exception e) {
            TestsCotisant.fail((String)("exception inattendue !!! " + e.getMessage()));
        }
    }
}
