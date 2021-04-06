package com.dasmatarix.multiplayer;

import com.fs.starfarer.api.EveryFrameScript;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.impl.campaign.tutorial.TutorialWelcomeDialogPluginImpl;
import com.fs.starfarer.api.ui.HintPanelAPI;

public class MultiplayerModSetupScript implements EveryFrameScript {

	public static enum MultiplayerSetupStage {
		WAIT_TO_CHOOSE_CLIENT_OR_SERVER,
		FINISHED
	}

	protected MultiplayerSetupStage stage = MultiplayerSetupStage.WAIT_TO_CHOOSE_CLIENT_OR_SERVER;

	@Override
	public void advance(float amount) {

		// I think maybe it's a good idea to let the sector finish processing before trying to replace its values, to test
		if (Global.getSector().isInFastAdvance()) {
			return;
		}

		HintPanelAPI hints = Global.getSector().getCampaignUI().getHintPanel();

		// wait for hints to become available
		if (hints == null) {
			return;
		}

		if (stage == MultiplayerSetupStage.WAIT_TO_CHOOSE_CLIENT_OR_SERVER) {
			// the TutorialWelcomeDialogPluginImpl handles input and the result, replace with new class and pass by reference
			// things we need to keep track of
			if (Global.getSector().getCampaignUI().showInteractionDialog(new TutorialWelcomeDialogPluginImpl(), null)) {
				stage = MultiplayerSetupStage.FINISHED;
			}
			return;
		}

	}

	@Override
	public boolean isDone() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean runWhilePaused() {
		return true;
	}

}
