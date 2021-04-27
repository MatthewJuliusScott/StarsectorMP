
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class ConditionGenDataSpecSerializer {


    public byte[] serialize(com.fs.starfarer.api.impl.campaign.procgen.ConditionGenDataSpec obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeUTF(obj.getGroup());
            out.writeFloat(obj.getHazard());
            out.writeUTF(obj.getId());
            out.writeFloat(obj.getOrder());
            out.writeFloat(obj.getRank());
            java.lang.String[] requiresAllArray = obj.getRequiresAll().toArray(new java.lang.String[ 0 ] );
            out.writeInt(requiresAllArray.length);
            for (int i = 0; (i<requiresAllArray.length); i ++) {
                out.writeUTF(requiresAllArray[i]);
            }
            java.lang.String[] requiresAnyArray = obj.getRequiresAny().toArray(new java.lang.String[ 0 ] );
            out.writeInt(requiresAnyArray.length);
            for (int i = 0; (i<requiresAnyArray.length); i ++) {
                out.writeUTF(requiresAnyArray[i]);
            }
            java.lang.String[] requiresNotAnyArray = obj.getRequiresNotAny().toArray(new java.lang.String[ 0 ] );
            out.writeInt(requiresNotAnyArray.length);
            for (int i = 0; (i<requiresNotAnyArray.length); i ++) {
                out.writeUTF(requiresNotAnyArray[i]);
            }
            out.writeBoolean(obj.isRequiresSurvey());
            out.writeFloat(obj.getXpMult());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
    }

    public com.fs.starfarer.api.impl.campaign.procgen.ConditionGenDataSpec deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.impl.campaign.procgen.ConditionGenDataSpec obj = objenesis.newInstance(com.fs.starfarer.api.impl.campaign.procgen.ConditionGenDataSpec.class);
        return obj;
    }

}
