package com.immunizationcalendar;

import com.immunizationcalendar.model.BaseScheme;
import com.immunizationcalendar.model.VaccineType;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the user's choice of vaccines
 */
public class VaccineSelector {
    /**
     * Source of internal data.
     */
    private final DataProvider dataProvider;

    /**
     * List of supported vaccines, taken from the {@link #dataProvider}.
     */
    private final List<VaccineType> vaccines;

    private final List<BaseScheme> schemes;

    /**
     * Constructs the initial Form, pulling vaccine data from the provided {@link DataProvider}.
     *
     * @param dataProvider Source of supported vaccine data.
     */

    public VaccineSelector(DataProvider dataProvider) {
        this.dataProvider = dataProvider;
        this.vaccines = dataProvider.getVaccines();
        this.schemes = dataProvider.getSchemes();
    }

    public List<VaccineType> getSelectedVaccines() {
        List<VaccineType> resultList = new ArrayList<>();
        for (VaccineType vaccine : vaccines) {
            if (vaccine.isSelected())
                resultList.add(vaccine);
        }
        return resultList;
    }

    public List<BaseScheme> getSelectedSchemes() {
        List<BaseScheme> resultList = new ArrayList<>();
        for (BaseScheme scheme : schemes) {
            if (scheme.isChecked())
                resultList.add(scheme);
        }
        return resultList;
    }


}
