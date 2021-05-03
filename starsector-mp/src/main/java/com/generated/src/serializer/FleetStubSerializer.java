
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import com.fs.starfarer.api.EveryFrameScript;
import com.fs.starfarer.campaign.ai.CampaignFleetAI.FleetAssignmentData;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class FleetStubSerializer {


    public byte[] serialize(com.fs.starfarer.campaign.fleet.FleetStub obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeUTF(obj.getAdmiralPost());
            out.writeUTF(obj.getAdmiralRank());
            FleetAssignmentData[] assignmentsArray = obj.getAssignments().toArray(new FleetAssignmentData[ 0 ] );
            out.writeInt(assignmentsArray.length);
            for (int i = 0; (i<assignmentsArray.length); i ++) {
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.LocationAPI.class));
                out.write(serializer.serialize(obj.getContainingLocation()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeUTF(obj.getId());
            EveryFrameScript[] scriptsArray = obj.getScripts().toArray(new EveryFrameScript[ 0 ] );
            out.writeInt(scriptsArray.length);
            for (int i = 0; (i<scriptsArray.length); i ++) {
                try {
                    ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.EveryFrameScript.class));
                    out.write(serializer.serialize(scriptsArray[i]));
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
        return obj.getLocation().writeObject();
        return obj.getLocationInHyperspace().writeObject();
    }

    public com.fs.starfarer.campaign.fleet.FleetStub deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.campaign.fleet.FleetStub obj = objenesis.newInstance(com.fs.starfarer.campaign.fleet.FleetStub.class);
        return obj;
    }

}
