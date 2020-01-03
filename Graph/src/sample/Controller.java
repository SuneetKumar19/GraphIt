package sample;

import com.sun.javafx.geom.AreaOp;
import com.sun.prism.shader.Solid_ImagePattern_AlphaTest_Loader;
import javafx.animation.PathTransition;
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
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;

class Vertex
{
    String name;
    Double xcoordinate;
    Double ycoordinate;

    Vertex(String name,Double xcoordinate,Double ycoordinate)
    {
        this.name = name;
        this.xcoordinate = xcoordinate;
        this.ycoordinate = ycoordinate;
    }
}

class SampleCircle
{
    int xcrd,ycrd,radius;
    SampleCircle(int xcrd,int ycrd,int radius)
    {
        this.xcrd=xcrd;
        this.ycrd=ycrd;
        this.radius=radius;
    }
    Circle circle = new Circle(10*xcrd,10*ycrd,10);
}
class SampleTriangle
{
    int x1,x2,x3,y1,y2,y3;
    SampleTriangle(int x1,int x2,int x3,int y1,int y2,int y3)
    {
        this.x1=x1;
        this.x2=x2;
        this.x3=x3;
        this.y1=y1;
        this.y2=y2;
        this.y3=y3;
    }
    Polygon triangle = new Polygon();
}
class SampleSquare
{
    int top_leftx,top_lefty;
    SampleSquare(int xcrd,int ycrd,int radius)
    {
        this.top_leftx=top_leftx;
        this.top_lefty=top_lefty;
    }
    Rectangle rec = new Rectangle(10*top_leftx,10*top_lefty,30,30);
}

public class Controller
{
    ArrayList<Vertex>vertices=new ArrayList<Vertex>();
    int counter=0,toggle_count=0,onc=0;
    int [][] weight=new int[1003][1003];
    int []sun=new int [1003];

    ArrayList<Integer>[] graph = new ArrayList[1000+3];
    Map<String,Integer> map = new HashMap<String,Integer>();
    Map<Integer,String> map1 = new HashMap<Integer,String>();

