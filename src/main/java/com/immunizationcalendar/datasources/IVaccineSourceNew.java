package com.immunizationcalendar.datasources;

import com.immunizationcalendar.model.Disease;
import com.immunizationcalendar.model.VaccineType;


import java.util.List;

/**
 * A source of data (incl. {@link VaccineType}s and their initial settings) for a given "vaccination plan" or
 * "scheme".
 */
public interface IVaccineSourceNew {
    /**
     * Supplies data for this vaccination plan (scheme).
     * @return The list of {@link VaccineType}s in this plan with their default settings. Includes vaccines that are
     * optional and/or not selected by default.
     */
    List<VaccineType> getVaccines();
    List<Disease> getDiseases();
}
