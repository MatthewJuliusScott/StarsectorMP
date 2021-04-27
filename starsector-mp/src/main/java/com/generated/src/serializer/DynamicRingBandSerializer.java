
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class DynamicRingBandSerializer {


    public byte[] serialize(com.fs.starfarer.campaign.DynamicRingBand obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeBoolean(obj.isAdditiveBlend());
            out.writeFloat(obj.getAngle());
            out.writeFloat(obj.getAngularVelocity());
            out.writeInt(obj.getBandIndex());
            out.writeFloat(obj.getBandWidthInEngine());
            out.writeFloat(obj.getBandWidthInTexture());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.awt.Color.class));
                out.write(serializer.serialize(obj.getColor()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeFloat(obj.getExtraAlphaMult());
            out.writeFloat(obj.getFluctuationAngVel());
            out.writeFloat(obj.getFluctuationAngleMult());
            out.writeFloat(obj.getInnerRadius());
            out.writeFloat(obj.getMaxFluctuation());
            out.writeFloat(obj.getPixelsPerSegment());
            out.writeFloat(obj.getWaveAngle());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
    }

    public com.fs.starfarer.campaign.DynamicRingBand deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.campaign.DynamicRingBand obj = objenesis.newInstance(com.fs.starfarer.campaign.DynamicRingBand.class);
        return obj;
    }

}
