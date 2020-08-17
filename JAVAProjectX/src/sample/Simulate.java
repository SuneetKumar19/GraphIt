package sample;
import com.sun.javafx.geom.AreaOp;
import com.sun.prism.shader.Solid_ImagePattern_AlphaTest_Loader;
import javafx.animation.*;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import com.sun.source.tree.Tree;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.util.Random;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.w3c.dom.*;
import javafx.scene.text.Text;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileWriter;
import java.lang.*;
import java.net.SocketTimeoutException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.*;
import javafx.scene.layout.GridPane;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
public class Simulate {
    Random rand=new Random();
    Map<ArrayList<Integer>,Integer> map = new HashMap<ArrayList<Integer>,Integer>();
//    ===========================Required For Optimisation==================================
//    But we cannot do since robot doesnot know its previous position
//    public int checkMap(ArrayList<Integer> a)
//    {
//        for(ArrayList<Integer>temp :map.keySet())
//        {
//            int flag=0;
//            for(int i=0;i<temp.size();i++)
//            {
//                if(temp.get(i)==(a.get(i)))
//                {
//
//                }
//                else
//                {
//                    flag=1;
//                    break;
//                }
//            }
//            if(flag==0)
//            {
//                map.replace(temp, map.get(temp)+1);
//                return map.get(temp);
//            }
//        }
//        map.put(a,1);
//        return 1;
//    }
    public void simulation( Agent a,AnchorPane g,int destx,int desty,int [][]grid)
    {
        Rectangle rec=a.get_rect();
        Timeline tl=new Timeline(new KeyFrame(Duration.millis(50),
                event->{
                        int x=a.get_x();
                         int y=a.get_y();

                         if(x==destx&&y==desty)
                         {
                             a.get_rect().setFill(Color.BLUE);
                         }
                         else {
                             int For10PTimes=rand.nextInt(10);
                             if(For10PTimes!=0) {
                                 int org_dist = Math.abs(x - destx) + Math.abs(y - desty);
                                 //For 10percent of the times the point does not
                                 int tx1 = -1, ty1 = -1, tx2 = -1, tx3 = -1, tx4 = -1, ty2 = -1, ty4 = -1, ty3 = -1;
                                 if (x + 1 < 100) {
                                     tx1 = x + 1;
                                     ty1 = y;
                                 }
                                 if (x - 1 > 0) {
                                     tx2 = x - 1;
                                     ty2 = y;
                                 }
                                 if (y - 1 > 0) {
                                     tx3 = x;
                                     ty3 = y - 1;
                                 }
                                 if (y + 1 < 100) {
                                     tx4 = x;
                                     ty4 = y + 1;
                                 }

//                                 ArrayList<Integer> arr1 = new ArrayList<Integer>();
//                                 ArrayList<Integer> arr2 = new ArrayList<Integer>();
//                                 ArrayList<Integer> arr3 = new ArrayList<Integer>();
//                                 ArrayList<Integer> arr4 = new ArrayList<Integer>();
//                                 arr1.add(x);
//                                 arr1.add(y);
//                                 arr1.add(tx1);
//                                 arr1.add(ty1);
//
//                                 arr2.add(x);
//                                 arr2.add(y);
//                                 arr2.add(tx2);
//                                 arr2.add(ty2);
//
//                                 arr3.add(x);
//                                 arr3.add(y);
//                                 arr3.add(tx3);
//                                 arr3.add(ty3);
//
//                                 arr4.add(x);
//                                 arr4.add(y);
//                                 arr4.add(tx4);
//                                 arr4.add(ty4);
                                 if (a.if_down(grid, destx, desty) && a.if_left(grid, destx, desty)) {
                                     int rn = rand.nextInt(2);
                                     if (rn == 0) {
                                         a.move_down(g,grid);
                                     } else if (rn == 1) {
                                         a.move_left(g,grid);
                                     }
                                 } else if (a.if_left(grid, destx, desty) && a.if_up(grid, destx, desty)) {
                                     int rn = rand.nextInt(2);
                                     if (rn == 0) {
                                         a.move_up(g,grid);
                                     } else if (rn == 1) {
                                         a.move_left(g,grid);
                                     }

                                 } else if (a.if_right(grid, destx, desty) && a.if_down(grid, destx, desty)) {
                                     int rn = rand.nextInt(2);
                                     if (rn == 0) {
                                         a.move_down(g,grid);
                                     } else if (rn == 1) {
                                         a.move_right(g,grid);
                                     }

                                 } else if (a.if_right(grid, destx, desty) && a.if_up(grid, destx, desty)) {
                                     int rn = rand.nextInt(2);
                                     if (rn == 0) {
                                         a.move_up(g,grid);
                                     } else if (rn == 1) {
                                         a.move_right(g,grid);
                                     }

                                 }
                                 else
                                 {
                                 if (a.if_down(grid, destx, desty))//&&checkMap(arr4)<=5)
                                 {
                                     a.move_down(g,grid);
                                 } else if (a.if_left(grid, destx, desty))//&&checkMap(arr2)<=5)
                                 {
                                     a.move_left(g,grid);
                                 } else if (a.if_up(grid, destx, desty))//&&checkMap(arr3)<=5)
                                 {
                                     a.move_up(g,grid);
                                 } else if (a.if_right(grid, destx, desty))//&&checkMap(arr1)<=5)
                                 {
                                     a.move_right(g,grid);
                                 } else {
                                     int rnum = rand.nextInt(4);
                                     if (tx1 != -1 && grid[tx1][ty1] == 0 && rnum == 0)//&&(checkMap(arr1)<=15)) //&& map.get(check1) == null)//moving right
                                     {
                                         a.move_right(g,grid);
                                     } else if (tx2 != -1 && grid[tx2][ty2] == 0 && rnum == 1)//&&(checkMap(arr2)<=15)) //&& map.get(check2) == null)//moving left
                                     {
                                         a.move_left(g,grid);
                                     } else if (tx3 != -1 && grid[tx3][ty3] == 0 && rnum == 2) //&&(checkMap(arr3)<=15))//&& map.get(check3) == null)//moving up
                                     {
                                         a.move_up(g,grid);
                                     } else if (tx4 != -1 && grid[tx4][ty4] == 0 && rnum == 3)//&&(checkMap(arr4)<=15))//&& map.get(check4) == null)//moving down
                                     {
                                         a.move_down(g,grid);
                                     }
                                 }
                             }
                             }
                         }
                }));
        tl.setCycleCount(Timeline.INDEFINITE);
        tl.play();
        if(a.get_x()==destx&&a.get_y()==desty)
        {
            System.out.println("OUT");
            tl.pause();
        }
    }

}
