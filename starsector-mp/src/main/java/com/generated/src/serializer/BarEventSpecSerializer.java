
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class BarEventSpecSerializer {


    public byte[] serialize(com.fs.starfarer.api.loading.BarEventSpec obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeFloat(obj.getFreq());
            out.writeUTF(obj.getId());
            out.writeFloat(obj.getMaxAcceptedTimeout());
            out.writeFloat(obj.getMaxDur());
            out.writeFloat(obj.getMaxTimeout());
            out.writeFloat(obj.getMinAcceptedTimeout());
            out.writeFloat(obj.getMinDur());
            out.writeFloat(obj.getMinTimeout());
            out.writeUTF(obj.getPluginClass());
            out.writeFloat(obj.getProb());
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
    }

    public com.fs.starfarer.api.loading.BarEventSpec deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.loading.BarEventSpec obj = objenesis.newInstance(com.fs.starfarer.api.loading.BarEventSpec.class);
        return obj;
    }

}
