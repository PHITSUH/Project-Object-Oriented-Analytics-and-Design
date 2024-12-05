package view;

import java.util.List;

import javafx.event.Event;
import javafx.scene.layout.Pane;
import model.User;

public class EventDetailsPage extends Page<EventDetailsPage.Props> {
	public class Props {
		protected Event event;
		protected List<User> participants;

		public Props(Event event, List<User> participants) {
			super();
			this.event = event;
			this.participants = participants;
		}
	}

	@Override
	Pane initRoot() {

		return null;
	}

}
