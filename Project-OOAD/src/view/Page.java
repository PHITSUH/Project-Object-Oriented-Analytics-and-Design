package view;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

// base class untuk view
// setiap page bisa meminta props tersendiri
// untuk data yang perlu diisi/diperlukan view
public abstract class Page<T> {
	protected Pane root;
	protected T data;
	protected Stage popupStage;

	// wajib implement ini setiap page
	abstract Pane initRoot();

	public void show(Scene scene) {
		this.root = initRoot();
		scene.setRoot(root);
	}

	public void closePopup() {
		popupStage.close();
	}

	// to display the page as a popup, we need a different stage
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
