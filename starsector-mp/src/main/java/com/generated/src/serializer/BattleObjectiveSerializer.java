
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class BattleObjectiveSerializer {


    public byte[] serialize(com.fs.starfarer.combat.entities.BattleObjective obj)
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
            out.writeFloat(obj.getCollisionRadius());
            out.writeFloat(obj.getFacing());
            out.writeBoolean(obj.isGhost());
            out.writeFloat(obj.getHitpoints());
            out.writeFloat(obj.getMass());
            out.writeFloat(obj.getMaxHitpoints());
            out.writeInt(obj.getOwner());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.graphics.SpriteAPI.class));
                out.write(serializer.serialize(obj.getSprite()));
            } catch (SerializerNotFoundException _x) {
            }
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
        return obj.getActiveLayers().writeObject();
        return obj.getDisplayName().writeObject();
        return obj.getDisplayNameForTask().writeObject();
        return obj.getImportance().writeObject();
        return obj.getLocation().writeObject();
        return obj.getLocationName().writeObject();
        return obj.getTargetSize().writeObject();
        return obj.getType().writeObject();
        return obj.getVelocity().writeObject();
    }

    public com.fs.starfarer.combat.entities.BattleObjective deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.combat.entities.BattleObjective obj = objenesis.newInstance(com.fs.starfarer.combat.entities.BattleObjective.class);
        return obj;
    }

}
