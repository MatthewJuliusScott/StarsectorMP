
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class TriTachMajorLoanBarEventSerializer {


    public byte[] serialize(com.fs.starfarer.api.impl.campaign.intel.bar.events.TriTachMajorLoanBarEvent obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeInt(obj.getLoanAmount());
            out.writeInt(obj.getRepaymentAmount());
            out.writeInt(obj.getRepaymentDays());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
    }

    public com.fs.starfarer.api.impl.campaign.intel.bar.events.TriTachMajorLoanBarEvent deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.impl.campaign.intel.bar.events.TriTachMajorLoanBarEvent obj = objenesis.newInstance(com.fs.starfarer.api.impl.campaign.intel.bar.events.TriTachMajorLoanBarEvent.class);
        return obj;
    }

}
