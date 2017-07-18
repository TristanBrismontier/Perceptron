import perceptron.Perceptron;
import pojo.Color;
import pojo.Point;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

final public class PappletProcessing extends PApplet{

    public static void main(String args[]) {
        PApplet.main("PappletProcessing");
    }

    private Perceptron perceptron;
    final private List<float[]> point = new ArrayList<>();
    final private List<Point> trainned = new ArrayList<>();
    private final int pointNb = 500;
    private float coef;
    private float bias;
    private final Color bg = new Color(79,98,114);
    private final Color dark = new Color(41,50,59);
    private final Color good = new Color(130 ,209,115);
    private final Color bad = new Color(221,110,66);



    @Override
    public void settings() {
        perceptron = new Perceptron(this);
        size(1000,1000);
        initPoints();
    }

    private float function(float x){
        return x*coef+bias;
    }

    @Override
    public void setup() {
        background(255);


    }

    @Override
    public void draw() {
        background(bg.r,bg.g,bg.b);
        translate(width/2,height/2);

        fill(255,0,0);
        ellipse(0,0,15,15);
        strokeWeight(2);
        stroke(dark.r,dark.g,dark.b);
        line(-width/2,-function(-width/2),width/2,-function(width/2));


        stroke(8,126,139);
        line(-width/2,perceptron.guessY(-width/2),width/2,perceptron.guessY(width/2));

        strokeWeight(1);
        for (Point point : trainned) {
            point.draw(dark);
            noStroke();
            float[] inputs = {point.getX(), point.getY(),point.bias};
            strokeWeight(1);
            stroke(dark.r,dark.g,dark.b);
            if ( perceptron.guess(inputs)==point.label){
                fill(good.r,good.g,good.b);
            }else{
                fill(bad.r,bad.g,bad.b);
            }

            ellipse(point.getX(),point.getY(),10,10);
            perceptron.train(point.label,inputs);
        }

        for (float[] floats : point) {
            if( perceptron.guess(floats)>0){
                fill(0,255,255);
            }else{
                fill(255,0,255);
            }

            ellipse(floats[0],floats[1],10,10);
        }
        fill(183,195,243);
        ellipse(-width/2,-height/2,300,200);
        fill(dark.r,dark.g,dark.b);
        textSize(14);

        text(String.format("%.8f",perceptron.weights[0]),-width/2+10,-height/2+20);
        text(String.format("%.8f",perceptron.weights[1]),-width/2+10,-height/2+40);
        text(String.format("%.8f",perceptron.weights[2]),-width/2+10,-height/2+60);
    }

    @Override
    public void mouseClicked() {
        perceptron = new Perceptron(this);
        point.clear();
        trainned.clear();
        initPoints();
    }

    @Override
    public void keyPressed() {
        for (int i = 0; i < 50; i++) {
            float x = random(-width/2,width/2);
            float y = random(-height/2,height/2);
            point.add(new float[]{x,y,1});
        }
    }

    private void initPoints() {
        coef = random(-1,1);
        bias = random(-100,100);
        for (int i = 0; i < pointNb; i++) {
            float x = random(-width/2,width/2);
            float y = random(-height/2,height/2);
            int label =1;
            if(y<function(x))label=-1;
            trainned.add(new Point(this,x,y,label));
        }
    }

}
