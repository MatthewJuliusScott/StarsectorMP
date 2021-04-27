
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class HegemonyInspectionIntelSerializer {


    public byte[] serialize(com.fs.starfarer.api.impl.campaign.intel.inspection.HegemonyInspectionIntel obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeBoolean(obj.isEnteredSystem());
            String[] expectedCoresArray = obj.getExpectedCores().toArray(new String[ 0 ] );
            out.writeInt(expectedCoresArray.length);
            for (int i = 0; (i<expectedCoresArray.length); i ++) {
                out.writeUTF(expectedCoresArray[i]);
            }
            out.writeFloat(obj.getExtraDays());
            out.writeInt(obj.getInvestedCredits());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.lang.Object.class));
                out.write(serializer.serialize(obj.getListInfoParam()));
            } catch (SerializerNotFoundException _x) {
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.lang.Long.class));
                out.write(serializer.serialize(obj.getPlayerVisibleTimestamp()));
            } catch (SerializerNotFoundException _x) {
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.SectorEntityToken.class));
                out.write(serializer.serialize(obj.getPostingLocation()));
            } catch (SerializerNotFoundException _x) {
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.lang.Float.class));
                out.write(serializer.serialize(obj.getPostingRangeLY()));
            } catch (SerializerNotFoundException _x) {
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

    public com.fs.starfarer.api.impl.campaign.intel.inspection.HegemonyInspectionIntel deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.impl.campaign.intel.inspection.HegemonyInspectionIntel obj = objenesis.newInstance(com.fs.starfarer.api.impl.campaign.intel.inspection.HegemonyInspectionIntel.class);
        return obj;
    }

}
