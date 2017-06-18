import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.sun.javafx.iio.ImageLoader;
import com.sun.prism.paint.Color;

import javafx.application.*;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Media_Player extends Application{

	public static void main(String args[])
	{
		launch(args);
	}


	@Override
	public void start(Stage args) throws IOException {
		Group root =new Group();
		Button button1=new Button();
		Button button2=new Button();
	    Button button3 =new Button();
	    Button button4 =new Button();
	    Button button5 =new Button();
	    Button button6 =new Button();
	    Button button7 =new Button();
		
		Media media=new Media("file:///c:/videos/Befikra.mp4");
		MediaPlayer mediaplayer =new MediaPlayer(media);
		MediaView view =new MediaView(mediaplayer);
		
		final DoubleProperty width = view.fitWidthProperty();
	    final DoubleProperty height = view.fitHeightProperty();
	    
	    width.bind(Bindings.selectDouble(view.sceneProperty(), "width"));
	    height.bind(Bindings.selectDouble(view.sceneProperty(), "height"));
	    
	    view.setPreserveRatio(true);
		
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
		
		Slider slider =new Slider();
		Slider volumeslider =new Slider();
		VBox vbox=new VBox();
		vbox.getChildren().add(slider);
		
		root.getChildren().add(view);
		root.getChildren().add(vbox);
		root.getChildren().addAll(button1,button2,button3,button4,button5,button7);
		
		Scene scene=new Scene(root);
		args.setScene(scene);
		args.show();
		
		
		
		
		slider.setMin(0.0);
		slider.setValue(0.0);
		slider.setMax(mediaplayer.getTotalDuration().toSeconds());
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
	
		mediaplayer.setAutoPlay(true);
		
		button1.setOnMouseClicked(new EventHandler<MouseEvent>()                  // Play button
		{

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				mediaplayer.play();
				
			}});
		
		button2.setOnMouseClicked(new EventHandler<MouseEvent>()                   //Pause Button
		{

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				mediaplayer.pause();
			}});

// Video rate control
		button3.setOnMouseClicked(new EventHandler<MouseEvent>()                    // Fast forward by 2x,4x,8x 
		{
			
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(mediaplayer.getRate()+1 <=8 )
				{
				mediaplayer.setRate(mediaplayer.getRate()*2);
				
				}
			}});
		
		button7.setOnMouseClicked(new EventHandler<MouseEvent>()                    // reduce rate by 2x on every click
				{
					
					@Override
					public void handle(MouseEvent arg0) {
						// TODO Auto-generated method stub
						if(mediaplayer.getRate()>=0.25 )
						{
						mediaplayer.setRate(mediaplayer.getRate()/2);
						}
					}});
		
		button4.setOnMouseClicked(new EventHandler<MouseEvent>()                   // Enter into full screen
		{
            
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(args.isFullScreen()==false)
				{
				args.setFullScreen(true);
				int h=mediaplayer.getMedia().getHeight();
				int w=mediaplayer.getMedia().getWidth();
				
				args.setMinWidth(w);
				args.setMinHeight(h);
				vbox.setMinWidth(w+700);
				vbox.setTranslateY(h+320);
				button1.setTranslateX(w-635);
				button1.setTranslateY(h+350);
				button2.setTranslateY(h+350);
				button2.setTranslateX(w-585);
				button3.setTranslateY(h+350);
				button3.setTranslateX(w-535);
				button4.setTranslateY(h+350);
				button4.setTranslateX(w+680);
				button5.setTranslateY(h+350);
				button5.setTranslateX(w-435);
				button7.setTranslateY(h+350);
				button7.setTranslateX(w-485);
				}
				else
				{
			    args.setFullScreen(false);
			    int h=mediaplayer.getMedia().getHeight();
				int w=mediaplayer.getMedia().getWidth();
				
				args.setMinWidth(w);
				args.setMinHeight(h);
				
				vbox.setMinWidth(w);
				vbox.setTranslateY(h-90);
				button1.setTranslateX(w-635);
				button1.setTranslateY(h-72);
				button2.setTranslateY(h-72);
				button2.setTranslateX(w-585);
				button3.setTranslateY(h-72);
				button3.setTranslateX(w-535);
				button4.setTranslateY(h-72);
				button4.setTranslateX(w-45);
				button5.setTranslateY(h-72);
				button5.setTranslateX(w-435);
				button7.setTranslateY(h-72);
				button7.setTranslateX(w-485);
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
				int h=mediaplayer.getMedia().getHeight();
				int w=mediaplayer.getMedia().getWidth();
				
				args.setMinWidth(w);
				args.setMinHeight(h);
				vbox.setMinWidth(w+700);
				vbox.setTranslateY(h+320);
				button1.setTranslateX(w-635);
				button1.setTranslateY(h+350);
				button2.setTranslateY(h+350);
				button2.setTranslateX(w-585);
				button3.setTranslateY(h+350);
				button3.setTranslateX(w-535);
				button4.setTranslateY(h+350);
				button4.setTranslateX(w+680);
				button5.setTranslateY(h+350);
				button5.setTranslateX(w-435);
				button7.setTranslateY(h+350);
				button7.setTranslateX(w-485);
				}
				else
				{
			    args.setFullScreen(false);
			    int h=mediaplayer.getMedia().getHeight();
				int w=mediaplayer.getMedia().getWidth();
				
				args.setMinWidth(w);
				args.setMinHeight(h);
				
				vbox.setMinWidth(w);
				vbox.setTranslateY(h-90);
				button1.setTranslateX(w-635);
				button1.setTranslateY(h-72);
				button2.setTranslateY(h-72);
				button2.setTranslateX(w-585);
				button3.setTranslateY(h-72);
				button3.setTranslateX(w-535);
				button4.setTranslateY(h-72);
				button4.setTranslateX(w-45);
				button5.setTranslateY(h-72);
				button5.setTranslateX(w-435);
				button7.setTranslateY(h-72);
				button7.setTranslateX(w-485);
			    
			    
				}
		    	}
		    }
		});
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>()                 // Play and Pause on keyboard space bar key
	    {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
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
				
			}
	        
	    });
		
		
	button5.setOnMouseMoved(new EventHandler<MouseEvent>()                           // Volume button hover over
				{

					@Override
					public void handle(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
						int h=mediaplayer.getMedia().getHeight();
						int w=mediaplayer.getMedia().getWidth();
						
						args.setMinWidth(w);
						args.setMinHeight(h);
						root.getChildren().add(volumeslider);
						
						volumeslider.setTranslateY(h-67);
						volumeslider.setTranslateX(w-452);
					
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
					//System.out.println(root.isVisible());
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
				int h=mediaplayer.getMedia().getHeight();
				int w=mediaplayer.getMedia().getWidth();
				
				args.setMinWidth(w);
				args.setMinHeight(h);
				
				vbox.setMinWidth(w);
				vbox.setTranslateY(h-90);
				button1.setTranslateX(w-635);
				button1.setTranslateY(h-72);
				button2.setTranslateY(h-72);
				button2.setTranslateX(w-585);
				button3.setTranslateY(h-72);
				button3.setTranslateX(w-535);
				button4.setTranslateY(h-72);
				button4.setTranslateX(w-45);
				button5.setTranslateY(h-72);
				button5.setTranslateX(w-435);
				button7.setTranslateY(h-72);
				button7.setTranslateX(w-485);
				
			}
		});
		
		slider.setOnMouseClicked(new EventHandler<MouseEvent>()
		{

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				slider.setValue(slider.getValue()+20);
				mediaplayer.seek(Duration.seconds(slider.getValue()));
			}
			
		});
	}
}