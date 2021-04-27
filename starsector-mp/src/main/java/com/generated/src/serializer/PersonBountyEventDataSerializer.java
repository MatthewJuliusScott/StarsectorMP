
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class PersonBountyEventDataSerializer {


    public byte[] serialize(com.fs.starfarer.api.impl.campaign.shared.PersonBountyEventData obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeInt(obj.getLevel());
            String[] participatingFactionsArray = obj.getParticipatingFactions().toArray(new String[ 0 ] );
            out.writeInt(participatingFactionsArray.length);
            for (int i = 0; (i<participatingFactionsArray.length); i ++) {
                out.writeUTF(participatingFactionsArray[i]);
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

    public com.fs.starfarer.api.impl.campaign.shared.PersonBountyEventData deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.impl.campaign.shared.PersonBountyEventData obj = objenesis.newInstance(com.fs.starfarer.api.impl.campaign.shared.PersonBountyEventData.class);
        return obj;
    }

}
