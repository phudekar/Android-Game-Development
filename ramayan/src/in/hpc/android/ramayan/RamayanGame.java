package in.hpc.android.ramayan;

import android.os.Bundle;
import in.hpc.R;
import in.hpc.android.game.Game;
import in.hpc.android.game.Screen;

public class RamayanGame extends Game {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Screen screen = new HomeScreen(this);
        setCurrentScreen(screen);
    }

}
