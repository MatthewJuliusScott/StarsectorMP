
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class IntelTabDataSerializer {


    public byte[] serialize(com.fs.starfarer.campaign.comms.IntelTabData obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            SectorEntityToken[] commSnifferLocationsArray = obj.getCommSnifferLocations().toArray(new SectorEntityToken[ 0 ] );
            out.writeInt(commSnifferLocationsArray.length);
            for (int i = 0; (i<commSnifferLocationsArray.length); i ++) {
                try {
                    ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.SectorEntityToken.class));
                    out.write(serializer.serialize(commSnifferLocationsArray[i]));
                } catch (SerializerNotFoundException _x) {
                }
            }
            String[] intelTagsArray = obj.getIntelTags().toArray(new String[ 0 ] );
            out.writeInt(intelTagsArray.length);
            for (int i = 0; (i<intelTagsArray.length); i ++) {
                out.writeUTF(intelTagsArray[i]);
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.comm.IntelInfoPlugin.class));
                out.write(serializer.serialize(obj.getSelectedIntel()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeInt(obj.getSelectedSubtabIndex());
            out.writeInt(obj.getSelectedTabIndex());
            out.writeBoolean(obj.isShowMapCheckedOnIntelScreen());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
    }

    public com.fs.starfarer.campaign.comms.IntelTabData deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.campaign.comms.IntelTabData obj = objenesis.newInstance(com.fs.starfarer.campaign.comms.IntelTabData.class);
        return obj;
    }

}
