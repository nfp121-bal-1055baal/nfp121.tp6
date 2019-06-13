/*
 * Decompiled with CFR 0.139.
 * 
 * Could not load the following classes:
 *  question1.Cotisant
 *  question3.Memento
 */
package question3;

import java.io.PrintStream;
import java.util.Stack;
import question1.Cotisant;
import question3.Memento;

public class Gardien {
    private Stack<Memento> mementoStk = new Stack();

    public Memento getMemento() {
        Memento m = this.mementoStk.pop();
        System.out.println("popping " + m.cotisant.toString() + "\n");
        return m;
    }

    public void setMemento(Memento memento) {
        System.out.println("pushing " + memento.cotisant.toString() + "\n");
        this.mementoStk.push(memento);
    }
}
