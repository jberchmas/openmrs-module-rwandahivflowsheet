package org.openmrs.module.rwandaadulthivflowsheet.impl.pih;

/**
 * Class mirrors the concept ids in the PIH Concept dictionary. NOTE: We would
 * want an abstraction layer, however, this is being done specifically for
 * Rwanda PIH.
 */
public class ConceptDictionary {
	
	public static int TESTS_ORDERED_NON_CODED;
	public static int OIS_THAT_ARE_STIS;
	public static int  NORMAL;
	public static int  ASSISTANCE_REQUIRED ;
	public static int  BEDRIDDEN ;
	public static int  ABSTINENCE ;
	public static int  CONDOMS ;
	public static int  NATURAL_FAMILY_PLANNING ;
	
	public static int ABSOLUTE_LYMPHOCYTE_COUNT;

	public static int ADVERSE_EFFECT;

	public static int ADVERSE_EFFECT_ACTION_TAKEN_NONCODED;
	public static int ADVERSE_EFFECT_CONSTRUCT;
	public static int ADVERSE_EFFECT_DATE;
	public static int ADVERSE_EFFECT_MEDICATION;
	public static int ADVERSE_EFFECT_MEDICATION_NONCODED;
	public static int ADVERSE_EFFECT_NONCODED;
	public static int ADVERSE_EVENT_ACTION_TAKEN;

	/** Anaphylaxie */
	public static int ANAPHYLAXIS;
	/** Anémie */
	public static int ANEMIA;
	public static int ASSESSMENT_COMMENTS;
	public static int ASTHMA;
	public static int CD4_COUNT;
	/** Changement de dose */
	public static int CHANGE_OF_DOSAGE;
	public static int CHEST_XRAY;
	public static int CHEST_XRAY_CONSTRUCT;
	public static int CHRONIC_CARE_CONSTRUCT;
	public static int CHRONIC_CARE_DIAGNOSIS;
	public static int CHRONIC_CARE_DIAGNOSIS_OTHER;
	public static int CLINICAL_IMPRESSION_COMMENTS;
	public static int COMPUTED_TOMOGRAPHY_SCAN_HEAD;
	public static int CURRENT_OPPORTUNISTIC_INFECTION;
	public static int Current_opportunistic_infection_construct;
	public static int CURRENT_OPPORTUNISTIC_INFECTION_OR_COMORBIDITY_CONFIRMED_OR_PRESUMED;
	public static int CURRENT_OPPORTUNISTIC_INFECTION_OR_COMORBIDITY_CONFIRMED_OR_PRESUMED_NON_CODED;
	
	//these should only get shown once total
	public static int CURRENT_OI_DEPRESSION;
	public static int CURRENT_OI_CARDIOVASCULAR_DISEASE;
	public static int CURRENT_OI_CEREBRAL_LYMPHOMA;
	public static int CURRENT_OI_KAPOSIS_SARCOMA;
	public static int CURRENT_OI_NEUROLOGICAL_DEFICIT;
	public static int CURRENT_OI_TUBERCULOSIS;
	public static int CURRENT_OI_PULMONARY_TUBERCULOSIS;
	public static int CURRENT_OI_EXTRAPULMONARY_TUBERCULOSIS;
	public static int CURRENT_OI_WEIGHT_LOSS_GREATER_THAN_10_PERCENT;

	public static int DATE_OF_GENERAL_TEST;

	public static int DATE_OF_HOSPITAL_ADMITTANCE;
	public static int DATE_OF_LABORATORY_TEST;
	public static int DAYS;
	public static int DIABETES;

	public static int DIABETES_MELLITUS;
	public static int DIAGNOSIS_WHILE_HOSPITALIZED;
	public static int DIAGNOSIS_WHILE_HOSPITALIZED_NON_CODED;
	public static int DRUG_3TC;
	public static int DRUG_ABC;
	public static int DRUG_AZT;
	public static int DRUG_AZT_3TC;
	public static int DRUG_AZT_3TC_AND_ABC;
	public static int DRUG_AZT_3TC_EFV;
	public static int DRUG_AZT_3TC_LORR;
	public static int DRUG_AZT_3TC_NVP;

