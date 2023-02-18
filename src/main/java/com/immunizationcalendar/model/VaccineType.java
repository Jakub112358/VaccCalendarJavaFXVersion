package com.immunizationcalendar.model;



import com.immunizationcalendar.Form;
import com.immunizationcalendar.utils.RecommendationTableBox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * A <strong>VaccineType</strong> represents all the data associated with a single type of vaccination, such as the name
 * of the product, its numeric identifier, number of doses and the recommended delay between doses.
 * <p>VaccineType objects also track whether the user chose to add the given vaccination to their calendar.</p>
 */
public class VaccineType {

    /**
     * Name of the product.
     */
    private final String name;
    /**
     * The disease that this vaccine is against.
     */
    private final List<Disease> disease;

    /**
     * Unique number, for internal identification, not visible to the end user.
     */
    private final int id;

    /**
     * List of all schemes available for this concrete vaccination. User should have opportunity to add a new scheme and change default one.
     * Default scheme should depend on age of kid.
     */
    private List<VaccineScheme> vaccineSchemesList;

    /**
     * chosen scheme for this vaccination
     */

    private VaccineScheme vaccineScheme;
    // idea of dateOffsets moved to VaccineScheme class.
//    /**
//     * Recommended dates of administering individual doses, represented as <emphasis>offsets in days</emphasis> from the
//     * start point (which is currently equal to the date of the first vaccination).
//     * <p>An offset of "0" means that the given dose should be administered right at the start point.</p>
//     */
//    private int[] dateOffsets;

    /**
     * Display names for individual (offset) doses. If it's null, the default {@link #name} is used instead.
     */
    private final String[] altNames; //nullable

    /**
     * Column setup for Section 4 of the HTML (recommended schedule).
     */
    private final List<RecommendationTableBox> displayBoxes;
    /**
     * Functions that run whenever this VaccineType is selected/deselected.
     */
    //private final Map<VaccineType[], Consumer<VaccineType[]>> vaccineSelectionHandlers;
    private final List<Runnable> vaccineSelectionHandlers;
    // TODO: Note, that the current setup won't work with "live" updating of input. If we need that,
    //  formDataHandlers need to run every time the form gets modified in some way.
    /**
     * Functions that run once when the form is submitted.
     */
    private final List<Consumer<Form>> formDataHandlers;

    /**
     * Is this vaccination selected by the user. <strong>This value is bound bidirectionally with an HTML element
     * </strong>.
     */
    private boolean selected;
    /**
     * hold info about route of administration and if a vaccine is live
     */
    private List<VaccTag> tags;

    /**
     * description of vaccination for user info
     */
    private String description;
    // TODO: Should probably include the recommended RANGE of dates: minimum/maximum??/optimal

    // TODO: Should include a description/tooltip for the end user

    /**
     * Constructs a VaccineType based on Builder data.
     *
     * @param builder the internal Builder instance that takes care of this object's initialization.
     */
    private VaccineType(Builder builder) {
        this.name = builder.name;
        this.disease = builder.disease;
        this.id = builder.id;
        this.vaccineSchemesList = builder.vaccineSchemesList;
        this.vaccineScheme = builder.vaccineScheme;
        this.altNames = builder.altNames;
        this.displayBoxes = builder.displayBoxes;
        this.vaccineSelectionHandlers = builder.vaccineSelectionHandlers;
        this.formDataHandlers = builder.formDataHandlers;
        this.selected = builder.selected;
        this.tags = builder.tags;
        this.description = builder.description;
    }

    /**
     * Handles the construction of {@link VaccineType} instances.
     */
    public static class Builder {
        private static int currentID = 0;
        private String name;
        private List<Disease> disease;
        private int id;
        private List<VaccineScheme> vaccineSchemesList;
        private VaccineScheme vaccineScheme;
        private String[] altNames;
        private List<RecommendationTableBox> displayBoxes;
        //        private Map<VaccineType[], Consumer<VaccineType[]>> vaccineSelectionHandlers;
        private List<Runnable> vaccineSelectionHandlers;
        private List<Consumer<Form>> formDataHandlers;
        private boolean selected;
        private List<VaccTag> tags = new ArrayList<>();
        private String description;

        public Builder withDisease(List<Disease> disease) {
            this.disease = disease;
            return this;
        }
        /**
         * Adds the name of the disease that this {@link VaccineType} works against.
         * @param   disease
         *          enumerated type for the disease.
         * @return
         *          this Builder instance.
         */
        public Builder withDisease(Disease... disease) {
            this.disease = new ArrayList<>();
            this.disease.addAll(Arrays.asList(disease));
            return this;
        }
        /**
         * Adds daily offsets of this {@link VaccineType}s doses. An offset of zero indicates that the dose should be
         * administered on the date of birth.
         * @param   dateOffsets
         *          any number of offsets (measured in days), each indicating a separate dose.
         * @return
         *          this Builder instance.
         */
        //        public Builder withDateOffsets(int... dateOffsets) {
//            this.dateOffsets = dateOffsets;
//            return this;
//        }
        public Builder withVaccineScehemesList (List<VaccineScheme> vaccineScehemesList){
            this.vaccineSchemesList = vaccineScehemesList;
            return this;
        }

        public Builder withVaccineSceheme (VaccineScheme vaccineSceheme){
            this.vaccineScheme = vaccineSceheme;
            return this;
        }

        public Builder withTags (VaccTag... tag){
            this.tags.addAll(Arrays.asList(tag));
            return this;
        }

