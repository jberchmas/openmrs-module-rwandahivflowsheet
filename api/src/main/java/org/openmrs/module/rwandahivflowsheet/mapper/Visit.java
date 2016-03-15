package org.openmrs.module.rwandahivflowsheet.mapper;

import org.openmrs.Obs;

public interface Visit extends BaseObs {

	public abstract Obs getWeight();

	public abstract Obs getFunctionalAbilityOfThePatient();

	public abstract Obs getOI();

	public abstract Obs getSTI();

	public abstract Obs getTBScreening();

	public abstract Obs getFamilyPlanning();

	public abstract Obs getPregnancyStatus();

	public abstract Obs getNumberOfMissedDosesInThePastMonth();

	public abstract Obs getCreat();

	public abstract Obs getReasonForPoorAdherenceToAntiRetoroviralTherapy();

}