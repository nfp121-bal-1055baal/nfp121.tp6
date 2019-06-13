/*
 * Decompiled with CFR 0.139.
 * 
 * Could not load the following classes:
 *  question1.Cotisant
 */
package question3;

import question1.Cotisant;
import question3.AbstractTransaction;
import question3.Gardien;
import question3.Memento;

public class TransactionDebit
extends AbstractTransaction {
    private Gardien gardien = new Gardien();

    public TransactionDebit(Cotisant cotisant) {
        super(cotisant);
    }

    @Override
    public void beginTransaction() {
        this.gardien.setMemento(new Memento(this.cotisant));
    }

    @Override
    public void endTransaction() {
    }

    @Override
    public void rollbackTransaction() {
        this.gardien.getMemento().setState(this.cotisant);
    }
}
