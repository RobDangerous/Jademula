package jademula;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import sun.misc.URLClassPath;

public class ClassPathHacker {
	public static void addFile(String filename) {
		addFile(new File(filename));
	}

	public static void addFile(File file) {
		try {
			addURL(file.toURI().toURL());
		}
		catch (MalformedURLException e) {
			throw new RuntimeException("Could not add " + file.getName() + " to the classpath.");
		}
	}

	public static void addURL(URL url) {
		try {
			Method method = URLClassLoader.class.getDeclaredMethod("addURL", new Class[] { URL.class });
			method.setAccessible(true);
			method.invoke(ClassLoader.getSystemClassLoader(), new Object[] { url });
		}
		catch (Throwable t) {
			throw new RuntimeException("Could not add " + url + " to the classpath.");
		}
	}
	
	public static void removeFile(String filename) {
		removeFile(new File(filename));
	}
	
	public static void removeFile(File file) {
		try {
			removeURL(file.toURI().toURL());
		}
		catch (MalformedURLException e) {
			throw new RuntimeException("Could not remove " + file.getName() + " from the classpath.");
		}
	}
	
	public static void removeURL(URL url) {
		try {
			Field f = URLClassLoader.class.getDeclaredField("ucp");
			f.setAccessible(true);
			URL[] old = ((URLClassLoader)ClassLoader.getSystemClassLoader()).getURLs();
			URL[] newurls = new URL[old.length - 1];
			for (int i = 0, j = 0; i < old.length; ++i) {
				if (!old[i].equals(url)) {
					newurls[j] = old[i];
					++j;
				}
			}
			f.set(ClassLoader.getSystemClassLoader(), new URLClassPath(newurls));
		}
		catch (Throwable t) {
			throw new RuntimeException("Could not remove " + url + " from the classpath.");
		}
	}
}