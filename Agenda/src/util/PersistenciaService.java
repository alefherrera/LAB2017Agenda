package util;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class PersistenciaService {

	private static PersistenciaService instance;

	public static PersistenciaService getInstance() {
		if (instance == null)
			instance = new PersistenciaService();
		return instance;
	}

	private PersistenciaService() {

	}

	public <T> void put(T obj, String filename) throws FileNotFoundException {
		XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(
				new FileOutputStream(filename)));
		encoder.writeObject(obj);
		encoder.close();
	}

	public <T> T get(String filename) throws FileNotFoundException {
		XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(
				new FileInputStream(filename)));
		T o = (T) decoder.readObject();
		decoder.close();
		return o;
	}

}