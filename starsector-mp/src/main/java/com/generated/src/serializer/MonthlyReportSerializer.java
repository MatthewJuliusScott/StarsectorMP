
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class MonthlyReportSerializer {


    public byte[] serialize(com.fs.starfarer.api.campaign.econ.MonthlyReport obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeInt(obj.getDebt());
            out.writeInt(obj.getPreviousDebt());
            out.writeLong(obj.getTimestamp());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
    }

    public com.fs.starfarer.api.campaign.econ.MonthlyReport deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.campaign.econ.MonthlyReport obj = objenesis.newInstance(com.fs.starfarer.api.campaign.econ.MonthlyReport.class);
        return obj;
    }

}
