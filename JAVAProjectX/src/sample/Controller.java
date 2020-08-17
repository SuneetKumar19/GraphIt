package sample;
import com.sun.javafx.geom.AreaOp;
import com.sun.prism.shader.Solid_ImagePattern_AlphaTest_Loader;
import javafx.animation.PathTransition;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;

public class Controller {
@FXML
    GridPane matrix;
@FXML
    RowConstraints r0,r1,r2,r3,r4,r5,r6,r7,r8,r9;
@FXML
    ColumnConstraints c0,c1,c2,c3,c4,c5,c6,c7,c8,c9;

//     System.out.println("AS");
    public void setColor(GridPane gp) {
        System.out.println("AS");
            System.out.println(gp.getColumnCount());
    }

    public  void main(String[] args) {
        System.out.println("AS");
        setColor(matrix);
        matrix.getCellBounds(0,1);
    }
}

