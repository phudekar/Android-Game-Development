package in.hpc.android.game;

public class Shader {

    private int vertexShader;
    private int fragmentShader;

    public Shader(int vertexShader, int fragmentShader){

        this.vertexShader = vertexShader;
        this.fragmentShader = fragmentShader;
    }

    public int getVertexShader() {
        return vertexShader;
    }

    public void setVertexShader(int vertexShader) {
        this.vertexShader = vertexShader;
    }

    public int getFragmentShader() {
        return fragmentShader;
    }

    public void setFragmentShader(int fragmentShader) {
        this.fragmentShader = fragmentShader;
    }
}
