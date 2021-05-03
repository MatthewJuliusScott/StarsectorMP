
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class HIActionStageSerializer {


    public byte[] serialize(com.fs.starfarer.api.impl.campaign.intel.inspection.HIActionStage obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeFloat(obj.getAbortFP());
            String[] coresRemovedArray = obj.getCoresRemoved().toArray(new String[ 0 ] );
            out.writeInt(coresRemovedArray.length);
            for (int i = 0; (i<coresRemovedArray.length); i ++) {
                out.writeUTF(coresRemovedArray[i]);
            }
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
        return obj.getStatus().writeObject();
    }

    public com.fs.starfarer.api.impl.campaign.intel.inspection.HIActionStage deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.impl.campaign.intel.inspection.HIActionStage obj = objenesis.newInstance(com.fs.starfarer.api.impl.campaign.intel.inspection.HIActionStage.class);
        return obj;
    }

}