    @FXML
    Button b,b2,b11,bx,button_modify,medge;
    @FXML
    TextField tf,tf1,tf2,tf3,tf4,tf5,findVertex,deleteName;
    @FXML
    TextField  addx,dirpath,edirpath;
    @FXML
    TextField addy;
    @FXML
    TextField vto;
    @FXML
    TextField vfrom;
    @FXML
    TextField vweight,dfrom,dto;
    @FXML
    TextField modifyname;
    @FXML
    TextField modifyx;
    @FXML
    TextField modifyy,nfrom,nto;
    @FXML
    ToggleButton toggle;
    @FXML
    TextArea output;
    @FXML
    TextField presentname;
    @FXML
    TextField  addname;
    @FXML
    TextField  findFrom;
    @FXML
    TextField  findTo;
    @FXML
    TextField  fDel;
    @FXML
    TextField  tDel;
    @FXML
    TextField  nweight,trans;
    @FXML
    AnchorPane ap;
    double orgx,orgy,orgtx,orgty;
    public void addVertex(ActionEvent E)
    {
        if(counter==0)
        {
            for(int i=0;i<=200;i++)
            {
                for(int j=0;j<=200;j++)
                {
                    weight[i][j]=1000000;
                }
            }
        }
        counter++;

        try {
            String ss = addname.getText();
            String ss1 = addx.getText();
            String ss2 = addy.getText();

//        name + xcoordinate + ycoordinate

            double xc = Double.parseDouble(ss1);
            double yc = Double.parseDouble(ss2);

            String mapString = ss + ss1 + ss2;

            int flag1=0;

            for(int i=0;i<vertices.size();i++)
            {
                if(vertices.get(i).name.equals(ss))
                {
                    flag1=1;
                    break;
                }
            }
            if (flag1==1)
            {
                output.setText("Vertex Already Present");
                throw new Exception();
            }
            else {
                Circle c1 = new Circle(10 * xc, 10 * yc, 10);
                ap.getChildren().addAll(c1);
                c1.setFill(Color.GREENYELLOW);

//=====================================================================================================================================
                Text t= new Text();
                t.setX(10*xc);
                t.setY(10*yc);
                t.setText(ss);
                t.setStroke(Color.WHITE);
                ap.getChildren().add(t);
                t.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR,30));

                map1.put(counter, ss);
                map.put(mapString, counter);
                vertices.add(new Vertex(ss, xc, yc));
                output.setText("Vertex Added");
            }
            addname.setText("");
            addx.setText("");
            addy.setText("");
        }
        catch(Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR OCCURED!!");
            alert.setHeaderText("Invalid Parameters");
            alert.setContentText("Please Enter Correct Details");
            alert.showAndWait().ifPresent(rs->{
                    if(rs==ButtonType.OK)
                    {
                        addname.setText("");
                        addx.setText("");
                        addy.setText("");
                        output.setText("");
                        System.out.println("Error!! from catch Block");
                    }
            });
        }
    }

    public  void FindVertex(ActionEvent E)
    {
        try {


            String toFind = findVertex.getText();
            if(toFind.length()==0)
            {
                throw new Exception();
            }
            int flag = 0;
            for (int i = 0; i < vertices.size(); i++) {
                if (vertices.get(i).name.equals(toFind)) {
                    flag = 1;
                    String finalOutput;
                    finalOutput = vertices.get(i).name + "  " + Double.toString(vertices.get(i).xcoordinate) + "  " +  Double.toString(vertices.get(i).ycoordinate);
                    output.setText(finalOutput);
                    break;
                }
            }
            if (flag == 0) {
                output.setText("Vertex Not Present");
            }
            findVertex.setText("");
        }
        catch(Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR OCCURED!!");
            alert.setHeaderText("Invalid Parameters");
            alert.setContentText("Please Enter Correct Details");
            alert.showAndWait().ifPresent(rs->{
                if(rs==ButtonType.OK)
                {
                    output.setText("");
                    vfrom.setText("");
                    vto.setText("");
                    System.out.println("Error!! from catch Block");
                }
            });
        }
    }

    public void deleteVertex(ActionEvent E)
    {
        try {
            String deletename = deleteName.getText();
            if(deletename.length()==0)
            {
                throw new Exception();
            }
            int flag = 0;
            for (int i = 0; i < vertices.size(); i++) {
                if (vertices.get(i).name.equals(deletename)) {
                    String to_del = vertices.get(i).name + vertices.get(i).xcoordinate + vertices.get(i).ycoordinate;
                    int val = map.get(to_del);
                    if(graph[val]!=null)
                    graph[val].clear();
                    else
                    {
                        graph[val]=new ArrayList<Integer>();
                    }
                    for (int j = 0; j < 200; j++) {
                        if (graph[j] == null) {
                            graph[j] = new ArrayList<Integer>();
                        }

                        if (graph[j].size() > 0) {
                            for (int k = 0; k < graph[j].size(); k++) {
                                if (graph[j].get(k) == val) {
                                    graph[j].remove(k);
                                }
                            }
                        }
                    }
                    output.setText("Vertex " + vertices.get(i).name + " has been deleted");
                    flag = 1;
                    String temp;
                    temp = vertices.get(i).name +  Double.toString(vertices.get(i).xcoordinate) +  Double.toString(vertices.get(i).ycoordinate);
                    map.remove(temp);
                    vertices.remove(i);
                    break;
                }
            }
            if (flag == 0) {
                output.setText("This vertex is not present!!");
            }
            rall();
            deleteName.setText("");
        }
        catch(Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR OCCURED!!");
            alert.setHeaderText("Invalid Parameters");
            alert.setContentText("Please Enter Correct Details");
            alert.showAndWait().ifPresent(rs->{
                if(rs==ButtonType.OK)
                {
                    output.setText("");
                    presentname.setText("");
                    modifyname.setText("");
                    modifyx.setText("");
                    modifyy.setText("");
                    System.out.println("Error!! from catch Block");
                }
            });
        }
    }

    public void modifyDetails(ActionEvent E)
    {
        try {

            String present = presentname.getText();
            String nname = modifyname.getText();
            double nx, ny;
            nx =  Double.parseDouble(modifyx.getText());
            ny =  Double.parseDouble(modifyy.getText());

            int flag = 0;
            for (int i = 0; i < vertices.size(); i++) {
                if (vertices.get(i).name.equals(present)) {
                    flag = 1;
                    String temp, temp2;

                    temp = vertices.get(i).name +  Double.toString(vertices.get(i).xcoordinate) +  Double.toString(vertices.get(i).ycoordinate);

                    int prev_counter = map.get(temp);

                    map.remove(temp);
                    vertices.get(i).name = nname;
                    vertices.get(i).xcoordinate = nx;
                    vertices.get(i).ycoordinate = ny;

                    temp2 = vertices.get(i).name +  Double.toString(vertices.get(i).xcoordinate) +  Double.toString(vertices.get(i).ycoordinate);
                    map.put(temp2, prev_counter);
                    break;
                }
            }
            if (flag == 0) {
                output.setText("Vertex not Found!!");
            } else {
                output.setText("Vertex modified!!");
            }

            presentname.setText("");
            modifyname.setText("");
            modifyx.setText("");
            modifyy.setText("");
            rall();
        }
        catch(Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR OCCURED!!");
            alert.setHeaderText("Invalid Parameters");
            alert.setContentText("Please Enter Correct Details");
            alert.showAndWait().ifPresent(rs->{
                if(rs==ButtonType.OK)
                {
                    output.setText("");
                    presentname.setText("");
                    modifyname.setText("");
                    modifyx.setText("");
                    modifyy.setText("");
                    System.out.println("Error!! from catch Block");
                }
            });
        }
    }

    public void addEdge(ActionEvent E)
    {
        try {
            double xc1=0,yc1=0,xc2=0,yc2=0;
            String from = vfrom.getText();
            String to = vto.getText();
            if(from.length()==0||to.length()==0||vweight.getText().length()==0)
            {
                throw new Exception();
            }
            int flag = 0, flag1 = 0;
            String tempf = "", tempt = "";

            for (int i = 0; i < vertices.size(); i++) {
                if (vertices.get(i).name.equals(from)) {
                    xc1=vertices.get(i).xcoordinate;
                    yc1=vertices.get(i).ycoordinate;
                    tempf = vertices.get(i).name +Double.toString(vertices.get(i).xcoordinate) + Double.toString(vertices.get(i).ycoordinate);
                    flag = 1;
                }

                if (vertices.get(i).name.equals(to)) {
                    xc2=vertices.get(i).xcoordinate;
                    yc2=vertices.get(i).ycoordinate;
                    tempt = vertices.get(i).name +Double.toString(vertices.get(i).xcoordinate) + Double.toString(vertices.get(i).ycoordinate);
                    flag1 = 1;
                }
            }
            if (flag == 1 && flag1 == 1) {
                Line l = new Line();
                l.setStartX((double)(10*xc1));
                l.setStartY((double)(10*yc1));
                l.setEndX((double)(10*xc2));
                l.setEndY((double)(10*yc2));

                double xx1=(10*xc1+10*xc2)/2;
                double yy1=(10*yc1+10*yc2)/2;

                Text t= new Text();
                t.setX(xx1);
                t.setY(yy1);
                t.setText(vweight.getText());
                t.setStroke(Color.RED);
                t.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR,28));
                ap.getChildren().add(t);
                ap.getChildren().add(l);
                l.setStroke(Color.WHITE);
                l.setStrokeWidth(3);

//                NO OTHER CHANGE

                l.setOnMousePressed(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if(mouseEvent.isControlDown()&&toggle_count%2==1)
                        {
                            System.out.println("HERE!!!!!");
                                fDel.setText(from);
                                tDel.setText(to);
                                bx.fire();
                        }
                        if(mouseEvent.isShiftDown()&&mouseEvent.isAltDown()&&toggle_count%2==1)
                        {
                            String sss;
                            TextInputDialog td = new TextInputDialog();
                            td.showAndWait();
                            sss = td.getEditor().getText();
                            nfrom.setText(from);
                            nto.setText(map1.get(to));
                            nweight.setText(sss);
                            medge.fire();
                        }
                    }
                });

                int weightx = Integer.parseInt(vweight.getText());
                int xfrom = map.get(tempf);
                int xto = map.get(tempt);
                output.setText(Integer.toString(xto) + " " + Integer.toString(xfrom));
                if (graph[xfrom] == null) {
                    graph[xfrom] = new ArrayList<Integer>();
                }
                if (graph[xto] == null) {
                    graph[xto] = new ArrayList<Integer>();
                }
                graph[xfrom].add(xto);
                graph[xto].add(xfrom);
