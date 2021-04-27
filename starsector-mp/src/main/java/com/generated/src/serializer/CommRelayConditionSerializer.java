
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class CommRelayConditionSerializer {


    public byte[] serialize(com.fs.starfarer.api.impl.campaign.econ.CommRelayCondition obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            SectorEntityToken[] relaysArray = obj.getRelays().toArray(new SectorEntityToken[ 0 ] );
            out.writeInt(relaysArray.length);
            for (int i = 0; (i<relaysArray.length); i ++) {
                try {
                    ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.SectorEntityToken.class));
                    out.write(serializer.serialize(relaysArray[i]));
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

    public com.fs.starfarer.api.impl.campaign.econ.CommRelayCondition deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.impl.campaign.econ.CommRelayCondition obj = objenesis.newInstance(com.fs.starfarer.api.impl.campaign.econ.CommRelayCondition.class);
        return obj;
    }

}
