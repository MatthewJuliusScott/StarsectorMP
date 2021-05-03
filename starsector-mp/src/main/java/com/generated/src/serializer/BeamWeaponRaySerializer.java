
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class BeamWeaponRaySerializer {


    public byte[] serialize(com.fs.starfarer.combat.entities.BeamWeaponRay obj)
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
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.awt.Color.class));
                out.write(serializer.serialize(obj.getCoreColor()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeBoolean(obj.isDarkCore());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.awt.Color.class));
                out.write(serializer.serialize(obj.getFringeColor()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeFloat(obj.getFringeScrollSpeedMult());
            out.writeBoolean(obj.isGhost());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.graphics.SpriteAPI.class));
                out.write(serializer.serialize(obj.getHitGlow()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeFloat(obj.getImpactMass());
            out.writeFloat(obj.getMass());
            out.writeFloat(obj.getPixelsPerTexel());
            out.writeFloat(obj.getTextureScrollSpeed());
            out.writeFloat(obj.getWidth());
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
        return obj.getRayEndPrevFrame().writeObject();
        return obj.getTo().writeObject();
        return obj.getVelocity().writeObject();
    }

    public com.fs.starfarer.combat.entities.BeamWeaponRay deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.combat.entities.BeamWeaponRay obj = objenesis.newInstance(com.fs.starfarer.combat.entities.BeamWeaponRay.class);
        return obj;
    }

}
