package controller;

import model.Product;
import model.User;


public class ProductController extends Controller{
	
	public static void addProduct(User user, String name, String description) {
		// TODO Auto-generated method stub
		Product.addProduct(user, name, description);
	}

}
