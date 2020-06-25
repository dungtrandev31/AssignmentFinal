package net.codejava.Util;

import net.codejava.cart.CartInfo;

public class Util {

	public static CartInfo myCart;
	
	public static CartInfo getCart() {
		if (myCart == null) {
			myCart = new CartInfo();
		}
		return myCart;
	}
	
	public static void removeCart() {
		myCart = null;
	}
}
