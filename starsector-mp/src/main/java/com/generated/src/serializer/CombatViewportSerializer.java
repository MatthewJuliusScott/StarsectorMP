
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class CombatViewportSerializer {


    public byte[] serialize(com.fs.starfarer.combat.CombatViewport obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeFloat(obj.getAlphaMult());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((org.lwjgl.util.vector.Vector2f.class));
                out.write(serializer.serialize(obj.getCenter()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeBoolean(obj.isExternalControl());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.combat.CombatViewport.class));
                out.write(serializer.serialize(obj.getInset()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeFloat(obj.getScale());
            out.writeFloat(obj.getViewMult());
            out.writeFloat(obj.getWorldXtoScreenX());
            out.writeFloat(obj.getWorldYtoScreenY());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
    }

    public com.fs.starfarer.combat.CombatViewport deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.combat.CombatViewport obj = objenesis.newInstance(com.fs.starfarer.combat.CombatViewport.class);
        return obj;
    }

}
