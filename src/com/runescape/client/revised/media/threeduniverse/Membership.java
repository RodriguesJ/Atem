package com.runescape.client.revised.media.threeduniverse;

import java.util.Calendar;

public class Membership {

	private Calendar calendar;
	private byte seconds;
	private byte minutes;
	private byte hours;
	private byte days;
	private byte weeks;
	private byte months;
	private int years;

	public Membership() {
		this.setCalendar(Calendar.getInstance());
		this.setSeconds((byte) this.getCalendar().get(Calendar.SECOND));
		this.setMinutes((byte) this.getCalendar().get(Calendar.MINUTE));
		this.setHours((byte) this.getCalendar().get(Calendar.HOUR));
		this.setDays((byte) this.getCalendar().get(Calendar.DAY_OF_MONTH));
		this.setWeeks((byte) this.getCalendar().get(Calendar.WEEK_OF_MONTH));
		this.setMonths((byte) this.getCalendar().get(Calendar.MONTH));
		this.setYears(this.getCalendar().get(Calendar.YEAR));
	}

	public byte getSecondsLeft() {
		final Calendar calendar = Calendar.getInstance();
		if (this.getSeconds() > calendar.get(Calendar.SECOND)) {
			final byte secondsLeft = (byte) (this.getSeconds() - calendar.get(Calendar.SECOND));
			byte seconds = this.getSeconds();
			seconds -= secondsLeft;
			this.setSeconds(seconds);
			return this.getSeconds();
		}
		final byte secondsLeft = (byte) (this.getSeconds() - calendar.get(Calendar.SECOND));
		byte seconds = this.getSeconds();
		seconds += secondsLeft;
		this.setSeconds(seconds);
		return this.getSeconds();
	}

	public byte getMinutesLeft() {
		final Calendar calendar = Calendar.getInstance();
		if (this.getMinutes() > calendar.get(Calendar.MINUTE)) {
			final byte minutesLeft = (byte) (this.getMinutes() - calendar.get(Calendar.MINUTE));
			byte minutes = this.getMinutes();
			minutes -= minutesLeft;
			this.setMinutes(minutes);
			return this.getMinutes();
		}
		final byte minutesLeft = (byte) (this.getMinutes() - calendar.get(Calendar.MINUTE));
		byte minutes = this.getMinutes();
		minutes += minutesLeft;
		this.setMinutes(minutes);
		return this.getMinutes();
	}

	public byte getHoursLeft() {
		final Calendar calendar = Calendar.getInstance();
		if (this.getHours() > calendar.get(Calendar.HOUR)) {
			final byte hoursLeft = (byte) (this.getHours() - calendar.get(Calendar.HOUR));
			byte hours = this.getHours();
			hours -= hoursLeft;
			this.setHours(hours);
			return this.getHours();
		}
		final byte hoursLeft = (byte) (this.getHours() - calendar.get(Calendar.HOUR));
		byte hours = this.getHours();
		hours += hoursLeft;
		this.setHours(hours);
		return this.getHours();
	}

	public byte getDaysLeft() {
		final Calendar calendar = Calendar.getInstance();
		if (this.getDays() > calendar.get(Calendar.DAY_OF_MONTH)) {
			final byte daysLeft = (byte) (this.getDays() - calendar.get(Calendar.DAY_OF_MONTH));
			byte days = this.getDays();
			days -= daysLeft;
			this.setDays(days);
			return this.getDays();
		}
		final byte daysLeft = (byte) (this.getDays() - calendar.get(Calendar.DAY_OF_MONTH));
		byte days = this.getDays();
		days += daysLeft;
		this.setDays(days);
		return this.getDays();
	}

	public byte getWeeksLeft() {
		final Calendar calendar = Calendar.getInstance();
		if (this.getWeeks() > calendar.get(Calendar.WEEK_OF_MONTH)) {
			final byte weeksLeft = (byte) (this.getWeeks() - calendar.get(Calendar.WEEK_OF_MONTH));
			byte weeks = this.getWeeks();
			weeks -= weeksLeft;
			this.setWeeks(weeks);
			return this.getWeeks();
		}
		final byte weeksLeft = (byte) (this.getWeeks() - calendar.get(Calendar.WEEK_OF_MONTH));
		byte weeks = this.getWeeks();
		weeks += weeksLeft;
		this.setWeeks(weeks);
		return this.getWeeks();
	}

	public byte getMonthsLeft() {
		final Calendar calendar = Calendar.getInstance();
		if (this.getMonths() > calendar.get(Calendar.MONTH)) {
			final byte monthsLeft = (byte) (this.getMonths() - calendar.get(Calendar.MONTH));
			byte months = this.getMonths();
			months -= monthsLeft;
			this.setMonths(months);
			return this.getMonths();
		}
		final byte monthsLeft = (byte) (this.getMonths() - calendar.get(Calendar.MONTH));
		byte months = this.getMonths();
		months += monthsLeft;
		this.setMonths(months);
		return this.getMonths();
	}

	public int getYearsLeft() {
		final Calendar calendar = Calendar.getInstance();
		if (this.getYears() > calendar.get(Calendar.YEAR)) {
			final int yearsLeft = this.getYears() - calendar.get(Calendar.YEAR);
			int years = this.getYears();
			years -= yearsLeft;
			this.setYears(years);
			return this.getYears();
		}
		final int yearsLeft = this.getYears() - calendar.get(Calendar.YEAR);
		int years = this.getYears();
		years += yearsLeft;
		this.setYears(years);
		return this.getYears();
	}

	public String getLastLoggedIn() {
		return "You last logged in " + this.getSecondsLeft() + " seconds, " + this.getMinutesLeft() + " minutes, " + this.getHoursLeft() + " hours, " + this.getDaysLeft() + " days, " + this.getWeeksLeft() + " weeks, " + this.getMonthsLeft() + " months, " + this.getYearsLeft() + " years";
	}

	public void resetMembership() {
		if (this.getSecondsLeft() <= 0) {
			this.setCalendar(null);
			this.setDays((byte) -1);
			this.setHours((byte) -1);
			this.setMinutes((byte) -1);
			this.setMonths((byte) -1);
			this.setSeconds((byte) -1);
			this.setWeeks((byte) -1);
			this.setYears(-1);
		}
	}

	public void setCalendar(final Calendar calendar) {
		this.calendar = calendar;
	}

	public Calendar getCalendar() {
		return this.calendar;
	}

	public void setSeconds(final byte seconds) {
		this.seconds = seconds;
	}

	public byte getSeconds() {
		return this.seconds;
	}

	public void setMinutes(final byte minutes) {
		this.minutes = minutes;
	}

	public byte getMinutes() {
		return this.minutes;
	}

	public void setHours(final byte hours) {
		this.hours = hours;
	}

	public byte getHours() {
		return this.hours;
	}

	public void setDays(final byte days) {
		this.days = days;
	}

	public byte getDays() {
		return this.days;
	}

	public void setWeeks(final byte weeks) {
		this.weeks = weeks;
	}

	public byte getWeeks() {
		return this.weeks;
	}

	public void setMonths(final byte months) {
		this.months = months;
	}

	public byte getMonths() {
		return this.months;
	}

	public void setYears(final int years) {
		this.years = years;
	}

	public int getYears() {
		return this.years;
	}
}