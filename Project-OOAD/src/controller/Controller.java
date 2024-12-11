package controller;

import javafx.scene.Scene;
import view.Page;

public abstract class Controller {
	private static Scene primaryScene;

	public static void initialize(Scene scene) {
		primaryScene = scene;
	}

	protected static void navigate(Page<Void> page) {
		if (primaryScene == null) {
			throw new IllegalStateException("Controller not yet initialized!");
		}
		page.show(primaryScene);
	}

	protected static <T> void navigate(Page<T> page, T data) {
		if (primaryScene == null) {
			throw new IllegalStateException("Controller not yet initialized!");
		}
		page.withData(data);
		page.show(primaryScene);
	}

	protected static <T> void popup(Page<T> page, T data) {
		if (primaryScene == null) {
			throw new IllegalStateException("Controller not yet initialized!");
		}

		page.withData(data);
		page.popup();
	}

}
