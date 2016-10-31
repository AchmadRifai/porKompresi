/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import beans.DataRL;
import beans.Jobs;
import java.io.IOException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

/**
 *
 * @author ai
 */
public class Work {
    public static java.io.File f=new java.io.File(System.getProperty("user.home")+"/.pze/runlist");

    public static List<Jobs> getJobs() throws ParserConfigurationException, SAXException, IOException {
        List<Jobs>l=new java.util.LinkedList<Jobs>();
        org.w3c.dom.Document d=javax.xml.parsers.DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(f);
        org.w3c.dom.NodeList nl=d.getElementsByTagName("jobs");
        for(int x=0;x<nl.getLength();x++)if(nl.item(x).getNodeType()==org.w3c.dom.Node.ELEMENT_NODE){
            org.w3c.dom.Element e=(org.w3c.dom.Element) nl.item(x);
            l.add(new beans.Jobs(Integer.parseInt(e.getAttribute("mode")), e.getAttribute("asal"), e.getAttribute("ke")));
        }return l;
    }

    public static void initFile() throws ParserConfigurationException, TransformerException {
        if(!f.getParentFile().exists())f.getParentFile().mkdirs();
        org.w3c.dom.Document d=javax.xml.parsers.DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        org.w3c.dom.Element e=d.createElement("por");
        d.appendChild(e);
        save(d);
    }

    private static void save(org.w3c.dom.Document d) throws TransformerException {
        javax.xml.transform.stream.StreamResult sr=new javax.xml.transform.stream.StreamResult(f);
        javax.xml.transform.dom.DOMSource ds=new javax.xml.transform.dom.DOMSource(d);
        javax.xml.transform.Transformer t=javax.xml.transform.TransformerFactory.newInstance().newTransformer();
        t.transform(ds, sr);
    }

    public static void simpanBit(DataRL d, String ke) throws IOException {
        java.io.File t=new java.io.File(ke);
        java.io.FileOutputStream o=new java.io.FileOutputStream(t);
        o.write(oleh2(d));
        o.close();
    }

    private static byte[] oleh2(DataRL d) {
        byte[]b=new byte[]{d.getB(),(byte)d.getC()};
        return b;
    }
}