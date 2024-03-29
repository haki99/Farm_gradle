import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * 
 * @author Hofi
 * @version 1.0
 * @since   2019-11-06
 */
public class ConfirmBox {
	static boolean answer;
	
	/**Create a ConfirmBox to close the program
	 * 
	 * @param title the title of the Box
	 * @param message the message which appears inside the box
	 * @return return itself
	 */
	public static boolean display(String title, String message) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		Label label = new Label();
		label.setText(message);
		
		Button yesButton = new Button("Igen");
		yesButton.setId("yes");
		Button noButton = new Button("Nem");
		
		yesButton.setOnAction((event) -> {
			answer = true;
			window.close();
		});
		
		noButton.setOnAction((event) -> {
			answer = false;
			window.close();
		});
		
		VBox layout = new VBox(10);;
		layout.getChildren().addAll(label, yesButton, noButton);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
		
		return answer;
	}
}
