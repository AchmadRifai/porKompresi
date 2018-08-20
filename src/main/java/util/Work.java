/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import beans.DataRL;
import beans.Jobs;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

/**
 *
 * @author ai
 */
public class Work {
    public static File f=new File(System.getProperty("user.home")+"/.pze/runlist");

    public static List<Jobs> getJobs() throws ParserConfigurationException, SAXException, IOException {
        List<Jobs>l=new java.util.LinkedList<Jobs>();
        org.w3c.dom.Document d=javax.xml.parsers.DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(f);
        org.w3c.dom.NodeList nl=d.getElementsByTagName("jobs");
        for(int x=0;x<nl.getLength();x++)if(nl.item(x).getNodeType()==org.w3c.dom.Node.ELEMENT_NODE){
            org.w3c.dom.Element e=(org.w3c.dom.Element) nl.item(x);
            beans.Jobs j=new beans.Jobs(Integer.parseInt(e.getAttribute("mode")), e.getAttribute("asal"), e.getAttribute("ke"));
            j.setEfek(Float.parseFloat(e.getAttribute("efek")));
            j.setTerproses(Boolean.parseBoolean(e.getAttribute("terproses")));
            j.setTgl(org.joda.time.DateTime.parse(e.getAttribute("tgl")));
            if(!"null".equals(e.getAttribute("waktu")))j.setWaktu(org.joda.time.DateTime.parse(e.getAttribute("waktu")));
            else j.setWaktu(null);
            l.add(j);
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
        t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        t.setOutputProperty(javax.xml.transform.OutputKeys.METHOD, "xml");
        t.setOutputProperty(javax.xml.transform.OutputKeys.OMIT_XML_DECLARATION, "no");
        t.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");
        t.setOutputProperty(javax.xml.transform.OutputKeys.ENCODING, "utf-8");
        t.transform(ds, sr);
    }

    public static void addJobs(Jobs j) throws ParserConfigurationException, SAXException, IOException, TransformerException {
        org.w3c.dom.Document d=javax.xml.parsers.DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(f);
        org.w3c.dom.Element root=(org.w3c.dom.Element) d.getElementsByTagName("por").item(0),e=d.createElement("jobs");
        e.setAttribute("mode", ""+j.getMode());
        e.setAttribute("asal", j.getAsal());
        e.setAttribute("ke", j.getKe());
        e.setAttribute("efek", ""+j.getEfek());
        e.setAttribute("terproses", ""+j.isTerproses());
        e.setAttribute("tgl", ""+j.getTgl());
        e.setAttribute("waktu", ""+j.getWaktu());
        root.appendChild(e);
        save(d);
    }

    public static void saveComp(DataRL d, String text) throws IOException {
        java.io.File f=new java.io.File(text);
        java.io.FileOutputStream o=new java.io.FileOutputStream(f,f.exists());
        o.write(isine1(d));
        o.close();
    }

    private static byte[] isine1(DataRL d) {
        byte[]b=null;
        if(d.oleh()){
            b=new byte[4];
            b[0]=0;
            b[3]=0;
            b[1]=(byte) ((byte) d.getC()-3);
            b[2]=d.getB();
        }else{
            b=new byte[d.getC()];
            for(int x=0;x<d.getC();x++)b[x]=d.getB();
        }return b;
    }

    public static void Decompres(List<Byte> l, String text) throws IOException {
        java.io.File f=new java.io.File(text);
        java.io.FileOutputStream o=new java.io.FileOutputStream(f,f.exists());
        byte[]b;
        if(avaiable(l)){
            b=kabeh(l);
            l.clear();
        }else{
            b=sitok(l);
            l.remove(0);
        }o.write(b);
        o.close();
    }

    public static void lastCompres(List<Byte> l, String text) throws IOException {
        java.io.File f=new java.io.File(text);
        java.io.FileOutputStream o=new java.io.FileOutputStream(f,f.exists());
        byte[]b;
        if(avaiable(l))b=kabeh(l);
        else b=yo(l);
        o.write(b);
        o.close();
    }

    private static boolean avaiable(List<Byte> l) {
        boolean b=4==l.size();
        if(b)return 0==l.get(0)&&0==l.get(3);
        return b;
    }

    private static byte[] kabeh(List<Byte> l) {
        byte[]b=new byte[3+(l.get(1)&0xFF)];
        for(int x=0;x<3+(l.get(1)&0xFF);x++)b[x]=l.get(2);
        return b;
    }

    private static byte[] sitok(List<Byte> l) {
        return new byte[]{l.get(0)};
    }

    private static byte[] yo(List<Byte> l) {
        byte[]b=new byte[l.size()];
        for(int x=0;x<l.size();x++)b[x]=l.get(x);
        return b;
    }

    public static void hindar(Exception ex) {
        org.joda.time.DateTime d=org.joda.time.DateTime.now();
        java.io.File f=new java.io.File(System.getProperty("user.home")+"/.pze/error/"+d.getYear()+"a"+d.getMonthOfYear()+"a"+d.getDayOfMonth()+
                "b"+d.getHourOfDay()+"a"+d.getMinuteOfHour()+"a"+d.getSecondOfMinute()+"b"+d.getMillisOfSecond()+".log");
        if(!f.getParentFile().exists())f.getParentFile().mkdirs();
        if(f.exists())f.delete();
        try {
            java.io.PrintStream o=new java.io.PrintStream(f);
            ex.printStackTrace(o);
            o.close();
        } catch (FileNotFoundException ex1) {
            Logger.getLogger(Work.class.getName()).log(Level.SEVERE, null, ex1);
        }
    }

    public static void removeAll() {
        File d=f.getParentFile();
        delDir(d);
    }

    private static void delDir(File d) {
        for(File fi:d.listFiles()){
            if(fi.isDirectory())delDir(fi);
            else fi.delete();
        }d.delete();
    }

    public static void compres(List<Byte> lb, String ke) throws IOException {
        java.io.File f=new java.io.File(ke);
        java.io.FileOutputStream o=new java.io.FileOutputStream(f,f.exists());
        beans.DataRL d=new beans.DataRL(lb.get(0));
        d.setC(lb.size());
        byte[]b=convertData(d);
        o.write(b);
        o.close();
    }

    private static byte[] convertData(DataRL d) {
        byte[]b=new byte[4];
        b[0]=0x00;
        b[1]=d.getB();
        b[2]=(byte) d.getC();
        b[3]=0x00;
        return b;
    }
}