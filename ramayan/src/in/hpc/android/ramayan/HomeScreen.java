package in.hpc.android.ramayan;

import in.hpc.R;
import in.hpc.android.game.*;

public class HomeScreen extends Screen {
    public HomeScreen(Game game) {
        super(game);
        Texture boxTexture = new Texture(game, R.drawable.background);
        Sprite sprite = new Button(game, boxTexture, null, new Vector2(0,0));
        sprite.translate(1, 2);
        sprites.add(sprite);
    }
}
