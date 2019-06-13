/*
 * Decompiled with CFR 0.139.
 * 
 * Could not load the following classes:
 *  org.jdom.Document
 *  org.jdom.Element
 *  org.jdom.output.Format
 *  org.jdom.output.XMLOutputter
 *  question1.Contributeur
 *  question1.Cotisant
 *  question1.GroupeDeContributeurs
 *  question1.Visiteur
 *  question2.VisiteurToXML
 */
package question2;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import question1.Contributeur;
import question1.Cotisant;
import question1.GroupeDeContributeurs;
import question1.Visiteur;
import question2.CompositeValide;
import question2.DebitMaximal;
import question2.VisiteurToXML;

public class Main {
    public static void main(String[] args) throws Exception {
        GroupeDeContributeurs g = new GroupeDeContributeurs("g");
        g.ajouter((Cotisant)new Contributeur("g_a", 100));
        g.ajouter((Cotisant)new Contributeur("g_b", 200));
        g.ajouter((Cotisant)new Contributeur("g_c", 300));
        GroupeDeContributeurs g1 = new GroupeDeContributeurs("g1");
        g1.ajouter((Cotisant)new Contributeur("g1_a1", 100));
        g1.ajouter((Cotisant)new Contributeur("g1_b1", 200));
        g.ajouter((Cotisant)g1);
        if (((Boolean)g.accepter((Visiteur)new CompositeValide())).booleanValue()) {
            System.out.println(Main.arbreXML((Cotisant)g));
            if ((Integer)g.accepter((Visiteur)new DebitMaximal()) >= 50) {
                g.debit(50);
                System.out.println(Main.arbreXML((Cotisant)g));
            }
        } else {
            System.out.println("Composite invalide");
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static String arbreXML(Cotisant c) throws Exception {
        String result;
        ByteArrayOutputStream baos = null;
        result = new String();
        Element racine = (Element)c.accepter((Visiteur)new VisiteurToXML());
        Document document = new Document(racine);
        XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
        try {
            baos = new ByteArrayOutputStream();
            out.output(document, (OutputStream)baos);
            result = baos.toString();
        }
        finally {
            baos.close();
        }
        return result;
    }
}
