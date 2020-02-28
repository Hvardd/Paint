import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
import javafx.collections.ObservableList;
import javafx.scene.text.Text;
import java.util.ArrayList;
import java.util.List;
import java.awt.Shape;
import javafx.scene.control.Button;
import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.util.Collections;
import javafx.scene.control.ColorPicker;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.Node;
import javafx.collections.FXCollections;


public class TestOpg2 extends Application {

	Circle circle = new Circle();
	Rectangle rectangle = new Rectangle();
	Polygon polygon = new Polygon();
	Line line = new Line();
	Text text = new Text();

	public void start(Stage primaryStage) {

	// the layout 
	Pane layout = new Pane();
	BorderPane borderPane = new BorderPane();
	VBox vBox = new VBox();
	HBox hBox = new HBox();
	ColorPicker colorStroke = new ColorPicker();
	ColorPicker colorFill = new ColorPicker();
	VBox vBox2 = new VBox();

	borderPane.setTop(hBox);
	borderPane.setCenter(layout);
	borderPane.setRight(vBox);

	// ObservableList<Node> shapes = FXCollections.observableArrayList();
	 ArrayList<Node> shapes = new ArrayList<Node>();

	// ComboBox with the items 
	ComboBox<String> cbo = new ComboBox<>();
	cbo.getItems().addAll("Circle", "Rectangle", "Line", "Polygon", "Text","Gammelt");
	cbo.setStyle("-fx-color:blue");
	cbo.setValue("Shapes");
	System.out.println(cbo.getValue());  

	Text figurType = new Text(cbo.getValue());
	Text figurType2 = new Text("");
	Text strokeColor = new Text("Stroke Color:");
	Text fillColor = new Text("Fill color:");
	TextField input = new TextField("Input text here");
	// figurType = "test";               

	primaryStage.show();

	// test check button
	Button button = new Button("size()");

	// for loop to check if the objects x and y coordinates matches the selected node 
     
    // Test 
	button.setOnMouseClicked(v -> {
		System.out.println(layout.getChildren());
		System.out.println(shapes.get(shapes.size()-1));
	 });

	
	// Moving arrayList objects backward and forward
	Button forwardBtn = new Button("Forward");
	Button backwardBtn = new Button("Backward");
	Button redrawBtn = new Button("Redraw");
	Button moveToFirstBtn = new Button("Move to first");
	Button moveToLastBtn = new Button("Move to last");


	forwardBtn.setOnMouseClicked(f -> {
		Collections.swap(shapes, 1, 0);
		layout.getChildren().clear();
		layout.getChildren().setAll(shapes);
	});

	backwardBtn.setOnMouseClicked(b -> {
		Collections.swap(shapes,shapes.size()-1,shapes.size()-2);
		layout.getChildren().clear();
		layout.getChildren().setAll(shapes); 
	 });
	moveToFirstBtn.setOnMouseClicked(b -> {
		Collections.swap(shapes,shapes.size()-1,0);
		layout.getChildren().clear();
		layout.getChildren().setAll(shapes);
	});
	moveToLastBtn.setOnMouseClicked(c -> {
		Collections.swap(shapes,0,shapes.size()-1);
		layout.getChildren().clear();
		layout.getChildren().setAll(shapes);
		
	});

	// Redraw the scene after swapping positions in array 
	 redrawBtn.setOnMouseClicked(r -> { 
	layout.getChildren().clear();
	shapes.clear();
	});

	
	// layout.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
	layout.setOnMouseClicked(e-> {
       //  if (e.getButton().equals(MouseButton.PRIMARY)) {
			if (cbo.getValue() == "Circle") { 
        
				Circle circle = new Circle();

				figurType.setText(cbo.getValue());
    			circle.setCenterX(e.getScreenX());
    			circle.setCenterY(e.getScreenY());
    			circle.setRadius(50);
    			circle.setStroke(colorStroke.getValue());
    			circle.setFill(colorFill.getValue());
    			shapes.add(circle);  // Add circle to circle ArrayList
    			figurType2.setText("Areal er: " + circle.getRadius()*3.14);  // finn ut areal formel
    			layout.getChildren().add(circle);
    		
				circle.setOnMouseDragged(h -> {
				circle.setCenterX(h.getX());
				circle.setCenterY(h.getY());
				circle.setStroke(colorStroke.getValue());
    			circle.setFill(colorFill.getValue());
    			// shapes.add(circle);
    			});
	
    			
 }

    		else if (cbo.getValue() == "Rectangle"){
    			figurType.setText(cbo.getValue());
    			Rectangle rectangle = new Rectangle();
    			rectangle.setX(e.getScreenX());
    			rectangle.setY(e.getScreenY());
    			rectangle.setWidth(100);
    			rectangle.setHeight(50);
    			rectangle.setStroke(colorStroke.getValue());
    			rectangle.setFill(colorFill.getValue());
    			figurType2.setText("Areal er: " + rectangle.getWidth() * rectangle.getHeight()); // Areal av rektangel 
    			shapes.add(rectangle);
    			
    			layout.getChildren().add(rectangle);

    			rectangle.setOnMouseDragged(h -> {
				rectangle.setX(h.getX());
				rectangle.setY(h.getY());
				rectangle.setStroke(colorStroke.getValue());
    			rectangle.setFill(colorFill.getValue());
    			});

    			/*
    			rectangle.setOnMouseClicked(d-> {	
				System.out.println("test" + rectangle.getX());
				System.out.println(rectangle.getY());
				});  
				*/ 
    			// }
    		}

    		else if (cbo.getValue() == "Line"){
    			figurType.setText(cbo.getValue());
    			Line line = new Line(e.getX(), e.getY(), e.getX(), e.getY());
    			line.setStrokeWidth(10);
    			line.setStroke(colorStroke.getValue());
    			line.setFill(colorFill.getValue()); 

    			//line.setStartX(e.getScreenX());
				//line.setStartY(e.getScreenY());

				// Må finnne bedre løsning om det blir tid 
				// line.setEndX(Math.random()*500);
				// line.setEndY(Math.random()*500);

    			line.setOnMouseDragged(h -> {
				line.setEndX(h.getX());
				line.setEndY(h.getY());
				line.setStroke(colorStroke.getValue());
    			line.setFill(colorFill.getValue());
    			figurType2.setText("Linjelengde er: " + Math.abs((line.getStartX() + line.getStartY() - (line.getEndX() + line.getEndY()) )));
    			});

    			// figurType2.setText("Linjelengde er: " + (line.getStartX() + line.getStartY() - (line.getEndX() + line.getEndY()) ) );
    			layout.getChildren().add(line);
    			shapes.add(line);

    		}

    		else if (cbo.getValue() == "Polygon"){

    		//layout.getChildren().add(new Circle(e.getScreenX(), e.getScreenY(), 10, Color.GREEN));
    			figurType.setText(cbo.getValue());
    			Polygon polygon = new Polygon();
    			polygon.setStroke(colorStroke.getValue());
    			polygon.setFill(colorFill.getValue());

    			ObservableList<Double> list = polygon.getPoints();
    			double centerX = 10;
				double radius = 20;

				
				double x = (e.getScreenX()), y = (e.getScreenY());

				double r = 10;

				for (int i = 0; i < 6; i++) {
				list.add(x + radius * Math.cos(2* i * Math.PI / 6));
				list.add(y - radius * Math.sin(2* i * Math.PI / 6));
				}


				polygon.setOnMouseReleased(h -> {

    			for (int i = 0; i < 6; i++) {
				list.add(h.getScreenX() + radius * Math.cos(2* i * Math.PI / 6));
				list.add(h.getScreenY() - radius * Math.sin(2* i * Math.PI / 6));
				}
				});

			
				// figurType2.setText("Areal av polygon er: " + (3*Math.sqrt(3)/2) * (Math.pow(radius,2))); // ?? 
				figurType2.setText("Areal av polygon er: " + (0.5) * (radius*6) * radius);
				shapes.add(polygon);
				layout.getChildren().add(polygon); 
			}

    			else if (cbo.getValue() == "Text"){
    
    			figurType.setText(cbo.getValue());
    			Text text = new Text(input.getText());
    			text.setX(e.getX());
				text.setY(e.getY());
				// text.setColor(colorStroke.getValue());
				figurType2.setText("Tekst er: " + text.getText()); // ?? 
				shapes.add(text);
				layout.getChildren().add(text);

				text.setOnMouseDragged(h -> {
				text.setX(h.getX());
				text.setY(h.getY());
    			});
    		}
    		else if (cbo.getValue() == "Gammelt"){
/*

				rectangle.setOnMouseDragged(h -> {
				rectangle.setX(h.getX());
				rectangle.setY(h.getY());
				rectangle.setStroke(colorStroke.getValue());
    			rectangle.setFill(colorFill.getValue());
    			});

    			 line.setOnMouseDragged(h -> {
				line.setEndX(h.getX());
				line.setEndY(h.getY());
				line.setStroke(colorStroke.getValue());
    			line.setFill(colorFill.getValue());
    			}); }


    			circle.setOnMouseDragged(h -> {
				circle.setCenterX(h.getX());
				circle.setCenterY(h.getY());
				circle.setStroke(colorStroke.getValue());
    			circle.setFill(colorFill.getValue());
    			});
    			
    			text.setOnMouseDragged(h -> {
				text.setX(h.getX());
				text.setY(h.getY());
					//line.setStroke(colorStroke.getValue());
    			//line.setFill(colorFill.getValue());
    			});

    			ObservableList<Double> list = polygon.getPoints();
    			double centerX = 10;
				double radius = 20;

				double x = (e.getScreenX()), y = (e.getScreenY());
				double r = 10;

    			polygon.setOnMouseReleased(h -> {
    			for (int i = 0; i < 6; i++) {
				list.add(h.getScreenX() + radius * Math.cos(2* i * Math.PI / 6));
				list.add(h.getScreenY() - radius * Math.sin(2* i * Math.PI / 6));
				}
				});  */ }

    	});   // End of action event 

	// layout.getChildren().add(groupCircle); // Adds circlegroup to layout
	hBox.setStyle("-fx-color:white");
	vBox.setStyle("-fx-color:grey");
	vBox.getChildren().addAll(figurType, figurType2,input,strokeColor,colorStroke,fillColor,colorFill,button);  // forward,redraw, moveToFirst, moveToLast
	vBox.setSpacing(10);
	hBox.getChildren().addAll(cbo,forwardBtn,backwardBtn,redrawBtn,moveToFirstBtn,moveToLastBtn);
	hBox.setSpacing(10);

	Scene scene = new Scene(borderPane, 750, 750);

	// Sets the title and scene
	primaryStage.setTitle("ShowCircleCentered");
	primaryStage.setScene(scene);


	}
}
