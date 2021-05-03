
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class TerrainGenDataSpecSerializer {


    public byte[] serialize(com.fs.starfarer.api.impl.campaign.procgen.TerrainGenDataSpec obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            String[] tagsArray = obj.getTags().toArray(new String[ 0 ] );
            out.writeInt(tagsArray.length);
            for (int i = 0; (i<tagsArray.length); i ++) {
                out.writeUTF(tagsArray[i]);
            }
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
        return obj.getCategory().writeObject();
        return obj.getId().writeObject();
        return obj.getMaxColor().writeObject();
        return obj.getMinColor().writeObject();
    }

    public com.fs.starfarer.api.impl.campaign.procgen.TerrainGenDataSpec deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.impl.campaign.procgen.TerrainGenDataSpec obj = objenesis.newInstance(com.fs.starfarer.api.impl.campaign.procgen.TerrainGenDataSpec.class);
        return obj;
    }

}
