
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class CampaignEngineSerializer {


    public byte[] serialize(com.fs.starfarer.campaign.CampaignEngine obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.campaign.PlayerCharacterData.class));
                out.write(serializer.serialize(obj.getCharacterData()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeUTF(obj.getDifficulty());
            out.writeBoolean(obj.isFastForwardIteration());
            out.writeBoolean(obj.isInFastAdvance());
            out.writeBoolean(obj.isInNewGameAdvance());
            out.writeLong(obj.getLastPlayerBattleTimestamp());
            out.writeBoolean(obj.isLastPlayerBattleWon());
            out.writeBoolean(obj.isPaused());
            out.writeLong(obj.getPlayerBattleSeed());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.LocationAPI.class));
                out.write(serializer.serialize(obj.getRespawnLocation()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeUTF(obj.getSaveDirName());
            com.fs.starfarer.api.EveryFrameScript[] scriptsArray = obj.getScripts().toArray(new com.fs.starfarer.api.EveryFrameScript[ 0 ] );
            out.writeInt(scriptsArray.length);
            for (int i = 0; (i<scriptsArray.length); i ++) {
                try {
                    ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.EveryFrameScript.class));
                    out.write(serializer.serialize(scriptsArray[i]));
                } catch (SerializerNotFoundException _x) {
                }
            }
            out.writeUTF(obj.getSeedString());
            out.writeBoolean(obj.isSkipNextRender());
            com.fs.starfarer.api.EveryFrameScript[] transientScriptsArray = obj.getTransientScripts().toArray(new com.fs.starfarer.api.EveryFrameScript[ 0 ] );
            out.writeInt(transientScriptsArray.length);
            for (int i = 0; (i<transientScriptsArray.length); i ++) {
                try {
                    ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.EveryFrameScript.class));
                    out.write(serializer.serialize(transientScriptsArray[i]));
                } catch (SerializerNotFoundException _x) {
                }
            }
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
        return obj.getRespawnCoordinates().writeObject();
    }

    public com.fs.starfarer.campaign.CampaignEngine deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.campaign.CampaignEngine obj = objenesis.newInstance(com.fs.starfarer.campaign.CampaignEngine.class);
        return obj;
    }

}
