import java.io.File;
import java.io.IOException;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Media_Player extends Application {

	public static void main(String args[])
	{
		launch(args);
	}


	@Override
	public void start(final Stage args) throws IOException {
		
		String filepath;
		FileChooser open=new FileChooser();
		FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Select a file","*.mp4");
	    open.getExtensionFilters().add(filter);
	    File file= open.showOpenDialog(null);
	    filepath =file.toURI().toString();
	    
		Group root =new Group();
		Button button1=new Button();
		Button button2=new Button();
	    Button button3 =new Button();
	    Button button4 =new Button();
	    Button button5 =new Button();
	    Button button6 =new Button();
	    Button button7 =new Button();
		
		Media media=new Media(filepath);
		MediaPlayer mediaplayer =new MediaPlayer(media);
		MediaView view =new MediaView(mediaplayer);
		
		final DoubleProperty width = view.fitWidthProperty();
	    final DoubleProperty height = view.fitHeightProperty();
	    
	    width.bind(Bindings.selectDouble(view.sceneProperty(), "width"));
	    height.bind(Bindings.selectDouble(view.sceneProperty(), "height"));
	    view.setPreserveRatio(true);
	    Rectangle2D screen = Screen.getPrimary().getVisualBounds();
		
		Image img = new Image(getClass().getClassLoader().getResourceAsStream("img/start.png"),30,20,true,true);
		Image img1 = new Image(getClass().getClassLoader().getResourceAsStream("img/pause.png"),30,20,true,true);
		Image img2 = new Image(getClass().getClassLoader().getResourceAsStream("img/fast.png"),30,20,true,true);
		Image img3 = new Image(getClass().getClassLoader().getResourceAsStream("img/fullscrean.png"),30,20,true,true);
		Image img4 = new Image(getClass().getClassLoader().getResourceAsStream("img/volume.png"),30,20,true,true);
		Image img5 = new Image(getClass().getClassLoader().getResourceAsStream("img/mute.png"),30,20,true,true);
		Image img6 = new Image(getClass().getClassLoader().getResourceAsStream("img/slow.png"),30,20,true,true);
		
	    
		button1.setGraphic(new ImageView(img));
		button2.setGraphic(new ImageView(img1));
		button3.setGraphic(new ImageView(img2));
		button4.setGraphic(new ImageView(img3));
		button5.setGraphic(new ImageView(img4));
		button6.setGraphic(new ImageView(img5));
		button7.setGraphic(new ImageView(img6));
		
		//button1.setStyle("-fx-background-color: white; -fx-text-fill: white;");
		//button4.setStyle("-fx-background-color: white; -fx-text-fill: white; -fx-position:center; -fx-background-size:5 5 ");
		
		int h=mediaplayer.getMedia().getHeight();
		int w=mediaplayer.getMedia().getWidth();
		
		Slider slider =new Slider();
		VBox vbox=new VBox();
		
		HBox hbox=new HBox();
	    HBox hbox_l =new HBox();
	    HBox hbox_c=new HBox();
	    HBox hbox_r=new HBox();
	    HBox hbox_cr=new HBox();
	    
	    
	    hbox_l.getChildren().addAll(button1,button5); 
	    hbox_c.getChildren().addAll(button7,button2,button3);
	    hbox_r.getChildren().add(button4);
	    hbox_cr.getChildren().addAll(hbox_c,hbox_r);
	    hbox.getChildren().addAll(hbox_l,hbox_cr);
		vbox.getChildren().addAll(slider);
		
		
		final DoubleProperty width1 = hbox.prefWidthProperty();
	    final DoubleProperty height1 = hbox.prefHeightProperty();
	    
	    width1.bind(Bindings.selectDouble(hbox.sceneProperty(), "width"));
	    height1.bind(Bindings.selectDouble(hbox.sceneProperty(), "height"));
	    
	    
		root.getChildren().add(view);
		root.getChildren().add(vbox);
		root.getChildren().add(hbox);
	
		
		final Scene scene=new Scene(root,600,600,Color.BLACK);
		args.setScene(scene);
		args.setTitle("Movie Player");
		args.show();

		
		button1.setOnMouseClicked(new EventHandler<MouseEvent>()                  // Play button
		{

			@Override
			public void handle(MouseEvent arg0) {
				mediaplayer.play();
				
			}});
		
		button2.setOnMouseClicked(new EventHandler<MouseEvent>()                   //Pause Button
		{

			@Override
			public void handle(MouseEvent arg0) {
				mediaplayer.pause();
			}});

// Video rate control
		button3.setOnMouseClicked(new EventHandler<MouseEvent>()                    // Fast forward by 2x,4x,8x 
		{
			
			@Override
			public void handle(MouseEvent arg0) {
				if(mediaplayer.getRate()+1 <=8 )
				{
				mediaplayer.setRate(mediaplayer.getRate()*2);
				
				}
			}});
		
		button7.setOnMouseClicked(new EventHandler<MouseEvent>()                    // reduce rate by 2x on every click
				{
					
					@Override
					public void handle(MouseEvent arg0) {
						if(mediaplayer.getRate()>=0.25 )
						{
						mediaplayer.setRate(mediaplayer.getRate()/2);
						}
					}});
		
		
		button4.setOnMouseClicked(new EventHandler<MouseEvent>()                   // Enter into full screen on clicking full screen icon 
		{
            
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(args.isFullScreen()==false)
				{
				args.setFullScreen(true);
	
				}
				else
				{
			    args.setFullScreen(false);
			    int h=mediaplayer.getMedia().getHeight();
				int w=mediaplayer.getMedia().getWidth();
				
		
				}
			}});
		
		view.setOnMouseClicked(new EventHandler<MouseEvent>() {                  // Mouse Double click for full screen 
		    @Override
		    public void handle(MouseEvent click) {
		    	if(click.getClickCount()==2)
		    	{
		    	if(args.isFullScreen()==false)
				{
				args.setFullScreen(true);
				vbox.setPrefWidth(screen.getWidth());
				vbox.setMinWidth(screen.getWidth());
				vbox.setTranslateY(screen.getHeight()/1.2);
				hbox.setMinWidth(screen.getWidth());
				hbox.setTranslateY(screen.getHeight()/1.15);
				hbox_l.setSpacing((args.getWidth())/24);
				hbox_c.setSpacing((args.getWidth())/20);
				hbox_cr.setSpacing((args.getWidth())/3);
				
				hbox.setSpacing((args.getWidth())/3.5);
				System.out.println("media width"+media.getWidth());
				System.out.println("media height"+media.getHeight());
				
				System.out.println("args width"+args.getWidth());
				System.out.println("args height"+args.getHeight());
				
				}
				else
				{
			    args.setFullScreen(false);
			    int h=mediaplayer.getMedia().getHeight();
				int w=mediaplayer.getMedia().getWidth();
				
				vbox.setMinWidth(w);
				vbox.setTranslateY(h/1.2);
				hbox.setMinWidth(w);
				hbox.setTranslateY(h/1.15);
				
				
				
				hbox_l.setSpacing((args.getWidth())/24);
				hbox_c.setSpacing((args.getWidth())/20);
				hbox_cr.setSpacing((args.getWidth())/3);
				
				hbox.setSpacing((args.getWidth())/3.5);
				
			    
			    
				}
		    	}
		    }
		});
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>()                 // Play and Pause on keyboard space bar key
	    {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if (event.getCode().equals(KeyCode.SPACE)||event.getCode().equals(KeyCode.SHIFT)||event.getCode().equals(KeyCode.RIGHT)||event.getCode().equals(KeyCode.LEFT)||event.getCode().equals(KeyCode.UP)||event.getCode().equals(KeyCode.DOWN))
				{
				if (event.getCode().equals(KeyCode.SPACE))
	            {
				  if(mediaplayer.getStatus().toString()=="PLAYING")
				  {
	               mediaplayer.pause();
	               }
				  else
				  {
				   mediaplayer.play();
				  }
				  
	            }
				if(event.getCode().equals(KeyCode.SHIFT)||event.getCode().equals(KeyCode.RIGHT))
				{
					slider.setValue(slider.getValue()+20);
					mediaplayer.seek(Duration.seconds(slider.getValue()));
				}
				if(event.getCode().equals(KeyCode.SHIFT)||event.getCode().equals(KeyCode.LEFT))
					{
						slider.setValue(slider.getValue()-20);
						mediaplayer.seek(Duration.seconds(slider.getValue()));
					}
				
				}
			}
	        
	    });
		
			
	button5.setOnMouseClicked(new EventHandler<MouseEvent>()                          // volume key clicked
	{

		@Override
		public void handle(MouseEvent arg0) {
			mediaplayer.setMute(false);
			root.getChildren().add(button6);
			int h=mediaplayer.getMedia().getHeight();
			int w=mediaplayer.getMedia().getWidth();
			
			args.setMinWidth(w);
			args.setMinHeight(h);
			button6.setTranslateY(h-72);
			button6.setTranslateX(w-485);
			
		}

	});
	
	button6.setOnMouseClicked(new EventHandler<MouseEvent>()                          // volume key clicked
			{

				@Override
				public void handle(MouseEvent arg0) {
					int h=mediaplayer.getMedia().getHeight();
					int w=mediaplayer.getMedia().getWidth();
					
					args.setMinWidth(w);
					args.setMinHeight(h);
					button5.setTranslateY(h-72);
					button5.setTranslateX(w-485);
					
					mediaplayer.setMute(true);
				}

			});
		
		mediaplayer.setOnReady(new Runnable()
		{
			public void run()
			{   
				mediaplayer.setAutoPlay(true);
				int h=mediaplayer.getMedia().getHeight();
				int w=mediaplayer.getMedia().getWidth();
				
				args.setMinWidth(0);
				args.setMinHeight(0);
				
		        args.setWidth(w);
		        args.setHeight(h);
		        
		        args.setMaxWidth(screen.getWidth());
		        args.setMaxHeight(screen.getHeight());
		        
		        args.centerOnScreen();
		        args.setResizable(true);
		       
				vbox.setMinWidth(w);
				vbox.setTranslateY(h/1.2);
				hbox.setMinWidth(0.0);
				hbox.setTranslateY(h/1.15);
			
				
				slider.setMin(0.0);
				slider.setValue(0.0);
				slider.setMax(mediaplayer.getTotalDuration().toSeconds());
				slider.setShowTickLabels(true);
				slider.setShowTickMarks(true);

				
				hbox_l.setSpacing((args.getWidth())/24);
				hbox_c.setSpacing((args.getWidth())/20);
				hbox_cr.setSpacing((args.getWidth())/3);
				hbox.setSpacing((args.getWidth())/3.5);
			}
		});
				scene.widthProperty().addListener(new ChangeListener<Number>() {
				    @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
				        vbox.setMinWidth((double) newSceneWidth);
						hbox.setMinWidth((double) newSceneWidth);
				        hbox_l.setSpacing((double)(newSceneWidth)/24);
						hbox_c.setSpacing((double)(newSceneWidth)/20);
						hbox_cr.setSpacing((double)(newSceneWidth)/2.5);
						hbox.setSpacing((double)(newSceneWidth)/5);
						
						scene.setOnMouseEntered(new EventHandler<MouseEvent>
					    () {

					        @Override
					        public void handle(MouseEvent t) {
					        if((double)newSceneWidth<600)
					        {
					         vbox.setVisible(false);
					         hbox.setVisible(false);
					        }
					        else
					        {
					        	vbox.setVisible(true);
						        hbox.setVisible(true);
					        }
					        }
					    });
						if((double)newSceneWidth<600)
						{   
							vbox.setVisible(false);
							hbox.setVisible(false);
						}
						else
						{   vbox.setVisible(true);
							hbox.setVisible(true);
						}
				    }
				});
				scene.heightProperty().addListener(new ChangeListener<Number>() {
				    @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
				        vbox.setTranslateY((double)(newSceneHeight)/1.2);
				        hbox.setTranslateY((double)(newSceneHeight)/1.15);
				    }
				});
		
		
		scene.setOnMouseEntered(new EventHandler<MouseEvent>
	    () {

	        @Override
	        public void handle(MouseEvent t) {
	         vbox.setVisible(true);
	         hbox.setVisible(true);
	        }
	    });
		System.out.println("Time"+System.currentTimeMillis());
		
		
		scene.setOnMouseExited(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				vbox.setVisible(false);
				hbox.setVisible(false);
				
			}
			
		});
		mediaplayer.currentTimeProperty().addListener(new ChangeListener<Duration>()
				{

					@Override
					public void changed(ObservableValue<? extends Duration> arg0, Duration duration, Duration arg2) {
						// TODO Auto-generated method stub
						slider.setValue(arg2.toSeconds());
					}
			
				});
		
		slider.setOnMousePressed(new EventHandler<MouseEvent>()
				{

					@Override
					public void handle(MouseEvent arg0) {
						// TODO Auto-generated method stub
						mediaplayer.seek(Duration.seconds(slider.getValue()));
					}});
		
	}

}