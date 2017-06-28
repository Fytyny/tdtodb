/**
 * Created by Cziczarito on 09.06.2017.
 */

import databaseUtils.DatabaseBeans;
import javafx.application.Application;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

public class MainApp extends Application {
    String[][] htmlTable;
    Map<String,String>[] values;
    ApplicationContext context;

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.context = new AnnotationConfigApplicationContext(DatabaseBeans.class);

    }

    private TableView getTableView(){
        return null;
    }
}
