
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class DetailedSmokeParticleSerializer {


    public byte[] serialize(com.fs.starfarer.renderers.fx.DetailedSmokeParticle obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeFloat(obj.getAge());
            out.writeFloat(obj.getAngle());
            out.writeFloat(obj.getDx());
            out.writeFloat(obj.getDy());
            out.writeFloat(obj.getMaxAge());
            out.writeFloat(obj.getRampUpPeriod());
            out.writeFloat(obj.getRotationSpeed());
            out.writeFloat(obj.getSize());
            out.writeFloat(obj.getX());
            out.writeFloat(obj.getY());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
    }

    public com.fs.starfarer.renderers.fx.DetailedSmokeParticle deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.renderers.fx.DetailedSmokeParticle obj = objenesis.newInstance(com.fs.starfarer.renderers.fx.DetailedSmokeParticle.class);
        return obj;
    }

}
