
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class DamagingExplosionSpecSerializer {


    public byte[] serialize(com.fs.starfarer.api.loading.DamagingExplosionSpec obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.combat.CollisionClass.class));
                out.write(serializer.serialize(obj.getCollisionClass()));
            } catch (SerializerNotFoundException _x) {
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.combat.CollisionClass.class));
                out.write(serializer.serialize(obj.getCollisionClassByFighter()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeFloat(obj.getCoreRadius());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.combat.DamageType.class));
                out.write(serializer.serialize(obj.getDamageType()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeFloat(obj.getDuration());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.combat.OnHitEffectPlugin.class));
                out.write(serializer.serialize(obj.getEffect()));
            } catch (SerializerNotFoundException _x) {
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.awt.Color.class));
                out.write(serializer.serialize(obj.getExplosionColor()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeFloat(obj.getMaxDamage());
            out.writeFloat(obj.getMinDamage());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.combat.MutableStat.class));
                out.write(serializer.serialize(obj.getModifier()));
            } catch (SerializerNotFoundException _x) {
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.awt.Color.class));
                out.write(serializer.serialize(obj.getParticleColor()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeInt(obj.getParticleCount());
            out.writeFloat(obj.getParticleDuration());
            out.writeFloat(obj.getParticleSizeMin());
            out.writeFloat(obj.getParticleSizeRange());
            out.writeFloat(obj.getRadius());
            out.writeBoolean(obj.isShowGraphic());
            out.writeUTF(obj.getSoundSetId());
            out.writeBoolean(obj.isUseDetailedExplosion());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
    }

    public com.fs.starfarer.api.loading.DamagingExplosionSpec deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.loading.DamagingExplosionSpec obj = objenesis.newInstance(com.fs.starfarer.api.loading.DamagingExplosionSpec.class);
        return obj;
    }

}
