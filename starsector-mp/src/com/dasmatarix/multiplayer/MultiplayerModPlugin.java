package com.dasmatarix.multiplayer;

import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;

public class MultiplayerModPlugin extends BaseModPlugin {
	@Override
	public void onGameLoad(boolean newGame) {
		super.onGameLoad(newGame);
		Global.getSector().addScript(new MultiplayerModSetupScript());
	}
}
