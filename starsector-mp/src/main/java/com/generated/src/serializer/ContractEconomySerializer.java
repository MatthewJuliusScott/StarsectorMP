
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import com.fs.starfarer.campaign.econ.contract.Contract;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class ContractEconomySerializer {


    public byte[] serialize(com.fs.starfarer.campaign.econ.contract.ContractEconomy obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            Contract[] contractsArray = obj.getContracts().toArray(new Contract[ 0 ] );
            out.writeInt(contractsArray.length);
            for (int i = 0; (i<contractsArray.length); i ++) {
                try {
                    ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.campaign.econ.contract.Contract.class));
                    out.write(serializer.serialize(contractsArray[i]));
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

    public com.fs.starfarer.campaign.econ.contract.ContractEconomy deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.campaign.econ.contract.ContractEconomy obj = objenesis.newInstance(com.fs.starfarer.campaign.econ.contract.ContractEconomy.class);
        return obj;
    }

}
