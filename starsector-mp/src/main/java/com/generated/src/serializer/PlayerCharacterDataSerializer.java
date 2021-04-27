
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import com.fs.starfarer.api.characters.AdminData;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class PlayerCharacterDataSerializer {


    public byte[] serialize(com.fs.starfarer.campaign.PlayerCharacterData obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            java.lang.String[] abilitiesArray = obj.getAbilities().toArray(new java.lang.String[ 0 ] );
            out.writeInt(abilitiesArray.length);
            for (int i = 0; (i<abilitiesArray.length); i ++) {
                out.writeUTF(abilitiesArray[i]);
            }
            AdminData[] adminsArray = obj.getAdmins().toArray(new AdminData[ 0 ] );
            out.writeInt(adminsArray.length);
            for (int i = 0; (i<adminsArray.length); i ++) {
                try {
                    ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.characters.AdminData.class));
                    out.write(serializer.serialize(adminsArray[i]));
                } catch (SerializerNotFoundException _x) {
                }
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.util.Map.class));
                out.write(serializer.serialize(obj.getCustom()));
            } catch (SerializerNotFoundException _x) {
            }
            java.lang.String[] hullModsArray = obj.getHullMods().toArray(new java.lang.String[ 0 ] );
            out.writeInt(hullModsArray.length);
            for (int i = 0; (i<hullModsArray.length); i ++) {
                out.writeUTF(hullModsArray[i]);
            }
            out.writeBoolean(obj.isIronMode());
            out.writeUTF(obj.getName());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
    }

    public com.fs.starfarer.campaign.PlayerCharacterData deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.campaign.PlayerCharacterData obj = objenesis.newInstance(com.fs.starfarer.campaign.PlayerCharacterData.class);
        return obj;
    }

}
