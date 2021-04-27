
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class ContractEconomyStepperSerializer {


    public byte[] serialize(com.fs.starfarer.campaign.econ.contract.iter.ContractEconomyStepper obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.campaign.econ.contract.ContractEconomy.class));
                out.write(serializer.serialize(obj.getContractEconomy()));
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

    public com.fs.starfarer.campaign.econ.contract.iter.ContractEconomyStepper deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.campaign.econ.contract.iter.ContractEconomyStepper obj = objenesis.newInstance(com.fs.starfarer.campaign.econ.contract.iter.ContractEconomyStepper.class);
        return obj;
    }

}