	public static int DRUG_D4T = 1;
	// public static int DRUG_D4T30_3TC_EFV = 1505;
	// public static int DRUG_D4T40_3TC_EFV = 1506;
	// public static int DRUG_D4T30_3TC_NVP = 1502;
	// public static int DRUG_D4T40_3TC_NVP = 1503;
	// public static int DRUG_D4T_3TC_EFV = 1613;
	// public static int DRUG_D4T_3TC_NVP = 792;
	// public static int DRUG_d4T_3TC = 2833;
	// public static int DRUG_AZT_3TC_AND_ABC = 2204;
	// public static int DRUG_AZT_3TC = 630;
	public static int DRUG_D4T_3TC;

	// public static int DRUG_AZT_3TC_EFV = 1612;
	public static int DRUG_D4T_3TC_EFV;

	public static int DRUG_D4T_3TC_NVP;
	public static int DRUG_D4T30_3TC_EFV;

	public static int DRUG_D4T30_3TC_NVP;
	public static int DRUG_D4T40_3TC_EFV;

	public static int DRUG_D4T40_3TC_NVP;
	public static int DRUG_DDI;
	// public static int DRUG_DDI_3TC_LOPR = 2989;
	// public static int DRUG_DDI_ABC_LOPR = 2988;
	// public static int DRUG_DDI_AZT_LOPR = 2990;
	// public static int DRUG_DDI_TDF_LOPR = 2987;
	public static int DRUG_DDI_3TC_LOPR;

	public static int DRUG_DDI_ABC_LOPR;
	// public static int DRUG_TDF_ABC_LOPR = 2991;
	// public static int DRUG_AZT_3TC_AND_ABC = 2204;
	public static int DRUG_DDI_AZT_LOPR;

	public static int DRUG_DDI_TDF_LOPR;

	// public static int DRUG_TDF_3TC_LOPR = 2992;
	// public static int DRUG_TDF_AZT_LOPR = 2993;
	public static int DRUG_DIDANOSINE_ABACAVIR_LOPINAVIR_RITONAVIR;
	// public static int DRUG_DDI_TDF_LOPR = 2987;
	// public static int DRUG_DDI_3TC_LOPR = 2989;
	// public static int DRUG_DDI_AZT_LOPR = 2990;
	// public static int DRUG_TDF_ABC_LOPR = 2991;
	// public static int DRUG_TDF_3TC_LOPR = 2992;
	// public static int DRUG_TDF_AZT_LOPR = 2993;
	// public static int DRUG_AZT_3TC_LORR = 2994;
	public static int DRUG_EFV;
	// public static int DRUG_D4T30_3TC_NVP = 1502;
	// public static int DRUG_D4T40_3TC_NVP = 1503;
	public static int DRUG_INFANT_NVP;
	public static int DRUG_LOPINAVIR_AND_RITONAVIR;
	public static int DRUG_NVP;

	// public static int DRUG_AZT_3TC_NVP = 1610;
	// public static int DRUG_TDF_3TC_NVP = 2984;
	public static int DRUG_NVP_UNIQUE_DOSE;

	public static int DRUG_TDF;

	public static int DRUG_TDF_3TC_EFV;

