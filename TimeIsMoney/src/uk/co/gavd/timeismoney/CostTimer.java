package uk.co.gavd.timeismoney;

import android.os.CountDownTimer;

/**
 * A timer that counts up and calculates a running cost based upon
 * the number of people in the meeting and the hourly rate supplied
 * to the app
 * 
 * @author Gavin Davies http://www.gavd.co.uk/
 */
public class CostTimer extends CountDownTimer {

    private static final long MILLISECONDS_BETWEEN_TICKS = 1000;
    private static final int SECONDS_PER_HOUR = 60 * 60;
    private static final int PENCE_PER_POUND = 100;
    
    /**
     * Time since the timer was started
     */
    private int secsElapsed = 0;
    
    /**
     * Default to the amount in constants, but users can set their own
     * using the setPencePerHour method
     */
    private int pencePerHour = Constants.POUNDS_PER_HOUR * 100; // should be passed into setPencePerHour
    
    /**
     * should be passed into setPeople
     */
    private int people = 2;
    
    /**
     * Link back to the activity so we can push back the timer
     * updates
     */
    private SecondScreenActivity view;
    
    /**
     * Constructor sets up the timer
     * 
     * @param view Activity to report the running cost back to every second
     */
    public CostTimer(SecondScreenActivity view) {
        
        // TODO how should this be used? i.e. what should we put
        // for millisInFuture - should we just count down second by
        // second?
        super(MILLISECONDS_BETWEEN_TICKS * 1000000, MILLISECONDS_BETWEEN_TICKS);
        
        this.view = view;
    }
    
    /**
     * Every time the counter "ticks" (once a second) report the
     * cost back to the View
     */
    public void onTick(long millisUntilFinished) {
        secsElapsed++;
        this.view.reportCost(this.calculateCostInPounds());
    }
    
    /**
     * if something runs this long it is an error
     */
    public void onFinish() {
        this.start();
    }
    
    /**
     * Cancel the timer and reset the elapsed seconds
     */
    public void reset() {
        super.cancel();
        this.secsElapsed = 0;
    }

    /**
     * @return The cost in pence converted to pounds
     */
    private double calculateCostInPounds() {
        return calculateCostInPence() / PENCE_PER_POUND;
    }
    
    /**
     * @return How much it has cost to have the number of people
     * for the amount of time at the pencePerHour rate
     */
    private double calculateCostInPence() {
        double hoursElapsed = (double)secsElapsed / (double)SECONDS_PER_HOUR;
        return hoursElapsed * getPencePerHour() * getPeople();
    }

    /**
     * Use this if you are not using the price defined in Constants
     * @param pencePerHour The number of pence you charge per hour
     */
    public void setPencePerHour(int pencePerHour) {
        this.pencePerHour = pencePerHour;
    }

    /**
     * @return The amount of pennies per hour that the meeting is costing you,
     * calculated on the basis of your hourly rate and the number of people in
     * the meeting
     */
    public int getPencePerHour() {
        return pencePerHour;
    }

    /**
     * @param people The number of people in the meeting
     */
    public void setPeople(int people) {
        this.people = people;
    }

    /**
     * @return The number of people in the meeting
     */
    public int getPeople() {
        return people;
    }
    
    /**
     * @return The number of seconds since the timer was started
     */
    public int getSeconds() {
        return this.secsElapsed;
    }
}