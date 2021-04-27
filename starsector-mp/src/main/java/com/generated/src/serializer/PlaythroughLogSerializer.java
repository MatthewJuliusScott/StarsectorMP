
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import com.fs.starfarer.api.impl.campaign.plog.PLEntry;
import com.fs.starfarer.api.impl.campaign.plog.PLSnapshot;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class PlaythroughLogSerializer {


    public byte[] serialize(com.fs.starfarer.api.impl.campaign.plog.PlaythroughLog obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            PLSnapshot[] dataArray = obj.getData().toArray(new PLSnapshot[ 0 ] );
            out.writeInt(dataArray.length);
            for (int i = 0; (i<dataArray.length); i ++) {
                try {
                    ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.impl.campaign.plog.PLSnapshot.class));
                    out.write(serializer.serialize(dataArray[i]));
                } catch (SerializerNotFoundException _x) {
                }
            }
            PLEntry[] entriesArray = obj.getEntries().toArray(new PLEntry[ 0 ] );
            out.writeInt(entriesArray.length);
            for (int i = 0; (i<entriesArray.length); i ++) {
                try {
                    ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.impl.campaign.plog.PLEntry.class));
                    out.write(serializer.serialize(entriesArray[i]));
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

    public com.fs.starfarer.api.impl.campaign.plog.PlaythroughLog deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.impl.campaign.plog.PlaythroughLog obj = objenesis.newInstance(com.fs.starfarer.api.impl.campaign.plog.PlaythroughLog.class);
        return obj;
    }

}
