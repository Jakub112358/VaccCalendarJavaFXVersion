package com.immunizationcalendar.model;

import java.util.HashMap;

/**
 * holds all the information about single scheme of concrete vaccination, including number of doses, minimum delay between doses,
 * min and max age of kid for this scheme
 */
public class VaccineScheme {
    private int numberOfDoses;
    /**
     * Minimum offsets between previous and current dose. Key is number of dose, Value is offset in days.
     * First offset is measured from birth, if first offset equals 0 it means that
     * the vaccination should be performed on the first day of life;
     */
     //TODO: consider extracting an internal Range class to hold the offset data.
    private HashMap<Integer, Integer> minOffsets;
    /**
     * as above, but Value[0] means minimum recommended offset, Value[1] means maximum recommended offset
     */
    private HashMap<Integer, Integer[]> recommendedOffsets;
    /**
     * Maximum recommended age for this scheme, if is exceeded program should recommend changing scheme for adequate, or consulting
     * it with doctor.
     *
     */
    private int maxAge;

    public VaccineScheme() {
        this.numberOfDoses = 1;
        this.minOffsets = new HashMap<>();
        this.recommendedOffsets = new HashMap<>();
        this.maxAge = Integer.MAX_VALUE;
    }


    public VaccineScheme(int numberOfDoses, HashMap<Integer, Integer> minOffsets, HashMap<Integer, Integer[]> recommendedOffsets, int maxAge) {
        this.numberOfDoses = numberOfDoses;
        this.minOffsets = minOffsets;
        this.recommendedOffsets = recommendedOffsets;
        this.maxAge = maxAge;
    }

    public int getNumberOfDoses() {
        return numberOfDoses;
    }

    public void setNumberOfDoses(int numberOfDoses) {
        this.numberOfDoses = numberOfDoses;
    }

    public HashMap<Integer, Integer> getMinOffsets() {
        return minOffsets;
    }

    public void setMinOffsets(HashMap<Integer, Integer> minOffsets) {
        this.minOffsets = minOffsets;
    }

    public HashMap<Integer, Integer[]> getRecommendedOffsets() {
        return recommendedOffsets;
    }

    public void setRecommendedOffsets(HashMap<Integer, Integer[]> recommendedOffsets) {
        this.recommendedOffsets = recommendedOffsets;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }
}
