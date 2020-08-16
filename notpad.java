import javafx.application.*;
import javafx.scene.*;
import javafx.scene.input.*;

import javafx.stage.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.io.*;
import java.io.File;

import javafx.scene.image.*;

public class notpad extends Application{
	File opend;
	double zoom=1;
	
	public static void main(String[] args){
		launch(args);

	}
	public void start(Stage mstg){
		mstg.setTitle("notepad");
		BorderPane bp=new BorderPane();
		Scene sc=new Scene(bp,600,600);
		mstg.setScene(sc);
		MenuBar mb=new MenuBar();
               
		mstg.getIcons().add(new Image("note.png"));
		Menu file=new Menu("File");
              
		MenuItem opn=new MenuItem("Open");
		MenuItem save=new MenuItem("Save");
		MenuItem save_as=new MenuItem("Save as");
		MenuItem exit=new MenuItem("Exit");
		save.setAccelerator(KeyCombination.keyCombination("CTRL+S"));
		save_as.setAccelerator(KeyCombination.keyCombination("CTRL+SHIFT+S"));
		opn.setAccelerator(KeyCombination.keyCombination("CTRL+O"));
		exit.setAccelerator(KeyCombination.keyCombination("CTRL+E"));
	    Menu edit=new Menu("View");
	    MenuItem zoom_in=new MenuItem("zoom in");
	    MenuItem zoom_out=new MenuItem("zoom out");
	    zoom_in.setAccelerator(KeyCombination.keyCombination("CTRL+P"));
	    zoom_out.setAccelerator(KeyCombination.keyCombination("CTRL+M"));
	    edit.getItems().addAll(zoom_in,zoom_out);
		file. getItems().addAll(opn,save,save_as,exit);
		Menu hp=new Menu("Help");
		mb.getMenus().addAll(file,edit,hp);
		bp.setTop(mb);
		TextArea txt=new TextArea();
                txt.setStyle("-fx-background:green");
	   
	    txt.setStyle("-fx-font-weight:normal");
	    
	    bp.setCenter(txt);
	    mstg.show();
	    zoom_out.setOnAction((ae)-> {
	    	zoom=zoom-0.1;
	    	txt.setStyle("-fx-font-size:"+zoom+"em");
	     });
	    zoom_in.setOnAction((ae)-> {
	    	zoom=zoom+0.1;
	    	txt.setStyle("-fx-font-size:"+zoom+"em");
	     });
	    exit.setOnAction((ae)->System.exit(0));
	    save.setOnAction((ae)->{
	    	if(opend!=null){
	    		try(FileOutputStream fout=new FileOutputStream(opend)){
	    			for(char a: txt.getText().toCharArray()){
	    				fout.write(a);
	    			}
	    		}catch(Exception ex){

	    		}
	    	}
	    });
	    save_as.setOnAction((ae)->{
	    	FileChooser flc=new FileChooser();
	    	flc.setTitle("save file");
	    	File fl=flc.showSaveDialog(mstg);
	    	if(fl!=null){
              try{
              	FileOutputStream fout=new FileOutputStream(fl);
              	for(char a : txt.getText().toCharArray()){
              		fout.write(a);
              	}
              }catch(Exception ex){
              	System.out.println("bekar hay tu");
              }
	    	}
	    });
	    opn.setOnAction((ae)->{
	    	FileChooser flc=new FileChooser();
	    	flc.setTitle("open file");
	    	opend=flc.showOpenDialog(mstg);
	    	if(opend!=null)
	    	{
	    		try{
	    		FileInputStream fin=new FileInputStream(opend);
	    		String str="";
	    		int size=fin.available();
	    		for(int i=0;i<size;i++){

	    			str+=(char)fin.read();
	    		}
	    		txt.setText(str);
	    	}catch(Exception ek){
	    			System.out.print("lag gaye");
	    		}
	    	}
	    });



	}
}
