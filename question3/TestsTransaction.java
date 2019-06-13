/*
 * Decompiled with CFR 0.139.
 * 
 * Could not load the following classes:
 *  junit.framework.TestCase
 *  question1.Contributeur
 *  question1.Cotisant
 *  question1.GroupeDeContributeurs
 *  question1.SoldeDebiteurException
 *  question3.TransactionDebit
 */
package question3;

import java.io.PrintStream;
import junit.framework.TestCase;
import question1.Contributeur;
import question1.Cotisant;
import question1.GroupeDeContributeurs;
import question1.SoldeDebiteurException;
import question3.TransactionDebit;

public class TestsTransaction
extends TestCase {
    public void testDebitEnErreur() {
        GroupeDeContributeurs g = new GroupeDeContributeurs("g");
        g.ajouter((Cotisant)new Contributeur("g_a", 300));
        g.ajouter((Cotisant)new Contributeur("g_b", 200));
        g.ajouter((Cotisant)new Contributeur("g_c", 100));
        int ancienSolde = g.solde();
        System.out.println(" solde: " + g.solde());
        try {
            g.debit(120);
            TestsTransaction.fail((String)"une exception est attendue, voir rollbackTransaction");
        }
        catch (Exception e) {
            TestsTransaction.assertTrue((boolean)(e instanceof SoldeDebiteurException));
        }
        System.out.println(" solde: " + g.solde());
        TestsTransaction.assertEquals((String)" ce solde n'est pas transactionnel, c'est bien une erreur !!! ", (int)ancienSolde, (int)g.solde());
    }

    public void testDebitAvecTransaction() {
        GroupeDeContributeurs g = new GroupeDeContributeurs("g");
        g.ajouter((Cotisant)new Contributeur("g_a", 300));
        g.ajouter((Cotisant)new Contributeur("g_b", 200));
        g.ajouter((Cotisant)new Contributeur("g_c", 100));
        int ancienSolde = g.solde();
        TransactionDebit transaction = new TransactionDebit((Cotisant)g);
        try {
            transaction.debit(120);
        }
        catch (Exception exception) {
            // empty catch block
        }
        TestsTransaction.assertEquals((String)(" Transaction erron\u00c3\u00a9e , l'ancien solde n'est pas restitu\u00c3\u00a9 !!! " + g.solde()), (int)ancienSolde, (int)g.solde());
    }

    public void testDebitAvecTransaction2() {
        GroupeDeContributeurs g = new GroupeDeContributeurs("g");
        g.ajouter((Cotisant)new Contributeur("g_a", 100));
        g.ajouter((Cotisant)new Contributeur("g_b", 200));
        g.ajouter((Cotisant)new Contributeur("g_c", 300));
        GroupeDeContributeurs g1 = new GroupeDeContributeurs("g1");
        g1.ajouter((Cotisant)new Contributeur("g1_a", 100));
        g1.ajouter((Cotisant)new Contributeur("g1_b", 200));
        GroupeDeContributeurs g11 = new GroupeDeContributeurs("g11");
        g11.ajouter((Cotisant)new Contributeur("g11_a", 100));
        g1.ajouter((Cotisant)g11);
        GroupeDeContributeurs g2 = new GroupeDeContributeurs("g2");
        g2.ajouter((Cotisant)new Contributeur("g2_a", 100));
        g2.ajouter((Cotisant)new Contributeur("g2_b", 200));
        g2.ajouter((Cotisant)new Contributeur("g2_c", 400));
        g2.ajouter((Cotisant)new Contributeur("g2_d", 200));
        GroupeDeContributeurs g22 = new GroupeDeContributeurs("g22");
        g22.ajouter((Cotisant)new Contributeur("g22_a", 100));
        g22.ajouter((Cotisant)new Contributeur("g22_b", 200));
        g22.ajouter((Cotisant)new Contributeur("g22_c", 400));
        g22.ajouter((Cotisant)new Contributeur("g22_d", 200));
        GroupeDeContributeurs g222 = new GroupeDeContributeurs("g222");
        g222.ajouter((Cotisant)new Contributeur("g222_a", 100));
        g222.ajouter((Cotisant)new Contributeur("g222_b", 200));
        g222.ajouter((Cotisant)new Contributeur("g222_c", 40));
        g222.ajouter((Cotisant)new Contributeur("g222_d", 200));
        g22.ajouter((Cotisant)g222);
        g2.ajouter((Cotisant)g22);
        g.ajouter((Cotisant)g1);
        g.ajouter((Cotisant)g2);
        TransactionDebit transaction = new TransactionDebit((Cotisant)g);
        int ancienSolde = g.solde();
        try {
            transaction.debit(50);
            TestsTransaction.fail();
        }
        catch (Exception exception) {
            // empty catch block
        }
        TestsTransaction.assertTrue((String)" solde erron\u00c3\u00a9, revoyez la transaction !!! ", (boolean)(g.solde() == ancienSolde));
    }

    public void testTransactionDebitSure5() {
        GroupeDeContributeurs g = new GroupeDeContributeurs("g");
        g.ajouter((Cotisant)new Contributeur("g_a", 100));
        g.ajouter((Cotisant)new Contributeur("g_b", 200));
        g.ajouter((Cotisant)new Contributeur("g_c", 300));
        GroupeDeContributeurs g1 = new GroupeDeContributeurs("g1");
        g1.ajouter((Cotisant)new Contributeur("g1_a", 100));
        g1.ajouter((Cotisant)new Contributeur("g1_b", 200));
        GroupeDeContributeurs g11 = new GroupeDeContributeurs("g11");
        g11.ajouter((Cotisant)new Contributeur("g11_a", 100));
        g1.ajouter((Cotisant)g11);
        GroupeDeContributeurs g2 = new GroupeDeContributeurs("g2");
        g2.ajouter((Cotisant)new Contributeur("g2_a", 100));
        g2.ajouter((Cotisant)new Contributeur("g2_b", 200));
        g2.ajouter((Cotisant)new Contributeur("g2_c", 400));
        g2.ajouter((Cotisant)new Contributeur("g2_d", 200));
        GroupeDeContributeurs g22 = new GroupeDeContributeurs("g22");
        g22.ajouter((Cotisant)new Contributeur("g22_a", 100));
        g22.ajouter((Cotisant)new Contributeur("g22_b", 200));
        g22.ajouter((Cotisant)new Contributeur("g22_c", 400));
        g22.ajouter((Cotisant)new Contributeur("g22_d", 200));
        GroupeDeContributeurs g222 = new GroupeDeContributeurs("g222");
        g222.ajouter((Cotisant)new Contributeur("g222_a", 100));
        g222.ajouter((Cotisant)new Contributeur("g222_b", 200));
        g222.ajouter((Cotisant)new Contributeur("g222_c", 40));
        g222.ajouter((Cotisant)new Contributeur("g222_d", 200));
        g22.ajouter((Cotisant)g222);
        g2.ajouter((Cotisant)g22);
        g.ajouter((Cotisant)g1);
        g.ajouter((Cotisant)g2);
        TransactionDebit transaction = new TransactionDebit((Cotisant)g);
        try {
            transaction.debit(30);
        }
        catch (Exception e) {
            TestsTransaction.fail();
        }
        int ancienSolde = g.solde();
        transaction = new TransactionDebit((Cotisant)g);
        try {
            transaction.debit(30);
            TestsTransaction.fail();
        }
        catch (Exception exception) {
            // empty catch block
        }
        TestsTransaction.assertTrue((String)" solde erron\u00c3\u00a9, revoyez la transaction !!! ", (boolean)(g.solde() == ancienSolde));
    }

    public void testTransactionsImbriquees() {
        GroupeDeContributeurs g = new GroupeDeContributeurs("g");
        g.ajouter((Cotisant)new Contributeur("g_a", 100));
        g.ajouter((Cotisant)new Contributeur("g_b", 200));
        g.ajouter((Cotisant)new Contributeur("g_c", 300));
        GroupeDeContributeurs g1 = new GroupeDeContributeurs("g1");
        g1.ajouter((Cotisant)new Contributeur("g1_a", 100));
        g1.ajouter((Cotisant)new Contributeur("g1_b", 200));
        GroupeDeContributeurs g11 = new GroupeDeContributeurs("g11");
        g11.ajouter((Cotisant)new Contributeur("g11_a", 100));
        g1.ajouter((Cotisant)g11);
        GroupeDeContributeurs g2 = new GroupeDeContributeurs("g2");
        g2.ajouter((Cotisant)new Contributeur("g2_a", 100));
        g2.ajouter((Cotisant)new Contributeur("g2_b", 200));
        g2.ajouter((Cotisant)new Contributeur("g2_c", 400));
        g2.ajouter((Cotisant)new Contributeur("g2_d", 200));
        GroupeDeContributeurs g22 = new GroupeDeContributeurs("g22");
        g22.ajouter((Cotisant)new Contributeur("g22_a", 100));
        g22.ajouter((Cotisant)new Contributeur("g22_b", 200));
        g22.ajouter((Cotisant)new Contributeur("g22_c", 400));
        g22.ajouter((Cotisant)new Contributeur("g22_d", 200));
        GroupeDeContributeurs g222 = new GroupeDeContributeurs("g222");
        g222.ajouter((Cotisant)new Contributeur("g222_a", 100));
        g222.ajouter((Cotisant)new Contributeur("g222_b", 200));
        g222.ajouter((Cotisant)new Contributeur("g222_c", 400));
        g222.ajouter((Cotisant)new Contributeur("g222_d", 200));
        g22.ajouter((Cotisant)g222);
        g2.ajouter((Cotisant)g22);
        g.ajouter((Cotisant)g1);
        g.ajouter((Cotisant)g2);
        int ancienSolde = g.solde();
        TransactionDebit transaction = new TransactionDebit((Cotisant)g);
        try {
            transaction.beginTransaction();
            transaction.debit(30);
            transaction.beginTransaction();
            transaction.debit(30);
            transaction.endTransaction();
            transaction.endTransaction();
        }
        catch (Exception e) {
            TestsTransaction.fail((String)"revoyez les transactions imbriqu\u00c3\u00a9es");
        }
        g.credit(60);
        TestsTransaction.assertEquals((String)" solde erron\u00c3\u00a9, revoyez la transaction !!! ", (int)ancienSolde, (int)g.solde());
    }
}
