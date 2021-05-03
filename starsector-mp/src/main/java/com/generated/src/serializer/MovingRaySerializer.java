
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class MovingRaySerializer {


    public byte[] serialize(com.fs.starfarer.combat.entities.MovingRay obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.combat.ai.AI.class));
                out.write(serializer.serialize(obj.getAI()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeFloat(obj.getAngularVelocity());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.combat.CollisionClass.class));
                out.write(serializer.serialize(obj.getCollisionClass()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeBoolean(obj.isFromMissile());
            out.writeBoolean(obj.isGhost());
            out.writeFloat(obj.getImpactMass());
            out.writeFloat(obj.getMass());
            out.writeInt(obj.getOwner());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
        return obj.getActiveLayers().writeObject();
        return obj.getFrom().writeObject();
        return obj.getLocation().writeObject();
        return obj.getSpawnLocation().writeObject();
        return obj.getTo().writeObject();
    }

    public com.fs.starfarer.combat.entities.MovingRay deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.combat.entities.MovingRay obj = objenesis.newInstance(com.fs.starfarer.combat.entities.MovingRay.class);
        return obj;
    }

}
