
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import com.fs.starfarer.api.campaign.CampaignPlugin;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class ModAndPluginDataSerializer {


    public byte[] serialize(com.fs.starfarer.campaign.ModAndPluginData obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            com.fs.starfarer.campaign.ModAndPluginData.EnabledModData[] allModsEverEnabledArray = obj.getAllModsEverEnabled().toArray(new com.fs.starfarer.campaign.ModAndPluginData.EnabledModData[ 0 ] );
            out.writeInt(allModsEverEnabledArray.length);
            for (int i = 0; (i<allModsEverEnabledArray.length); i ++) {
            }
            com.fs.starfarer.campaign.ModAndPluginData.EnabledModData[] enabledModsArray = obj.getEnabledMods().toArray(new com.fs.starfarer.campaign.ModAndPluginData.EnabledModData[ 0 ] );
            out.writeInt(enabledModsArray.length);
            for (int i = 0; (i<enabledModsArray.length); i ++) {
            }
            CampaignPlugin[] pluginsArray = obj.getPlugins().toArray(new CampaignPlugin[ 0 ] );
            out.writeInt(pluginsArray.length);
            for (int i = 0; (i<pluginsArray.length); i ++) {
                try {
                    ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.CampaignPlugin.class));
                    out.write(serializer.serialize(pluginsArray[i]));
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
    }

    public com.fs.starfarer.campaign.ModAndPluginData deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.campaign.ModAndPluginData obj = objenesis.newInstance(com.fs.starfarer.campaign.ModAndPluginData.class);
        return obj;
    }

}
