package Test.gameScript;

import java.util.Scanner;

import java.awt.event.KeyEvent;

import gameScript.KeyBoard;

public class KeyBoardTest {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("enter something: ");
		sc.nextLine();
		//KeyBoard.pasteText(sc.nextLine());
		//KeyBoard.multipleKey(20, KeyEvent.VK_WINDOWS, KeyEvent.VK_R);
		//KeyBoard.multipleKey(20, KeyEvent.VK_LEFT);
		
		KeyBoard.keyCombination("ca", KeyEvent.VK_DELETE);
		//`1234567890-=[]\\;',./~!@#$%^&*()_+{}|:\"<>?qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXVBNM
		KeyBoard.printText("`1234567890-=[]\\;',./~!@#$%^&*()_+{}|:\"<>?qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXVBNM");
		//KeyBoard.multipleKey(20, KeyEvent.VK_SHIFT, KeyEvent.VK_OPEN_BRACKET);
		sc.close();
	}
}
