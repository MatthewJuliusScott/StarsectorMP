
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class SpySatDeploymentSerializer {


    public byte[] serialize(com.fs.starfarer.api.impl.campaign.missions.SpySatDeployment obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeInt(obj.getXPReward());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.impl.campaign.missions.hub.HubMissionCreator.class));
                out.write(serializer.serialize(obj.getCreator()));
            } catch (SerializerNotFoundException _x) {
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.impl.campaign.missions.hub.MissionTrigger.class));
                out.write(serializer.serialize(obj.getCurrTrigger()));
            } catch (SerializerNotFoundException _x) {
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.util.Random.class));
                out.write(serializer.serialize(obj.getGenRandom()));
            } catch (SerializerNotFoundException _x) {
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.impl.campaign.missions.hub.MissionHub.class));
                out.write(serializer.serialize(obj.getHub()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeUTF(obj.getMissionId());
            out.writeUTF(obj.getName());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.RepLevel.class));
                out.write(serializer.serialize(obj.getPenaltyLimitFaction()));
            } catch (SerializerNotFoundException _x) {
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.RepLevel.class));
                out.write(serializer.serialize(obj.getPenaltyLimitPerson()));
            } catch (SerializerNotFoundException _x) {
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.characters.PersonAPI.class));
                out.write(serializer.serialize(obj.getPersonOverride()));
            } catch (SerializerNotFoundException _x) {
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.lang.Long.class));
                out.write(serializer.serialize(obj.getPlayerVisibleTimestamp()));
            } catch (SerializerNotFoundException _x) {
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.SectorEntityToken.class));
                out.write(serializer.serialize(obj.getPostingLocation()));
            } catch (SerializerNotFoundException _x) {
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.lang.Float.class));
                out.write(serializer.serialize(obj.getPostingRangeLY()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeFloat(obj.getQuality());
            out.writeFloat(obj.getRewardMult());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
        return obj.getBackgroundGlowColor().writeObject();
        return obj.getBaseName().writeObject();
        return obj.getBlurbText().writeObject();
        return obj.getCommMessageSound().writeObject();
        return obj.getGetWithinCommsRangeText().writeObject();
        return obj.getGetWithinCommsRangeTextShort().writeObject();
        return obj.getGiverGender().writeObject();
        return obj.getNextStepText().writeObject();
        return obj.getPostfixForState().writeObject();
        return obj.getReason().writeObject();
        return obj.getRewardLimitFaction().writeObject();
        return obj.getRewardLimitPerson().writeObject();
        return obj.getSmallDescriptionTitle().writeObject();
        return obj.getSortString().writeObject();
        return obj.getSortTier().writeObject();
        return obj.getStageDescriptionText().writeObject();
        return obj.getTriggerPrefix().writeObject();
    }

    public com.fs.starfarer.api.impl.campaign.missions.SpySatDeployment deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.impl.campaign.missions.SpySatDeployment obj = objenesis.newInstance(com.fs.starfarer.api.impl.campaign.missions.SpySatDeployment.class);
        return obj;
    }

}
