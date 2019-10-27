package cube.ui;

import cube.model.food.FoodList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;

public class ListPanel extends UiManager<StackPane> {
    private static final String FXML = "ListPanel.fxml";

    @FXML
    private ListView<FoodList> foodListView;

    public ListPanel() {
        super(FXML);
    }
}
