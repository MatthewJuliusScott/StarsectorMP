
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import com.fs.starfarer.ui.newui.HintPanel.SavedHintData;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class CampaignUIPersistentDataSerializer {


    public byte[] serialize(com.fs.starfarer.campaign.CampaignUIPersistentData obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeBoolean(obj.isCampaignMapCoordinatesValid());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.LocationAPI.class));
                out.write(serializer.serialize(obj.getCampaignMapLocation()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeFloat(obj.getCampaignMapZoom());
            out.writeFloat(obj.getCampaignZoom());
            out.writeInt(obj.getCargoSubmarketIndex());
            String[] checkedRefitTagsArray = obj.getCheckedRefitTags().toArray(new String[ 0 ] );
            out.writeInt(checkedRefitTagsArray.length);
            for (int i = 0; (i<checkedRefitTagsArray.length); i ++) {
                out.writeUTF(checkedRefitTagsArray[i]);
            }
            out.writeInt(obj.getCommodityGraphMode());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.SectorEntityToken.class));
                out.write(serializer.serialize(obj.getCourseTarget()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeInt(obj.getFleetSubmarketIndex());
            out.writeBoolean(obj.isHideInstalledHullmods());
            out.writeBoolean(obj.isHyperMapCoordinatesValid());
            out.writeFloat(obj.getHyperMapZoom());
            out.writeUTF(obj.getLastMarketId());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.campaign.fleet.FleetMember.class));
                out.write(serializer.serialize(obj.getLastSelectedForRefit()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeBoolean(obj.isOpenedCharacterTabSinceLevelUp());
            out.writeInt(obj.getRefitAddModTabIndex());
            SavedHintData[] savedHintsArray = obj.getSavedHints().toArray(new SavedHintData[ 0 ] );
            out.writeInt(savedHintsArray.length);
            for (int i = 0; (i<savedHintsArray.length); i ++) {
            }
            out.writeBoolean(obj.isShowOnlyInstallableHullmods());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
    }

    public com.fs.starfarer.campaign.CampaignUIPersistentData deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.campaign.CampaignUIPersistentData obj = objenesis.newInstance(com.fs.starfarer.campaign.CampaignUIPersistentData.class);
        return obj;
    }

}
