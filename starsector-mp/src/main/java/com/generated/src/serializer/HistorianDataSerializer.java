
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import com.fs.starfarer.api.impl.campaign.intel.bar.events.historian.HistorianBackstory.HistorianBackstoryInfo;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class HistorianDataSerializer {


    public byte[] serialize(com.fs.starfarer.api.impl.campaign.intel.bar.events.historian.HistorianData obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            HistorianBackstoryInfo[] backstoryArray = obj.getBackstory().toArray(new HistorianBackstoryInfo[ 0 ] );
            out.writeInt(backstoryArray.length);
            for (int i = 0; (i<backstoryArray.length); i ++) {
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.util.List.class));
                out.write(serializer.serialize(obj.getCreators()));
            } catch (SerializerNotFoundException _x) {
            }
            java.lang.String[] givenOffersArray = obj.getGivenOffers().toArray(new java.lang.String[ 0 ] );
            out.writeInt(givenOffersArray.length);
            for (int i = 0; (i<givenOffersArray.length); i ++) {
                out.writeUTF(givenOffersArray[i]);
            }
            out.writeBoolean(obj.isIntroduced());
            java.lang.String[] shownBackstoryArray = obj.getShownBackstory().toArray(new java.lang.String[ 0 ] );
            out.writeInt(shownBackstoryArray.length);
            for (int i = 0; (i<shownBackstoryArray.length); i ++) {
                out.writeUTF(shownBackstoryArray[i]);
            }
            out.writeInt(obj.getTier());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
    }

    public com.fs.starfarer.api.impl.campaign.intel.bar.events.historian.HistorianData deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.impl.campaign.intel.bar.events.historian.HistorianData obj = objenesis.newInstance(com.fs.starfarer.api.impl.campaign.intel.bar.events.historian.HistorianData.class);
        return obj;
    }

}
