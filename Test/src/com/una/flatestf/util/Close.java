package com.una.flatestf.util;

import java.io.Closeable;
import java.io.IOException;

public class Close {
	public static void Closing(Closeable... io) {
		for (Closeable s : io) {
			try {
				s.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
