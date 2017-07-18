package perceptron;

import processing.core.PApplet;



final public class Perceptron {

    final private PApplet pApplet;

    final public float[] weights = new float[3];
    final private float learningRate = 0.00000005f;

    public Perceptron(PApplet pApplet) {
        this.pApplet = pApplet;
        for (int i = 0; i < weights.length; i++) {
            weights[i] = pApplet.random(-1, 1);
        }
        weights[2]=0;
    }

    public int guess(float[] inputs) {
        float sum =0;
        for (int i = 0; i < weights.length; i++) {
            sum += inputs[i] * weights[i];
        }
        return sign(sum);
    }

    //Activation function
    private int sign(float sum) {
        return sum >= 0?1:-1;
    }

    public void train(int target, float[] inputs){
        int guess = guess(inputs);
        int error = target - guess;

        for (int i = 0; i < weights.length; i++) {
            weights[i]+=error * inputs[i] * learningRate;
        }

    }

    public float guessY(float x) {
        float m = -weights[2]/weights[1]-weights[0]/weights[1]*x;
        return m;
    }
}