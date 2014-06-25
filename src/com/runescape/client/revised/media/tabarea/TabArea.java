package com.runescape.client.revised.media.tabarea;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.media.j3d.ImageComponent;
import javax.media.j3d.ImageComponent2D;
import javax.vecmath.Vector3d;

import com.runescape.client.revised.AbstractSprite;
import com.runescape.client.revised.media.tabarea.armour.ArmourTab;
import com.runescape.client.revised.media.tabarea.combat.CombatTab;
import com.runescape.client.revised.media.tabarea.inventory.InventoryTab;
import com.runescape.client.revised.media.tabarea.logout.LogoutTab;
import com.runescape.client.revised.media.tabarea.magic.MagicTab;
import com.runescape.client.revised.media.tabarea.music.MusicTab;
import com.runescape.client.revised.media.tabarea.prayer.PrayerTab;
import com.runescape.client.revised.media.tabarea.quest.QuestTab;
import com.runescape.client.revised.media.tabarea.run.RunTab;
import com.runescape.client.revised.media.tabarea.settings.SettingsTab;
import com.runescape.client.revised.media.tabarea.skill.SkillTab;
import com.runescape.client.revised.media.tabarea.social.friend.FriendTab;
import com.runescape.client.revised.media.tabarea.social.ignore.IgnoreTab;
import com.runescape.client.revised.media.threeduniverse.Location;
import com.runescape.client.revised.media.threeduniverse.LocationArea;
import com.runescape.client.revised.model.entity.player.GamePlayer;

public class TabArea extends AbstractSprite {

	private AbstractTab[] tabs;
	private GamePlayer player;

	public TabArea(final GamePlayer player) {
		this.setPlayer(player);
		this.setTabs(new AbstractTab[13]);
		this.getTabs()[0] = new CombatTab(player);
		this.getTabs()[1] = new SkillTab(player);
		this.getTabs()[2] = new QuestTab(player);
		this.getTabs()[3] = new InventoryTab(player);
		this.getTabs()[4] = new ArmourTab(player);
		this.getTabs()[5] = new PrayerTab(player);
		this.getTabs()[6] = new MagicTab(player);
		// this.getTabs()[7] = new OtherTab(player);
		this.getTabs()[7] = new FriendTab(player);
		this.getTabs()[8] = new IgnoreTab(player);
		this.getTabs()[9] = new LogoutTab(player);
		this.getTabs()[10] = new SettingsTab(player);
		this.getTabs()[11] = new RunTab(player);
		this.getTabs()[12] = new MusicTab(player);
	}

	@Override
	public Location getLocation() {
		return new Location(new Vector3d((short) 519, (short) 167, (byte) -1));
	}

	@Override
	public LocationArea getLocationArea() {
		return LocationArea.TAB_AREA;
	}

	@Override
	public ImageComponent2D getImage() {
		try {
			return new ImageComponent2D(ImageComponent.FORMAT_RGB, ImageIO.read(new File("./images/tabarea.png")));
		} catch (final IOException ioe) {
			ioe.printStackTrace();
		}
		return null;
	}

	public void setTabs(final AbstractTab[] tabs) {
		this.tabs = tabs;
	}

	public AbstractTab[] getTabs() {
		return this.tabs;
	}

	public void setPlayer(final GamePlayer player) {
		this.player = player;
	}

	public GamePlayer getPlayer() {
		return this.player;
	}
}