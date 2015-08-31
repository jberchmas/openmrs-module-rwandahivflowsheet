package org.openmrs.module.rwandaadulthivflowsheet.mapper;

import java.util.Date;

import org.openmrs.Obs;

public interface Lab extends BaseObs {

	public abstract Date getDate();

	public abstract Obs getHb();

	public abstract Obs getHt();

	public abstract Obs getGb();

	public abstract Obs getNeutro();

	public abstract Obs getLympho();

	public abstract Obs getPlt();

	public abstract Obs getSgot();

	public abstract Obs getSgpt();

	public abstract Obs getCreat();

	public abstract Obs getGlucose();

	public abstract Obs getRpr();

	public abstract Obs getOtherLabTestName();

	public abstract Obs getOtherLabTestResult();

	public abstract Obs getCd4();

	public abstract Obs getViralLoad();

}