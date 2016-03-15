package org.openmrs.module.rwandaadulthivflowsheet.web;

import java.io.Reader;
import java.io.StringReader;
import java.util.Properties;

import org.openmrs.Concept;
import org.openmrs.PatientIdentifierType;
import org.openmrs.Program;
import org.openmrs.api.context.Context;

public class MetadataHelper {
    
    public static void setupConstants(){
        String gpString = Context.getAdministrationService().getGlobalProperty("rwandaadulthivflowsheet.constants");
        Reader reader = new StringReader(gpString);
        Properties props = new Properties();
        try{
            props.load(reader);
        } catch (Exception ex){
            throw new RuntimeException("invalid values found in global property rwandaadulthivflowsheet.constants, please correct and try again.");
        } finally {
            reader = null;
        }
        if (props.size() > 0){
        	
        	org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.TESTS_ORDERED_NON_CODED = setup("TESTS_ORDERED_NON_CODED", props);
        	org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.OIS_THAT_ARE_STIS = setup("OIS_THAT_ARE_STIS", props);
        	org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.NORMAL = setup("NORMAL", props);
        	org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.ASSISTANCE_REQUIRED = setup("ASSISTANCE_REQUIRED", props);
        	org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.BEDRIDDEN = setup("BEDRIDDEN", props);
        	org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.ABSTINENCE = setup("ABSTINENCE", props);
        	org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.CONDOMS = setup("CONDOMS", props);
        	org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.NATURAL_FAMILY_PLANNING = setup("NATURAL_FAMILY_PLANNING", props);
        	
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.ABSOLUTE_LYMPHOCYTE_COUNT = setup("ABSOLUTE_LYMPHOCYTE_COUNT", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.ADVERSE_EFFECT  = setup("ADVERSE_EFFECT", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.ADVERSE_EFFECT_ACTION_TAKEN_NONCODED  = setup("ADVERSE_EFFECT_ACTION_TAKEN_NONCODED", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.ADVERSE_EFFECT_CONSTRUCT  = setup("ADVERSE_EFFECT_CONSTRUCT", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.ADVERSE_EFFECT_DATE  = setup("ADVERSE_EFFECT_DATE", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.ADVERSE_EFFECT_MEDICATION  = setup("ADVERSE_EFFECT_MEDICATION", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.ADVERSE_EFFECT_MEDICATION_NONCODED  = setup("ADVERSE_EFFECT_MEDICATION_NONCODED", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.ADVERSE_EFFECT_NONCODED  = setup("ADVERSE_EFFECT_NONCODED", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.ADVERSE_EVENT_ACTION_TAKEN  = setup("ADVERSE_EVENT_ACTION_TAKEN", props);
            
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.ANAPHYLAXIS  = setup("ANAPHYLAXIS", props);
            
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.ANEMIA = setup("ANEMIA", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.ASSESSMENT_COMMENTS  = setup("ASSESSMENT_COMMENTS", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.ASTHMA  = setup("ASTHMA", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.CD4_COUNT  = setup("CD4_COUNT", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.CHANGE_OF_DOSAGE  = setup("CHANGE_OF_DOSAGE", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.CHEST_XRAY  = setup("CHEST_XRAY", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.CHEST_XRAY_CONSTRUCT  = setup("CHEST_XRAY_CONSTRUCT", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.CHRONIC_CARE_CONSTRUCT  = setup("CHRONIC_CARE_CONSTRUCT", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.CHRONIC_CARE_DIAGNOSIS = setup("CHRONIC_CARE_DIAGNOSIS", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.CHRONIC_CARE_DIAGNOSIS_OTHER = setup("CHRONIC_CARE_DIAGNOSIS_OTHER", props);
            
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.CLINICAL_IMPRESSION_COMMENTS  = setup("CLINICAL_IMPRESSION_COMMENTS", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.COMPUTED_TOMOGRAPHY_SCAN_HEAD  = setup("COMPUTED_TOMOGRAPHY_SCAN_HEAD", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.CURRENT_OPPORTUNISTIC_INFECTION  = setup("CURRENT_OPPORTUNISTIC_INFECTION", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.Current_opportunistic_infection_construct  = setup("Current_opportunistic_infection_construct", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.CURRENT_OPPORTUNISTIC_INFECTION_OR_COMORBIDITY_CONFIRMED_OR_PRESUMED  = setup("CURRENT_OPPORTUNISTIC_INFECTION_OR_COMORBIDITY_CONFIRMED_OR_PRESUMED", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.CURRENT_OPPORTUNISTIC_INFECTION_OR_COMORBIDITY_CONFIRMED_OR_PRESUMED_NON_CODED  = setup("CURRENT_OPPORTUNISTIC_INFECTION_OR_COMORBIDITY_CONFIRMED_OR_PRESUMED_NON_CODED", props);
            
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.CURRENT_OI_CARDIOVASCULAR_DISEASE  = setup("CURRENT_OI_CARDIOVASCULAR_DISEASE", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.CURRENT_OI_CEREBRAL_LYMPHOMA  = setup("CURRENT_OI_CEREBRAL_LYMPHOMA", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.CURRENT_OI_DEPRESSION  = setup("CURRENT_OI_DEPRESSION", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.CURRENT_OI_EXTRAPULMONARY_TUBERCULOSIS  = setup("CURRENT_OI_EXTRAPULMONARY_TUBERCULOSIS", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.CURRENT_OI_KAPOSIS_SARCOMA  = setup("CURRENT_OI_KAPOSIS_SARCOMA", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.CURRENT_OI_NEUROLOGICAL_DEFICIT  = setup("CURRENT_OI_NEUROLOGICAL_DEFICIT", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.CURRENT_OI_PULMONARY_TUBERCULOSIS  = setup("CURRENT_OI_PULMONARY_TUBERCULOSIS", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.CURRENT_OI_TUBERCULOSIS  = setup("CURRENT_OI_TUBERCULOSIS", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.CURRENT_OI_WEIGHT_LOSS_GREATER_THAN_10_PERCENT  = setup("CURRENT_OI_WEIGHT_LOSS_GREATER_THAN_10_PERCENT", props);
            
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.DATE_OF_GENERAL_TEST  = setup("DATE_OF_GENERAL_TEST", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.DATE_OF_HOSPITAL_ADMITTANCE  = setup("DATE_OF_HOSPITAL_ADMITTANCE", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.DATE_OF_LABORATORY_TEST  = setup("DATE_OF_LABORATORY_TEST", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.DAYS  = setup("DAYS", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.DIABETES  = setup("DIABETES", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.DIABETES_MELLITUS  = setup("DIABETES_MELLITUS", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.DIAGNOSIS_WHILE_HOSPITALIZED  = setup("DIAGNOSIS_WHILE_HOSPITALIZED", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.DIAGNOSIS_WHILE_HOSPITALIZED_NON_CODED  = setup("DIAGNOSIS_WHILE_HOSPITALIZED_NON_CODED", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.DRUG_3TC = setup("DRUG_3TC", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.DRUG_ABC  = setup("DRUG_ABC", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.DRUG_AZT  = setup("DRUG_AZT", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.DRUG_AZT_3TC  = setup("DRUG_AZT_3TC", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.DRUG_AZT_3TC_AND_ABC  = setup("DRUG_AZT_3TC_AND_ABC", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.DRUG_AZT_3TC_EFV  = setup("DRUG_AZT_3TC_EFV", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.DRUG_AZT_3TC_LORR  = setup("DRUG_AZT_3TC_LORR", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.DRUG_AZT_3TC_NVP  = setup("DRUG_AZT_3TC_NVP", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.DRUG_D4T  = setup("DRUG_D4T", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.DRUG_D4T_3TC = setup("DRUG_D4T_3TC", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.DRUG_D4T_3TC_EFV = setup("DRUG_D4T_3TC_EFV", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.DRUG_D4T_3TC_NVP  = setup("DRUG_D4T_3TC_NVP", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.DRUG_D4T30_3TC_EFV  = setup("DRUG_D4T30_3TC_EFV", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.DRUG_D4T30_3TC_NVP  = setup("DRUG_D4T30_3TC_NVP", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.DRUG_D4T40_3TC_EFV  = setup("DRUG_D4T40_3TC_EFV", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.DRUG_D4T40_3TC_NVP  = setup("DRUG_D4T40_3TC_NVP", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.DRUG_DDI  = setup("DRUG_DDI", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.DRUG_DDI_3TC_LOPR  = setup("DRUG_DDI_3TC_LOPR", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.DRUG_DDI_ABC_LOPR  = setup("DRUG_DDI_ABC_LOPR", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.DRUG_DDI_AZT_LOPR  = setup("DRUG_DDI_AZT_LOPR", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.DRUG_DDI_TDF_LOPR  = setup("DRUG_DDI_TDF_LOPR", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.DRUG_DIDANOSINE_ABACAVIR_LOPINAVIR_RITONAVIR  = setup("DRUG_DIDANOSINE_ABACAVIR_LOPINAVIR_RITONAVIR", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.DRUG_EFV  = setup("DRUG_EFV", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.DRUG_INFANT_NVP  = setup("DRUG_INFANT_NVP", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.DRUG_LOPINAVIR_AND_RITONAVIR  = setup("DRUG_LOPINAVIR_AND_RITONAVIR", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.DRUG_NVP  = setup("DRUG_NVP", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.DRUG_NVP_UNIQUE_DOSE  = setup("DRUG_NVP_UNIQUE_DOSE", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.DRUG_TDF  = setup("DRUG_TDF", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.DRUG_TDF_3TC_EFV  = setup("DRUG_TDF_3TC_EFV", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.DRUG_TDF_3TC_LOPR  = setup("DRUG_TDF_3TC_LOPR", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.DRUG_TDF_3TC_NVP  = setup("DRUG_TDF_3TC_NVP", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.DRUG_TDF_ABC_LOPR  = setup("DRUG_TDF_ABC_LOPR", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.DRUG_TDF_AZT_LOPR  = setup("DRUG_TDF_AZT_LOPR", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.DRUG_TDF_FTC  = setup("DRUG_TDF_FTC", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.DRUG_TDF_FTC_NVP  = setup("DRUG_TDF_FTC_NVP", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.DRUG_TNF_3TC  = setup("DRUG_TNF_3TC", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.DRUG_UNAVAILABLE  = setup("DRUG_UNAVAILABLE", props);
            
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.DRUG_COTRIMOXAZOLE  = setup("DRUG_COTRIMOXAZOLE", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.DRUG_FLUCONAZOLE  = setup("DRUG_FLUCONAZOLE", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.DRUG_DAPSONE  = setup("DRUG_DAPSONE", props);
            
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.DRUG_WITHDRAWN  = setup("DRUG_WITHDRAWN", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.Duration_of_hospitalization  = setup("Duration_of_hospitalization", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.DURATION_OF_HOSPITALIZATION_CONSTRUCT  = setup("DURATION_OF_HOSPITALIZATION_CONSTRUCT", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.EPILEPSY  = setup("EPILEPSY", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.FREE_TEXT_REASON_FOR_POOR_ADHERENCE_TO_ANTIRETROVIRAL_THERAPY  = setup("FREE_TEXT_REASON_FOR_POOR_ADHERENCE_TO_ANTIRETROVIRAL_THERAPY", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.FUNCTIONAL_ABILITY_OF_THE_PATIENT  = setup("FUNCTIONAL_ABILITY_OF_THE_PATIENT", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.GASTROENTERITIS  = setup("GASTROENTERITIS", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.GRANULOCYTE  = setup("GRANULOCYTE", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.HEART_FAILURE  = setup("HEART_FAILURE", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.HEIGHT_CM  = setup("HEIGHT_CM", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.HEMATOCRIT  = setup("HEMATOCRIT", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.HEMOGLOBIN  = setup("HEMOGLOBIN", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.HEPATITIS  = setup("HEPATITIS", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.HIV_VIRAL_LOAD  = setup("HIV_VIRAL_LOAD", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.HOSPITALIZATION_COMMENT  = setup("HOSPITALIZATION_COMMENT", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.HOSPITALIZATION_CONSTRUCT  = setup("HOSPITALIZATION_CONSTRUCT", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.HOSPITALIZATION_DISCHARGE_DATE  = setup("HOSPITALIZATION_DISCHARGE_DATE", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.INTERACTION_WITH_TUBERCULOSIS_DRUG  = setup("INTERACTION_WITH_TUBERCULOSIS_DRUG", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.JAUNDICE  = setup("JAUNDICE", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.LABORATORY_EXAMINATIONS_CONSTRUCT  = setup("LABORATORY_EXAMINATIONS_CONSTRUCT", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.LACTIC_ACIDOSIS  = setup("LACTIC_ACIDOSIS", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.LIPODYSTROPHY  = setup("LIPODYSTROPHY", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.MALARIA  = setup("MALARIA", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.MALNUTRITION  = setup("MALNUTRITION", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.MEDICAL_IMAGE_CONSTRUCT  = setup("MEDICAL_IMAGE_CONSTRUCT", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.MENINGITIS  = setup("MENINGITIS", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.METHOD_OF_FAMILY_PLANNING  = setup("METHOD_OF_FAMILY_PLANNING", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.MONTHS  = setup("MONTHS", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.NAUSEA  = setup("NAUSEA", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.NEGATIVE  = setup("NEGATIVE", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.NIGHTMARES  = setup("NIGHTMARES", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.NO  = setup("NO", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.NON_REACTIVE  = setup("NON_REACTIVE", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.NONE  = setup("NONE", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.NOT_DONE  = setup("NOT_DONE", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.NUMBER_OF_DOSES_OF_ANTIRETROVIRALS_MISSED_IN_THE_PAST_MONTH  = setup("NUMBER_OF_DOSES_OF_ANTIRETROVIRALS_MISSED_IN_THE_PAST_MONTH", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.OTHER_LAB_TEST_CONSTRUCT  = setup("OTHER_LAB_TEST_CONSTRUCT", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.OTHER_LAB_TEST_NAME  = setup("OTHER_LAB_TEST_NAME", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.OTHER_LAB_TEST_RESULT  = setup("OTHER_LAB_TEST_RESULT", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.OTHER_NON_CODED  = setup("OTHER_NON_CODED", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.OUTPATIENT_DIAGNOSIS  = setup("OUTPATIENT_DIAGNOSIS", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.PATIENT_DEFAULTED  = setup("PATIENT_DEFAULTED", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.PATIENT_DIED  = setup("PATIENT_DIED", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.PATIENT_HAD_SIDE_EFFECTS  = setup("PATIENT_HAD_SIDE_EFFECTS", props);
            
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.PATIENT_HAS_DIABETES   = setup("PATIENT_HAS_DIABETES", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.PATIENT_PREGNANT   = setup("PATIENT_PREGNANT", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.PATIENT_VISIT_CONSTRUCT   = setup("PATIENT_VISIT_CONSTRUCT", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.PERIPHERAL_NEUROPATHY   = setup("PERIPHERAL_NEUROPATHY", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.PLATELETS   = setup("PLATELETS", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.PNEUMONIA   = setup("PNEUMONIA", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.POSITIVE   = setup("POSITIVE", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.PREGNANCY_STATUS   = setup("PREGNANCY_STATUS", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.PREVIOUS_DIAGNOSIS   = setup("PREVIOUS_DIAGNOSIS", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.PREVIOUS_DIAGNOSIS_CONSTRUCT   = setup("PREVIOUS_DIAGNOSIS_CONSTRUCT", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.PULMONARY_EFFUSION   = setup("PULMONARY_EFFUSION", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.RAPID_PLASMIN_REAGENT   = setup("RAPID_PLASMIN_REAGENT", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.RASH_MINOR   = setup("RASH_MINOR", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.RASH_MODERATE   = setup("RASH_MODERATE", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.RASH_SEVERE   = setup("RASH_SEVERE", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.REACTIVE   = setup("REACTIVE", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.REGIMEN_FAILURE   = setup("REGIMEN_FAILURE", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.RENAL_FAILURE   = setup("RENAL_FAILURE", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.RESULT_OF_TUBERCULOSIS_SCREENING_QUALITATIVE   = setup("RESULT_OF_TUBERCULOSIS_SCREENING_QUALITATIVE", props);

            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.RESULT_OF_TB_SMEAR  = setup("RESULT_OF_TB_SMEAR", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.RESULT_OF_TB_SMEAR_POSITIVE = setup("RESULT_OF_TB_SMEAR_POSITIVE", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.RESULT_OF_TB_SMEAR_WEAKLY_POSITIVE = setup("RESULT_OF_TB_SMEAR_WEAKLY_POSITIVE", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.RESULT_OF_TB_SMEAR_MODERATELY_POSITIVE = setup("RESULT_OF_TB_SMEAR_MODERATELY_POSITIVE", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.RESULT_OF_TB_SMEAR_STRONGLY_POSITIVE = setup("RESULT_OF_TB_SMEAR_STRONGLY_POSITIVE", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.RESULT_OF_TB_SMEAR_NEGATIVE  = setup("RESULT_OF_TB_SMEAR_NEGATIVE", props);
            
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.SERUM_CREATININE   = setup("SERUM_CREATININE", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.SERUM_GLUCOSE   = setup("SERUM_GLUCOSE", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.SERUM_GLUTAMIC_OXALOACETIC_TRANSAMINASE   = setup("SERUM_GLUTAMIC_OXALOACETIC_TRANSAMINASE", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.SERUM_GLUTAMIC_PYRUVIC_TRANSAMINASE   = setup("SERUM_GLUTAMIC_PYRUVIC_TRANSAMINASE", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.SEVERITY_OF_CARDIOMEGALY   = setup("SEVERITY_OF_CARDIOMEGALY", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.SEXUALLY_TRANSMITTED_INFECTION_SYMPTOMS_COMMENT   = setup("SEXUALLY_TRANSMITTED_INFECTION_SYMPTOMS_COMMENT", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.SYMPTOMATIC   = setup("SYMPTOMATIC", props);
            
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.TB_DRUG_ISONIAZID_PROPHYLAXIS = setup("TB_DRUG_ISONIAZID_PROPHYLAXIS",props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.TB_DRUG_RHEZ= setup("TB_DRUG_RHEZ",props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.TB_DRUG_RH= setup("TB_DRUG_RH",props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.TB_DRUG_RHZ= setup("TB_DRUG_RHZ",props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.TB_DRUG_RHE= setup("TB_DRUG_RHE",props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.TB_TREATMENT_TYPE= setup("TB_TREATMENT_TYPE",props);
            
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.TB_TREATMENT_TYPE_INITIAL= setup("TB_TREATMENT_TYPE_INITIAL",props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.TB_TREATMENT_TYPE_MDRTB= setup("TB_TREATMENT_TYPE_MDRTB",props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.TB_TREATMENT_TYPE_RETREATMENT= setup("TB_TREATMENT_TYPE_RETREATMENT",props);
            
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.TB_TREATMENT_OUTCOME_GUERI_CURED= setup("TB_TREATMENT_OUTCOME_GUERI_CURED",props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.TB_TREATMENT_OUTCOME_ECHEC_FAILED= setup("TB_TREATMENT_OUTCOME_ECHEC_FAILED",props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.TB_TREATMENT_OUTCOME_COMPLETE= setup("TB_TREATMENT_OUTCOME_COMPLETE",props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.TB_TREATMENT_OUTCOME_ABAONDONED= setup("TB_TREATMENT_OUTCOME_ABAONDONED",props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.TB_TREATMENT_OUTCOME_TRANSFERED= setup("TB_TREATMENT_OUTCOME_TRANSFERED",props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.TB_TREATMENT_OUTCOME_DIED= setup("TB_TREATMENT_OUTCOME_DIED",props);
            
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.TESTS_ORDERED   = setup("TESTS_ORDERED", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.TIME_UNITS   = setup("TIME_UNITS", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.TREATMENT_GROUP = setup("TREATMENT_GROUP", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.VOMITING   = setup("VOMITING", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.WEEKS   = setup("WEEKS", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.WEIGHT_KG   = setup("WEIGHT_KG", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.WHITE_BLOOD_CELLS   = setup("WHITE_BLOOD_CELLS", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.WHO_STAGE   = setup("WHO_STAGE", props);
            
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.XRAY_CHEST    = setup("XRAY_CHEST", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.YES    = setup("YES", props);
            
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.PatientIdentiferId_Tracnet    = setupPatientIdentifierType("PatientIdentiferId_Tracnet", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.ProgramId_AdultHIVProgram    = setupProgram("ProgramId_AdultHIVProgram", props);
            
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.PROPHYLAXIS_REASON_FOR_STOPPING_ABANDONED    = setup("PROPHYLAXIS_REASON_FOR_STOPPING_ABANDONED", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.PROPHYLAXIS_REASON_FOR_STOPPING_CD4_IMPROVEMENT   = setup("PROPHYLAXIS_REASON_FOR_STOPPING_CD4_IMPROVEMENT", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.PROPHYLAXIS_REASON_FOR_STOPPING_OUT_OF_STOCK    = setup("PROPHYLAXIS_REASON_FOR_STOPPING_OUT_OF_STOCK", props);
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.PROPHYLAXIS_REASON_FOR_STOPPING_TOXICITY    = setup("PROPHYLAXIS_REASON_FOR_STOPPING_TOXICITY", props);
            
            org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary.TB_DRUG_AMOX_CLAV    = setup("TB_DRUG_AMOX_CLAV", props);
        }
    }
    
    private static Integer setup(String key, Properties props){
    	try {
    		String input = props.getProperty(key).trim();
	        if (input != null && !"".equals(input)){
	            Concept c = Context.getConceptService().getConceptByUuid(input);
	        	if (c == null){
	        		try {
	        			c = Context.getConceptService().getConcept(Integer.valueOf(input));
	        		} catch (Exception ex){
	        			//pass, string was not numeric
	        		}
	        	}
	            if (c != null)
	            	return c.getConceptId();
	            else
	            	throw new RuntimeException("no concept found for " + key);
	        } else
	            throw new RuntimeException("No value found for key " + key);
    	} catch (Exception ex){
    		throw new RuntimeException("RwandaAdultFlowsheetModule unable to load metadata for key " + key + ". Please check your mappings in the global property.");
    	}
    }
    
    private static Integer setupPatientIdentifierType(String key, Properties props){
        String input = props.getProperty(key).trim();
        if (input != null && !"".equals(input)){
        	PatientIdentifierType c = Context.getPatientService().getPatientIdentifierTypeByUuid(input);
        	if (c == null){
        		try {
        			c = Context.getPatientService().getPatientIdentifierType(Integer.valueOf(input));
        		} catch (Exception ex){
        			//pass, string was not numeric
        		}
        	}
            if (c != null)
            	return c.getPatientIdentifierTypeId();
            else
            	throw new RuntimeException("no concept found for " + key);
        } else
            throw new RuntimeException("No value found for key " + key);
    }
    
    private static Integer setupProgram(String key, Properties props){
        String input = props.getProperty(key).trim();
        if (input != null && !"".equals(input)){
            Program c = Context.getProgramWorkflowService().getProgramByUuid(input);
        	if (c == null){
        		try {
        			c = Context.getProgramWorkflowService().getProgram(Integer.valueOf(input));
        		} catch (Exception ex){
        			//pass, string was not numeric
        		}
        	}
            if (c != null)
            	return c.getProgramId();
            else
            	throw new RuntimeException("no concept found for " + key);
        } else
            throw new RuntimeException("No value found for key " + key);
    }
    
}
