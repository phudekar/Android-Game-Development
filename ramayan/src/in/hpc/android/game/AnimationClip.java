package in.hpc.android.game;

import android.provider.MediaStore;

public class AnimationClip {

    private final Sprite sprite;
    private final short duration;
    private MediaStore.Audio audio;

    public AnimationClip(Sprite sprite, short duration, MediaStore.Audio audio){
        this.sprite = sprite;
        this.duration = duration;
        this.audio = audio;
    }

    public short getDuration() {
        return duration;
    }

    public void play(){

    }

}
