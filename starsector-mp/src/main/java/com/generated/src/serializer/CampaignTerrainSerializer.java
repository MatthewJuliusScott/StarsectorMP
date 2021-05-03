
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class CampaignTerrainSerializer {


    public byte[] serialize(com.fs.starfarer.campaign.CampaignTerrain obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.lang.Boolean.class));
                out.write(serializer.serialize(obj.getAlwaysUseSensorFaderBrightness()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeUTF(obj.getAutogenJumpPointNameInHyper());
            out.writeUTF(obj.getAutogenJumpPointNameInSystem());
            out.writeFloat(obj.getCircularOrbitAngle());
            out.writeUTF(obj.getCustomDescriptionId());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.InteractionDialogImageVisual.class));
                out.write(serializer.serialize(obj.getCustomInteractionDialogImageVisual()));
            } catch (SerializerNotFoundException _x) {
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.lang.Float.class));
                out.write(serializer.serialize(obj.getDetectionRangeDetailsOverrideMult()));
            } catch (SerializerNotFoundException _x) {
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.lang.Float.class));
                out.write(serializer.serialize(obj.getDiscoveryXP()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeBoolean(obj.isExpired());
            out.writeFloat(obj.getFacing());
            out.writeBoolean(obj.isFreeTransfer());
            out.writeUTF(obj.getId());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.awt.Color.class));
                out.write(serializer.serialize(obj.getLightColor()));
            } catch (SerializerNotFoundException _x) {
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.SectorEntityToken.class));
                out.write(serializer.serialize(obj.getLightSource()));
            } catch (SerializerNotFoundException _x) {
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.econ.MarketAPI.class));
                out.write(serializer.serialize(obj.getMarket()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeUTF(obj.getName());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.OrbitAPI.class));
                out.write(serializer.serialize(obj.getOrbit()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeFloat(obj.getRadius());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.lang.Float.class));
                out.write(serializer.serialize(obj.getSalvageXP()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeBoolean(obj.isSkipForJumpPointAutoGen());
            out.writeBoolean(obj.isTransponderOn());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
        return obj.getCustomEntityType().writeObject();
        return obj.getLocation().writeObject();
        return obj.getLocationInHyperspace().writeObject();
        return obj.getType().writeObject();
        return obj.getVelocity().writeObject();
    }

    public com.fs.starfarer.campaign.CampaignTerrain deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.campaign.CampaignTerrain obj = objenesis.newInstance(com.fs.starfarer.campaign.CampaignTerrain.class);
        return obj;
    }

}
