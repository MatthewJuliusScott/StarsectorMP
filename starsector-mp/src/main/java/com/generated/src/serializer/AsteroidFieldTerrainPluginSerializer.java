
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class AsteroidFieldTerrainPluginSerializer {


    public byte[] serialize(com.fs.starfarer.api.impl.campaign.terrain.AsteroidFieldTerrainPlugin obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeUTF(obj.getTerrainName());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
        return obj.getActiveLayers().writeObject();
        return obj.getEffectCategory().writeObject();
        return obj.getIconSpriteName().writeObject();
        return obj.getModId().writeObject();
        return obj.getNameAOrAn().writeObject();
        return obj.getNameForTooltip().writeObject();
        return obj.getTerrainId().writeObject();
    }

    public com.fs.starfarer.api.impl.campaign.terrain.AsteroidFieldTerrainPlugin deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.impl.campaign.terrain.AsteroidFieldTerrainPlugin obj = objenesis.newInstance(com.fs.starfarer.api.impl.campaign.terrain.AsteroidFieldTerrainPlugin.class);
        return obj;
    }

}
