package com.una.flatestf.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.una.flatestf.controller.Inte_Copy;
import com.una.flatestf.util.Close;

public class Inte_Copy_impl implements Inte_Copy{
	@Override
	public void Copy(String  srcPath, String destPath) {
		File src = new File(srcPath);
		File dest = new File(destPath);
		if (src.isDirectory()) {
			dest = new File(dest, src.getName());
		}
		copy(src, dest);
	}

	private static void copy(File src, File dest) {
		if (src.isFile()) {
			copyFile(src, dest);
		} else if (src.isDirectory()) {
			dest.mkdirs();
			for (File de : src.listFiles()) {
				copy(de, new File(dest, de.getName()));
			}
		}
	}

	public static void copyFile(File src, File dest) {
		OutputStream os = null;
		InputStream is = null;
		try {
			os = new FileOutputStream(dest);
			is = new FileInputStream(src);
			int len = 0;
			byte[] b = new byte[1024];
			while (-1 != (len = is.read(b))) {
				os.write(b, 0, len);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Close.Closing(os, is);
	}

}
