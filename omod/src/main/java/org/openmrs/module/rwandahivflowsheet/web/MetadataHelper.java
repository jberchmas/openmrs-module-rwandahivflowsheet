package org.openmrs.module.rwandahivflowsheet.web;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Concept;
import org.openmrs.EncounterType;
import org.openmrs.Form;
import org.openmrs.PatientIdentifierType;
import org.openmrs.Program;
import org.openmrs.api.context.Context;

public class MetadataHelper {
	
	private static Log log = LogFactory.getLog(MetadataHelper.class);
    
    public static List<String> setupConstants(){
    	List<String> unfoundItems = new ArrayList<String>();
        String gpString = Context.getAdministrationService().getGlobalProperty("rwandahivflowsheet.constants");
        Reader reader = new StringReader(gpString);
        Properties props = new Properties();
        try{
            props.load(reader);
        } catch (Exception ex){
            throw new RuntimeException("invalid values found in global property rwandahivflowsheet.constants, please correct and try again.");
        } finally {
            reader = null;
        }
        if (props.size() > 0){
        	
        	org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.TESTS_ORDERED_NON_CODED = setup("TESTS_ORDERED_NON_CODED", props, unfoundItems);
        	org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.OIS_THAT_ARE_STIS = setup("OIS_THAT_ARE_STIS", props, unfoundItems);
        	org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.NORMAL = setup("NORMAL", props, unfoundItems);
        	org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.ASSISTANCE_REQUIRED = setup("ASSISTANCE_REQUIRED", props, unfoundItems);
        	org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.BEDRIDDEN = setup("BEDRIDDEN", props, unfoundItems);
        	org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.ABSTINENCE = setup("ABSTINENCE", props, unfoundItems);
        	org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.CONDOMS = setup("CONDOMS", props, unfoundItems);
        	org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.NATURAL_FAMILY_PLANNING = setup("NATURAL_FAMILY_PLANNING", props, unfoundItems);
        	
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.ABSOLUTE_LYMPHOCYTE_COUNT = setup("ABSOLUTE_LYMPHOCYTE_COUNT", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.ADVERSE_EFFECT  = setup("ADVERSE_EFFECT", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.ADVERSE_EFFECT_ACTION_TAKEN_NONCODED  = setup("ADVERSE_EFFECT_ACTION_TAKEN_NONCODED", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.ADVERSE_EFFECT_CONSTRUCT  = setup("ADVERSE_EFFECT_CONSTRUCT", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.ADVERSE_EFFECT_DATE  = setup("ADVERSE_EFFECT_DATE", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.ADVERSE_EFFECT_MEDICATION  = setup("ADVERSE_EFFECT_MEDICATION", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.ADVERSE_EFFECT_MEDICATION_NONCODED  = setup("ADVERSE_EFFECT_MEDICATION_NONCODED", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.ADVERSE_EFFECT_NONCODED  = setup("ADVERSE_EFFECT_NONCODED", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.ADVERSE_EVENT_ACTION_TAKEN  = setup("ADVERSE_EVENT_ACTION_TAKEN", props, unfoundItems);
            
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.ANAPHYLAXIS  = setup("ANAPHYLAXIS", props, unfoundItems);
            
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.ANEMIA = setup("ANEMIA", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.ASSESSMENT_COMMENTS  = setup("ASSESSMENT_COMMENTS", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.ASTHMA  = setup("ASTHMA", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.CD4_COUNT  = setup("CD4_COUNT", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.CHANGE_OF_DOSAGE  = setup("CHANGE_OF_DOSAGE", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.CHEST_XRAY  = setup("CHEST_XRAY", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.CHEST_XRAY_CONSTRUCT  = setup("CHEST_XRAY_CONSTRUCT", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.CHRONIC_CARE_CONSTRUCT  = setup("CHRONIC_CARE_CONSTRUCT", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.CHRONIC_CARE_DIAGNOSIS = setup("CHRONIC_CARE_DIAGNOSIS", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.CHRONIC_CARE_DIAGNOSIS_OTHER = setup("CHRONIC_CARE_DIAGNOSIS_OTHER", props, unfoundItems);
            
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.CLINICAL_IMPRESSION_COMMENTS  = setup("CLINICAL_IMPRESSION_COMMENTS", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.COMPUTED_TOMOGRAPHY_SCAN_HEAD  = setup("COMPUTED_TOMOGRAPHY_SCAN_HEAD", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.CURRENT_OPPORTUNISTIC_INFECTION  = setup("CURRENT_OPPORTUNISTIC_INFECTION", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.Current_opportunistic_infection_construct  = setup("Current_opportunistic_infection_construct", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.CURRENT_OPPORTUNISTIC_INFECTION_OR_COMORBIDITY_CONFIRMED_OR_PRESUMED  = setup("CURRENT_OPPORTUNISTIC_INFECTION_OR_COMORBIDITY_CONFIRMED_OR_PRESUMED", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.CURRENT_OPPORTUNISTIC_INFECTION_OR_COMORBIDITY_CONFIRMED_OR_PRESUMED_NON_CODED  = setup("CURRENT_OPPORTUNISTIC_INFECTION_OR_COMORBIDITY_CONFIRMED_OR_PRESUMED_NON_CODED", props, unfoundItems);
            
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.CURRENT_OI_CARDIOVASCULAR_DISEASE  = setup("CURRENT_OI_CARDIOVASCULAR_DISEASE", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.CURRENT_OI_CEREBRAL_LYMPHOMA  = setup("CURRENT_OI_CEREBRAL_LYMPHOMA", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.CURRENT_OI_DEPRESSION  = setup("CURRENT_OI_DEPRESSION", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.CURRENT_OI_EXTRAPULMONARY_TUBERCULOSIS  = setup("CURRENT_OI_EXTRAPULMONARY_TUBERCULOSIS", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.CURRENT_OI_KAPOSIS_SARCOMA  = setup("CURRENT_OI_KAPOSIS_SARCOMA", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.CURRENT_OI_NEUROLOGICAL_DEFICIT  = setup("CURRENT_OI_NEUROLOGICAL_DEFICIT", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.CURRENT_OI_PULMONARY_TUBERCULOSIS  = setup("CURRENT_OI_PULMONARY_TUBERCULOSIS", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.CURRENT_OI_TUBERCULOSIS  = setup("CURRENT_OI_TUBERCULOSIS", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.CURRENT_OI_WEIGHT_LOSS_GREATER_THAN_10_PERCENT  = setup("CURRENT_OI_WEIGHT_LOSS_GREATER_THAN_10_PERCENT", props, unfoundItems);
            
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.DATE_OF_GENERAL_TEST  = setup("DATE_OF_GENERAL_TEST", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.DATE_OF_HOSPITAL_ADMITTANCE  = setup("DATE_OF_HOSPITAL_ADMITTANCE", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.DATE_OF_LABORATORY_TEST  = setup("DATE_OF_LABORATORY_TEST", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.DAYS  = setup("DAYS", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.DIABETES  = setup("DIABETES", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.DIABETES_MELLITUS  = setup("DIABETES_MELLITUS", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.DIAGNOSIS_WHILE_HOSPITALIZED  = setup("DIAGNOSIS_WHILE_HOSPITALIZED", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.DIAGNOSIS_WHILE_HOSPITALIZED_NON_CODED  = setup("DIAGNOSIS_WHILE_HOSPITALIZED_NON_CODED", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.DRUG_3TC = setup("DRUG_3TC", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.DRUG_ABC  = setup("DRUG_ABC", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.DRUG_AZT  = setup("DRUG_AZT", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.DRUG_AZT_3TC  = setup("DRUG_AZT_3TC", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.DRUG_AZT_3TC_AND_ABC  = setup("DRUG_AZT_3TC_AND_ABC", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.DRUG_AZT_3TC_EFV  = setup("DRUG_AZT_3TC_EFV", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.DRUG_AZT_3TC_LORR  = setup("DRUG_AZT_3TC_LORR", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.DRUG_AZT_3TC_NVP  = setup("DRUG_AZT_3TC_NVP", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.DRUG_D4T  = setup("DRUG_D4T", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.DRUG_D4T_3TC = setup("DRUG_D4T_3TC", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.DRUG_D4T_3TC_EFV = setup("DRUG_D4T_3TC_EFV", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.DRUG_D4T_3TC_NVP  = setup("DRUG_D4T_3TC_NVP", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.DRUG_D4T30_3TC_EFV  = setup("DRUG_D4T30_3TC_EFV", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.DRUG_D4T30_3TC_NVP  = setup("DRUG_D4T30_3TC_NVP", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.DRUG_D4T40_3TC_EFV  = setup("DRUG_D4T40_3TC_EFV", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.DRUG_D4T40_3TC_NVP  = setup("DRUG_D4T40_3TC_NVP", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.DRUG_DDI  = setup("DRUG_DDI", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.DRUG_DDI_3TC_LOPR  = setup("DRUG_DDI_3TC_LOPR", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.DRUG_DDI_ABC_LOPR  = setup("DRUG_DDI_ABC_LOPR", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.DRUG_DDI_AZT_LOPR  = setup("DRUG_DDI_AZT_LOPR", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.DRUG_DDI_TDF_LOPR  = setup("DRUG_DDI_TDF_LOPR", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.DRUG_DIDANOSINE_ABACAVIR_LOPINAVIR_RITONAVIR  = setup("DRUG_DIDANOSINE_ABACAVIR_LOPINAVIR_RITONAVIR", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.DRUG_EFV  = setup("DRUG_EFV", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.DRUG_INFANT_NVP  = setup("DRUG_INFANT_NVP", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.DRUG_LOPINAVIR_AND_RITONAVIR  = setup("DRUG_LOPINAVIR_AND_RITONAVIR", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.DRUG_NVP  = setup("DRUG_NVP", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.DRUG_NVP_UNIQUE_DOSE  = setup("DRUG_NVP_UNIQUE_DOSE", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.DRUG_TDF  = setup("DRUG_TDF", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.DRUG_TDF_3TC_EFV  = setup("DRUG_TDF_3TC_EFV", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.DRUG_TDF_3TC_LOPR  = setup("DRUG_TDF_3TC_LOPR", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.DRUG_TDF_3TC_NVP  = setup("DRUG_TDF_3TC_NVP", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.DRUG_TDF_ABC_LOPR  = setup("DRUG_TDF_ABC_LOPR", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.DRUG_TDF_AZT_LOPR  = setup("DRUG_TDF_AZT_LOPR", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.DRUG_TDF_FTC  = setup("DRUG_TDF_FTC", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.DRUG_TDF_FTC_NVP  = setup("DRUG_TDF_FTC_NVP", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.DRUG_TNF_3TC  = setup("DRUG_TNF_3TC", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.DRUG_UNAVAILABLE  = setup("DRUG_UNAVAILABLE", props, unfoundItems);
            
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.DRUG_COTRIMOXAZOLE  = setup("DRUG_COTRIMOXAZOLE", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.DRUG_FLUCONAZOLE  = setup("DRUG_FLUCONAZOLE", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.DRUG_DAPSONE  = setup("DRUG_DAPSONE", props, unfoundItems);
            
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.DRUG_WITHDRAWN  = setup("DRUG_WITHDRAWN", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.Duration_of_hospitalization  = setup("Duration_of_hospitalization", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.DURATION_OF_HOSPITALIZATION_CONSTRUCT  = setup("DURATION_OF_HOSPITALIZATION_CONSTRUCT", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.EPILEPSY  = setup("EPILEPSY", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.FREE_TEXT_REASON_FOR_POOR_ADHERENCE_TO_ANTIRETROVIRAL_THERAPY  = setup("FREE_TEXT_REASON_FOR_POOR_ADHERENCE_TO_ANTIRETROVIRAL_THERAPY", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.FUNCTIONAL_ABILITY_OF_THE_PATIENT  = setup("FUNCTIONAL_ABILITY_OF_THE_PATIENT", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.GASTROENTERITIS  = setup("GASTROENTERITIS", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.GRANULOCYTE  = setup("GRANULOCYTE", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.HEART_FAILURE  = setup("HEART_FAILURE", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.HEIGHT_CM  = setup("HEIGHT_CM", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.HEMATOCRIT  = setup("HEMATOCRIT", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.HEMOGLOBIN  = setup("HEMOGLOBIN", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.HEPATITIS  = setup("HEPATITIS", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.HIV_VIRAL_LOAD  = setup("HIV_VIRAL_LOAD", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.HOSPITALIZATION_COMMENT  = setup("HOSPITALIZATION_COMMENT", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.HOSPITALIZATION_CONSTRUCT  = setup("HOSPITALIZATION_CONSTRUCT", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.HOSPITALIZATION_DISCHARGE_DATE  = setup("HOSPITALIZATION_DISCHARGE_DATE", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.INTERACTION_WITH_TUBERCULOSIS_DRUG  = setup("INTERACTION_WITH_TUBERCULOSIS_DRUG", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.JAUNDICE  = setup("JAUNDICE", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.LABORATORY_EXAMINATIONS_CONSTRUCT  = setup("LABORATORY_EXAMINATIONS_CONSTRUCT", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.LACTIC_ACIDOSIS  = setup("LACTIC_ACIDOSIS", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.LIPODYSTROPHY  = setup("LIPODYSTROPHY", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.MALARIA  = setup("MALARIA", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.MALNUTRITION  = setup("MALNUTRITION", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.MEDICAL_IMAGE_CONSTRUCT  = setup("MEDICAL_IMAGE_CONSTRUCT", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.MENINGITIS  = setup("MENINGITIS", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.METHOD_OF_FAMILY_PLANNING  = setup("METHOD_OF_FAMILY_PLANNING", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.MONTHS  = setup("MONTHS", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.NAUSEA  = setup("NAUSEA", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.NEGATIVE  = setup("NEGATIVE", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.NIGHTMARES  = setup("NIGHTMARES", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.NO  = setup("NO", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.NON_REACTIVE  = setup("NON_REACTIVE", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.NONE  = setup("NONE", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.NOT_DONE  = setup("NOT_DONE", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.NUMBER_OF_DOSES_OF_ANTIRETROVIRALS_MISSED_IN_THE_PAST_MONTH  = setup("NUMBER_OF_DOSES_OF_ANTIRETROVIRALS_MISSED_IN_THE_PAST_MONTH", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.OTHER_LAB_TEST_CONSTRUCT  = setup("OTHER_LAB_TEST_CONSTRUCT", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.OTHER_LAB_TEST_NAME  = setup("OTHER_LAB_TEST_NAME", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.OTHER_LAB_TEST_RESULT  = setup("OTHER_LAB_TEST_RESULT", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.OTHER_NON_CODED  = setup("OTHER_NON_CODED", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.OUTPATIENT_DIAGNOSIS  = setup("OUTPATIENT_DIAGNOSIS", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.CURRENT_OI = setup("OPPORTUNISTIC_INFECTION", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.PATIENT_DEFAULTED  = setup("PATIENT_DEFAULTED", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.PATIENT_DIED  = setup("PATIENT_DIED", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.PATIENT_HAD_SIDE_EFFECTS  = setup("PATIENT_HAD_SIDE_EFFECTS", props, unfoundItems);
            
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.PATIENT_HAS_DIABETES   = setup("PATIENT_HAS_DIABETES", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.PATIENT_PREGNANT   = setup("PATIENT_PREGNANT", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.PATIENT_VISIT_CONSTRUCT   = setup("PATIENT_VISIT_CONSTRUCT", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.PERIPHERAL_NEUROPATHY   = setup("PERIPHERAL_NEUROPATHY", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.PLATELETS   = setup("PLATELETS", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.PNEUMONIA   = setup("PNEUMONIA", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.POSITIVE   = setup("POSITIVE", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.PREGNANCY_STATUS   = setup("PREGNANCY_STATUS", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.PREVIOUS_DIAGNOSIS   = setup("PREVIOUS_DIAGNOSIS", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.PREVIOUS_DIAGNOSIS_CONSTRUCT   = setup("PREVIOUS_DIAGNOSIS_CONSTRUCT", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.PREVIOUS_DIAGNOSIS_DATE   = setup("PREVIOUS_DIAGNOSIS_DATE", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.PULMONARY_EFFUSION   = setup("PULMONARY_EFFUSION", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.RAPID_PLASMIN_REAGENT   = setup("RAPID_PLASMIN_REAGENT", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.RASH_MINOR   = setup("RASH_MINOR", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.RASH_MODERATE   = setup("RASH_MODERATE", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.RASH_SEVERE   = setup("RASH_SEVERE", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.REACTIVE   = setup("REACTIVE", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.REGIMEN_FAILURE   = setup("REGIMEN_FAILURE", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.RENAL_FAILURE   = setup("RENAL_FAILURE", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.RESULT_OF_TUBERCULOSIS_SCREENING_QUALITATIVE   = setup("RESULT_OF_TUBERCULOSIS_SCREENING_QUALITATIVE", props, unfoundItems);

            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.RESULT_OF_TB_SMEAR  = setup("RESULT_OF_TB_SMEAR", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.RESULT_OF_TB_SMEAR_POSITIVE = setup("RESULT_OF_TB_SMEAR_POSITIVE", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.RESULT_OF_TB_SMEAR_WEAKLY_POSITIVE = setup("RESULT_OF_TB_SMEAR_WEAKLY_POSITIVE", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.RESULT_OF_TB_SMEAR_MODERATELY_POSITIVE = setup("RESULT_OF_TB_SMEAR_MODERATELY_POSITIVE", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.RESULT_OF_TB_SMEAR_STRONGLY_POSITIVE = setup("RESULT_OF_TB_SMEAR_STRONGLY_POSITIVE", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.RESULT_OF_TB_SMEAR_NEGATIVE  = setup("RESULT_OF_TB_SMEAR_NEGATIVE", props, unfoundItems);
            
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.SERUM_CREATININE   = setup("SERUM_CREATININE", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.SERUM_GLUCOSE   = setup("SERUM_GLUCOSE", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.SERUM_GLUTAMIC_OXALOACETIC_TRANSAMINASE   = setup("SERUM_GLUTAMIC_OXALOACETIC_TRANSAMINASE", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.SERUM_GLUTAMIC_PYRUVIC_TRANSAMINASE   = setup("SERUM_GLUTAMIC_PYRUVIC_TRANSAMINASE", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.SEVERITY_OF_CARDIOMEGALY   = setup("SEVERITY_OF_CARDIOMEGALY", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.SEXUALLY_TRANSMITTED_INFECTION_SYMPTOMS_COMMENT   = setup("SEXUALLY_TRANSMITTED_INFECTION_SYMPTOMS_COMMENT", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.SYMPTOMATIC   = setup("SYMPTOMATIC", props, unfoundItems);
            
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.TB_DRUG_ISONIAZID_PROPHYLAXIS = setup("TB_DRUG_ISONIAZID_PROPHYLAXIS",props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.TB_DRUG_RHEZ= setup("TB_DRUG_RHEZ",props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.TB_DRUG_RH= setup("TB_DRUG_RH",props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.TB_DRUG_RHZ= setup("TB_DRUG_RHZ",props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.TB_DRUG_RHE= setup("TB_DRUG_RHE",props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.TB_TREATMENT_TYPE= setup("TB_TREATMENT_TYPE",props, unfoundItems);
            
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.TB_TREATMENT_TYPE_INITIAL= setup("TB_TREATMENT_TYPE_INITIAL",props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.TB_TREATMENT_TYPE_MDRTB= setup("TB_TREATMENT_TYPE_MDRTB",props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.TB_TREATMENT_TYPE_RETREATMENT= setup("TB_TREATMENT_TYPE_RETREATMENT",props, unfoundItems);
            
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.TB_TREATMENT_OUTCOME_GUERI_CURED= setup("TB_TREATMENT_OUTCOME_GUERI_CURED",props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.TB_TREATMENT_OUTCOME_ECHEC_FAILED= setup("TB_TREATMENT_OUTCOME_ECHEC_FAILED",props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.TB_TREATMENT_OUTCOME_COMPLETE= setup("TB_TREATMENT_OUTCOME_COMPLETE",props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.TB_TREATMENT_OUTCOME_ABAONDONED= setup("TB_TREATMENT_OUTCOME_ABAONDONED",props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.TB_TREATMENT_OUTCOME_TRANSFERED= setup("TB_TREATMENT_OUTCOME_TRANSFERED",props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.TB_TREATMENT_OUTCOME_DIED= setup("TB_TREATMENT_OUTCOME_DIED",props, unfoundItems);
            
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.TESTS_ORDERED   = setup("TESTS_ORDERED", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.TIME_UNITS   = setup("TIME_UNITS", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.TREATMENT_GROUP = setup("TREATMENT_GROUP", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.VOMITING   = setup("VOMITING", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.WEEKS   = setup("WEEKS", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.WEIGHT_KG   = setup("WEIGHT_KG", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.WHITE_BLOOD_CELLS   = setup("WHITE_BLOOD_CELLS", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.WHO_STAGE   = setup("WHO_STAGE", props, unfoundItems);
            
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.XRAY_CHEST    = setup("XRAY_CHEST", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.YES    = setup("YES", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.Z_SCORE_WEIGHT   = setup("Z_SCORE_WEIGHT", props, unfoundItems); 
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.Z_SCORE_HEIGHT   = setup("Z_SCORE_HEIGHT", props, unfoundItems); 
            
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.PatientIdentiferId_Tracnet    = setupPatientIdentifierType("PatientIdentiferId_Tracnet", props);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.ProgramId_AdultHIVProgram    = setupProgram("ProgramId_AdultHIVProgram", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.ProgramId_PediHIVProgram    = setupProgram("ProgramId_PediHIVProgram", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.ProgramId_PMTCT_mother    = setupProgram("ProgramId_PMTCT_mother", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.ProgramId_Exposed_Infant_mother    = setupProgram("ProgramId_Exposed_Infant_mother", props, unfoundItems);
            
          
            
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.ADULT_FLOWSHEET_ENCOUNTER_ID    = setupEncounterTypeId("ADULT_FLOWSHEET_ENCOUNTER_ID", props);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.PEDI_FLOWSHEET_ENCOUNTER_ID    = setupEncounterTypeId("PEDI_FLOWSHEET_ENCOUNTER_ID", props);
            
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.HEIGHT_WEIGHT_PERCENTILE   = setup("HEIGHT_WEIGHT_PERCENTILE", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.PATIENT_INFORMED   = setup("PATIENT_INFORMED", props, unfoundItems); 
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.PATIENT_NOT_INFORMED   = setup("PATIENT_NOT_INFORMED", props, unfoundItems); 
            
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.PROPHYLAXIS_REASON_FOR_STOPPING_ABANDONED    = setup("PROPHYLAXIS_REASON_FOR_STOPPING_ABANDONED", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.PROPHYLAXIS_REASON_FOR_STOPPING_CD4_IMPROVEMENT   = setup("PROPHYLAXIS_REASON_FOR_STOPPING_CD4_IMPROVEMENT", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.PROPHYLAXIS_REASON_FOR_STOPPING_OUT_OF_STOCK    = setup("PROPHYLAXIS_REASON_FOR_STOPPING_OUT_OF_STOCK", props, unfoundItems);
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.PROPHYLAXIS_REASON_FOR_STOPPING_TOXICITY    = setup("PROPHYLAXIS_REASON_FOR_STOPPING_TOXICITY", props, unfoundItems);
            
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.CD4_PERCENTAGE   = setup("CD4_PERCENTAGE", props, unfoundItems); 
            
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.ABDOMINAL_ULTRASOUND   = setup("ABDOMINAL_ULTRASOUND", props, unfoundItems);
            
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.HIV_DIAGNOSIS_DATE    = setup("HIV_DIAGNOSIS_DATE", props, unfoundItems);
            
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.TB_DRUG_AMOX_CLAV    = setup("TB_DRUG_AMOX_CLAV", props, unfoundItems);
            
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.TREATMENT_COMPLETE    = setup("TREATMENT_COMPLETE", props, unfoundItems);
            
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.INFORMED_STATUS    = setup("INFORMED_STATUS", props, unfoundItems);
            
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.NEXT_VISIT    = setup("NEXT_VISIT", props, unfoundItems);
            
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.OPPORTUNISTIC_INFECTION    = setup("OPPORTUNISTIC_INFECTIONS", props, unfoundItems);
            
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.OPPORTUNISTIC_INFECTION_NON_CODED    = setup("OPPORTUNISTIC_INFECTIONS_NON_CODED", props, unfoundItems);
            
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.OPPORTUNISTIC_INFECTION_SET    = setup("OPPORTUNISTIC_INFECTIONS_SET", props, unfoundItems);
            
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.OPPORTUNISTIC_INFECTION_START_DATE    = setup("OPPORTUNISTIC_INFECTIONS_START_DATE", props, unfoundItems);
            
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.OPPORTUNISTIC_INFECTION_COMMENTS    = setup("OPPORTUNISTIC_INFECTIONS_COMMENTS", props, unfoundItems);
            
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.STI_INFECTION    = setup("STI_INFECTIONS", props, unfoundItems);
            
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.STI_INFECTION_NON_CODED    = setup("STI_INFECTIONS_NON_CODED", props, unfoundItems);
            
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.STI_SET    = setup("STI_SET", props, unfoundItems);
            
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.ADULT_ALLERGY_FORM = setupForm("rwandaadulthivflowsheet.Form_NewAllergy");
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.ADULT_OI_FORM = setupForm("rwandaadulthivflowsheet.Form_NewOI");
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.ADULT_HOSPITALISATION_FORM = setupForm("rwandaadulthivflowsheet.Form_NewHospitalisations");
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.ADULT_PROBLEM_FORM = setupForm("rwandaadulthivflowsheet.Form_NewProblem");
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.ADULT_VISIT_FORM = setupForm("rwandaadulthivflowsheet.Form_NewVisit");
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.ADULT_LAB_FORM = setupForm("rwandaadulthivflowsheet.Form_NewLab");
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.ADULT_IMAGE_FORM = setupForm("rwandaadulthivflowsheet.Form_NewImage");
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.PEDI_ALLERGY_FORM = setupForm("rwandapedihivflowsheet.Form_NewAllergy");
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.PEDI_OI_FORM = setupForm("rwandapedihivflowsheet.Form_NewOI");
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.PEDI_HOSPITALISATION_FORM = setupForm("rwandapedihivflowsheet.Form_NewHospitalisations");
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.PEDI_PROBLEM_FORM = setupForm("rwandapedihivflowsheet.Form_NewProblem");
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.PEDI_VISIT_FORM = setupForm("rwandapedihivflowsheet.Form_NewVisit");
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.PEDI_LAB_FORM = setupForm("rwandapedihivflowsheet.Form_NewLab");
            org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary.PEDI_IMAGE_FORM = setupForm("rwandapedihivflowsheet.Form_NewImage");
            
            for (String str: unfoundItems)
            	log.error("RwandaHivFlowsheet module could not load the folowing item in global property rwandahivflowsheet.constants: " + str);
            
        }
        return unfoundItems;
    }
    
    private static Integer setup(String key, Properties props, List<String> unfoundItems){
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
	            	unfoundItems.add(key);
	        } else
	        	unfoundItems.add(key);
	           
    	} catch (Exception ex){
    		unfoundItems.add(key);
    		//log.warn("RwandaFlowsheetModule unable to load metadata for key " + key + ". Please check your mappings in the global property rwandahivflowsheet.constants.");
    	}
    	return -100;
    }
    
    private static Integer setupPatientIdentifierType(String key, Properties props){
    	if (props.getProperty(key) == null){
    		throw new RuntimeException("Unable to load value for key " + key + ".  Please check the mappings in the global property rwandahivflowsheet.constants.");
    	}
    		
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
            	throw new RuntimeException("RwandaFlowsheetModule unable to load metadata for key " + key + ". Please check your mappings in the global property rwandahivflowsheet.constants.");
        } else
            throw new RuntimeException("RwandaFlowsheetModule unable to load metadata for key " + key + ". Please check your mappings in the global property rwandahivflowsheet.constants.");
    }
    
    private static Integer setupProgram(String key, Properties props, List<String> unfoundItems){
    	if (props.getProperty(key) != null){
	        String input = props.getProperty(key).trim();
	        if (input != null && !"".equals(input)){
	            Program c = Context.getProgramWorkflowService().getProgramByUuid(input);
	            if (c == null)
	            	c = Context.getProgramWorkflowService().getProgramByName(input);
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
	            	unfoundItems.add(key);
	        } else
	        	unfoundItems.add(key);
    	} else
    		unfoundItems.add(key);
        return -100;
    }
    
    private static Integer setupEncounterTypeId(String key, Properties props){
    	if (props.getProperty(key) == null){
    		throw new RuntimeException("RwandaFlowsheetModule unable to load metadata for key " + key + ". Please check your mappings in the global property rwandahivflowsheet.constants.");
    	}
        String input = props.getProperty(key).trim();
        if (input != null && !"".equals(input)){
        	EncounterType c = Context.getEncounterService().getEncounterTypeByUuid(input);
        	if (c == null){
        		try {
        			c = Context.getEncounterService().getEncounterType(Integer.valueOf(input));
        		} catch (Exception ex){
        			//pass, string was not numeric
        		}
        	}
            if (c != null)
            	return c.getEncounterTypeId();
            else
            	throw new RuntimeException("RwandaFlowsheetModule unable to load metadata for key " + key + ". Please check your mappings in the global property rwandahivflowsheet.constants.");
        } else
            throw new RuntimeException("RwandaFlowsheetModule unable to load metadata for key " + key + ". Please check your mappings in the global property rwandahivflowsheet.constants.");
    }
    
    private static Integer setupForm(String key){
        String input = Context.getAdministrationService().getGlobalProperty(key);
        if (input != null && !"".equals(input)){
            Form c = Context.getFormService().getFormByUuid(input);
        	if (c == null){
        		try {
        			c = Context.getFormService().getForm(Integer.valueOf(input));
        		} catch (Exception ex){
        			//pass, string was not numeric
        		}
        	}
            if (c != null)
            	return c.getFormId();
            else
            	throw new RuntimeException("RwandaFlowsheetModule unable to load metadata for key " + key + ". Please check your mappings in the global property rwandahivflowsheet.constants.");
        } else
            throw new RuntimeException("RwandaFlowsheetModule unable to load metadata for key " + key + ". Please check your mappings in the global property rwandahivflowsheet.constants.");
    }

    
}