	public static int DRUG_TDF_3TC_LOPR;
	// public static int DRUG_AZT_3TC_LORR = 2994;
	// public static int DRUG_AZT_3TC_NVP = 1610;
	// public static int DRUG_AZT_3TC_EFV = 1612;
	// public static int DRUG_TDF_3TC_EFV = 2985;
	// public static int DRUG_D4T_3TC_EFV = 1613;
	public static int DRUG_TDF_3TC_NVP;
	// public static int DRUG_TDF_3TC_EFV = 2985;
	// public static int DRUG_TDF_3TC_NVP = 2984;
	// public static int DRUG_TDF_FTC_NVP = 2986;
	public static int DRUG_TDF_ABC_LOPR;
	public static int DRUG_TDF_AZT_LOPR;
	public static int DRUG_TDF_FTC;
	public static int DRUG_TDF_FTC_NVP;
	// public static int DRUG_D4T_3TC_NVP = 792;
	// public static int DRUG_D4T30_3TC_EFV = 1505;
	// public static int DRUG_D4T40_3TC_EFV = 1506;
	public static int DRUG_TNF_3TC;

	public static int DRUG_UNAVAILABLE;

	public static int  DRUG_COTRIMOXAZOLE;
	public static int  DRUG_FLUCONAZOLE;
	public static int DRUG_DAPSONE;
	
	/** Arret du medicament */
	public static int DRUG_WITHDRAWN;
	public static int Duration_of_hospitalization;
	public static int DURATION_OF_HOSPITALIZATION_CONSTRUCT ;
	public static int EPILEPSY ;

	public static int FREE_TEXT_REASON_FOR_POOR_ADHERENCE_TO_ANTIRETROVIRAL_THERAPY;

	public static int FUNCTIONAL_ABILITY_OF_THE_PATIENT;
	public static int GASTROENTERITIS ;
	public static int GRANULOCYTE ;

	public static int HEART_FAILURE ;

	public static int HEIGHT_CM ;
	public static int HEMATOCRIT ;
	public static int HEMOGLOBIN ;
	/** Hépatite */
	public static int HEPATITIS ;

	public static int HIV_VIRAL_LOAD;
	public static int HOSPITALIZATION_COMMENT;
	public static int HOSPITALIZATION_CONSTRUCT;
	public static int HOSPITALIZATION_DISCHARGE_DATE;
	public static int INTERACTION_WITH_TUBERCULOSIS_DRUG;
	/** Ictère */
	public static int JAUNDICE;
	public static int LABORATORY_EXAMINATIONS_CONSTRUCT;

	/** Acidose Lactique */
	public static int LACTIC_ACIDOSIS ;
	/** Lipodystrophie */
	public static int LIPODYSTROPHY ;
	public static int MALARIA ;
	public static int MALNUTRITION ;
	public static int MEDICAL_IMAGE_CONSTRUCT ;

	public static int MENINGITIS;
	public static int METHOD_OF_FAMILY_PLANNING;
	public static int MONTHS;
	/** Nausées */
	public static int NAUSEA;
	public static int NEGATIVE;
	/** Cauchemars */
	public static int NIGHTMARES;
	public static int NO ;
	public static int NON_REACTIVE;

	public static int NONE;
	public static int NOT_DONE;
	public static int NUMBER_OF_DOSES_OF_ANTIRETROVIRALS_MISSED_IN_THE_PAST_MONTH;
	public static int OTHER_LAB_TEST_CONSTRUCT;

	public static int OTHER_LAB_TEST_NAME;
	public static int OTHER_LAB_TEST_RESULT;

	public static int OTHER_NON_CODED;
	public static int OUTPATIENT_DIAGNOSIS;

	public static int PATIENT_DEFAULTED;

	public static int PATIENT_DIED;

	public static int PATIENT_HAD_SIDE_EFFECTS;

	public static int PATIENT_HAS_DIABETES;

	public static int PATIENT_PREGNANT;

	public static int PATIENT_VISIT_CONSTRUCT;

	/** Neuropathie */
	public static int PERIPHERAL_NEUROPATHY;

	public static int PLATELETS;

	public static int PNEUMONIA;

	public static int POSITIVE;

	public static int PREGNANCY_STATUS;

	public static int PREVIOUS_DIAGNOSIS;

	public static int PREVIOUS_DIAGNOSIS_CONSTRUCT;

	public static int PULMONARY_EFFUSION;