//                weight[xfrom][xto] = Math.min(weightx, weight[xfrom][xto]);
//                weight[xto][xfrom] = Math.min(weightx, weight[xto][xfrom]);
            weight[xfrom][xto]=weightx;
            weight[xto][xfrom]=weightx;
                output.setText("Edge has been added");
            } else {
                output.setText("Required Vertices not present");
            }
            vfrom.setText("");
            vto.setText("");
            vweight.setText("");
            rall();
        }
        catch(Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(" add Edge ERROR OCCURED!!");
            alert.setHeaderText("Invalid Parameters");
            alert.setContentText("Please Enter Correct Details");
            alert.showAndWait().ifPresent(rs->{
                if(rs==ButtonType.OK)
                {
                    output.setText("");
                    vfrom.setText("");
                    vto.setText("");
                    System.out.println("Error!! from add Edge catch Block");
                }
            });
        }
    }
    public void searchEdge(ActionEvent E) {
        try {


            int flag = 0;
            String ffrom = findFrom.getText();
            String fto = findTo.getText();
            if(ffrom.length()==0||fto.length()==0)
            {
                throw new Exception();
            }

            int ff = -1, ft = -1;
            for (int i = 0; i < vertices.size(); i++) {
                if (vertices.get(i).name.equals(ffrom)) {
                    ff = map.get(ffrom + Double.toString(vertices.get(i).xcoordinate) + Double.toString(vertices.get(i).ycoordinate));
                }

                if (vertices.get(i).name.equals(fto)) {
                    ft = map.get(fto + Double.toString(vertices.get(i).xcoordinate) + Double.toString(vertices.get(i).ycoordinate));
                }
            }
            int flags = 0;
            if (ff != -1 && ft != -1) {
                for (int i = 0; i < graph[ff].size(); i++) {
                    if (graph[ff].get(i) == ft) {
                        flags = 1;
                        break;
                    }
                }
                if (flags == 1)
                    output.setText("YES!! Edge Exists and its weight is " + Integer.toString(weight[ff][ft]));
                else {
                    output.setText("No Such Edge Exists");
                }
            } else {
                output.setText("No Such Edge Exists");
            }
            findFrom.setText("");
            findTo.setText("");
        }
        catch(Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR OCCURED!!");
            alert.setHeaderText("Invalid Parameters");
            alert.setContentText("Please Enter Correct Details");
            alert.showAndWait().ifPresent(rs->{
                if(rs==ButtonType.OK)
                {
                    output.setText("");
                    vfrom.setText("");
                    vto.setText("");
                    System.out.println("Error!! from catch Block");
                }
            });
        }
    }

    public void DeleteEdge(ActionEvent E)
    {
        try {
            String fdel = fDel.getText();
            String tdel = tDel.getText();
            int ff = -1, ft = -1;
            for (int i = 0; i < vertices.size(); i++) {
                if (vertices.get(i).name.equals(fdel)) {
                    ff = map.get(fdel + Double.toString(vertices.get(i).xcoordinate) + Double.toString(vertices.get(i).ycoordinate));
                }

                if (vertices.get(i).name.equals(tdel)) {
                    ft = map.get(tdel + Double.toString(vertices.get(i).xcoordinate) + Double.toString(vertices.get(i).ycoordinate));
                }
            }
            int flag = 0, flag1 = 0;
            for (int i = 0; i < graph[ft].size(); i++) {
                if (graph[ft].get(i) == ff) {
                    flag = 1;
                    graph[ft].remove(i);
                    break;
                }
            }
            for (int i = 0; i < graph[ff].size(); i++) {
                if (graph[ff].get(i) == ft) {
                    flag1 = 1;
                    graph[ff].remove(i);
                    break;
                }
            }
            if (flag == 1 && flag1 == 1) {
                output.setText("Edge Deleted!!");
            } else {
                output.setText("No Edge Found!!");
            }
            fDel.setText("");
            tDel.setText("");
            rall();
        }
        catch(Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("deletion ERROR OCCURED!!");
            alert.setHeaderText("Invalid Parameters");
            alert.setContentText("Please Enter Correct Details");
            alert.showAndWait().ifPresent(rs->{
                if(rs==ButtonType.OK)
                {
                    fDel.setText("");
                    tDel.setText("");
                    output.setText("");
                    nfrom.setText("");
                    nto.setText("");
                    System.out.println("Error!! from catch Block");
                }
            });
        }

    }

    public void modifyEdge(ActionEvent E)
    {
        try {

            String nf = nfrom.getText();
            String nt = nto.getText();
            int nwe = Integer.parseInt(nweight.getText());

            int ff = -1, ft = -1;
            for (int i = 0; i < vertices.size(); i++) {
                if (vertices.get(i).name.equals(nf)) {
                    ff = map.get(nf + Double.toString(vertices.get(i).xcoordinate) + Double.toString(vertices.get(i).ycoordinate));
                }

                if (vertices.get(i).name.equals(nt)) {
                    ft = map.get(nt + Double.toString(vertices.get(i).xcoordinate) + Double.toString(vertices.get(i).ycoordinate));
                }
            }
            if (ff != -1 && ft != -1) {
                int flag = 0;
                for (int i = 0; i < graph[ff].size(); i++) {
                    if (graph[ff].get(i) == ft) {
                        flag = 1;
                        weight[ff][ft] = nwe;
                        weight[ft][ff] = nwe;
                        break;
                    }
                }
                if (flag == 1) {
                    output.setText("Modified!!");
                } else {
                    output.setText("No Edge Found!!");
                }
            } else {
                output.setText("No Edge Found!!");
            }
            nfrom.setText("");
            nto.setText("");
            nweight.setText("");
        }
        catch(Exception e)
        {
            nfrom.setText("");
            nto.setText("");
            nweight.setText("");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR OCCURED!!");
            alert.setHeaderText("Invalid Parameters");
            alert.setContentText("Please Enter Correct Details");
            alert.showAndWait().ifPresent(rs->{
                if(rs==ButtonType.OK)
                {
                    output.setText("");
                    nfrom.setText("");
                    nto.setText("");
                    System.out.println("Error!! from catch Block");
                }
            });
        }
        rall();
    }

    public void djkistraPath(ActionEvent E) {
        try {

//            rall();
            String djt = dto.getText();
            String djf = dfrom.getText();
            if(djf.length()==0||djt.length()==0)
            {
                throw new Exception();
            }
            int ff = -1, ft = -1;
            for (int i = 0; i < vertices.size(); i++) {
                if (vertices.get(i).name.equals(djf)) {
                    ff = map.get(djf + Double.toString(vertices.get(i).xcoordinate) +Double.toString(vertices.get(i).ycoordinate));
                }

                if (vertices.get(i).name.equals(djt)) {
                    ft = map.get(djt + Double.toString(vertices.get(i).xcoordinate) + Double.toString(vertices.get(i).ycoordinate));
                }
            }
            if (ff != -1 && ft != -1) {
//            Implement djikstra here
                if (ff == ft) {
                    output.setText(djt);
                } else {
                    int[] mark = new int[200 + 3];
                    int[] dist = new int[200 + 3];
                    int[] sptset = new int[200 + 3];
                    int[] parent = new int[200 + 3];
                    int imax = 100000;
                    for (int i = 0; i < 201; i++) {
                        mark[i] = -1;
                        parent[i] = -1;
                        dist[i] = imax;
                        sptset[i] = 0;
                    }

                    dist[ff] = 0;

                    for (int j = 0; j < vertices.size(); j++) {
                        int y = map.get(vertices.get(j).name + Double.toString(vertices.get(j).xcoordinate) + Double.toString(vertices.get(j).ycoordinate));
                        mark[y] = 1;
                    }
                    for (int j = 0; j < vertices.size(); j++) {
                        int cmin = imax, smallest_dist, mi = 0;
                        for (int i = 0; i <= 201; i++) {
                            if (mark[i] == 1) {
                                if (sptset[i] == 0 && dist[i] <= cmin) {
                                    cmin = dist[i];
                                    mi = i;
                                }
                            }
                        }
                        int u = mi;
                        sptset[u] = 1;
                        if (!(graph[u] == null)) {
                            for (int k = 0; k < graph[u].size(); k++) {
                                int v = graph[u].get(k);
                                int path_cost = weight[u][v];
                                if (sptset[v] == 0 && dist[u] != imax && dist[u] + path_cost < dist[v] && path_cost < imax) {
                                    parent[v] = u;
                                    dist[v] = dist[u] + path_cost;
                                }
                            }
                        }
                    }

                    ArrayList<Integer> printing = new ArrayList<Integer>();
//                ft -> destination
//                ff->parent
                    if (dist[ft] != imax) {
                        int ss = ft;
                        while (!(parent[ss] == -1)) {
                            printing.add(ss);
                            ss = parent[ss];
                        }
                        String s1 = "", s2 = "";
                        s1 = s1 + map1.get(ff);

                        double x1 = 0, x2 = 0, y1 = 0, y2 = 0;

                        String temp = map1.get(ff);

                        for (int i = 0; i < vertices.size(); i++) {
                            if (vertices.get(i).name.equals(temp)) {
                                x1 = vertices.get(i).xcoordinate;
                                y1 = vertices.get(i).ycoordinate;
                                break;
                            }
                        }

                        int cc = 0;
                        for (int p = printing.size() - 1; p >= 0; p--) {
                            if (cc == 0) {
                                String x = map1.get(printing.get(p));
                                for (int i = 0; i < vertices.size(); i++) {
                                    if (vertices.get(i).name.equals(x)) {
                                        x2 = vertices.get(i).xcoordinate;
                                        y2 = vertices.get(i).ycoordinate;
                                        break;
                                    }
                                }

                                Line l = new Line();
                                l.setStartX((double) (10 * x1));
                                l.setStartY((double) (10 * y1));
                                l.setEndX((double) (10 * x2));
                                l.setEndY((double) (10 * y2));

                                l.setStrokeWidth(3);
                                l.setStroke(Color.BLUE);

                                ap.getChildren().add(l);

                                x1 = x2;
                                y1 = y2;
                            } else {
                                String x = map1.get(printing.get(p));
                                for (int i = 0; i < vertices.size(); i++) {
                                    if (vertices.get(i).name.equals(x)) {
                                        x2 = vertices.get(i).xcoordinate;
                                        y2 = vertices.get(i).ycoordinate;
                                        break;
                                    }
                                }

                                Line l = new Line();
                                l.setStartX((double) (10 * x1));
                                l.setStartY((double) (10 * y1));
                                l.setEndX((double) (10 * x2));
                                l.setEndY((double) (10 * y2));

//                                l.setOnMousePressed(new EventHandler<MouseEvent>() {
//                                    @Override
//                                    public void handle(MouseEvent mouseEvent) {
//                                        if(mouseEvent.isAltDown())
//                                        {
//                                            System.out.println("HERE!!!!!");
//                                            fDel.setText(r1);
//                                            tDel.setText(r2);
//                                            bx.fire();
//                                        }
//                                    }
//                                });

                                l.setStrokeWidth(3);
                                l.setStroke(Color.BLUE);

                                ap.getChildren().add(l);

                                x1 = x2;
                                y1 = y2;
                            }

                            if (parent[printing.get(p)] != -1) {
                                s1 = s1 + "->" + map1.get(printing.get(p));
                            }

                            cc++;

                        }
                        Circle circle =new Circle();

//                            parent -> ff

                        double xs=0,ys=0;
                        String s=map1.get(ff);
                        for(int i=0;i<vertices.size();i++)
                        {
                            if(vertices.get(i).name.equals(s))
                            {
                                xs=vertices.get(i).xcoordinate;
                                ys=vertices.get(i).ycoordinate;
                                break;
                            }
                        }
//  ===----------------- For Transitions of a Circle ----------------------------------------------------------------
                        String str=trans.getText().toLowerCase();
                        if(str.equals("circle"))
                        {
                            circle.setCenterX((double) (10 * xs));
                            circle.setCenterY((double) (10 * ys));
                            circle.setFill(Color.WHITE);
                            circle.setRadius(25);
                            ap.getChildren().addAll(circle);
                            Path path = new Path();
                            MoveTo mt = new MoveTo(xs * 10, ys * 10);
                            path.getElements().add(mt);
                            for (int i = printing.size() - 1; i >= 0; i--) {
                                String t1 = map1.get(printing.get(i));
                                for (int jj = 0; jj < vertices.size(); jj++) {
                                    if (t1.equals(vertices.get(jj).name)) {
                                        double x11 = 0, y11 = 0;
                                        x11 = vertices.get(jj).xcoordinate * 10;
                                        y11 = vertices.get(jj).ycoordinate * 10;
                                        LineTo l = new LineTo(x11, y11);
                                        path.getElements().addAll(l);
                                        break;
                                    }
                                }
                            }
                            PathTransition pt = new PathTransition();
                            pt.setDuration(Duration.millis(3000));
                            pt.setPath(path);
                            pt.setNode(circle);
                            pt.setCycleCount(1500);
                            pt.setAutoReverse(false);
                            pt.play();
                        }

                        if(str.equals("square"))
                        {
                            Rectangle rec = new Rectangle(10*xs,10*ys,30,30);
                            ap.getChildren().addAll(rec);
                            rec.setFill(Color.PINK);
                            Path path = new Path();
                            MoveTo mt = new MoveTo(xs * 10, ys * 10);
                            path.getElements().add(mt);
                            for (int i = printing.size() - 1; i >= 0; i--) {
                                String t1 = map1.get(printing.get(i));
                                for (int jj = 0; jj < vertices.size(); jj++) {
                                    if (t1.equals(vertices.get(jj).name)) {
                                        double x11 = 0, y11 = 0;
                                        x11 = vertices.get(jj).xcoordinate * 10;
                                        y11 = vertices.get(jj).ycoordinate * 10;
                                        LineTo l = new LineTo(x11, y11);
                                        path.getElements().addAll(l);
                                        break;
                                    }
                                }
                            }
                            PathTransition pt = new PathTransition();
                            pt.setDuration(Duration.millis(3000));
                            pt.setPath(path);
                            pt.setNode(rec);
                            pt.setCycleCount(1500);
                            pt.setAutoReverse(false);
                            pt.play();

                        }

//                        Transitions for a cross=================
                        if(str.equals("cross"))
                        {
                            Line l = new Line();
                            l.setStartX((double) (10 * xs-10));
                            l.setStartY((double) (10 * ys-10));
                            l.setEndX((double) (10 * xs+10));
                            l.setEndY((double) (10 * ys+10));

                            Line l1 = new Line();
                            l1.setStartX((double) (10 * xs-10));
                            l1.setStartY((double) (10 * ys+10));
                            l1.setEndX((double) (10 * xs+10));
                            l1.setEndY((double) (10 * ys-10));

//                            triangle.getPoints().add( 20*xs);

//                            Rectangle rec = new Rectangle(20*xs,20*ys,30,30);
                            ap.getChildren().add(l);
                            ap.getChildren().add(l1);
//                            triangle.setFill(Color.WHITE);
                            Path path = new Path();
                            MoveTo mt = new MoveTo(xs * 10 - 5, ys * 10 - 5);
                            path.getElements().add(mt);
                            for (int i = printing.size() - 1; i >= 0; i--) {
                                String t1 = map1.get(printing.get(i));
                                for (int jj = 0; jj < vertices.size(); jj++) {
                                    if (t1.equals(vertices.get(jj).name)) {
                                        double x11 = 0, y11 = 0;
                                        x11 = vertices.get(jj).xcoordinate * 10;
                                        y11 = vertices.get(jj).ycoordinate * 10;
                                        LineTo lx = new LineTo(x11, y11);
                                        path.getElements().addAll(lx);
                                        break;
                                    }
                                }
                            }
                            l1.setStroke(Color.DEEPPINK);
                            l.setStroke(Color.DEEPPINK);
                          l.setStrokeWidth(5);
                          l1.setStrokeWidth(5);

                            PathTransition pt = new PathTransition();
                            PathTransition pt1 = new PathTransition();
                            pt.setDuration(Duration.millis(3000));
                            pt.setPath(path);
                            pt.setNode(l);
                            pt.setCycleCount(1500);
                            pt.setAutoReverse(false);
                            pt1.setDuration(Duration.millis(3000));
                            pt1.setPath(path);
                            pt1.setNode(l1);
                            pt1.setCycleCount(1500);
                            pt1.setAutoReverse(false);
                            pt1.play();
                            pt.play();
                        }

//                        For Triangle =========================================================

                        if(str.equals("triangle"))
                        {
                            Polygon triangle = new Polygon();
                            triangle.getPoints().addAll(new Double[]{
                                    (double)(10*xs),  (double)10*ys,
                                    (double)(10*xs-30),   (double)(10*ys-30),
                                    (double)(10*xs+30), (double)(10*ys-35)});

//                            triangle.getPoints().add( 20*xs);

//                            Rectangle rec = new Rectangle(20*xs,20*ys,30,30);
                            ap.getChildren().add(triangle);
                            triangle.setFill(Color.RED);
                            Path path = new Path();
                            MoveTo mt = new MoveTo(xs * 10 - 5, ys * 10 - 5);
                            path.getElements().add(mt);
                            for (int i = printing.size() - 1; i >= 0; i--) {
                                String t1 = map1.get(printing.get(i));
                                for (int jj = 0; jj < vertices.size(); jj++) {
                                    if (t1.equals(vertices.get(jj).name)) {
                                        double x11 = 0, y11 = 0;
                                        x11 = vertices.get(jj).xcoordinate * 10;
                                        y11 = vertices.get(jj).ycoordinate * 10;
                                        LineTo l = new LineTo(x11, y11);
                                        path.getElements().addAll(l);
                                        break;
                                    }
                                }
                            }
                            PathTransition pt = new PathTransition();
                            pt.setDuration(Duration.millis(3000));
                            pt.setPath(path);
                            pt.setNode(triangle);
                            pt.setCycleCount(1500);
                            pt.setAutoReverse(false);
                            pt.play();
                        }

//                        For Plus Sign========================================
                        if(str.equals("plus"))
                        {
                            Line l = new Line();
                            l.setStartX((double) (10* xs));
                            l.setStartY((double) (10 * ys-10));
                            l.setEndX((double) (10 * xs));
                            l.setEndY((double) (10 * ys+10));

                            Line l1 = new Line();
                            l1.setStartX((double) (10 * xs-10));
                            l1.setStartY((double) (10 * ys));
                            l1.setEndX((double) (10 * xs+10));
                            l1.setEndY((double) (10 * ys));

//                            triangle.getPoints().add( 20*xs);

//                            Rectangle rec = new Rectangle(20*xs,20*ys,30,30);
                            ap.getChildren().add(l);
                            ap.getChildren().add(l1);
//                            triangle.setFill(Color.WHITE);
                            Path path = new Path();
                            MoveTo mt = new MoveTo(xs * 10 - 5, ys * 10 - 5);
                            path.getElements().add(mt);
                            for (int i = printing.size() - 1; i >= 0; i--) {
                                String t1 = map1.get(printing.get(i));
                                for (int jj = 0; jj < vertices.size(); jj++) {
                                    if (t1.equals(vertices.get(jj).name)) {
                                        double x11 = 0, y11 = 0;
                                        x11 = vertices.get(jj).xcoordinate * 10;
                                        y11 = vertices.get(jj).ycoordinate * 10;
                                        LineTo lx = new LineTo(x11, y11);
                                        path.getElements().addAll(lx);
                                        break;
                                    }
                                }
                            }
                            l1.setStroke(Color.ORANGE);
                            l.setStroke(Color.ORANGE);
                            l.setStrokeWidth(7);
                            l1.setStrokeWidth(7);

                            PathTransition pt = new PathTransition();
                            PathTransition pt1 = new PathTransition();
                            pt.setDuration(Duration.millis(3000));
                            pt.setPath(path);
                            pt.setNode(l);
                            pt.setCycleCount(1500);
                            pt.setAutoReverse(false);
                            pt1.setDuration(Duration.millis(3000));
                            pt1.setPath(path);
                            pt1.setNode(l1);
                            pt1.setCycleCount(1500);
                            pt1.setAutoReverse(false);
                            pt1.play();
                            pt.play();
                        }


                        output.setText("Yes path exists " + s1);
                    } else {
                        output.setText("No Path Exists!!");
//                    no path exists
                    }
                }
            } else {
                output.setText("No Path Exists!!");
            }

            dto.setText("");
            dfrom.setText("");
        }
        catch(Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR OCCURED!!");
            alert.setHeaderText("Invalid Parameters");
            alert.setContentText("Please Enter Correct Details");
            alert.showAndWait().ifPresent(rs->{
                if(rs==ButtonType.OK)
                {
                    output.setText("");
                    vfrom.setText("");
                    vto.setText("");
                    System.out.println("Error!! from catch Block");
                }
            });
        }
    }
    public void ImportFile(ActionEvent E) throws Exception
    {
        try {
            String path = dirpath.getText();
            File f = new File(path);
            try (Scanner sc = new Scanner(f)) {
                int nv = Integer.parseInt(sc.nextLine());
                while (nv != 0) {
                    nv--;
                    String[] input = sc.nextLine().split(" ");
                    addname.setText(input[0]);
                    addx.setText(input[1]);
                    addy.setText(input[2]);
                    b.fire();
                }
                int ne = Integer.parseInt(sc.nextLine());
                while (ne != 0) {
                    ne--;
                    String[] input = sc.nextLine().split(" ");
                    vfrom.setText(input[0]);
                    vto.setText(input[1]);
                    vweight.setText(input[2]);
                    b2.fire();
                }

                output.setText("Graph Imported Successfully");
            }
        }
        catch(Exception e)
        {
            vertices.clear();
            ap.getChildren().clear();
            for(int i=0;i<200;i++)
            {
                if(graph[i]==null)
                {

                }
                else
                {
                    graph[i].clear();
                }
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR OCCURED!!");
            alert.setHeaderText("Invalid Parameters");
            alert.setContentText("Please Enter Correct Details");
            alert.showAndWait().ifPresent(rs->{
                if(rs==ButtonType.OK)
                {
                    output.setText("");
                    vfrom.setText("");
                    vto.setText("");
                    System.out.println("Error!! from catch Block");
                }
            });
        }
    }
    public void ExportFile(ActionEvent E) throws Exception
    {
        try {
            Map<String,Integer> mxp = new HashMap<String,Integer>();
            String path_name = edirpath.getText();
            ArrayList<String> Arr = new ArrayList<String>();
            ArrayList<String> Arr1 = new ArrayList<String>();
            String s = "";
            for (int i = 0; i <= 201; i++) {
                if (graph[i] == null) {
                    graph[i] = new ArrayList<Integer>();
                }
                if (graph[i].size() > 0) {
                    for (int j = 0; j < graph[i].size(); j++) {
                        s = "";
                        if (map1.containsKey(i) && map1.containsKey(graph[i].get(j))) {
                            s = s + map1.get(graph[i].get(j)) + " " + map1.get(i) + " " + Integer.toString(weight[i][graph[i].get(j)]);

                            if(!(mxp.containsKey(s))) {
                                mxp.put(s,1);
                                Arr.add(s);
                            }
                        }
                    }
                }
            }

            Collections.sort(Arr);
            s = "";

            for (int i = 0; i < vertices.size(); i++) {
                s = s + vertices.get(i).name + " " + Double.toString(vertices.get(i).xcoordinate) + " " + Double.toString(vertices.get(i).ycoordinate);
                Arr1.add(s);
                s = "";
            }
            Collections.sort(Arr1);
            FileWriter f = new FileWriter(path_name);
            f.write(Integer.toString(Arr1.size()) + "\n");

            for (int i = 0; i < Arr1.size(); i++) {
                f.write(Arr1.get(i) + "\n");
            }

            f.write(Integer.toString(Arr.size()) + "\n");
            for (int i = 0; i < Arr.size(); i++) {
                f.write(Arr.get(i) + "\n");
            }

            f.close();
            output.setText("Exported Successfully!!");
        }
        catch(Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR OCCURED!!");
            alert.setHeaderText("Invalid Parameters");
            alert.setContentText("Please Enter Correct Details");
            alert.showAndWait().ifPresent(rs->{
                if(rs==ButtonType.OK)
                {
                    output.setText("");
                    vfrom.setText("");
                    vto.setText("");
                    System.out.println("Error!! from catch Block");
                }
            });
        }
    }
    String r1="";
    String r2="";
//    String s1,s2;

    public  void  rall()
    {
        ap.getChildren().clear();
        for(int i=0;i<vertices.size();i++)
        {
            double x,y;
            String s;
            s=vertices.get(i).name;
            x=vertices.get(i).xcoordinate;
            y=vertices.get(i).ycoordinate;

            Circle c1 = new Circle(10* x, 10* y, 10);
            ap.getChildren().addAll(c1);
            c1.setFill(Color.GREENYELLOW);
// ==============================================================================

            Text t= new Text();
            t.setX(10*x);
            t.setY(10*y);
            t.setText(s);
            t.setStroke(Color.WHITE);
            t.setStroke(Color.WHITE);
            t.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR,30));

            ap.getChildren().add(t);
        }
        for(int i=0;i<=201;i++)
        {
            if(graph[i]==null)
            {
                graph[i]=new ArrayList<Integer>();
            }
            if(graph[i].size()>0)
            {
                double x1=0,y1=0;
                String sx=map1.get(i);

                for(int k=0;k<vertices.size();k++)
                {
                    if(vertices.get(k).name.equals(sx))
                    {
                         x1=vertices.get(k).xcoordinate;
                         y1=vertices.get(k).ycoordinate;
                         break;
                    }
                }
                for(int j=0;j<graph[i].size();j++)
                {
                    int l1=i;
                    int l2=graph[i].get(j);

                     s2=map1.get(i);
                     s1=map1.get(graph[i].get(j));

                    for(int k=0;k<vertices.size();k++)
                    {
                        if(vertices.get(k).name.equals(s1))
                        {
                           double x2=vertices.get(k).xcoordinate;
                            double y2=vertices.get(k).ycoordinate;
//                            r1=vertices.get(k).name;
//                            r2=vertices.get(i).name;


                            Line l = new Line();
                            l.setStartX((double)(10*x1));
                            l.setStartY((double)(10*y1));
                            l.setEndX((double)(10*x2));
                            l.setEndY((double)(10*y2));

                            double xx1=(10*x1+10*x2)/2;
                            double yy1=(10*y1+10*y2)/2;

                            Text txx= new Text();
                            txx.setX(xx1);
                            txx.setY(yy1);
                            txx.setText(Integer.toString(weight[i][graph[i].get(j)]));
                            txx.setStroke(Color.RED);
//                            txx.
                            txx.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR,28));

                            l.setOnMousePressed(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent mouseEvent) {
                                    if(mouseEvent.isControlDown()&&toggle_count%2==1)
                                    {
                                        System.out.println("HERE!!!!!");
                                        fDel.setText(map1.get(l1));
                                        tDel.setText(map1.get(l2));
                                        bx.fire();
                                    }
                                    if(mouseEvent.isShiftDown()&&mouseEvent.isAltDown()&&toggle_count%2==1)
                                    {
                                        String sss;
                                        TextInputDialog td = new TextInputDialog();
                                        td.showAndWait();
                                        sss = td.getEditor().getText();
                                        nfrom.setText(map1.get(l1));
                                        nto.setText(map1.get(l2));
                                        nweight.setText(sss);
                                        medge.fire();
                                    }

                                }
                            });
                            l.setStrokeWidth(3);
                            l.setStroke(Color.WHITE);
                            ap.getChildren().add(l);
                            ap.getChildren().add(txx);

                            break;
                        }
                    }

                }
            }
        }
    }
