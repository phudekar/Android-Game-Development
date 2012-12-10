package in.hpc.android.game;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class BufferFactory {

    public static FloatBuffer createFloatBuffer(float[] data) {
        java.nio.FloatBuffer floatBuffer;
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(data.length * 4);
        byteBuffer.order(ByteOrder.nativeOrder());
        floatBuffer = byteBuffer.asFloatBuffer();  // Create a float buffer
        floatBuffer.put(data);  // Put our data array in float buffer
        floatBuffer.position(0);
        return floatBuffer;
    }

    public static ShortBuffer createShortBuffer(short[] data) {
        java.nio.ShortBuffer shortBuffer;
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(data.length * 2);
        byteBuffer.order(ByteOrder.nativeOrder());
        shortBuffer = byteBuffer.asShortBuffer();  // Create a float buffer
        shortBuffer.put(data);  // Put our data array in float buffer
        shortBuffer.position(0);
        return shortBuffer;
    }

}
