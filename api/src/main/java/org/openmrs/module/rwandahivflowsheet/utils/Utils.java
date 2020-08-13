package org.openmrs.module.rwandahivflowsheet.utils;

import org.openmrs.Concept;
import org.openmrs.DrugOrder;
import org.openmrs.Order;
import org.openmrs.api.context.Context;

public class Utils {  

	public  static Concept getDiscontinuedReason(DrugOrder dor){
		Concept reason = null;
		Order discontinuationOrder = Context.getOrderService().getDiscontinuationOrder(dor);	
		if(discontinuationOrder != null) {
			reason = discontinuationOrder.getOrderReason();
		}
		return reason;
	}
}