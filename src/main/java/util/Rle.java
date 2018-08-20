package util;

import java.io.IOException;
import java.util.List;

public class Rle {

	public static void saving(List<Byte> l, String ke) throws IOException {
		java.io.File f=new java.io.File(ke);
		java.io.FileOutputStream o=new java.io.FileOutputStream(f, f.exists());
		byte[]b=toByteArray(l);
		o.write(b);
		o.close();
	}

	private static byte[] toByteArray(List<Byte> l) {
		byte[]b=null;
		if(4<=l.size()) {
			b=new byte[4];
			b[0]=0;
			b[1]=(byte) l.size();
			b[3]=0;
			b[2]=l.get(0);
		} else {
			b=new byte[l.size()];
			for(int x=0;x<l.size();x++)b[x]=l.get(x);
		} return b;
	}

}
