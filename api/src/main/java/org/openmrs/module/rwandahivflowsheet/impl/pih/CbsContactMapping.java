package org.openmrs.module.rwandahivflowsheet.impl.pih;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.openmrs.Concept;
import org.openmrs.ConceptAnswer;
import org.openmrs.ConceptSet;
import org.openmrs.Encounter;
import org.openmrs.Obs;
import org.openmrs.api.context.Context;

import org.openmrs.module.rwandahivflowsheet.mapper.*;

public class CbsContactMapping extends ObsMapping implements Comparable<CbsContactMapping>, CbsContact {

    private Date obsDate = null;

    public CbsContactMapping(Obs obs) {
        super(obs);
    }

    public Date getCBSContactDate() {
        if (!isEmr()) {
            return null;
        }
        return getObs().getObsDatetime();

    }


    /* (non-Javadoc)
	 * @see org.openmrs.module.rwandahivflowsheet.web.controller.CbsIndexMapper#getbsContactCode()
     */
    private Obs getObsOfType(Integer conceptId) {
        if (getObs() == null) {
            return null;
        }

        if (getObs().getConcept() != null && getObs().getConcept().getConceptId().equals(conceptId)) {
            return getObs();
        }

        return null;
    }

    @Override
    public Obs getcbsContactCode() {
        return getObsOfType(ConceptDictionary.CBS_CONTACT_CODE);
    }

    @Override
    public Obs getcbsContactAge() {
        return getObsOfType(ConceptDictionary.CBS_CONTACT_AGE);
    }

    @Override
    public Obs getcbsContactgender() {
        return getObsOfType(ConceptDictionary.CBS_CONTACT_GENDER);
    }

    @Override
    public Obs getcbsContactNotified() {
        return getObsOfType(ConceptDictionary.CBS_CONTACT_NOTIFIER);
    }

    @Override
    public int compareTo(CbsContactMapping obj) {
        if (this == obj) {
            return 0;
        }
        if (obj == null) {
            return -1;
        }
        if (getObsDate() == null && obj.getObsDate() == null) {
            return 0;
        }
        if (getObsDate() == null) {
            return 1;
        }
        return getObsDate().compareTo(obj.getObsDate());
    }

}
