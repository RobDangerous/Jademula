package jademula;

import java.util.ArrayList;
import java.util.List;

public class Handies {
	private static List<Handy> handies = new ArrayList<Handy>();
	
	public static List<Handy> getAll() {
		return handies;
	}
}