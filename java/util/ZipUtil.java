package util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipUtil {
	private static int BUFFSIZE = 2048;
	
	public static boolean decompress(File file){
        InputStream is;
        ZipInputStream zis;
        BufferedOutputStream bos;
        String path = file.getAbsolutePath().split("\\.")[0];
        File folder = new File(path);
        if(!folder.exists())
            folder.mkdir();
        try {
            String filename;
            int i = 1 ;

            is = new FileInputStream(file);
            zis = new ZipInputStream(new BufferedInputStream(is));

            ZipEntry ze;
            byte[] buffer = new byte[BUFFSIZE];
            int len;

            while ((ze = zis.getNextEntry()) != null) {

                filename = ze.getName();
                if (filename.contains(".jpg")) {
                	filename = "pic"+i+".jpg";
                	++i;
                } else if (filename.contains("p_")) {
                	filename = "p_a";
                } else if (filename.contains("info_")) {
                	filename = "info_a";
                } else {
                	filename = "points";
                }
                //Here we set destination for extracted files
                FileOutputStream fout = new FileOutputStream(path +"/"+ filename);
                bos = new BufferedOutputStream(fout);

                while ((len = zis.read(buffer)) != -1) {
                    bos.write(buffer, 0, len);
                }
                bos.flush();
                bos.close();
                fout.close();
            }
            zis.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
