package view;

import java.util.List;

import controller.ProductController;
import controller.VendorController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Product;

public class ManageVendorPage extends Page<List<Product>> {

	private BorderPane mainPane;
	private VBox mainBox;
	private TableView<Product> tableView;
	private Label titleLabel;
	private Button addProductButton;

	public void event() {
		addProductButton.setOnAction(e -> {
			VendorController.viewAddProduct();
		});
	}

	public Pane init() {
		mainPane = new BorderPane();
		mainPane.setTop(ComponentFactory.vendorNavbar());

		titleLabel = new Label("Manage Vendor");
		titleLabel.setFont(Font.font("", FontWeight.EXTRA_BOLD, 60));

		mainBox = new VBox();
		mainBox.setAlignment(Pos.CENTER);
		mainBox.setPadding(new Insets(20));
		mainBox.getChildren().add(titleLabel);

		if (data.isEmpty() || data == null) {
			mainBox.getChildren().add(new Label("No Products yet"));
		} else {
			tableView = ProductController.createTableView(data);
			mainBox.getChildren().add(tableView);
		}

		addProductButton = new Button("Add Product");
		mainBox.getChildren().add(addProductButton);

		VBox.setMargin(addProductButton, new Insets(15, 0, 0, 0));

		mainPane.setCenter(mainBox);
		event();

		return mainPane;
	}

	@Override
	Pane initRoot() {
		root = init();

		return root;
	}

}
