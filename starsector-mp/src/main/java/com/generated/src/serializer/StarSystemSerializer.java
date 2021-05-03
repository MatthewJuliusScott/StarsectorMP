
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import com.fs.starfarer.api.campaign.JumpPointAPI;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class StarSystemSerializer {


    public byte[] serialize(com.fs.starfarer.campaign.StarSystem obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.impl.campaign.procgen.StarAge.class));
                out.write(serializer.serialize(obj.getAge()));
            } catch (SerializerNotFoundException _x) {
            }
            JumpPointAPI[] autogeneratedJumpPointsInHyperArray = obj.getAutogeneratedJumpPointsInHyper().toArray(new JumpPointAPI[ 0 ] );
            out.writeInt(autogeneratedJumpPointsInHyperArray.length);
            for (int i = 0; (i<autogeneratedJumpPointsInHyperArray.length); i ++) {
                try {
                    ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.JumpPointAPI.class));
                    out.write(serializer.serialize(autogeneratedJumpPointsInHyperArray[i]));
                } catch (SerializerNotFoundException _x) {
                }
            }
            out.writeUTF(obj.getBackgroundTextureFilename());
            out.writeUTF(obj.getBaseName());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.SectorEntityToken.class));
                out.write(serializer.serialize(obj.getCenter()));
            } catch (SerializerNotFoundException _x) {
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.impl.campaign.procgen.Constellation.class));
                out.write(serializer.serialize(obj.getConstellation()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeUTF(obj.getDisplayName());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.lang.Boolean.class));
                out.write(serializer.serialize(obj.getDoNotShowIntelFromThisLocationOnMap()));
            } catch (SerializerNotFoundException _x) {
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.SectorEntityToken.class));
                out.write(serializer.serialize(obj.getHyperspaceAnchor()));
            } catch (SerializerNotFoundException _x) {
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.awt.Color.class));
                out.write(serializer.serialize(obj.getLightColor()));
            } catch (SerializerNotFoundException _x) {
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.lang.Float.class));
                out.write(serializer.serialize(obj.getMapGridHeightOverride()));
            } catch (SerializerNotFoundException _x) {
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.lang.Float.class));
                out.write(serializer.serialize(obj.getMapGridWidthOverride()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeUTF(obj.getName());
            out.writeBoolean(obj.isProcgen());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.PlanetAPI.class));
                out.write(serializer.serialize(obj.getSecondary()));
            } catch (SerializerNotFoundException _x) {
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.PlanetAPI.class));
                out.write(serializer.serialize(obj.getStar()));
            } catch (SerializerNotFoundException _x) {
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.PlanetAPI.class));
                out.write(serializer.serialize(obj.getTertiary()));
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
        return obj.getLocation().writeObject();
    }

    public com.fs.starfarer.campaign.StarSystem deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.campaign.StarSystem obj = objenesis.newInstance(com.fs.starfarer.campaign.StarSystem.class);
        return obj;
    }

}
