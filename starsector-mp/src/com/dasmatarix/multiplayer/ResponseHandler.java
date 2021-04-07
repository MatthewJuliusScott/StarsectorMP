
package com.dasmatarix.multiplayer;

import org.lazywizard.console.Console;

import com.fs.starfarer.api.Global;

public class ResponseHandler {

	private byte[] rsp = null;

	public synchronized boolean handleResponse(byte[] rsp) {
		this.rsp = rsp;
		this.notify();
		return true;
	}

	public synchronized void waitForResponse() {
		while (this.rsp == null) {
			try {
				this.wait();
			} catch (InterruptedException e) {
			}
		}

		System.out.println(new String(this.rsp));
		// Global.getSector().getCampaignUI().addMessage(new String(this.rsp));
	}
}