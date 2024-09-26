package view;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public abstract class Page {
	protected Scene scene;
	protected Pane root;

	public Page(Scene scene) {
		this.scene = scene;
		this.root = initRoot();
	}

	abstract Pane initRoot();

	public void show() {
		scene.setRoot(root);
	}
}