        public Builder withDescription (String description){
            this.description = description;
            return this;
        }
        /**
         * Adds alternative/variant display names for the individual doses. If no alt names are provided <em>at
         * all</em>, the default {@link #name} will be used for every dose. However, if <em>any</em> alt names are
         * provided, they MUST match the number of {@link #dateOffsets}.
         * @param   altNames
         *          a number of variant display names, exactly one per offset (dose).
         * @return
         *          this Builder instance.
         */
        public Builder withAltNames(String... altNames) {
            this.altNames = altNames;
            return this;
        }
        /**
         * Adds {@link RecommendationTableBox}es which are used to set up the table in Section 4 of the HTML.
         * @param   displayBoxes
         *          a list of display boxes, with information on column setup for Section 4 of the HTML.
         * @return
         *          this Builder instance.
         */
        public Builder withDisplayBoxes(List<RecommendationTableBox> displayBoxes) {
            this.displayBoxes = displayBoxes;
            return this;
        }

        /**
         * Creates an instance of {@link VaccineType}. The ID is automatically incremented with each invocation. The
         * only nullable field is (by design) {@link #altNames}.
         * @param   name
         *          the name of this VaccineType.
         * @param   selected
         *          whether this VaccineType should be selected by default.
         * @return
         *          a properly initialized VaccineType object.
         */
        public VaccineType create(String name) {
            this.name = name;
            if (this.disease == null) this.disease = new ArrayList<>();
            this.id = currentID++;
//            if (this.dateOffsets == null) this.dateOffsets = new int[]{0};
            if (this.vaccineSchemesList == null) this.vaccineSchemesList = new ArrayList<>();
            if (this.vaccineScheme == null) this.vaccineScheme = new VaccineScheme();
            if (this.displayBoxes == null) this.displayBoxes = new ArrayList<>();
            this.vaccineSelectionHandlers = new ArrayList<>();
            this.formDataHandlers = new ArrayList<>();
            this.selected = false;
            if(this.tags == null) this.tags = new ArrayList<>();
            if (this.description == null) this.description = "";
            return new VaccineType(this);
        }
    }

    /**
     * Registers a function that will be executed whenever this VaccineType becomes selected/deselected. Does NOT
     * execute during instantiation - only when the selection changes from initial.
     * @param   handler
     *          a function for handling selection/deselection.
     */
    public void addSelectionHandler(Runnable handler) {
        vaccineSelectionHandlers.add(handler);
    }
    /**
     * Registers a function that will be executed once for this VaccineType when the input {@link Form} becomes
     * submitted.
     * @param   handler
     *          a function for handling Form submission.
     */
    public void addFormDataHandler(Consumer<Form> handler) {
        formDataHandlers.add(handler);
    }

    public String getName() {
        return name;
    }

    public List<Disease> getDisease() {
        return disease;
    }

    public int getId() {
        return id;
    }

//    public int[] getDateOffsets() {
//        return dateOffsets;
//    }
//
//    public void setDateOffsets(int... dateOffsets) {
//        this.dateOffsets = dateOffsets;
//    }

    /**
     * Returns an alt (variant) name for the indicated {@link #dateOffsets} index, if {@link #altNames} are available.
     * Otherwise, returns the default {@link #name}.
     * @param   index
     *          the index of the alt/variant name, corresponding to the index of the associated date offset (dose).
     * @return
     *          the display name for the indicated date offset.
     */
    public String getAltName(int index) {
        if (altNames == null) return name;
        return altNames[index];
    }

    /**
     * <strong>Bidirectionally bound to an HTML input check selector.</strong>
     * @return
     *          <code>true</code> if this VaccineType is currently selected by the user.
     */
    public boolean isSelected() {
        return selected;
    }

    public List<VaccineScheme> getVaccineSchemesList() {
        return vaccineSchemesList;
    }

    public VaccineScheme getVaccineScheme() {
        return vaccineScheme;
    }

    public List<VaccTag> getTags() {
        return tags;
    }

    public String getDescription() {
        return description;
    }

    /**
     * <strong>Bidirectionally bound to an HTML input check selector.</strong>
     * @param   selected
     *          new status for this VaccineType, directly from user input.
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
        applySelectionHandlers();
    }

    public List<RecommendationTableBox> getBoxes() {
        return displayBoxes;
    }

    /**
     * Invokes every function registered as {@link #formDataHandlers} for this VaccineType.
     * @param   form
     *          the current input {@link Form}.
     */
    public void applyFormDataHandlers(Form form) {
        formDataHandlers.forEach(h -> h.accept(form));
    }

    /**
     * Invokes every function registered as {@link #vaccineSelectionHandlers} for this VaccineType.
     */
    private void applySelectionHandlers() {
        vaccineSelectionHandlers.forEach(Runnable::run);
    }
    public boolean hasTag(VaccTag tag){
        return tags.contains(tag);
    }

    // TODO: Probably not necessary in this version, just override .equals.
    /**
     * Utility method for quickly checking if two VaccineType instances refer to the same actual vaccine (by ID).
     * @param   v1
     *          first vaccine to compare.
     * @param   v2
     *          the other vaccine to compare.
     * @return
     *          <code>true</code> if the objects have the same ID.
     */
    public static boolean isSame(VaccineType v1, VaccineType v2) {
        return v1.id == v2.id;
    }


    @Override
    public String toString() {
        return "VaccineType{" +
                "name='" + name + '\'' +
                ", selected=" + selected +
                '}';
    }
}
