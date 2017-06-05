package nl.edegier.mouse;

import io.reactivex.Observable;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class HelloJavaFxAndMavenApp extends Application {



    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public void start(Stage stage) throws Exception {

        Group rootNode = new Group();
        Scene scene = new Scene(rootNode, 1024, 768);
        Circle circle = new Circle(40, 40, 40);
        circle.setFill(Color.RED);
        rootNode.getChildren().add(circle);

        Observable<MouseEvent> mouseObservable = Observable.create(observableEmitter -> {
            scene.setOnMouseMoved( event -> {
                observableEmitter.onNext(event);
            });
        });

        mouseObservable.map(event -> new double[]{event.getSceneX(),event.getSceneY()}).subscribe(point ->{
            circle.setCenterX(point[0]);
            circle.setCenterY(point[1]);
        });

        stage.setTitle("Follow me");
        stage.setScene(scene);
        stage.show();
    }
}
