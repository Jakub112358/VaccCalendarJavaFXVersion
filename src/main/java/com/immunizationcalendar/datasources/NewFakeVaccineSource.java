package com.immunizationcalendar.datasources;

import com.immunizationcalendar.model.Disease;
import com.immunizationcalendar.model.Recommendation;
import com.immunizationcalendar.model.VaccineType;

import java.util.ArrayList;
import java.util.List;

public class NewFakeVaccineSource implements IVaccineSourceNew {

    private static NewFakeVaccineSource instance;

    private List<Disease> diseaseList;
    private List<VaccineType> vaccineList;

    private NewFakeVaccineSource() {

        diseaseList = new ArrayList<>();
        Disease test1Of3 = new Disease("1of3", Recommendation.MANDATORY);
        Disease test2Of3 = new Disease("2of3", Recommendation.MANDATORY);
        Disease test3Of3 = new Disease("3of3", Recommendation.MANDATORY);
        Disease testMandatory = new Disease("disease with mandatory vacc", Recommendation.MANDATORY);
        Disease testRecommended = new Disease("disease with recommended vacc", Recommendation.RECOMMENDED);
        Disease testOptional = new Disease("disease with optional vacc", Recommendation.OPTIONAL);

        vaccineList = new ArrayList<>();
        VaccineType vaccine1 = new VaccineType.Builder()
                .withDisease(test1Of3)
                .create("szczepionka 1 z 3");

        VaccineType vaccine2 = new VaccineType.Builder()
                .withDisease(test2Of3)
                .create("szczepionka 2 z 3");

        VaccineType vaccine3 = new VaccineType.Builder()
                .withDisease(test3Of3)
                .create("szczepionka 3 z 3");

        VaccineType vaccineAllInOne = new VaccineType.Builder()
                .withDisease(test1Of3,test2Of3,test3Of3)
                .create("szczepionka 3 w 1");

        VaccineType vaccineMandatory = new VaccineType.Builder()
                .withDisease(testMandatory)
                .create("szczepionka obowiazkowa");

        VaccineType vaccineMandatoryNotDefaultVersion = new VaccineType.Builder()
                .withDisease(testMandatory)
                .create("szczepionka obowiazkowa dziwna");

        VaccineType vaccineRecommended = new VaccineType.Builder()
                .withDisease(testRecommended)
                .create("szczepionka zalecana");

        VaccineType vaccineOptional = new VaccineType.Builder()
                .withDisease(testOptional)
                .create("szczepionka opcjonalna");

        vaccineList.addAll(List.of(vaccine1,vaccine2,vaccine3,vaccineAllInOne,vaccineMandatory,vaccineMandatoryNotDefaultVersion,vaccineRecommended,vaccineOptional));

        test1Of3.setDefaultVaccine(vaccine1);
        test2Of3.setDefaultVaccine(vaccine2);
        test3Of3.setDefaultVaccine(vaccine3);
        testMandatory.setDefaultVaccine(vaccineMandatory);
        testRecommended.setDefaultVaccine(vaccineRecommended);
        testOptional.setDefaultVaccine(vaccineOptional);

        diseaseList.addAll(List.of(test1Of3, test2Of3, test3Of3, testMandatory, testRecommended, testOptional));
    }


    public static NewFakeVaccineSource getInstance() {
        if (instance == null)
            instance = new NewFakeVaccineSource();
        return instance;
    }

    @Override
    public List<VaccineType> getVaccines() {
        return vaccineList;
    }

    @Override
    public List<Disease> getDiseases() {
        return diseaseList;
    }
}
