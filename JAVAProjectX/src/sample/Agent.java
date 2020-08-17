package sample;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Agent {
    private  int ix,iy;
    Agent(int ix,int iy)
    {
        this.ix=ix;
        this.iy=iy;
    }
    public int get_x()
    {
        return ix;
    }
    public  int get_y()
    {
        return iy;
    }
    public void sety(int iy)
    {
        this.iy=iy;
    }
    public void setx(int ix)
    {
        this.ix=ix;
    }
    Random rand=new Random();
    private Rectangle e=new Rectangle();
    public Rectangle make_rect(AnchorPane g,int type)
    {
        int r,gr,b;
        r=rand.nextInt(255);
        b=rand.nextInt(255);
        gr=rand.nextInt(255);
        e.setX(ix*10);
        e.setY(iy*10);
        e.setWidth(10);
        e.setHeight(10);
        if(type==1) {
            e.setFill(Color.BLACK);
        }
        else if(type==2)
        {
            e.setFill(Color.RED);
        }
        else
        {
            e.setFill(Color.BLUE);
        }
        g.getChildren().addAll(e);
        return e;
    }
    public Rectangle get_rect()
    {
        return e;
    }
    public void move_left(AnchorPane g,int [][]grid)
    {
        grid[ix][iy] = 0;
        ix--;
        e.setX(ix*10);
        grid[ix][iy] = 1;
//        g.getChildren().addAll(e);
    }
    public void move_right(AnchorPane g,int [][]grid)
    {
        grid[ix][iy] = 0;
        ix++;
        grid[ix][iy] = 1;
        e.setX(ix*10);
    }
    public void move_down(AnchorPane g,int [][]grid)
    {
        grid[ix][iy] = 0;
        iy++;
        grid[ix][iy] = 0;
        e.setY(iy*10);
//        g.getChildren().addAll(e);
    }
    public void move_up(AnchorPane g,int [][]grid)
    {
        grid[ix][iy] = 0;
        iy--;
        grid[ix][iy] = 0;
        e.setY(iy*10);
//        g.getChildren().addAll(e);
    }
    public boolean if_up(int [][] grid,int dx,int dy)
    {
        if(iy>0)
        {
            int d1=Math.abs(dx-ix)+Math.abs(dy-iy);
            int d2=Math.abs(dx-ix)+Math.abs(dy-iy+1);
            if(d1>d2) {
                if (grid[ix][iy - 1] == 0||(ix==dx&&iy-1==dy)) {

                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }
    public boolean if_down(int [][] grid,int dx,int dy)
    {
        if(iy<99)
        {
            int d1=Math.abs(dx-ix)+Math.abs(dy-iy);
            int d2=Math.abs(dx-ix)+Math.abs(dy-iy-1);
            if(d1>d2) {

                if (grid[ix][iy + 1] == 0||(ix==dx&&iy+1==dy)) {


                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public boolean if_right(int [][] grid,int dx,int dy)
    {
        int d1=Math.abs(dx-ix)+Math.abs(dy-iy);
        int d2=Math.abs(dx-ix-1)+Math.abs(dy-iy);
        if(ix<99)
        {
            if(d2<d1) {
                if (grid[ix + 1][iy] == 0||(ix+1==dx&&iy==dy)) {


                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }
    public boolean if_left(int [][] grid,int dx,int dy)
    {
        int d1=Math.abs(dx-ix)+Math.abs(dy-iy);
        int d2=Math.abs(dx-ix+1)+Math.abs(dy-iy);
        if(ix>0)
        {
            if(d1>d2) {
                if (grid[ix - 1][iy] == 0||(ix-1==dx&&iy==dy)) {


                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }


}