//    ==============================================================================================================================
String temp1="",xcord="",ycord="";
//    Rall ends here
    public void OnToggle(ActionEvent E)
    {
        toggle_count++;
    }
    public void getAll(ActionEvent E)
    {
        ap.getChildren().clear();

        for(int i=0;i<200;i++)
        {
            if(graph[i]==null)
            {

            }
            else {
                graph[i].clear();
            }
        }
        vertices.clear();
    }
    String s1="",s2="";
    double px=0,py=0;
    int dea=0;
    public void onclick(MouseEvent E) {
        try {
            String S;
            int flag=0;
            if (toggle_count % 2 == 1) {
                double x =  E.getX();
                double y =  E.getY();
//            output.setText(Integer.toString(x)+"--->"+Integer.toString(y));
                if((!(E.isControlDown()))&&(!(E.isAltDown()))&&(!(E.isShiftDown()))) {
                    TextInputDialog td = new TextInputDialog();
                    td.showAndWait();
                    S = td.getEditor().getText();

//                output.setText(S);
                    if (S.length() > 0) {
                        double x1 = 0.0000, y1 = 0.0000;
                        x1 = x / 10;
                        y1 = y / 10;
//                        System.out.println("Added->"+Integer.toString(x1));
                        addname.setText(S);
                        addx.setText(Double.toString(x1));
                        addy.setText(Double.toString(y1));
                        b.fire();
                    }
                }
                if(E.isControlDown()&&(!E.isAltDown()))
                {
                    double x11=E.getX();
                    double y11=E.getY();

                    x11=x11/10;
                    y11=y11/10;
                    int idx=0;
                    for(int i=0;i<vertices.size();i++)
                    {
                        double x1=0,y1=0;

                        x1=vertices.get(i).xcoordinate;
                        y1=vertices.get(i).ycoordinate;
                        if(Math.abs(x1-x11)<=1&&Math.abs(y1-y11)<=1)
                        {
                            System.out.println(x11);
                            System.out.println(x1);
                            deleteName.setText(vertices.get(i).name);
                            b11.fire();

                            break;
                        }
                    }
                }
                if(E.isAltDown()&&(!E.isControlDown())&&(!(E.isShiftDown())))
                {
                    onc++;
                    double x11=E.getX();
                    double y11=E.getY();
                    x11=x11/10;
                    y11=y11/10;
                    int idx=0;
                    int fll=0;

                    for(int i=0;i<vertices.size();i++)
                    {
                        double x1=0,y1=0;
                        x1=vertices.get(i).xcoordinate;
                        y1=vertices.get(i).ycoordinate;
                        if(Math.abs(x1-x11)<=1&&Math.abs(y1-y11)<=1)
                        {
                            fll=1;
                            System.out.println(x11);
                            System.out.println(x1);
                            sun[i]=1;
                             if(onc%2==1)
                             {
                                 s1=vertices.get(i).name;
                                 px=x1;
                                 py=y1;
                                 Circle c1 = new Circle(10 * x1, 10 * y1, 10);
                                 ap.getChildren().addAll(c1);
                                 c1.setFill(Color.BLUE);

                                 Text t= new Text();
                                 t.setX(10*px);
                                 t.setY(10*py);
                                 t.setText(s1);
                                 t.setStroke(Color.WHITE);
                                 t.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR,30));
                                 ap.getChildren().add(t);
                             }
                             else
                             {
                                 s2=vertices.get(i).name;
                                 System.out.println("-->"+s1);
                                 System.out.println("-->"+s2);
                                 Circle c1 = new Circle(10 * px, 10 * py, 10);
                                 ap.getChildren().addAll(c1);
                                 c1.setFill(Color.GREENYELLOW);
                                 vfrom.setText(s1);
                                 vto.setText(s2);
                                 String temp;
                                 TextInputDialog td = new TextInputDialog();
                                 td.showAndWait();
                                 temp = td.getEditor().getText();
                                 vweight.setText(temp);
                                 b2.fire();
                             }
                            break;
                        }

                    }
                    if(fll==0)
                    {
                        onc--;
                    }

                }
                if(E.isShiftDown())
                {
                    dea++;
                    if(dea%2==1)
                    {
                        double x11=E.getX();
                        double y11=E.getY();

                        x11=x11/10;
                        y11=y11/10;
                        for(int i=0;i<vertices.size();i++)
                        {
                            double x1=0,y1=0;
                            x1=vertices.get(i).xcoordinate;
                            y1=vertices.get(i).ycoordinate;
                            if(Math.abs(x1-x11)<=1&&Math.abs(y1-y11)<=1)
                            {

                                Circle c12 = new Circle(10 * x1, 10 * y1, 10);
                                ap.getChildren().addAll(c12);
                                c12.setFill(Color.RED);

                                System.out.println(x11);
                                System.out.println(x1);
                                temp1=vertices.get(i).name;
                                xcord=Double.toString(vertices.get(i).xcoordinate);
                                ycord=Double.toString(vertices.get(i).ycoordinate);

                                Text t= new Text();
                                t.setX(10*x1);
                                t.setY(10*y1);
                                t.setText(temp1);
                                t.setStroke(Color.WHITE);
                                t.setStroke(Color.WHITE);
                                t.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR,30));
                                ap.getChildren().addAll(t);
                                break;
                            }
                        }
                    }
                    else
                    {
                        double x11=E.getX();
                        double y11=E.getY();
                        x11=x11/10;
                        y11=y11/10;
                        if((x11-Integer.parseInt(xcord)<=1)&&(y11-Integer.parseInt(ycord)<=1))
                        {
                            x11=x11+2;
                            y11=y11+2;
                        }
                        String s1=Double.toString(x11);
                        String s2=Double.toString(y11);
                        presentname.setText(temp1);
                        modifyname.setText(temp1);
                        modifyx.setText(s1);
                        modifyy.setText(s2);
                        button_modify.fire();
                    }

                }
            }
        }
        catch(Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR OCCURED on click!!");
            alert.setHeaderText("Invalid Parameters");
            alert.setContentText("Please Enter Correct Details");
            alert.showAndWait().ifPresent(rs->{
                if(rs==ButtonType.OK)
                {
                    output.setText("");
                    vfrom.setText("");
                    vto.setText("");
                    System.out.println("Error!! from catch Block");
                }
            });
        }
    }
    public void stopani(ActionEvent E)
    {
        rall();
    }
}