package controller;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Product;
import model.User;

public class ProductController extends Controller {

	public static TableView<Product> createTableView(List<Product> productList) {
		TableView<Product> tableView = new TableView<>();

		if (productList.isEmpty()) {
			return null;
		} else {
			tableView = new TableView<>();

			TableColumn<Product, String> productNameColumn = new TableColumn<>("Product Name");
			productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
			productNameColumn.prefWidthProperty().bind(tableView.widthProperty().divide(2));

			TableColumn<Product, String> productDescriptionColumn = new TableColumn<>("Product Description");
			productDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
			productDescriptionColumn.prefWidthProperty().bind(tableView.widthProperty().divide(2));

			tableView.getColumns().add(productNameColumn);
			tableView.getColumns().add(productDescriptionColumn);

			ObservableList<Product> vendorObsList = FXCollections.observableArrayList(productList);

			tableView.setItems(vendorObsList);
		}

		return tableView;
	}

	public static void addProduct(User user, String name, String description) {
		// TODO Auto-generated method stub
		Product.addProduct(user, name, description);
	}

}
