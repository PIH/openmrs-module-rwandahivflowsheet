package org.openmrs.module.rwandahivflowsheet.mapper;

import org.openmrs.Concept;
import org.openmrs.Obs;

public interface CbsEnrollment extends BaseObs {

	public abstract Obs getCbsIndexCaseType();

	public abstract Obs getEnrolledInCbs();

}