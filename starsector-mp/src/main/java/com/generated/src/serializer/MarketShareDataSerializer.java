
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class MarketShareDataSerializer {


    public byte[] serialize(com.fs.starfarer.campaign.econ.reach.MarketShareData obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeFloat(obj.getExportMarketShare());
            out.writeFloat(obj.getMarketValueFraction());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.econ.CommoditySourceType.class));
                out.write(serializer.serialize(obj.getSource()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeBoolean(obj.isSourceIsIllegal());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
    }

    public com.fs.starfarer.campaign.econ.reach.MarketShareData deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.campaign.econ.reach.MarketShareData obj = objenesis.newInstance(com.fs.starfarer.campaign.econ.reach.MarketShareData.class);
        return obj;
    }

}
