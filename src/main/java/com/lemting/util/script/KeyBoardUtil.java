package com.lemting.util.script;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.awt.event.KeyEvent;

/**
 * 模拟键盘操作类
 * 
 * @author Lemting
 * 
 * int getDefaultDelay(): 获取按键默认延迟 void setDefaultDelay(int
 * defaultDelay): 设置按键默认延迟
 * 
 * void delay(int delay): 延时 delay 毫秒
 * 
 * void printText(String text): 模拟键盘打出文本(仅支持字母和键盘上的字符) void
 * printText(String text, int delay): 模拟键盘打出文本, 指定延迟(仅支持字母和键盘上的字符)
 * 
 * void multipleKey(List<Integer> keys): 按下多个键 void
 * multipleKey(List<Integer> keys, int delay): 按下多个键, 指定按键延迟 void
 * multipleKey(int delay, Integer... keys): 按下多个键, 指定按键延迟
 * 
 * void keyPressAndRelease(int key): 按下并释放按键 void keyPressAndRelease(int key, int delay): 按下并释放按键, 指定延迟
 * 
 * void keyCombination(String comb, int key): 按下组合键, comb: 由 wcas 四种字符组成的字符串,代表需要按下的组合键(win,ctrl,alt,shift) 
 * void keyCombination(String comb, int key, int delay): 按下组合键, 指定延迟, comb: 由 wcas 四种字符组成的字符串,代表需要按下的组合键(win,ctrl,alt,shift)
 * 
 * void paste(): 模拟按下 ctrl + v void paste(int delay): 模拟按下 ctrl + v, 指定延迟 
 * void pasteText(String text): 将文本复制到剪切板, 然后模拟按下 ctrl + v void
 * pasteText(String text, int delay): 将文本复制到剪切板, 指定延迟, 然后模拟按下 ctrl + v
 * 
 * void setClipboardString(String text): 设置剪切板内容 String
 * getClipboardString(): 获取剪切板内容
 * 
 * Point mousePosition(): 获取鼠标位置
 *
 */
public class KeyBoardUtil {

	private static Robot robot;
	private static int defaultDelay = 20;

