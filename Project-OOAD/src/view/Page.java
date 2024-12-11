package view;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public abstract class Page<T> {
	protected Pane root;
	protected T data;
	Stage popupStage;

	abstract Pane initRoot();

	public void show(Scene scene) {
		this.root = initRoot();
		scene.setRoot(root);
	}

	public void closePopup() {
		popupStage.close();
	}

	public void popup() {
		this.root = initRoot();
		popupStage = new Stage();
		popupStage.initModality(Modality.APPLICATION_MODAL);
		Scene popupScene = new Scene(root, 400, 400);

		popupStage.setScene(popupScene);
		popupStage.showAndWait();
	}

	public void withData(T data) {
		this.data = data;
	}
}
