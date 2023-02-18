package com.immunizationcalendar;

import com.immunizationcalendar.datasources.IVaccineSourceNew;
import com.immunizationcalendar.model.BaseScheme;
import com.immunizationcalendar.model.Disease;
import com.immunizationcalendar.model.Recommendation;
import com.immunizationcalendar.model.VaccineType;


import java.util.ArrayList;
import java.util.List;


/**
 * Mock implementation of the initial data provider. Creates and serves fake data, such as vaccine names, number of
 * vaccinations etc.
 * <p><s>Proper implementation will probably read the data from a JSON file.</s> <strong>Nope, that costs a ton in terms of
 * JS size!</strong>
 * </strong></p>
 */
public class DataProvider {
    /**
     * Currently used Source of vaccination data.
     */
    //private IVaccineSource vaccinesSource;
    /**
     * All available vaccines in the currently selected vaccination plan. Includes defaults and optionals.
     */
    private final List<VaccineType> vaccines;
    /**
     * A list of all {@link BaseScheme}s that can be selected by the user. The schemes only *visually*
     * represent the vaccination plans and don't hold any vaccination data themselves (the data is provided by
     * {@link IVaccineSource}s instead).
     */
    private final List<BaseScheme> schemes;
    private final List<Disease> diseases;

    public DataProvider(IVaccineSourceNew vaccinesSource) {
        // TODO: Should probably copy the list of vaccines from the source!

        // this.vaccinesSource = vaccinesSource;
        vaccines = vaccinesSource.getVaccines();
        diseases = vaccinesSource.getDiseases();
        schemes = buildSchemesList();
    }


    //TODO: Switch to an immutable list (unmodifiableList).

    /**
     * Helper function for creating a list of vaccination "plan" names and basic data. An example of a "plan" would
     * be the government-funded free plan, or the plan which uses the paid 6-in-1 polyivalent DTP+IPV+Hib+WZW vaccine.
     *
     * @return A list containing the supported {@link BaseScheme}s.
     */
    private List<BaseScheme> buildSchemesList() {
        List<BaseScheme> schemesList = new ArrayList<>();

        BaseScheme scheme1 = new BaseScheme("Darmowe dla plebsu");
        for (Disease disease : diseases) {
            if (disease.getRecommendation().equals(Recommendation.MANDATORY)){
                scheme1.addDisease(disease);
                scheme1.addVaccine(disease.getDefaultVaccine());
            }
        }
        schemesList.add(scheme1);

        BaseScheme scheme2 = new BaseScheme("obowiązkowe, ale z 3w1");
        List<VaccineType> vaccinesForScheme2 = new ArrayList<>(scheme1.getVaccinesList());
        switchToPolyvalent(vaccinesForScheme2, "szczepionka 3 w 1");
        for (VaccineType vaccineType : vaccinesForScheme2) {
            scheme2.addVaccine(vaccineType);
            for (Disease disease : vaccineType.getDisease()) {
                scheme2.addDisease(disease);
            }
        }
        schemesList.add(scheme2);

        schemesList.add(new BaseScheme("Jestę antyszczepę"));

        BaseScheme scheme4 = new BaseScheme("LUBIE STRZYKAWKI :) ");
        for (Disease disease : diseases) {
            scheme4.addVaccine(disease.getDefaultVaccine());
            scheme4.addDisease(disease);
        }
        schemesList.add(scheme4);

        BaseScheme scheme5 = new BaseScheme("wszystko na bogato");
        List<VaccineType> vaccinesForScheme5 = new ArrayList<>(scheme4.getVaccinesList());
        switchToPolyvalent(vaccinesForScheme5, "szczepionka 3 w 1");
        for (VaccineType vaccineType : vaccinesForScheme5) {
            scheme5.addVaccine(vaccineType);
            for (Disease disease : vaccineType.getDisease()) {
                scheme5.addDisease(disease);
            }
        }
        schemesList.add(scheme5);

        return schemesList;
    }

    private void switchToPolyvalent(List<VaccineType> vaccinesForScheme, String polyvalentVaccName) {
        VaccineType polyVaccine = null;
        for (VaccineType vaccine : vaccines) {
            if (vaccine.getName().equals(polyvalentVaccName)) {
                polyVaccine = vaccine;
                vaccinesForScheme.add(polyVaccine);
            }
        }
        if (polyVaccine != null) {
            for (Disease disease : polyVaccine.getDisease()) {
                vaccinesForScheme.remove(disease.getDefaultVaccine());
            }
        }
    }

    /**
     * Gets the list of all supported {@link BaseScheme}s. <strong>The list is currently mutable.</strong> It's
     * used for displaying the "vaccination plan" radio select group.
     *
     * @return a list of all supported {@link BaseScheme}s.
     */
    public List<BaseScheme> getSchemes() {
        return schemes;
    }

    /**
     * Gets the list of all potential {@link VaccineType}s. <strong>The list is mutable.</strong> It's used for
     * displaying the vaccine selection, the results (suggested vaccination dates), as well as tracking the selection
     * toggle.
     *
     * @return all supported vaccines.
     */
    public List<VaccineType> getVaccines() {
        return vaccines;
    }

    public List<Disease> getDiseases() {
        return diseases;
    }

    // TODO: Copy the list from the source. Make the original list immutable.

    /**
     * Updates the currently selected {@link BaseScheme}, which involves replacing the list of available
     * {@link VaccineType}s.
     *
     * @param newSource the newly selected source of data, corresponding to the chosen scheme.
     */
//    public void changeChosenVaccinationScheme(IVaccineSource newSource) {
//        vaccinesSource = newSource;
//
//        vaccines.clear();
//        vaccines.addAll(vaccinesSource.getVaccines());
//    }
    public void setBaseScheme(int schemeId) {

    }

}
