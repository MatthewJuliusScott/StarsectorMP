
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class PlanetSpecSerializer {


    public byte[] serialize(com.fs.starfarer.loading.specs.PlanetSpec obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.awt.Color.class));
                out.write(serializer.serialize(obj.getAtmosphereColor()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeFloat(obj.getAtmosphereThickness());
            out.writeFloat(obj.getAtmosphereThicknessMin());
            out.writeBoolean(obj.isBlackHole());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.awt.Color.class));
                out.write(serializer.serialize(obj.getCloudColor()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeFloat(obj.getCloudRotation());
            out.writeUTF(obj.getCloudTexture());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.awt.Color.class));
                out.write(serializer.serialize(obj.getCoronaColor()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeFloat(obj.getCoronaSize());
            out.writeUTF(obj.getCoronaTexture());
            out.writeUTF(obj.getDescriptionId());
            out.writeBoolean(obj.isDoNotShowInCombat());
            out.writeBoolean(obj.isGasGiant());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.awt.Color.class));
                out.write(serializer.serialize(obj.getGlowColor()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeUTF(obj.getGlowTexture());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.awt.Color.class));
                out.write(serializer.serialize(obj.getIconColor()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeBoolean(obj.isNebulaCenter());
            out.writeFloat(obj.getPitch());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.awt.Color.class));
                out.write(serializer.serialize(obj.getPlanetColor()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeBoolean(obj.isPulsar());
            out.writeFloat(obj.getRotation());
            out.writeFloat(obj.getScaleMultMapIcon());
            out.writeFloat(obj.getScaleMultStarscapeIcon());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.awt.Color.class));
                out.write(serializer.serialize(obj.getShieldColor()));
            } catch (SerializerNotFoundException _x) {
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.awt.Color.class));
                out.write(serializer.serialize(obj.getShieldColor2()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeUTF(obj.getShieldTexture());
            out.writeUTF(obj.getShieldTexture2());
            out.writeFloat(obj.getShieldThickness());
            out.writeFloat(obj.getShieldThickness2());
            out.writeUTF(obj.getStarscapeIcon());
            out.writeUTF(obj.getTexture());
            out.writeFloat(obj.getTilt());
            out.writeBoolean(obj.isUseReverseLightForGlow());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
        return obj.getAOrAn().writeObject();
        return obj.getIconTexture().writeObject();
        return obj.getLightPosition().writeObject();
        return obj.getName().writeObject();
        return obj.getPlanetType().writeObject();
    }

    public com.fs.starfarer.loading.specs.PlanetSpec deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.loading.specs.PlanetSpec obj = objenesis.newInstance(com.fs.starfarer.loading.specs.PlanetSpec.class);
        return obj;
    }

}
