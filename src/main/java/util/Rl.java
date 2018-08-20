/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author ashura
 */
public class Rl {

	public static boolean available(List<Byte> l) {
		boolean b=4==l.size();
		if(b)b=0==l.get(0)&&0==l.get(3);
		return b;
	}

	public static void configed(List<Byte> l, String ke) throws IOException {
		byte[]b=beres(l);
		java.io.File f=new java.io.File(ke);
		java.io.FileOutputStream o=new java.io.FileOutputStream(f, f.exists());
		o.write(b);
		o.close();
	}

	private static byte[] beres(List<Byte> l) {
		int s=(0xFF&l.get(1));
		byte[]b=new byte[s];
		for(int x=0;x<s;x++)b[x]=l.get(2);
		return b;
	}

	public static void saveBit(List<Byte> l, String ke) throws IOException {
		java.io.File f=new java.io.File(ke);
		java.io.FileOutputStream o=new java.io.FileOutputStream(f, f.exists());
		o.write(new byte[] {l.get(0)});
		o.close();
	}

	public static void lastBits(List<Byte> l, String ke) throws IOException {
		java.io.File f=new java.io.File(ke);
		java.io.FileOutputStream o=new java.io.FileOutputStream(f, f.exists());
		byte[]b;
		if(Rl.available(l))b=beres(l);
		else b=toByteArray(l);
		o.write(b);
		o.close();
	}

	private static byte[] toByteArray(List<Byte> l) {
		byte[]b=new byte[l.size()];
		for(int x=0;x<l.size();x++)b[x]=l.get(x);
		return b;
	}
}