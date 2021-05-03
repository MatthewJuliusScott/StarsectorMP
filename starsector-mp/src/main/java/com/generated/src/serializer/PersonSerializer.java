
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class PersonSerializer {


    public byte[] serialize(com.fs.starfarer.rpg.Person obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeUTF(obj.getAICoreId());
            out.writeBoolean(obj.isAdvanced());
            out.writeFloat(obj.getContactWeight());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.CampaignFleetAPI.class));
                out.write(serializer.serialize(obj.getFleet()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeUTF(obj.getId());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.PersonImportance.class));
                out.write(serializer.serialize(obj.getImportance()));
            } catch (SerializerNotFoundException _x) {
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.econ.MarketAPI.class));
                out.write(serializer.serialize(obj.getMarket()));
            } catch (SerializerNotFoundException _x) {
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.characters.FullName.class));
                out.write(serializer.serialize(obj.getName()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeUTF(obj.getPortraitSprite());
            out.writeUTF(obj.getPostId());
            out.writeUTF(obj.getRankId());
            String[] tagsArray = obj.getTags().toArray(new String[ 0 ] );
            out.writeInt(tagsArray.length);
            for (int i = 0; (i<tagsArray.length); i ++) {
                out.writeUTF(tagsArray[i]);
            }
            out.writeUTF(obj.getVoice());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
        return obj.getPost().writeObject();
        return obj.getPostArticle().writeObject();
        return obj.getRank().writeObject();
        return obj.getRankArticle().writeObject();
    }

    public com.fs.starfarer.rpg.Person deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.rpg.Person obj = objenesis.newInstance(com.fs.starfarer.rpg.Person.class);
        return obj;
    }

}
