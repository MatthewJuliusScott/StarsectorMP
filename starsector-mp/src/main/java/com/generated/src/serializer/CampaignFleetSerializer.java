
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class CampaignFleetSerializer {


    public byte[] serialize(com.fs.starfarer.campaign.fleet.CampaignFleet obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.ai.CampaignFleetAIAPI.class));
                out.write(serializer.serialize(obj.getAI()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeBoolean(obj.isAIMode());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.lang.Boolean.class));
                out.write(serializer.serialize(obj.getAbortDespawn()));
            } catch (SerializerNotFoundException _x) {
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.lang.Boolean.class));
                out.write(serializer.serialize(obj.getAlwaysUseSensorFaderBrightness()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeUTF(obj.getAutogenJumpPointNameInHyper());
            out.writeUTF(obj.getAutogenJumpPointNameInSystem());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.BattleAPI.class));
                out.write(serializer.serialize(obj.getBattle()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeFloat(obj.getCircularOrbitAngle());
            out.writeUTF(obj.getCustomDescriptionId());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.InteractionDialogImageVisual.class));
                out.write(serializer.serialize(obj.getCustomInteractionDialogImageVisual()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeFloat(obj.getDesiredFacing());
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
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.lang.Boolean.class));
                out.write(serializer.serialize(obj.getForceNoSensorProfileUpdate()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeBoolean(obj.isFreeTransfer());
            out.writeUTF(obj.getId());
            out.writeBoolean(obj.isInJumpTransitionWarpOutStage());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.FleetInflater.class));
                out.write(serializer.serialize(obj.getInflater()));
            } catch (SerializerNotFoundException _x) {
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.SectorEntityToken.class));
                out.write(serializer.serialize(obj.getInteractionTarget()));
            } catch (SerializerNotFoundException _x) {
            }
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
            out.writeBoolean(obj.isNoFactionInName());
            out.writeUTF(obj.getNullAIActionText());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.OrbitAPI.class));
                out.write(serializer.serialize(obj.getOrbit()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeBoolean(obj.isReportedSpawned());
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
        return obj.getActiveLayers().writeObject();
        return obj.getCustomEntityType().writeObject();
        return obj.getLocation().writeObject();
        return obj.getLocationInHyperspace().writeObject();
        return obj.getVelocity().writeObject();
    }

    public com.fs.starfarer.campaign.fleet.CampaignFleet deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.campaign.fleet.CampaignFleet obj = objenesis.newInstance(com.fs.starfarer.campaign.fleet.CampaignFleet.class);
        return obj;
    }

}
