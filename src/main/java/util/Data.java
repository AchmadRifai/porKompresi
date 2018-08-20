/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import beans.Jobs;
import java.io.IOException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author ashura
 */
public class Data {
    public static java.io.File f=new java.io.File(System.getProperty("user.home")+"/.pze/history.xml");

    public static void init() throws ParserConfigurationException, TransformerException {
        if(!f.getParentFile().exists())f.getParentFile().mkdirs();
        org.w3c.dom.Document d=javax.xml.parsers.DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        org.w3c.dom.Element root=d.createElement("por");
        d.appendChild(root);
        save(d);
    }

    private static void save(Document d) throws TransformerException {
        javax.xml.transform.stream.StreamResult sr=new javax.xml.transform.stream.StreamResult(f);
        javax.xml.transform.dom.DOMSource ds=new javax.xml.transform.dom.DOMSource(d);
        javax.xml.transform.Transformer t=javax.xml.transform.TransformerFactory.newInstance().newTransformer();
        t.transform(ds, sr);
    }

    public static void save(Jobs j) throws ParserConfigurationException, SAXException, IOException, TransformerException {
        org.w3c.dom.Document d=javax.xml.parsers.DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(f);
        org.w3c.dom.Element root=(org.w3c.dom.Element) d.getElementsByTagName("por").item(0),e=d.createElement("jobs");
        e.setAttribute("mode", ""+j.getMode());
        e.setAttribute("asal", j.getAsal());
        e.setAttribute("ke", j.getKe());
        e.setAttribute("rasio", ""+j.getRasio());
        e.setAttribute("tgl", ""+j.getTgl());
        e.setAttribute("durasi", ""+j.getDurasi());
        root.appendChild(e);
        save(d);
    }

    public static List<Jobs> load() throws ParserConfigurationException, SAXException, IOException {
        List<Jobs>l=new java.util.LinkedList<Jobs>();
        org.w3c.dom.Document d=javax.xml.parsers.DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(f);
        org.w3c.dom.NodeList nl=d.getElementsByTagName("jobs");
        for(int x=0;x<nl.getLength();x++)if(nl.item(x).getNodeType()==org.w3c.dom.Node.ELEMENT_NODE){
            org.w3c.dom.Element e=(org.w3c.dom.Element) nl.item(x);
            beans.Jobs j=new beans.Jobs(Integer.parseInt(e.getAttribute("mode")), e.getAttribute("asal"), e.getAttribute("ke"));
            j.setDurasi(org.joda.time.DateTime.parse(e.getAttribute("durasi")));
            j.setTgl(org.joda.time.DateTime.parse(e.getAttribute("tgl")));
            j.setRasio(Float.parseFloat(e.getAttribute("rasio")));
            l.add(j);
        }return l;
    }
}
