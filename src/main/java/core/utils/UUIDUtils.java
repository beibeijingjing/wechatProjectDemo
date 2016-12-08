package core.utils;

import java.util.UUID;

public class UUIDUtils {
	
	public static String generate(){
		UUID uuid = UUID.randomUUID();
		String s = uuid.toString();
		return s.replaceAll("-", "");
	}
	public static void main(String[] args) {
		System.out.println(generate());
	}
}