	static {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 延时 delay 毫秒
	 * @param delay
	 */
	public static void delay(int delay) {
		robot.delay(delay);
	}

	/**
	 * 获取按键默认延迟
	 * @return
	 */
	public static int getDefaultDelay() {
		return defaultDelay;
	}

	/**
	 * 设置按键默认延迟
	 * @param defaultDelay
	 */
	public static void setDefaultDelay(int defaultDelay) {
		KeyBoardUtil.defaultDelay = defaultDelay;
	}

	/**
	 * 模拟键盘打出文本(仅支持字母和键盘上的字符)
	 * @param text
	 */
	public static void printText(String text) {
		printText(text, defaultDelay);
	}

	/**
	 * 模拟键盘打出文本, 指定延迟(仅支持字母和键盘上的字符)
	 * @param text
	 * @param delay
	 */
	public static void printText(String text, int delay) {
		if (text == null || text.length() == 0)
			return;
		for (char c : text.toCharArray()) {
			boolean shift = false;
			int key = 0;
			switch (c) {
				case 'A': shift = true; case 'a': key = KeyEvent.VK_A; break;
				case 'B': shift = true; case 'b': key = KeyEvent.VK_B; break;
				case 'C': shift = true; case 'c': key = KeyEvent.VK_C; break;
				case 'D': shift = true; case 'd': key = KeyEvent.VK_D; break;
				case 'E': shift = true; case 'e': key = KeyEvent.VK_E; break;
				case 'F': shift = true; case 'f': key = KeyEvent.VK_F; break;
				case 'G': shift = true; case 'g': key = KeyEvent.VK_G; break;
				case 'H': shift = true; case 'h': key = KeyEvent.VK_H; break;
				case 'I': shift = true; case 'i': key = KeyEvent.VK_I; break;
				case 'J': shift = true; case 'j': key = KeyEvent.VK_J; break;
				case 'K': shift = true; case 'k': key = KeyEvent.VK_K; break;
				case 'L': shift = true; case 'l': key = KeyEvent.VK_L; break;
				case 'M': shift = true; case 'm': key = KeyEvent.VK_M; break;
				case 'N': shift = true; case 'n': key = KeyEvent.VK_N; break;
				case 'O': shift = true; case 'o': key = KeyEvent.VK_O; break;
				case 'P': shift = true; case 'p': key = KeyEvent.VK_P; break;
				case 'Q': shift = true; case 'q': key = KeyEvent.VK_Q; break;
				case 'R': shift = true; case 'r': key = KeyEvent.VK_R; break;
				case 'S': shift = true; case 's': key = KeyEvent.VK_S; break;
				case 'T': shift = true; case 't': key = KeyEvent.VK_T; break;
				case 'U': shift = true; case 'u': key = KeyEvent.VK_U; break;
				case 'V': shift = true; case 'v': key = KeyEvent.VK_V; break;
				case 'W': shift = true; case 'w': key = KeyEvent.VK_W; break;
				case 'X': shift = true; case 'x': key = KeyEvent.VK_X; break;
				case 'Y': shift = true; case 'y': key = KeyEvent.VK_Y; break;
				case 'Z': shift = true; case 'z': key = KeyEvent.VK_Z; break;
				case '~': shift = true; case '`': key = KeyEvent.VK_BACK_QUOTE; break;
				case '!': shift = true; case '1': key = KeyEvent.VK_1; break;
				case '@': shift = true; case '2': key = KeyEvent.VK_2; break;
				case '#': shift = true; case '3': key = KeyEvent.VK_3; break;
				case '$': shift = true; case '4': key = KeyEvent.VK_4; break;
				case '%': shift = true; case '5': key = KeyEvent.VK_5; break;
				case '^': shift = true; case '6': key = KeyEvent.VK_6; break;
				case '&': shift = true; case '7': key = KeyEvent.VK_7; break;
				case '*': shift = true; case '8': key = KeyEvent.VK_8; break;
				case '(': shift = true; case '9': key = KeyEvent.VK_9; break;
				case ')': shift = true; case '0': key = KeyEvent.VK_0; break;
				case '_': shift = true; case '-': key = KeyEvent.VK_MINUS; break;
				case '+': shift = true; case '=': key = KeyEvent.VK_EQUALS; break;
				case '{': shift = true; case '[': key = KeyEvent.VK_OPEN_BRACKET; break;
				case '}': shift = true; case ']': key = KeyEvent.VK_CLOSE_BRACKET; break;
				case '|': shift = true; case '\\': key = KeyEvent.VK_BACK_SLASH; break;
				case ':': shift = true; case ';': key = KeyEvent.VK_SEMICOLON; break;
				case '\"': shift = true; case '\'': key = KeyEvent.VK_QUOTE; break;
				case '<': shift = true; case ',': key = KeyEvent.VK_COMMA; break;
				case '>': shift = true; case '.': key = KeyEvent.VK_PERIOD; break;
				case '?': shift = true; case '/': key = KeyEvent.VK_SLASH; break;
				case '\n': key = KeyEvent.VK_ENTER; break;
				case ' ': default: key = KeyEvent.VK_SPACE; System.err.println("error char: " + c); break;
			}
			keyCombination(shift ? "s" : null, key, delay);
		}
	}

	/**
	 * 按下多个键
	 * 
	 * @param keys
	 */
	public static void multipleKey(List<Integer> keys) {
		if (keys == null || keys.isEmpty())
			return;
		multipleKey(keys, defaultDelay);
	}

	/**
	 * 按下多个键, 指定按键延迟
	 * 
	 * @param keys
	 * @param delay
	 */
	public static void multipleKey(List<Integer> keys, int delay) {
		if (keys == null || keys.isEmpty())
			return;
		for (int key : keys) {
			robot.keyPress(key);
			delay(delay);
		}
		for (int i = keys.size() - 1; i >= 0; i--) {
			robot.keyRelease(keys.get(i));
			delay(delay);
		}
	}

	/**
	 * 按下多个键, 指定按键延迟
	 * 
	 * @param keys
	 * @param delay
	 */
	public static void multipleKey(int delay, Integer... keys) {
		if (keys == null || keys.length == 0)
			return;
		multipleKey(Arrays.asList(keys), delay);
	}

	/**
	 * 按下并释放按键
	 */
	public static void keyPressAndRelease(int key) {
		keyPressAndRelease(key, defaultDelay);
	}

	/**
	 * 按下并释放按键, 指定延迟
	 */
	public static void keyPressAndRelease(int key, int delay) {
		robot.keyPress(key);
		delay(delay);
		robot.keyRelease(key);
		delay(delay);
	}

	/**
	 * 按下组合键
	 * 
	 * @param comb 由 wcas 四种字符组成的字符串,代表需要按下的组合键(win,ctrl,alt,shift)
	 * @param key
	 */
	public static void keyCombination(String comb, int key) {
		keyCombination(comb, key, defaultDelay);
	}

	/**
	 * 按下组合键, 指定延迟
	 * 
	 * @param comb 由 wcas 四种字符组成的字符串,代表需要按下的组合键(win,ctrl,alt,shift)
	 * @param key
	 */
	public static void keyCombination(String comb, int key, int delay) {
		List<Integer> keys = new ArrayList<Integer>();
		if (comb != null && comb.length() > 0) {
			for (char c : comb.toCharArray()) {
				switch (c) {
				case 'w':
					if (!keys.contains(KeyEvent.VK_WINDOWS))
						keys.add(KeyEvent.VK_WINDOWS);
					break;
				case 'c':
					if (!keys.contains(KeyEvent.VK_CONTROL))
						keys.add(KeyEvent.VK_CONTROL);
					break;
				case 'a':
					if (!keys.contains(KeyEvent.VK_ALT))
						keys.add(KeyEvent.VK_ALT);
					break;
				case 's':
					if (!keys.contains(KeyEvent.VK_SHIFT))
						keys.add(KeyEvent.VK_SHIFT);
					break;
				default:
					System.err.println("error character: " + c);
					return;
				}
			}
		}
		keys.add(key);
		multipleKey(keys, delay);
	}

	/**
	 * 模拟按下 ctrl + v
	 * 
	 * @param text
	 */
	public static void paste() {
		paste(defaultDelay);
	}

	/**
	 * 模拟按下 ctrl + v, 指定延迟
	 * 
	 * @param text
	 */
	public static void paste(int delay) {
		keyCombination("c", KeyEvent.VK_V, delay);
	}

	/**
	 * 将文本复制到剪切板, 然后模拟按下 ctrl + v
	 * 
	 * @param text
	 */
	public static void pasteText(String text) {
		setClipboardString(text);
		paste(defaultDelay);
	}

	/**
	 * 将文本复制到剪切板, 指定延迟, 然后模拟按下 ctrl + v
	 * 
	 * @param text
	 */
	public static void pasteText(String text, int delay) {
		setClipboardString(text);
		paste(delay);
	}

	/**
	 * 复制, 把文本设置到剪贴板
	 * 
	 * @param text
	 */
	public static void setClipboardString(String text) {
		// 获取系统粘贴板
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		// 封装文本内容
		Transferable trans = new StringSelection(text);
		// 把文本内容设置到系统剪切板
		clipboard.setContents(trans, null);
	}

	/**
	 * 粘贴, 从剪切板获取文本
	 * 
	 * @return
	 */
	public static String getClipboardString() {
		// 获取系统粘贴板
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		// 获取剪切板内容
		Transferable trans = clipboard.getContents(null);
		if (trans != null) {
			// 判断剪贴板中的内容是否支持文本
			if (trans.isDataFlavorSupported(DataFlavor.stringFlavor)) {
				try {
					String text = (String) trans.getTransferData(DataFlavor.stringFlavor);
					return text;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * 获取当前鼠标位置
	 * 
	 * @return
	 */
	public static Point mousePosition() {
		Point now = MouseInfo.getPointerInfo().getLocation();
		return now;
	}
}
