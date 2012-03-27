package uk.co.gavd.timeismoney;

import android.os.CountDownTimer;

public class CostTimer extends CountDownTimer {
	
	private int secsElapsed = 0;
	private int pencePerHour = 11500; // should be passed into setPencePerHour
	private int people = 2; // should be passed into setPeople
	
	private SecondScreenActivity view;
	
	private static final long MILLISECONDS_BETWEEN_TICKS = 1000;
	private static final int SECONDS_PER_HOUR = 60 * 60;
	private static final int PENCE_PER_POUND = 100;
	
    public CostTimer(SecondScreenActivity view) {
    	
    	// TODO how should this be used? i.e. what should we put
    	// for millisInFuture - should we just count down second by
    	// second?
    	super(MILLISECONDS_BETWEEN_TICKS * 1000000, MILLISECONDS_BETWEEN_TICKS);
    	
    	this.view = view;
    }
    
    public void onTick(long millisUntilFinished) {
    	secsElapsed++;
    	this.view.reportCost(this.calculateCostInPounds());
    }
    
    public void onFinish() {
//    	Log.println(Log.INFO, "box", "onFinish; restarting timer");
    	// TODO if something runs this long it is an error
    	this.start();
    }
    
    public void reset() {
    	super.cancel();
    	this.secsElapsed = 0;
    }

    private double calculateCostInPounds() {
    	return calculateCostInPence() / PENCE_PER_POUND;
    }
    
    private double calculateCostInPence() {
    	double hoursElapsed = (double)secsElapsed / (double)SECONDS_PER_HOUR;
    	return hoursElapsed * getPencePerHour() * getPeople();
    }

	public void setPencePerHour(int pencePerHour) {
		this.pencePerHour = pencePerHour;
	}

	public int getPencePerHour() {
		return pencePerHour;
	}

	public void setPeople(int people) {
		this.people = people;
	}

	public int getPeople() {
		return people;
	}
	
	public int getSeconds() {
		return this.secsElapsed;
	}
}