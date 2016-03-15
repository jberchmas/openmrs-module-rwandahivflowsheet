package org.openmrs.module.rwandahivflowsheet.mapper;

import org.openmrs.Concept;
import org.openmrs.Obs;

public interface OI extends BaseObs {

	public abstract Obs getDiagnosis();

	public abstract Concept getStage();

	public abstract String getComments();

}