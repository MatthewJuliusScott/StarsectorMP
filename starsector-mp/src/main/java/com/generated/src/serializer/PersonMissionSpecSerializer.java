
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class PersonMissionSpecSerializer {


    public byte[] serialize(com.fs.starfarer.api.loading.PersonMissionSpec obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeFloat(obj.getFreq());
            out.writeUTF(obj.getIcon());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.PersonImportance.class));
                out.write(serializer.serialize(obj.getImportance()));
            } catch (SerializerNotFoundException _x) {
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.RepLevel.class));
                out.write(serializer.serialize(obj.getMaxRep()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeFloat(obj.getMaxTimeout());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.RepLevel.class));
                out.write(serializer.serialize(obj.getMinRep()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeFloat(obj.getMinTimeout());
            out.writeUTF(obj.getMissionId());
            out.writeUTF(obj.getPersonId());
            out.writeUTF(obj.getPluginClass());
            java.lang.String[] reqMissionAllArray = obj.getReqMissionAll().toArray(new java.lang.String[ 0 ] );
            out.writeInt(reqMissionAllArray.length);
            for (int i = 0; (i<reqMissionAllArray.length); i ++) {
                out.writeUTF(reqMissionAllArray[i]);
            }
            java.lang.String[] reqMissionAnyArray = obj.getReqMissionAny().toArray(new java.lang.String[ 0 ] );
            out.writeInt(reqMissionAnyArray.length);
            for (int i = 0; (i<reqMissionAnyArray.length); i ++) {
                out.writeUTF(reqMissionAnyArray[i]);
            }
            java.lang.String[] reqMissionNoneArray = obj.getReqMissionNone().toArray(new java.lang.String[ 0 ] );
            out.writeInt(reqMissionNoneArray.length);
            for (int i = 0; (i<reqMissionNoneArray.length); i ++) {
                out.writeUTF(reqMissionNoneArray[i]);
            }
            java.lang.String[] tagsArray = obj.getTags().toArray(new java.lang.String[ 0 ] );
            out.writeInt(tagsArray.length);
            for (int i = 0; (i<tagsArray.length); i ++) {
                out.writeUTF(tagsArray[i]);
            }
            java.lang.String[] tagsAllArray = obj.getTagsAll().toArray(new java.lang.String[ 0 ] );
            out.writeInt(tagsAllArray.length);
            for (int i = 0; (i<tagsAllArray.length); i ++) {
                out.writeUTF(tagsAllArray[i]);
            }
            java.lang.String[] tagsAnyArray = obj.getTagsAny().toArray(new java.lang.String[ 0 ] );
            out.writeInt(tagsAnyArray.length);
            for (int i = 0; (i<tagsAnyArray.length); i ++) {
                out.writeUTF(tagsAnyArray[i]);
            }
            java.lang.String[] tagsNotAnyArray = obj.getTagsNotAny().toArray(new java.lang.String[ 0 ] );
            out.writeInt(tagsNotAnyArray.length);
            for (int i = 0; (i<tagsNotAnyArray.length); i ++) {
                out.writeUTF(tagsNotAnyArray[i]);
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

    public com.fs.starfarer.api.loading.PersonMissionSpec deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.loading.PersonMissionSpec obj = objenesis.newInstance(com.fs.starfarer.api.loading.PersonMissionSpec.class);
        return obj;
    }

}
