/*
 * Decompiled with CFR 0.139.
 * 
 * Could not load the following classes:
 *  question1.Cotisant
 *  question1.SoldeDebiteurException
 */
package question3;

import question1.Cotisant;
import question1.SoldeDebiteurException;

public abstract class AbstractTransaction {
    protected Cotisant cotisant;
    protected int somme;

    public abstract void beginTransaction();

    public abstract void endTransaction();

    public abstract void rollbackTransaction();

    public AbstractTransaction(Cotisant cotisant) {
        this.cotisant = cotisant;
    }

    public final void debit(int somme) throws SoldeDebiteurException {
        try {
            this.beginTransaction();
            this.somme = somme;
            this.cotisant.debit(somme);
            this.endTransaction();
        }
        catch (SoldeDebiteurException e) {
            this.rollbackTransaction();
            throw e;
        }
    }
}
