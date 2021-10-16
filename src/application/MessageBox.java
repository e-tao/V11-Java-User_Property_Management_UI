package application;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class MessageBox {

	private Alert message;
	private HBox details;
//	private AlertType type;
//	private String title;
//	private String content;
	private String detailMsg;

	public MessageBox(AlertType type, String title, String content, String detailMsg) {
		message = new Alert(type);
		message.setTitle(title);
		message.setHeaderText(null);
		message.setContentText(content);
		if (!detailMsg.isEmpty()) {
			message.getDialogPane().setExpandableContent(DetailMessage(detailMsg));
		}

		message.showAndWait();
	}

	public Node DetailMessage(String detailMsg) {
		details = new HBox();
		details.getChildren().add(new Text(detailMsg));

		return details;
	}

}
