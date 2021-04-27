
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import com.fs.starfarer.campaign.accidents.AccidentRisk;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class AccidentManagerSerializer {


    public byte[] serialize(com.fs.starfarer.campaign.accidents.AccidentManager obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            AccidentRisk[] risksArray = obj.getRisks().toArray(new AccidentRisk[ 0 ] );
            out.writeInt(risksArray.length);
            for (int i = 0; (i<risksArray.length); i ++) {
                try {
                    ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.campaign.accidents.AccidentRisk.class));
                    out.write(serializer.serialize(risksArray[i]));
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

    public com.fs.starfarer.campaign.accidents.AccidentManager deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.campaign.accidents.AccidentManager obj = objenesis.newInstance(com.fs.starfarer.campaign.accidents.AccidentManager.class);
        return obj;
    }

}
