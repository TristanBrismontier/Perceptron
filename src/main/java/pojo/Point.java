package pojo;

import processing.core.PApplet;

public class Point {

    private final PApplet p;

    private final float x;
    private final float y;
    public final float bias;
    public final int label;

    public Point(PApplet p, float x, float y, int label) {
        this.p = p;
        this.x = x;
        this.y = y;
        this.bias = 1;
        this.label = label;
    }

    public void draw(Color color){
        if(label>0){
            p.strokeWeight(1);
            p.stroke(color.r,color.g,color.b);
            p.fill(183,195,243);
        }else{
            p.noStroke();
            p.fill(color.r,color.g,color.b);
        }
        p.ellipse(getX(),getY(),18,18);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return -y;
    }
}
