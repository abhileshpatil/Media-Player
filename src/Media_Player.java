import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
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
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;
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
		FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Select a file","*.mp4","*.mp3");
	    open.getExtensionFilters().add(filter);
	    File file= open.showOpenDialog(null);
	    filepath =file.toURI().toString();
	    
		Group root =new Group();
		Button button1=new Button();
	    Button button3 =new Button();
	    Button button4 =new Button();
	    Button button5 =new Button();
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
		Image img3 = new Image(getClass().getClassLoader().getResourceAsStream("img/fullscreen.png"),30,20,true,true);
		Image img4 = new Image(getClass().getClassLoader().getResourceAsStream("img/volume.png"),30,20,true,true);
		Image img5 = new Image(getClass().getClassLoader().getResourceAsStream("img/mute.png"),30,20,true,true);
		Image img6 = new Image(getClass().getClassLoader().getResourceAsStream("img/slow.png"),30,20,true,true);
		Image img7 = new Image(getClass().getClassLoader().getResourceAsStream("img/audio.png"),350,350,true,true);
		Image img8 = new Image(getClass().getClassLoader().getResourceAsStream("img/Exit fullscreen.png"),30,20,true,true);
		
	    
		button1.setGraphic(new ImageView(img1));
		button3.setGraphic(new ImageView(img2));
		button4.setGraphic(new ImageView(img3));
		button5.setGraphic(new ImageView(img4));
		button7.setGraphic(new ImageView(img6));
		
	
		Slider slider =new Slider();
		Slider volumeslider =new Slider(); 
		VBox vbox=new VBox();
		HBox hbox=new HBox();
	    HBox hbox_l =new HBox();
	    HBox hbox_c=new HBox();
	    HBox hbox_r=new HBox();
	    HBox hbox_cr=new HBox();
	    
	    button1.setStyle("-fx-text-fill: #006464; -fx-background-color: #DFB951; -fx-border-radius: 25; -fx-background-radius: 25; -fx-padding: 5;");
		button3.setStyle("-fx-text-fill: #006464; -fx-background-color: #DFB951; -fx-border-radius: 25; -fx-background-radius: 25; -fx-padding: 5;");
		button4.setStyle("-fx-text-fill: #006464; -fx-background-color: #DFB951; -fx-border-radius: 25; -fx-background-radius: 25; -fx-padding: 5;");
		button5.setStyle("-fx-text-fill: #006464; -fx-background-color: #DFB951; -fx-border-radius: 25; -fx-background-radius: 25; -fx-padding: 5;");
		button7.setStyle("-fx-text-fill: #006464; -fx-background-color: #DFB951; -fx-border-radius: 25; -fx-background-radius: 25; -fx-padding: 5;");
		
	    hbox_l.getChildren().addAll(button5,volumeslider); 
	    hbox_c.getChildren().addAll(button7,button1,button3);
	    hbox_r.getChildren().addAll(button4);
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
		
		final Scene scene=new Scene(root,700,700,Color.BLACK);
		args.setScene(scene);
		args.setTitle("Media Player");
		args.show();
		
		button1.setTooltip(new Tooltip("pause"));
		button1.setOnMouseClicked(new EventHandler<MouseEvent>()                  // Play and pause button
		{

			@Override
			public void handle(MouseEvent arg0) {
				if(mediaplayer.getStatus().equals(Status.PLAYING))
				{
					mediaplayer.pause();
					button1.setGraphic(new ImageView(img));
					button1.setTooltip(new Tooltip("play"));
				}
				else
				{
				mediaplayer.play();
				button1.setGraphic(new ImageView(img1));
				button1.setTooltip(new Tooltip("pause"));
				}
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
		
		button3.setOnMouseEntered(new EventHandler<MouseEvent>()                    
				{
					
					@Override
					public void handle(MouseEvent arg0) {
						if((float)mediaplayer.getRate()>=8)
						{button3.setTooltip(null);}
						if((float)mediaplayer.getRate()<8)
						{
						button3.setTooltip(new Tooltip(""+2*(float)mediaplayer.getRate()+"x"));
						}
					}});
		
		button7.setOnMouseClicked(new EventHandler<MouseEvent>()                    // reduce rate by 2x on every click till 0.25x
				{
					
					@Override
					public void handle(MouseEvent arg0) {
						if(mediaplayer.getRate()>0.25 )
						{
						System.out.println("slow rate"+mediaplayer.getRate());
						mediaplayer.setRate(mediaplayer.getRate()/2);
						}
						
					}});
		
		button7.setOnMouseEntered(new EventHandler<MouseEvent>()                     
				{
					
					@Override
					public void handle(MouseEvent arg0) {
						if((float)mediaplayer.getRate()<0.5)
						{button7.setTooltip(null);}
						if((float)mediaplayer.getRate()>=0.5)
						{
							
						button7.setTooltip(new Tooltip(""+(float)mediaplayer.getRate()/2+"x"));
						}
						
					}});
		
		button4.setTooltip(new Tooltip("Fullscreen"));
		button4.setOnMouseClicked(new EventHandler<MouseEvent>()                   // Enter into full screen on clicking full screen icon 
		{
            
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(args.isFullScreen()==false)
				{   
					args.setFullScreen(true);
					button4.setGraphic(new ImageView(img8));
					button4.setTooltip(new Tooltip("Exit Fullscreen"));
					vbox.setMinWidth(screen.getWidth());
					vbox.setTranslateY(screen.getHeight()/1.2);
					hbox.setMinWidth(screen.getWidth());
					hbox.setTranslateY(screen.getHeight()/1.15);
					hbox_c.setSpacing((args.getWidth())/20);
					hbox_cr.setSpacing((args.getWidth())/3);
					hbox.setSpacing((args.getWidth())/3.5);
	
				}
				else
				{
					args.setFullScreen(false);
					button4.setGraphic(new ImageView(img3));
					button4.setTooltip(new Tooltip("Fullscreen"));
				    vbox.setMinWidth(scene.getWidth());
					vbox.setTranslateY(scene.getHeight()/1.2);
					hbox.setMinWidth(0.0);
					hbox.setTranslateY(scene.getHeight()/1.15);
					hbox_c.setSpacing((args.getWidth())/20);
					hbox_cr.setSpacing((args.getWidth())/4);
					hbox.setSpacing((args.getWidth())/4);
		
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
				button4.setGraphic(new ImageView(img8));
				button4.setTooltip(new Tooltip("Exit Fullscreen"));
				vbox.setMinWidth(screen.getWidth());
				vbox.setTranslateY(screen.getHeight()/1.2);
				hbox.setMinWidth(screen.getWidth());
				hbox.setTranslateY(screen.getHeight()/1.15);
				hbox_c.setSpacing((args.getWidth())/20);
				hbox_cr.setSpacing((args.getWidth())/3);
				hbox.setSpacing((args.getWidth())/3.5);
				
				}
				else
				{
			    args.setFullScreen(false);
			    button4.setGraphic(new ImageView(img3));
			    button4.setTooltip(new Tooltip("Fullscreen"));
			    vbox.setMinWidth(scene.getWidth());
				vbox.setTranslateY(scene.getHeight()/1.2);
				hbox.setMinWidth(0.0);
				hbox.setTranslateY(scene.getHeight()/1.15);
				
				hbox_c.setSpacing((args.getWidth())/20);
				hbox_cr.setSpacing((args.getWidth())/4);
				hbox.setSpacing((args.getWidth())/4);
				
				}
		    	}
		    }
		});
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>()                 // Play and Pause on keyboard space bar key
	    {                                                                  // Fast forward and rewind using shift + RIGHT/Left array key
			                                                              // Change Volume using shift + UP/Down array key
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
	               button1.setGraphic(new ImageView(img));
	               button1.setTooltip(new Tooltip("play"));
	               }
				  else
				  {
				   mediaplayer.play();
				   button1.setGraphic(new ImageView(img1));
				   button1.setTooltip(new Tooltip("pause"));
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
				if(event.getCode().equals(KeyCode.SHIFT)||event.getCode().equals(KeyCode.UP))
				{
					mediaplayer.setVolume(volumeslider.getValue()+5);
					volumeslider.setValue(volumeslider.getValue()+5);
				}
				if(event.getCode().equals(KeyCode.SHIFT)||event.getCode().equals(KeyCode.DOWN))
				{
					mediaplayer.setVolume(volumeslider.getValue()-5);
					volumeslider.setValue(volumeslider.getValue()-5);
				}
				}
			}
	        
	    });
		
	button5.setTooltip(new Tooltip("Mute"));		
	button5.setOnMouseClicked(new EventHandler<MouseEvent>()                          // volume key clicked
	{
		@Override
		public void handle(MouseEvent arg0) {
			if(mediaplayer.isMute()==true)
			{
				button5.setGraphic(new ImageView(img4));
				mediaplayer.setMute(false);
				button5.setTooltip(new Tooltip("Mute"));
				volumeslider.setValue(50);
			}
			else
			{
				button5.setGraphic(new ImageView(img5));
				mediaplayer.setMute(true);
				button5.setTooltip(new Tooltip("Volume"));
				volumeslider.setValue(0);
			}	
		}
	});
	
	button5.setOnMouseEntered(new EventHandler<MouseEvent>(){

		@Override
		public void handle(MouseEvent arg0) {
			// TODO Auto-generated method stub
			volumeslider.setVisible(true);
			
		}
		
	});
	
	button5.setOnMouseExited(new EventHandler<MouseEvent>(){

		@Override
		public void handle(MouseEvent arg0) {
			// TODO Auto-generated method stub
			volumeslider.setOnMouseEntered(new EventHandler<MouseEvent>(){

				@Override
				public void handle(MouseEvent event) {
					// TODO Auto-generated method stub
					volumeslider.setVisible(true);
				}
				
			});
			volumeslider.setOnMouseExited(new EventHandler<MouseEvent>(){

				@Override
				public void handle(MouseEvent event) {
					// TODO Auto-generated method stub
					volumeslider.setVisible(false);
				}
				
			});
			volumeslider.setVisible(false);
			
		}
		
	});
	    
		mediaplayer.setOnReady(new Runnable()
		{
			public void run()
			{   
				mediaplayer.setAutoPlay(true);
				
				args.setMinWidth(0);
				args.setMinHeight(0);
				
		        args.setWidth(scene.getWidth());
		        args.setHeight(scene.getHeight());
		        
		        args.setMaxWidth(screen.getWidth());
		        args.setMaxHeight(screen.getHeight());
		        
		        args.centerOnScreen();
		        args.setResizable(true);
		       
				vbox.setMinWidth(scene.getWidth());
				vbox.setTranslateY(scene.getHeight()/1.2);
				hbox.setMinWidth(0.0);
				hbox.setTranslateY(scene.getHeight()/1.15);
				
				
				slider.setMin(0.0);
				slider.setValue(0.0);
				volumeslider.setValue(mediaplayer.getVolume()*100);
				slider.setMax(mediaplayer.getTotalDuration().toSeconds());
				slider.setShowTickLabels(true);
				slider.setShowTickMarks(true);
				volumeslider.setShowTickLabels(true);
				volumeslider.setShowTickMarks(true);
				volumeslider.setVisible(false);

				
				hbox_c.setSpacing((args.getWidth())/20);
				hbox_r.setSpacing((args.getWidth())/20);
				hbox_cr.setSpacing((args.getWidth())/4);
				hbox.setSpacing((args.getWidth())/4);
				
			    if(mediaplayer.impl_getLatestFrame()==null)
				{
					root.getChildren().add(new ImageView(img7));
			        img7.isPreserveRatio();
				   
				}
			}
		});
				scene.widthProperty().addListener(new ChangeListener<Number>() {
				    @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
				        vbox.setMinWidth((double) newSceneWidth);
						hbox.setMinWidth((double) newSceneWidth);
						hbox_c.setSpacing((double)(newSceneWidth)/25);
						hbox_r.setSpacing((double)(newSceneWidth)/30);
						hbox_cr.setSpacing((double)(newSceneWidth)/3.5);
						hbox.setSpacing((double)(newSceneWidth)/4.5);
						
						
						if((double)newSceneWidth<480)
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
	        	if(scene.getWidth()>480)
	        	{
	              vbox.setVisible(true);
	              hbox.setVisible(true);
	        	}
	        }
	    });
		
		
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
		
		volumeslider.valueProperty().addListener(new InvalidationListener()
				{

					@Override
					public void invalidated(Observable arg0) {
						// TODO Auto-generated method stub
						mediaplayer.setVolume(volumeslider.getValue()/100);
						if(volumeslider.getValue()>0)
						{
							mediaplayer.setMute(false);
							button5.setTooltip(new Tooltip("Mute"));
							button5.setGraphic(new ImageView(img4));
						}
						if(volumeslider.getValue()==0)
						{   
							mediaplayer.setMute(true);
							button5.setTooltip(new Tooltip("Volume"));
							button5.setGraphic(new ImageView(img5));
						}
					}});
		
	}

}