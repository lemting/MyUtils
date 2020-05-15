package Test.gameScript;

import java.util.Scanner;

import java.awt.event.KeyEvent;

import gameScript.KeyBoardUtils;

public class KeyBoardUtilsTest {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("enter something: ");
		sc.nextLine();
		//KeyBoardUtils.pasteText(sc.nextLine());
		//KeyBoardUtils.multipleKey(20, KeyEvent.VK_WINDOWS, KeyEvent.VK_R);
		//KeyBoardUtils.multipleKey(20, KeyEvent.VK_LEFT);
		
		KeyBoardUtils.keyCombination("cs", KeyEvent.VK_ESCAPE);
		//`1234567890-=[]\\;',./~!@#$%^&*()_+{}|:\"<>?qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXVBNM
		//KeyBoardUtils.printText("`1234567890-=[]\\;',./~!@#$%^&*()_+{}|:\"<>?qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXVBNM");
		//KeyBoardUtils.multipleKey(20, KeyEvent.VK_SHIFT, KeyEvent.VK_OPEN_BRACKET);
		sc.close();
	}
}
