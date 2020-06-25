package net.codejava.cart;
import java.util.ArrayList;
import java.util.List;



public class CartInfo {

	private int orderId;
	
	private CustomerInfo customerInfo;
	
	
	private final List<CartLine> cartLines = new ArrayList<CartLine>();
	
	
	public CartInfo() {
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}

	public List<CartLine> getCartLines() {
		return cartLines;
	}
	
	  private CartLine findLineByCode(long code) {
	        for (CartLine line : this.cartLines) {
	            if (line.getProductInfo().getId() == code) {
	                return line;
	            }
	        }
	        return null;
	    }
	 
	  public void addProduct(ProductInfo productInfo, int quantity) {
		  CartLine line = this.findLineByCode(productInfo.getId());
		  
		  if (line == null) {
			line = new CartLine();
			line.setQuantity(0);
			line.setProductInfo(productInfo);	
			this.cartLines.add(line);
		}
		  int newQuantity = line.getQuantity()+quantity;
		  if (newQuantity <=0) {
			this.cartLines.remove(line);
		}else {
			line.setQuantity(newQuantity);
		}
		  
	  }
	  
	  public void removeProd(ProductInfo info) {
		CartLine line = this.findLineByCode(info.getId());
		if (line != null) {
			  this.cartLines.remove(line);
		}
	}
	  
	  public void botSoLuong(ProductInfo productInfo) {
		  CartLine line = this.findLineByCode(productInfo.getId());
	 
	        if (line == null) {
	            line = new CartLine();
	            line.setQuantity(0);
	            line.setProductInfo(productInfo);
	            this.cartLines.add(line);
	        }
	         else {
	        	 int newQuantity = line.getQuantity() - 1;
	            if (newQuantity < 0) {
					newQuantity = 0;
					
				}
	            line.setQuantity(newQuantity);
	        }
	    }
	
}
