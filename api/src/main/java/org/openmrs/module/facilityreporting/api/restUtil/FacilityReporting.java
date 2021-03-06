package org.openmrs.module.facilityreporting.api.restUtil;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.openmrs.api.context.Context;
import org.openmrs.module.facilityreporting.api.FacilityreportingService;
import org.openmrs.module.facilityreporting.api.models.FacilityReport;
import org.openmrs.module.facilityreporting.api.models.FacilityReportData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FacilityReporting {
	
	SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	public ObjectNode getReportDataForPeriod(Integer reportId, String startDate, String endDate) throws ParseException {
		FacilityreportingService service = Context.getService(FacilityreportingService.class);
		
		System.out.println("Variables as received: reportId:" + reportId + ", startDate: " + startDate + ", endDate: "
		        + endDate);
		FacilityReport report = service.getReportById(reportId);
		Date from = DATE_FORMAT.parse(startDate);
		Date to = DATE_FORMAT.parse(endDate);
		
		List<FacilityReportData> rData = service.getReportData(report, from, to);
		ObjectNode node = getJsonNodeFactory().objectNode();
		node.put("dataset", "test");
		node.put("indicator", "this is the first indicator");
		return node;
		
	}
	
	public JsonNodeFactory getJsonNodeFactory() {
		final JsonNodeFactory factory = JsonNodeFactory.instance;
		return factory;
	}
}
