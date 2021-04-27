
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class SalvageEntityGenDataSpecSerializer {


    public byte[] serialize(com.fs.starfarer.api.impl.campaign.procgen.SalvageEntityGenDataSpec obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeUTF(obj.getDefFaction());
            out.writeFloat(obj.getDefQuality());
            out.writeFloat(obj.getDetectionRange());
            com.fs.starfarer.api.impl.campaign.procgen.SalvageEntityGenDataSpec.DropData[] dropRandomArray = obj.getDropRandom().toArray(new com.fs.starfarer.api.impl.campaign.procgen.SalvageEntityGenDataSpec.DropData[ 0 ] );
            out.writeInt(dropRandomArray.length);
            for (int i = 0; (i<dropRandomArray.length); i ++) {
            }
            com.fs.starfarer.api.impl.campaign.procgen.SalvageEntityGenDataSpec.DropData[] dropValueArray = obj.getDropValue().toArray(new com.fs.starfarer.api.impl.campaign.procgen.SalvageEntityGenDataSpec.DropData[ 0 ] );
            out.writeInt(dropValueArray.length);
            for (int i = 0; (i<dropValueArray.length); i ++) {
            }
            out.writeUTF(obj.getId());
            out.writeFloat(obj.getMaxDefenderSize());
            out.writeFloat(obj.getMaxStr());
            out.writeFloat(obj.getMinDefenderSize());
            out.writeFloat(obj.getMinStr());
            out.writeUTF(obj.getNameOverride());
            out.writeFloat(obj.getProbDefenders());
            out.writeFloat(obj.getProbStation());
            out.writeFloat(obj.getRadiusOverride());
            out.writeFloat(obj.getSalvageRating());
            out.writeUTF(obj.getStationRole());
            String[] tagsArray = obj.getTags().toArray(new String[ 0 ] );
            out.writeInt(tagsArray.length);
            for (int i = 0; (i<tagsArray.length); i ++) {
                out.writeUTF(tagsArray[i]);
            }
            out.writeFloat(obj.getXpDiscover());
            out.writeFloat(obj.getXpSalvage());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
    }

    public com.fs.starfarer.api.impl.campaign.procgen.SalvageEntityGenDataSpec deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.impl.campaign.procgen.SalvageEntityGenDataSpec obj = objenesis.newInstance(com.fs.starfarer.api.impl.campaign.procgen.SalvageEntityGenDataSpec.class);
        return obj;
    }

}
