
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class InteractionDialogImageVisualSerializer {


    public byte[] serialize(com.fs.starfarer.api.InteractionDialogImageVisual obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeBoolean(obj.isShowRandomSubImage());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.SpriteId.class));
                out.write(serializer.serialize(obj.getSpriteId()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeFloat(obj.getSubImageDisplayHeight());
            out.writeFloat(obj.getSubImageDisplayWidth());
            out.writeFloat(obj.getSubImageHeight());
            out.writeFloat(obj.getSubImageWidth());
            out.writeFloat(obj.getSubImageX());
            out.writeFloat(obj.getSubImageY());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
        return obj.getSpriteName().writeObject();
    }

    public com.fs.starfarer.api.InteractionDialogImageVisual deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.InteractionDialogImageVisual obj = objenesis.newInstance(com.fs.starfarer.api.InteractionDialogImageVisual.class);
        return obj;
    }

}
