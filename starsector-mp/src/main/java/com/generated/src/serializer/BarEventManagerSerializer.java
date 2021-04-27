
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.fs.starfarer.api.impl.campaign.intel.bar.events.BarEventManager.GenericBarEventCreator;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class BarEventManagerSerializer {


    public byte[] serialize(com.fs.starfarer.api.impl.campaign.intel.bar.events.BarEventManager obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            GenericBarEventCreator[] creatorsArray = obj.getCreators().toArray(new GenericBarEventCreator[ 0 ] );
            out.writeInt(creatorsArray.length);
            for (int i = 0; (i<creatorsArray.length); i ++) {
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

    public com.fs.starfarer.api.impl.campaign.intel.bar.events.BarEventManager deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.impl.campaign.intel.bar.events.BarEventManager obj = objenesis.newInstance(com.fs.starfarer.api.impl.campaign.intel.bar.events.BarEventManager.class);
        return obj;
    }

}
