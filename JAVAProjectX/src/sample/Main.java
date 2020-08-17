package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        int [][]grid=new int[100][100];
        AnchorPane g=new AnchorPane();
        g.setStyle(" -fx-background-color: #2f4f4f;");
        Scene scene=new Scene(g,1000,1000);
        primaryStage.setScene(scene);
        primaryStage.show();
        g.onTouchMovedProperty();
        Random rand=new Random();
        int n=rand.nextInt(100);
//        n=49;
//        n=1;
        for(int i=0;i<100;i++)
        {
            for(int j=0;j<100;j++)
            {
                grid[i][j]=0;
            }
        }
        int obstacles=rand.nextInt(100);
        int count_obs=0;
//        obstacles=0;
        while(count_obs!=obstacles)
        {
            int x,y;
            x=rand.nextInt(100);
            y=rand.nextInt(100);
//            x=1;y=1;
            if(grid[x][y]==0)
            {
                grid[x][y]=2;
                Agent obs=new Agent(x,y);
                obs.make_rect(g,1);
                count_obs++;
            }
        }

        int destx=rand.nextInt(100),desty=rand.nextInt(100);
//        destx=0;desty=1;
        while((grid[destx][desty]!=0)) {
            destx = rand.nextInt(100);
            desty = rand.nextInt(100);
        }
        Agent des=new Agent(destx,desty);
        des.make_rect(g,3);
        System.out.println("->"+destx);
        System.out.println("->"+desty);

        int count=0;
        while(true)
        {
            int x=rand.nextInt(100);
            int y=rand.nextInt(100);
            if(grid[x][y]==0)
            {
                grid[x][y]=1;
                count++;
                if(count==n)
                {
                    break;
                }
            }
        }
        for(int i=0;i<100;i++)
        {
            for(int j=0;j<100;j++)
            {
                if(grid[i][j]==1)
                {
                    Agent a = new Agent(i, j);
                a.make_rect(g,2);
                Simulate s = new Simulate();
                s.simulation(a, g, destx, desty,grid);
                }
            }
        }
    }
    public static void main(String[] args) {
            launch(args);
    }
}
