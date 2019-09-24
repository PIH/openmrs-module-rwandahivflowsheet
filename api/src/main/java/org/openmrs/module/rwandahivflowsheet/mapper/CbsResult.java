package org.openmrs.module.rwandahivflowsheet.mapper;

import org.openmrs.Concept;
import org.openmrs.Obs;

public interface CbsResult extends BaseObs {

	public abstract Obs getRecencyAssayResults();

	public abstract Obs getRecencyTestDate();

}