package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class Box extends Application {
    public static void main(String[] args) {
        launch( args );
    }

    Notifications notificationBuilder;
    final Button button = new Button( "Submit" );

    final Label notification = new Label();
    final TextField subject = new TextField( "" );
    final TextArea text = new TextArea( "" );


    @Override
    public void start(Stage stage) {
        stage.setTitle( "Activity tracker" );
        Scene scene = new Scene( new Group(), 550, 350 );
        scene.setFill( Color.STEELBLUE );


        final ComboBox emailComboBox = new ComboBox();
        emailComboBox.getItems().addAll(
                "Production Issue",
                "P1",
                "Emails",
                "Geneos monitoring",
                "Meetings"
        );

        final ComboBox priorityComboBox = new ComboBox();
        priorityComboBox.getItems().addAll(
                "Highest",
                "High",
                "Normal",
                "Low",
                "Lowest"
        );

        priorityComboBox.setValue( "Normal" );

        Button b1 = new Button();
        b1.setText( "Show notificaiton example" );
        b1.setOnAction( e -> {

            notification( Pos.BOTTOM_RIGHT, "update the activity status" );
            notificationBuilder.show();

        } );

        VBox menu = new VBox( 4 );
        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu( "Log in" );
        Menu menuStats = new Menu( "Show tasks" );
        menuStats.setOnAction( e -> {

            notification( Pos.BOTTOM_RIGHT, "update the activity status" );
            notificationBuilder.show();

        } );
        menuBar.getMenus().addAll( menuFile, menuStats );


        GridPane grid = new GridPane();
        grid.setVgap( 4 );
        grid.setHgap( 10 );
        grid.setPadding( new Insets( 5, 5, 5, 5 ) );
        grid.add( menuBar, 3, 3 );
        grid.add( new Label( "Type of activity: " ), 0, 0 );
        grid.add( emailComboBox, 1, 0 );
        grid.add( new Label( "Priority: " ), 2, 0 );
        grid.add( priorityComboBox, 3, 0 );
        grid.add( new Label( "Comments: " ), 0, 1 );
        grid.add( subject, 1, 1, 3, 1 );
        grid.add( text, 0, 2, 4, 1 );
        grid.add( button, 0, 3 );
        grid.add( notification, 1, 3, 3, 1 );

        Group root = (Group) scene.getRoot();

        root.getChildren().add( grid );
        ////
        root.getChildren().add( b1 );
        stage.setScene( scene );
        stage.show();
    }


    private static Notifications notifications = null;

    public static Notifications getNotification(String title, String text) {
        notifications = Notifications.create().title( title ).text( text ).graphic( null ).hideAfter( Duration.seconds( 10 ) ).position( Pos.BOTTOM_RIGHT );
        return notifications;

    }

    private void notification(Pos pos, String Text) {

        notificationBuilder = Notifications.create()
                .title( "Have you updated activity tracker?" )
                .text( Text )
                .hideAfter( Duration.seconds( 2 ) )
                .position( pos )
                .onAction( new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent arg0) {
                        System.out.println( "Notifiation is Clicked" );
                    }

                } );
    }
}
