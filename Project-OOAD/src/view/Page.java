package view;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public abstract class Page<T> {
	protected Pane root;
	protected T data;

	public Page() {
		this.root = initRoot();
	}

	abstract Pane initRoot();

	public void show(Scene scene) {
		scene.setRoot(root);
	}

	public void withData(T data) {
		this.data = data;
	}
}