	public static int RAPID_PLASMIN_REAGENT;

	/** Eruption cutanée légère */
	public static int RASH_MINOR;

	/** Eruption cutanée modérée */
	public static int RASH_MODERATE;

	/** Eruption cutanée sévère (desquamant) */
	public static int RASH_SEVERE;

	public static int REACTIVE;

	public static int REGIMEN_FAILURE ;

	public static int RENAL_FAILURE;

	public static int RESULT_OF_TUBERCULOSIS_SCREENING_QUALITATIVE;
	
	public static int RESULT_OF_TB_SMEAR;
	
	public static int RESULT_OF_TB_SMEAR_POSITIVE;
	public static int RESULT_OF_TB_SMEAR_WEAKLY_POSITIVE;
	public static int RESULT_OF_TB_SMEAR_MODERATELY_POSITIVE;
	public static int RESULT_OF_TB_SMEAR_STRONGLY_POSITIVE;
	public static int RESULT_OF_TB_SMEAR_NEGATIVE;

	public static int SERUM_CREATININE ;

	public static int SERUM_GLUCOSE;

	public static int SERUM_GLUTAMIC_OXALOACETIC_TRANSAMINASE ;

	public static int SERUM_GLUTAMIC_PYRUVIC_TRANSAMINASE;

	public static int SEVERITY_OF_CARDIOMEGALY ;

	public static int SEXUALLY_TRANSMITTED_INFECTION_SYMPTOMS_COMMENT;

	/** Traitement symptomatique */
	public static int SYMPTOMATIC;
	
	public static int TB_DRUG_ISONIAZID_PROPHYLAXIS;
	public static int TB_DRUG_RHEZ;
	public static int TB_DRUG_RH;
	public static int TB_DRUG_RHZ;
	public static int TB_DRUG_RHE;
	public static int TB_DRUG_AMOX_CLAV;
	
	public static int TB_TREATMENT_TYPE;
	public static int TB_TREATMENT_TYPE_INITIAL;
	public static int TB_TREATMENT_TYPE_RETREATMENT;
	public static int TB_TREATMENT_TYPE_MDRTB;
	public static int TB_TREATMENT_OUTCOME_GUERI_CURED;
	public static int TB_TREATMENT_OUTCOME_ECHEC_FAILED;
	public static int TB_TREATMENT_OUTCOME_COMPLETE;
	public static int TB_TREATMENT_OUTCOME_ABAONDONED;
	public static int TB_TREATMENT_OUTCOME_TRANSFERED;
	public static int TB_TREATMENT_OUTCOME_DIED;

	public static int TESTS_ORDERED;

	public static int TIME_UNITS;
	
	public static int TREATMENT_GROUP;
	
	
	/** Vomissements */
	public static int VOMITING;

	public static int WEEKS;

	public static int WEIGHT_KG;

	public static int WHITE_BLOOD_CELLS;

	public static int WHO_STAGE;

	public static int XRAY_CHEST;

	public static int YES;
	
	public static int PatientIdentiferId_Tracnet;
	
	public static int ProgramId_AdultHIVProgram;
	
	public static String ANTIRETROVIRAL_DRUGS = "ANTIRETROVIRAL DRUGS";
	public static String TUBERCULOSIS_TREATMENT_DRUGS = "TUBERCULOSIS TREATMENT DRUGS";
	
	
	public static int PROPHYLAXIS_REASON_FOR_STOPPING_TOXICITY;
	public static int PROPHYLAXIS_REASON_FOR_STOPPING_ABANDONED;
	public static int PROPHYLAXIS_REASON_FOR_STOPPING_OUT_OF_STOCK;
	public static int PROPHYLAXIS_REASON_FOR_STOPPING_CD4_IMPROVEMENT;

	private ConceptDictionary() {
	    org.openmrs.module.rwandaadulthivflowsheet.web.MetadataHelper.setupConstants();
	}

}
