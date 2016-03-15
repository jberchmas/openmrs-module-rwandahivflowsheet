package org.openmrs.module.rwandahivflowsheet.mapper;

import java.util.List;

import org.openmrs.Obs;

public interface Image extends BaseObs {


	public abstract List<Obs> getComments();

	public abstract boolean isChestXRay();

	public abstract boolean isCTHead();

	public abstract boolean isOther();

}