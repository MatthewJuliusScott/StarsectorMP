
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class SaveGameDataSerializer {


    public byte[] serialize(com.fs.starfarer.campaign.save.SaveGameData obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.util.List.class));
                out.write(serializer.serialize(obj.getAllModsEverEnabled()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeInt(obj.getCharacterLevel());
            out.writeUTF(obj.getCharacterName());
            out.writeBoolean(obj.isCompressed());
            out.writeUTF(obj.getDifficulty());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.util.List.class));
                out.write(serializer.serialize(obj.getEnabledMods()));
            } catch (SerializerNotFoundException _x) {
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.campaign.CampaignClock.class));
                out.write(serializer.serialize(obj.getGameDate()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeBoolean(obj.isIronMode());
            out.writeUTF(obj.getPortraitName());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.util.Date.class));
                out.write(serializer.serialize(obj.getSaveDate()));
            } catch (SerializerNotFoundException _x) {
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.io.File.class));
                out.write(serializer.serialize(obj.getSaveDir()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeUTF(obj.getSaveFileVersion());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
    }

    public com.fs.starfarer.campaign.save.SaveGameData deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.campaign.save.SaveGameData obj = objenesis.newInstance(com.fs.starfarer.campaign.save.SaveGameData.class);
        return obj;
    }

}
