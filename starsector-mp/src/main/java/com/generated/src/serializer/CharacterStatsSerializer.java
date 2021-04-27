
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import com.fs.starfarer.campaign.CharacterStats.AptitudeLevel;
import com.fs.starfarer.campaign.CharacterStats.SkillLevel;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class CharacterStatsSerializer {


    public byte[] serialize(com.fs.starfarer.campaign.CharacterStats obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeLong(obj.getXP());
            AptitudeLevel[] aptitudesArray = obj.getAptitudes().toArray(new AptitudeLevel[ 0 ] );
            out.writeInt(aptitudesArray.length);
            for (int i = 0; (i<aptitudesArray.length); i ++) {
            }
            out.writeLong(obj.getBonusXp());
            out.writeLong(obj.getDeferredBonusXp());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.CampaignFleetAPI.class));
                out.write(serializer.serialize(obj.getFleet()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeInt(obj.getLevel());
            out.writeInt(obj.getPoints());
            SkillLevel[] skillsArray = obj.getSkills().toArray(new SkillLevel[ 0 ] );
            out.writeInt(skillsArray.length);
            for (int i = 0; (i<skillsArray.length); i ++) {
            }
            out.writeBoolean(obj.isSkipRefresh());
            out.writeInt(obj.getStoryPoints());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
    }

    public com.fs.starfarer.campaign.CharacterStats deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.campaign.CharacterStats obj = objenesis.newInstance(com.fs.starfarer.campaign.CharacterStats.class);
        return obj;
    }

}
