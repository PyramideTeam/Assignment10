
package jWhiteBoard;

import org.jgroups.util.Streamable;

import java.io.DataInput;
import java.io.DataOutput;

/**
 * Encapsulates information about a draw command.
 * Used by the {@link JWhiteBoard} and other demos.
 *
 */
public class DrawCommand implements Streamable {
    static final byte DRAW=1;
    static final byte CLEAR=2;
    byte mode;
    int x;
    int y;
    int rgb;
    String textMessage;//add

    public DrawCommand() { // needed for streamable
    }

    DrawCommand(byte mode) {
        this.mode=mode;
    }
    //add send message
     DrawCommand(byte mode,String textMessage) {//add
        this.mode=mode;
        this.textMessage=textMessage;
    }

    DrawCommand(byte mode, int x, int y, int rgb) {
        this.mode=mode;
        this.x=x;
        this.y=y;
        this.rgb=rgb;
    }


    public void writeTo(DataOutput out) throws Exception {
        out.writeByte(mode);
        out.writeInt(x);
        out.writeInt(y);
        out.writeInt(rgb);
        out.writeBytes(textMessage);//add
    }

    public void readFrom(DataInput in) throws Exception {
        mode=in.readByte();
        x=in.readInt();
        y=in.readInt();
        rgb=in.readInt();
        textMessage = in.readLine();/add
    }


    public String toString() {
        StringBuilder ret=new StringBuilder();
        switch(mode) {
            case DRAW: ret.append("DRAW(" + x + ", " + y + ") [" + rgb + "]");
                break;
            case CLEAR: ret.append("CLEAR");
                break;
            default:
                return "<undefined>";
        }
        return ret.toString();
    }

}
