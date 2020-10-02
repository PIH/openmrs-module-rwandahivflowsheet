package org.openmrs.module.rwandahivflowsheet.mapper;

import org.openmrs.Concept;
import org.openmrs.Obs;

public interface CbsIndex extends BaseObs {

	public abstract Obs getCbsContactCode();

	public abstract Obs getCbsContactAge();
        
	public abstract Obs getCbsEnrollementDate();

}