
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class ShipBlueprintOfferCreatorSerializer {


    public byte[] serialize(com.fs.starfarer.api.impl.campaign.intel.bar.events.historian.ShipBlueprintOfferCreator obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeFloat(obj.getFrequency());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
    }

    public com.fs.starfarer.api.impl.campaign.intel.bar.events.historian.ShipBlueprintOfferCreator deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.impl.campaign.intel.bar.events.historian.ShipBlueprintOfferCreator obj = objenesis.newInstance(com.fs.starfarer.api.impl.campaign.intel.bar.events.historian.ShipBlueprintOfferCreator.class);
        return obj;
    }

}
