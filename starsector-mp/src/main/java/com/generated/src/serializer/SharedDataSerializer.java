
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class SharedDataSerializer {


    public byte[] serialize(com.fs.starfarer.api.impl.campaign.shared.SharedData obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.econ.MonthlyReport.class));
                out.write(serializer.serialize(obj.getCurrentReport()));
            } catch (SerializerNotFoundException _x) {
            }
            String[] marketsWithoutTradeFleetSpawnArray = obj.getMarketsWithoutTradeFleetSpawn().toArray(new String[ 0 ] );
            out.writeInt(marketsWithoutTradeFleetSpawnArray.length);
            for (int i = 0; (i<marketsWithoutTradeFleetSpawnArray.length); i ++) {
                out.writeUTF(marketsWithoutTradeFleetSpawnArray[i]);
            }
            out.writeLong(obj.getPlayerLosingBattleTimestamp());
            out.writeFloat(obj.getPlayerPreLosingBattleCrew());
            out.writeFloat(obj.getPlayerPreLosingBattleFP());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.econ.MonthlyReport.class));
                out.write(serializer.serialize(obj.getPreviousReport()));
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

    public com.fs.starfarer.api.impl.campaign.shared.SharedData deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.impl.campaign.shared.SharedData obj = objenesis.newInstance(com.fs.starfarer.api.impl.campaign.shared.SharedData.class);
        return obj;
    }

}
