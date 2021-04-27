
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.fs.starfarer.api.impl.campaign.terrain.FlareManager.Flare;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class FlareManagerSerializer {


    public byte[] serialize(com.fs.starfarer.api.impl.campaign.terrain.FlareManager obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            Flare[] flaresArray = obj.getFlares().toArray(new Flare[ 0 ] );
            out.writeInt(flaresArray.length);
            for (int i = 0; (i<flaresArray.length); i ++) {
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

    public com.fs.starfarer.api.impl.campaign.terrain.FlareManager deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.impl.campaign.terrain.FlareManager obj = objenesis.newInstance(com.fs.starfarer.api.impl.campaign.terrain.FlareManager.class);
        return obj;
    }

}
