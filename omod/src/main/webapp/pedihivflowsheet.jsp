<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<%@ page import="java.util.*" %>
<%@ page import="org.openmrs.*" %>
<%@ page import="org.openmrs.module.rwandahivflowsheet.mapper.*" %>
<%@ page import="org.openmrs.module.rwandahivflowsheet.web.*" %>

<%@ include file="templates/include.jsp"%>
<%@ include file="templates/headerPedi.jsp"%>

<!-- Create htmlInclude tag that takes media -->

<%@ include file="fragments/popup.jspf"%>

<script type="text/javascript">
		var dataHeight = [];
		var sd3 = [];
		var sd2 = [];
		var sd1 = [];
		var sd0 = [];
		var sd1neg = [];
		var sd2neg = [];
		var sd3neg = [];
		
		<c:forEach var="o" items="${formData.obsMapper.heightForAge}">
			dataHeight.push([ ${o.ageInMonths} , ${o.heightInCM} ]);
		</c:forEach>
		
		<c:choose>
	      	<c:when test="${formData.gender == 'F'}">
		      	<c:forEach var="o" items="${formData.whoMapping.heightForAge5to19GirlsSD3}">
					sd3.push([ ${o.ageInMonths} , ${o.heightInCMDecimal} ]);
				</c:forEach>
	      	</c:when>
	      	<c:otherwise>
		      	<c:forEach var="o" items="${formData.whoMapping.heightForAge5to19BoysSD3}">
					sd3.push([ ${o.ageInMonths} , ${o.heightInCMDecimal} ]);
				</c:forEach>
	      	</c:otherwise>
	    </c:choose>
	    
	    <c:choose>
	      	<c:when test="${formData.gender == 'F'}">
		      	<c:forEach var="o" items="${formData.whoMapping.heightForAge5to19GirlsSD2}">
					sd2.push([ ${o.ageInMonths} , ${o.heightInCMDecimal} ]);
				</c:forEach>
	      	</c:when>
	      	<c:otherwise>
		      	<c:forEach var="o" items="${formData.whoMapping.heightForAge5to19BoysSD2}">
					sd2.push([ ${o.ageInMonths} , ${o.heightInCMDecimal} ]);
				</c:forEach>
	      	</c:otherwise>
    	</c:choose>
    	
	    <c:choose>
	      	<c:when test="${formData.gender == 'F'}">
		      	<c:forEach var="o" items="${formData.whoMapping.heightForAge5to19GirlsSD1}">
					sd1.push([ ${o.ageInMonths} , ${o.heightInCMDecimal} ]);
				</c:forEach>
	      	</c:when>
	      	<c:otherwise>
		      	<c:forEach var="o" items="${formData.whoMapping.heightForAge5to19BoysSD1}">
					sd1.push([ ${o.ageInMonths} , ${o.heightInCMDecimal} ]);
				</c:forEach>
	      	</c:otherwise>
		</c:choose>
		
	    <c:choose>
	      	<c:when test="${formData.gender == 'F'}">
		      	<c:forEach var="o" items="${formData.whoMapping.heightForAge5to19GirlsSD0}">
					sd0.push([ ${o.ageInMonths} , ${o.heightInCMDecimal} ]);
				</c:forEach>
	      	</c:when>
	      	<c:otherwise>
		      	<c:forEach var="o" items="${formData.whoMapping.heightForAge5to19BoysSD0}">
					sd0.push([ ${o.ageInMonths} , ${o.heightInCMDecimal} ]);
				</c:forEach>
	      	</c:otherwise>
		</c:choose>
		
	    <c:choose>
	      	<c:when test="${formData.gender == 'F'}">
		      	<c:forEach var="o" items="${formData.whoMapping.heightForAge5to19GirlsSD1neg}">
					sd1neg.push([ ${o.ageInMonths} , ${o.heightInCMDecimal} ]);
				</c:forEach>
	      	</c:when>
	      	<c:otherwise>
		      	<c:forEach var="o" items="${formData.whoMapping.heightForAge5to19BoysSD1neg}">
					sd1neg.push([ ${o.ageInMonths} , ${o.heightInCMDecimal} ]);
				</c:forEach>
	      	</c:otherwise>
		</c:choose>
		
	    <c:choose>
	      	<c:when test="${formData.gender == 'F'}">
		      	<c:forEach var="o" items="${formData.whoMapping.heightForAge5to19GirlsSD2neg}">
					sd2neg.push([ ${o.ageInMonths} , ${o.heightInCMDecimal} ]);
				</c:forEach>
	      	</c:when>
	      	<c:otherwise>
		      	<c:forEach var="o" items="${formData.whoMapping.heightForAge5to19BoysSD2neg}">
					sd2neg.push([ ${o.ageInMonths} , ${o.heightInCMDecimal} ]);
				</c:forEach>
	      	</c:otherwise>
		</c:choose>	
		
	    <c:choose>
	      	<c:when test="${formData.gender == 'F'}">
		      	<c:forEach var="o" items="${formData.whoMapping.heightForAge5to19GirlsSD3neg}">
					sd3neg.push([ ${o.ageInMonths} , ${o.heightInCMDecimal} ]);
				</c:forEach>
	      	</c:when>
	      	<c:otherwise>
		      	<c:forEach var="o" items="${formData.whoMapping.heightForAge5to19BoysSD3neg}">
					sd3neg.push([ ${o.ageInMonths} , ${o.heightInCMDecimal} ]);
				</c:forEach>
	      	</c:otherwise>
		</c:choose>	
		

		// setup background areas
	    var markingsHeight = [
	        { yaxis: { to: 200 } }
	    ];

		var optionsHeight = {
 			xaxis: {
 				min: 60,
 				max: 229,
 				ticks: [[60, "<b>5 years</b>"], [63, "3"], [66, "6"], [69, "9"], [72, "<b>6 years</b>"], [75, "3"], [78, "6"], [81, "9"], [84, "<b>7 years</b>"], [87, "3"], [90, "6"], [93, "9"], [96, "<b>8 years</b>"], [99, "3"], [102, "6"], [105, "9"], [108, "<b>9 years</b>"], [111, "3"], [114, "6"], [117, "9"], [120, "<b>10 years</b>"], [123, "3"], [126, "6"], [129, "9"], [132, "<b>11 years</b>"], [136, "3"], [139, "6"], [142, "9"], [145, "<b>12 years</b>"], [148, "3"], [151, "6"], [154, "9"], [157, "<b>13 years</b>"], [160, "3"], [163, "6"], [166, "9"], [169, "<b>14 years</b>"], [172, "3"], [175, "6"], [178, "9"], [181, "<b>15 years</b>"], [184, "3"], [187, "6"], [190, "9"], [193, "<b>16 years</b>"], [196, "3"], [199, "6"], [202, "9"], [205, "<b>17 years</b>"], [208, "3"], [211, "6"], [214, "9"], [217, "<b>18 years</b>"], [220, "3"], [223, "6"], [226, "9"], [229, "<b>19 years</b>"]]
 			},
			yaxis: { 
				min: 90, 
				max: 200, 
				ticksSize: [10, ""]
			},
			grid: { markings: markingsHeight },
			points: [{ show: false }, { show: false}, { show: false}, { show: false}, { show: false}, { show: false}, { show: false}, { show: true}],
			lines: { show: true },
			legend: { position: "nw"}
		};

		function plotHeightGraph() {
			var p =$j.plot($j("#obsGraphHeight"), [{ label: "3", color: "#FF0000", data: sd3 }, { label: "2", color: "#FF6600", data: sd2 }, { label: "1", color: "#FFFF66", data: sd1 }, { label: "0", color: "#00FF33", data: sd0 }, { label: "-1", color: "#FFFF00", data: sd1neg }, { label: "-2", color: "#CC6600", data: sd2neg }, { label: "-3", color: "#CC0000", data: sd3neg }, { label: "patient", color: "#000000", data: dataHeight } ], optionsHeight);
		}
		
		<c:if test="${!empty formData.obsMapper.lengthForAge || form.patient.age < 5}">
		var dataLength = [];
		var sd3Len = [];
		var sd2Len = [];
		var sd0Len = [];
		var sd2negLen = [];
		var sd3negLen = [];
		
		<c:forEach var="o" items="${formData.obsMapper.lengthForAge}">
			dataLength.push([ ${o.ageInMonths} , ${o.heightInCM} ]);
		</c:forEach>
		
		<c:choose>
	      	<c:when test="${formData.gender == 'F'}">
		      	<c:forEach var="o" items="${formData.whoMapping.heightForAgeFromBirthGirlsSD3}">
					sd3Len.push([ ${o.ageInMonths} , ${o.heightInCMDecimal} ]);
				</c:forEach>
	      	</c:when>
	      	<c:otherwise>
		      	<c:forEach var="o" items="${formData.whoMapping.heightForAgeFromBirthBoysSD3}">
					sd3Len.push([ ${o.ageInMonths} , ${o.heightInCMDecimal} ]);
				</c:forEach>
	      	</c:otherwise>
	    </c:choose>
	    
	    <c:choose>
	      	<c:when test="${formData.gender == 'F'}">
		      	<c:forEach var="o" items="${formData.whoMapping.heightForAgeFromBirthGirlsSD2}">
					sd2Len.push([ ${o.ageInMonths} , ${o.heightInCMDecimal} ]);
				</c:forEach>
	      	</c:when>
	      	<c:otherwise>
		      	<c:forEach var="o" items="${formData.whoMapping.heightForAgeFromBirthBoysSD2}">
					sd2Len.push([ ${o.ageInMonths} , ${o.heightInCMDecimal} ]);
				</c:forEach>
	      	</c:otherwise>
		</c:choose>
		
	    <c:choose>
	      	<c:when test="${formData.gender == 'F'}">
		      	<c:forEach var="o" items="${formData.whoMapping.heightForAgeFromBirthGirlsSD0}">
					sd0Len.push([ ${o.ageInMonths} , ${o.heightInCMDecimal} ]);
				</c:forEach>
	      	</c:when>
	      	<c:otherwise>
		      	<c:forEach var="o" items="${formData.whoMapping.heightForAgeFromBirthBoysSD0}">
					sd0Len.push([ ${o.ageInMonths} , ${o.heightInCMDecimal} ]);
				</c:forEach>
	      	</c:otherwise>
		</c:choose>
		
	    <c:choose>
	      	<c:when test="${formData.gender == 'F'}">
		      	<c:forEach var="o" items="${formData.whoMapping.heightForAgeFromBirthGirlsSD2neg}">
					sd2negLen.push([ ${o.ageInMonths} , ${o.heightInCMDecimal} ]);
				</c:forEach>
	      	</c:when>
	      	<c:otherwise>
		      	<c:forEach var="o" items="${formData.whoMapping.heightForAgeFromBirthBoysSD2neg}">
					sd2negLen.push([ ${o.ageInMonths} , ${o.heightInCMDecimal} ]);
				</c:forEach>
	      	</c:otherwise>
		</c:choose>	
		
	    <c:choose>
	      	<c:when test="${formData.gender == 'F'}">
		      	<c:forEach var="o" items="${formData.whoMapping.heightForAgeFromBirthGirlsSD3neg}">
					sd3negLen.push([ ${o.ageInMonths} , ${o.heightInCMDecimal} ]);
				</c:forEach>
	      	</c:when>
	      	<c:otherwise>
		      	<c:forEach var="o" items="${formData.whoMapping.heightForAgeFromBirthBoysSD3neg}">
					sd3negLen.push([ ${o.ageInMonths} , ${o.heightInCMDecimal} ]);
				</c:forEach>
	      	</c:otherwise>
		</c:choose>

		// setup background areas
	    var markingsLength = [
	        { yaxis: { to: 125 } }
	    ];

		var optionsLength = {
 			xaxis: {
 				min: 0,
 				max: 60,
 				ticks: [[0, "<b>Birth</b>"], [2, "2"], [4, "4"], [6, "6"], [8, "8"], [10, "10"], [12, "<b>1 year</b>"], [14, "2"], [16, "4"], [18, "6"], [20, "8"], [22, "10"], [24, "<b>2 years</b>"], [26, "2"], [28, "4"], [30, "6"], [32, "8"], [34, "10"], [36, "<b>3 years</b>"], [38, "2"], [40, "4"], [42, "6"], [44, "8"], [46, "10"], [48, "<b>4 years</b>"], [50, "2"], [52, "4"], [54, "6"], [56, "8"], [58, "10"], [60, "<b>5 years</b>"]]
	 			},
			yaxis: { 
				min: 40, 
				max: 125, 
				ticks: [[40, ""], [45, "45"], [50, "50"], [55, "55"], [60, "60"], [65, "65"], [70, "70"], [75, "75"], [80, "80"], [85, "85"], [90, "90"], [95, "95"], [100, "100"], [105, "105"], [110, "110"], [115, "115"], [120, "120"], [125, "125"]]
			},
			grid: { markings: markingsLength },
			points: [{ show: false }, { show: false}, { show: false}, { show: false}, { show: false}, { show: true}],
			lines: { show: true },
			legend: { position: "nw"}
		};

		function plotLengthGraph() {
			
			var p =$j.plot($j("#obsGraphLength"), [{ label: "3", color: "#000000", data: sd3Len }, { label: "2", color: "#FF0000", data: sd2Len }, { label: "0", color: "#00FF33", data: sd0Len }, { label: "-2", color: "#FF0000", data: sd2negLen }, { label: "-3", color: "#000000", data: sd3negLen }, { label: "Patient", color: "#0000CC", data: dataLength } ], optionsLength);
		}
		</c:if>
		
		<c:if test="${!empty formData.obsMapper.weightForAgeFromBirth || formData.patient.age < 5}">
		var dataWeightBirth = [];
		var sd3wb = [];
		var sd2wb = [];
		var sd0wb = [];
		var sd2negwb = [];
		var sd3negwb = [];
		
		<c:forEach var="o" items="${formData.obsMapper.weightForAgeFromBirth}">
			dataWeightBirth.push([ ${o.ageInMonths} , ${o.weightInKG} ]);
		</c:forEach>
		
		<c:choose>
	      	<c:when test="${formData.gender == 'F'}">
		      	<c:forEach var="o" items="${formData.whoMapping.weightForAgeFromBirthGirlsSD3}">
					sd3wb.push([ ${o.ageInMonths} , ${o.weightInKGDecimal} ]);
				</c:forEach>
	      	</c:when>
	      	<c:otherwise>
		      	<c:forEach var="o" items="${formData.whoMapping.weightForAgeFromBirthBoysSD3}">
					sd3wb.push([ ${o.ageInMonths} , ${o.weightInKGDecimal} ]);
				</c:forEach>
	      	</c:otherwise>
	    </c:choose>
	    
	    <c:choose>
	      	<c:when test="${formData.gender == 'F'}">
		      	<c:forEach var="o" items="${formData.whoMapping.weightForAgeFromBirthGirlsSD2}">
					sd2wb.push([ ${o.ageInMonths} , ${o.weightInKGDecimal} ]);
				</c:forEach>
	      	</c:when>
	      	<c:otherwise>
		      	<c:forEach var="o" items="${formData.whoMapping.weightForAgeFromBirthBoysSD2}">
					sd2wb.push([ ${o.ageInMonths} , ${o.weightInKGDecimal} ]);
				</c:forEach>
	      	</c:otherwise>
		</c:choose>
		
	    <c:choose>
	      	<c:when test="${formData.gender == 'F'}">
		      	<c:forEach var="o" items="${formData.whoMapping.weightForAgeFromBirthGirlsSD0}">
					sd0wb.push([ ${o.ageInMonths} , ${o.weightInKGDecimal} ]);
				</c:forEach>
	      	</c:when>
	      	<c:otherwise>
		      	<c:forEach var="o" items="${formData.whoMapping.weightForAgeFromBirthBoysSD0}">
					sd0wb.push([ ${o.ageInMonths} , ${o.weightInKGDecimal} ]);
				</c:forEach>
	      	</c:otherwise>
		</c:choose>
		
	    <c:choose>
	      	<c:when test="${formData.gender == 'F'}">
		      	<c:forEach var="o" items="${formData.whoMapping.weightForAgeFromBirthGirlsSD2neg}">
					sd2negwb.push([ ${o.ageInMonths} , ${o.weightInKGDecimal} ]);
				</c:forEach>
	      	</c:when>
	      	<c:otherwise>
		      	<c:forEach var="o" items="${formData.whoMapping.weightForAgeFromBirthBoysSD2neg}">
					sd2negwb.push([ ${o.ageInMonths} , ${o.weightInKGDecimal} ]);
				</c:forEach>
	      	</c:otherwise>
		</c:choose>	
		
	    <c:choose>
	      	<c:when test="${formData.gender == 'F'}">
		      	<c:forEach var="o" items="${formData.whoMapping.weightForAgeFromBirthGirlsSD3neg}">
					sd3negwb.push([ ${o.ageInMonths} , ${o.weightInKGDecimal} ]);
				</c:forEach>
	      	</c:when>
	      	<c:otherwise>
		      	<c:forEach var="o" items="${formData.whoMapping.weightForAgeFromBirthBoysSD3neg}">
					sd3negwb.push([ ${o.ageInMonths} , ${o.weightInKGDecimal} ]);
				</c:forEach>
	      	</c:otherwise>
		</c:choose>

		// setup background areas
	    var markingsWeightBirth = [
	        { yaxis: { to: 29 } }
	    ];

		var optionsWeightBirth = {
 			xaxis: {
 				min: 0,
 				max: 60,
 				ticks: [[0, "<b>Birth</b>"], [2, "2"], [4, "4"], [6, "6"], [8, "8"], [10, "10"], [12, "<b>1 year</b>"], [14, "2"], [16, "4"], [18, "6"], [20, "8"], [22, "10"], [24, "<b>2 years</b>"], [26, "2"], [28, "4"], [30, "6"], [32, "8"], [34, "10"], [36, "<b>3 years</b>"], [38, "2"], [40, "4"], [42, "6"], [44, "8"], [46, "10"], [48, "<b>4 years</b>"], [50, "2"], [52, "4"], [54, "6"], [56, "8"], [58, "10"], [60, "<b>5 years</b>"]]
 	 			},
			yaxis: { 
				min: 1, 
				max: 29, 
				ticks: [[2, "2"], [4, "4"], [6, "6"], [8, "8"], [10, "10"], [12, "12"], [14, "14"], [16, "16"], [18, "18"], [20, "20"], [22, "22"], [24, "24"], [26, "26"], [28, "28"]]
			},
			grid: { markings: markingsWeightBirth },
			points: [{ show: false }, { show: false}, { show: false}, { show: false}, { show: false}, { show: true}],
			lines: { show: true },
			legend: { position: "nw"}
		};

		function plotWeightBirthGraph() {
			
			var p =$j.plot($j("#obsGraphWeightBirth"), [ { label: "3", color: "#000000", data: sd3wb }, { label: "2", color: "#FF0000", data: sd2wb }, { label: "0", color: "#00FF33", data: sd0wb }, { label: "-2", color: "#FF0000", data: sd2negwb }, { label: "-3", color: "#FF0000", data: sd3negwb }, { label: "Patient", color: "#0000CC", data: dataWeightBirth }], optionsWeightBirth);
		}
		
		</c:if>
		
		var dataWeight = [];
		var sd3w = [];
		var sd2w = [];
		var sd1w = [];
		var sd0w = [];
		var sd1negw = [];
		var sd2negw = [];
		var sd3negw = [];
		
		<c:forEach var="o" items="${formData.obsMapper.weightForAge}">
			dataWeight.push([ ${o.ageInMonths} , ${o.weightInKG} ]);
		</c:forEach>
		
		<c:choose>
	      	<c:when test="${formData.gender == 'F'}">
		      	<c:forEach var="o" items="${formData.whoMapping.weightForAge5to10GirlsSD3}">
					sd3w.push([ ${o.ageInMonths} , ${o.weightInKGDecimal} ]);
				</c:forEach>
	      	</c:when>
	      	<c:otherwise>
		      	<c:forEach var="o" items="${formData.whoMapping.weightForAge5to10BoysSD3}">
					sd3w.push([ ${o.ageInMonths} , ${o.weightInKGDecimal} ]);
				</c:forEach>
	      	</c:otherwise>
	    </c:choose>
	    
	    <c:choose>
	      	<c:when test="${formData.gender == 'F'}">
		      	<c:forEach var="o" items="${formData.whoMapping.weightForAge5to10GirlsSD2}">
					sd2w.push([ ${o.ageInMonths} , ${o.weightInKGDecimal} ]);
				</c:forEach>
	      	</c:when>
	      	<c:otherwise>
		      	<c:forEach var="o" items="${formData.whoMapping.weightForAge5to10BoysSD2}">
					sd2w.push([ ${o.ageInMonths} , ${o.weightInKGDecimal} ]);
				</c:forEach>
	      	</c:otherwise>
		</c:choose>
		
		<c:choose>
	      	<c:when test="${formData.gender == 'F'}">
		      	<c:forEach var="o" items="${formData.whoMapping.weightForAge5to10GirlsSD1}">
					sd1w.push([ ${o.ageInMonths} , ${o.weightInKGDecimal} ]);
				</c:forEach>
	      	</c:when>
	      	<c:otherwise>
		      	<c:forEach var="o" items="${formData.whoMapping.weightForAge5to10BoysSD1}">
					sd1w.push([ ${o.ageInMonths} , ${o.weightInKGDecimal} ]);
				</c:forEach>
	      	</c:otherwise>
		</c:choose>
		
	    <c:choose>
	      	<c:when test="${formData.gender == 'F'}">
		      	<c:forEach var="o" items="${formData.whoMapping.weightForAge5to10GirlsSD0}">
					sd0w.push([ ${o.ageInMonths} , ${o.weightInKGDecimal} ]);
				</c:forEach>
	      	</c:when>
	      	<c:otherwise>
		      	<c:forEach var="o" items="${formData.whoMapping.weightForAge5to10BoysSD0}">
					sd0w.push([ ${o.ageInMonths} , ${o.weightInKGDecimal} ]);
				</c:forEach>
	      	</c:otherwise>
		</c:choose>
		
		<c:choose>
	      	<c:when test="${formData.gender == 'F'}">
		      	<c:forEach var="o" items="${formData.whoMapping.weightForAge5to10GirlsSD1neg}">
					sd1negw.push([ ${o.ageInMonths} , ${o.weightInKGDecimal} ]);
				</c:forEach>
	      	</c:when>
	      	<c:otherwise>
		      	<c:forEach var="o" items="${formData.whoMapping.weightForAge5to10BoysSD1neg}">
					sd1negw.push([ ${o.ageInMonths} , ${o.weightInKGDecimal} ]);
				</c:forEach>
	      	</c:otherwise>
		</c:choose>	
		
	    <c:choose>
	      	<c:when test="${formData.gender == 'F'}">
		      	<c:forEach var="o" items="${formData.whoMapping.weightForAge5to10GirlsSD2neg}">
					sd2negw.push([ ${o.ageInMonths} , ${o.weightInKGDecimal} ]);
				</c:forEach>
	      	</c:when>
	      	<c:otherwise>
		      	<c:forEach var="o" items="${formData.whoMapping.weightForAge5to10BoysSD2neg}">
					sd2negw.push([ ${o.ageInMonths} , ${o.weightInKGDecimal} ]);
				</c:forEach>
	      	</c:otherwise>
		</c:choose>	
		
	    <c:choose>
	      	<c:when test="${formData.gender == 'F'}">
		      	<c:forEach var="o" items="${formData.whoMapping.weightForAge5to10GirlsSD3neg}">
					sd3negw.push([ ${o.ageInMonths} , ${o.weightInKGDecimal} ]);
				</c:forEach>
	      	</c:when>
	      	<c:otherwise>
		      	<c:forEach var="o" items="${formData.whoMapping.weightForAge5to10BoysSD3neg}">
					sd3negw.push([ ${o.ageInMonths} , ${o.weightInKGDecimal} ]);
				</c:forEach>
	      	</c:otherwise>
		</c:choose>

		// setup background areas
	    var markingsWeight = [
	        { yaxis: { to: 60 } }
	    ];

		var optionsWeight = {
 			xaxis: {
 				min: 60,
 				max: 120,
 				ticks: [[60, "<b>5 years</b>"], [63, "3"], [66, "6"], [69, "9"], [72, "<b>6 years</b>"], [75, "3"], [78, "6"], [81, "9"], [84, "<b>7 years</b>"], [87, "3"], [90, "6"], [93, "9"], [96, "<b>8 years</b>"], [99, "3"], [102, "6"], [105, "9"], [108, "<b>9 years</b>"], [111, "3"], [114, "6"], [117, "9"], [120, "<b>10 years</b>"]]
 			},
			yaxis: { 
				min: 10, 
				max: 60, 
				ticks: [[15, "15"], [20, "20"], [25, "25"], [30, "30"], [35, "35"], [40, "40"], [45, "45"], [50, "50"], [55, "55"]]
			},
			grid: { markings: markingsWeight },
			points: [{ show: false }, { show: false}, { show: false}, { show: false}, { show: false}, { show: false}, { show: false}, { show: true}],
			lines: { show: true },
			legend: { position: "nw"}
		};

		function plotWeightGraph() {
			
			var p =$j.plot($j("#obsGraphWeight"), [{ label: "3", color: "#FF0000", data: sd3w }, { label: "2", color: "#FF6600", data: sd2w }, { label: "1", color: "#FFFF66", data: sd1w }, { label: "0", color: "#00FF33", data: sd0w }, { label: "-1", color: "#FFFF00", data: sd1negw }, { label: "-2", color: "#CC6600", data: sd2negw }, { label: "-3", color: "#CC0000", data: sd3negw }, { label: "Patient", color: "#000000", data: dataWeight } ], optionsWeight);
		}
		
		var datacd4Count = [];
		
		<c:forEach var="o" items="${formData.obsMapper.cd4ObsList}">
			datacd4Count.push([ ${o.date.time} , ${o.cd4.valueNumeric} ]);
		</c:forEach>
		
		// setup background areas
	    var markingscd4Count = [
	        { color: '#FFD2F2', yaxis: { to: 350 } }
	    ];

		var optionscd4Count = {
			<%= UIHelper.getCd4XAxis(formData) %>,
			yaxis: { 
				min: 0, 
				max: 3000, 
				ticks: [[0, "0"], [100, ""], [200, ""], [300, ""], [400, ""], [500, "500"], [600, ""], [700, ""], [800, ""], [900, ""], [1000, "1000"], [1100, ""], [1200, ""], [1300, ""], [1400, ""], [1500, "1500"], [1600, ""], [1700, ""], [1800, ""], [1900, ""], [2000, "2000"], [2100, ""], [2200, ""], [2300, ""], [2400, ""], [2500, "2500"], [2600, ""], [2700, ""], [2800, ""], [2900, ""], [3000, "3000"]]
			},
			grid: { markings: markingscd4Count },
			points: { show: true },
			lines: { show: true }
		};
		
		function plotCd4Graph() {
			var p =$j.plot($j("#obsGraphcd4Count"), [{ label: "", data: datacd4Count }], optionscd4Count);
		}
		
		<c:if test="${!empty formData.obsMapper.cd4PercentObsList || form.patient.age < 5}">
		var datacd4Percent = [];
		
		<c:forEach var="o" items="${formData.obsMapper.cd4PercentObsList}">
			datacd4Percent.push([ ${o.date.time} , ${o.cd4Percentage.valueNumeric} ]);
		</c:forEach>
		
		// setup background areas
	    var markingscd4Percent = [
	        { yaxis: { to: 100 } }
	    ];

		var optionscd4Percent = {
			<%= UIHelper.getCd4PercentXAxis(formData) %>,
			yaxis: { 
				min: 0, 
				max: 100, 
				ticks: [[0, "0"], [10, "10"], [20, "20"], [30, "30"], [40, "40"], [50, "50"], [60, "60"], [70, "70"], [80, "80"], [90, "90"], [100, "100"]]
			},
			grid: { markings: markingscd4Percent },
			points: { show: true },
			lines: { show: true }
		};
		
		function plotCd4PercentGraph() {
			var p =$j.plot($j("#obsGraphcd4Percent"), [{ label: "", data: datacd4Percent }], optionscd4Percent);
		}
		</c:if>
		
		<c:if test="${!empty formData.obsMapper.bmiForAge}">
		var dataBMITotal = [];
		var v3negw = [];
		var v2negw = [];
		var v1negw = [];
		var v0w = [];
		var v1w = [];
		var v2w = [];
		var v3w = [];
		
		<c:forEach var="o" items="${formData.obsMapper.bmiForAge}">
			dataBMITotal.push([ ${o.ageInMonths} , ${o.bmi} ]);
		</c:forEach>
		
		<c:choose>
	      	<c:when test="${formData.gender == 'F'}">
		      	<c:forEach var="o" items="${formData.whoMapping.bmiZScoreGirlsSD3neg}">
					v3negw.push([ ${o.ageInMonths} , ${o.bmi} ]);
				</c:forEach>
	      	</c:when>
	      	<c:otherwise>
		      	<c:forEach var="o" items="${formData.whoMapping.bmiZScoreBoysSD3neg}">
					v3negw.push([ ${o.ageInMonths} , ${o.bmi} ]);
				</c:forEach>
	      	</c:otherwise>
	    </c:choose>
	    
	    <c:choose>
	      	<c:when test="${formData.gender == 'F'}">
		      	<c:forEach var="o" items="${formData.whoMapping.bmiZScoreGirlsSD2neg}">
					v2negw.push([ ${o.ageInMonths} , ${o.bmi} ]);
				</c:forEach>
	      	</c:when>
	      	<c:otherwise>
		      	<c:forEach var="o" items="${formData.whoMapping.bmiZScoreBoysSD2neg}">
					v2negw.push([ ${o.ageInMonths} , ${o.bmi} ]);
				</c:forEach>
	      	</c:otherwise>
		</c:choose>
		
		<c:choose>
	      	<c:when test="${formData.gender == 'F'}">
		      	<c:forEach var="o" items="${formData.whoMapping.bmiZScoreGirlsSD1neg}">
					v1negw.push([ ${o.ageInMonths} , ${o.bmi} ]);
				</c:forEach>
	      	</c:when>
	      	<c:otherwise>
		      	<c:forEach var="o" items="${formData.whoMapping.bmiZScoreBoysSD1neg}">
					v1negw.push([ ${o.ageInMonths} , ${o.bmi} ]);
				</c:forEach>
	      	</c:otherwise>
		</c:choose>
		
	    <c:choose>
	      	<c:when test="${formData.gender == 'F'}">
		      	<c:forEach var="o" items="${formData.whoMapping.bmiZScoreGirlsSD0}">
					v0w.push([ ${o.ageInMonths} , ${o.bmi} ]);
				</c:forEach>
	      	</c:when>
	      	<c:otherwise>
		      	<c:forEach var="o" items="${formData.whoMapping.bmiZScoreBoysSD0}">
					v0w.push([ ${o.ageInMonths} , ${o.bmi} ]);
				</c:forEach>
	      	</c:otherwise>
		</c:choose>
		
		<c:choose>
	      	<c:when test="${formData.gender == 'F'}">
		      	<c:forEach var="o" items="${formData.whoMapping.bmiZScoreGirlsSD1}">
					v1w.push([ ${o.ageInMonths} , ${o.bmi} ]);
				</c:forEach>
	      	</c:when>
	      	<c:otherwise>
		      	<c:forEach var="o" items="${formData.whoMapping.bmiZScoreBoysSD1}">
					v1w.push([ ${o.ageInMonths} , ${o.bmi} ]);
				</c:forEach>
	      	</c:otherwise>
		</c:choose>	
		
		<c:choose>
	      	<c:when test="${formData.gender == 'F'}">
		      	<c:forEach var="o" items="${formData.whoMapping.bmiZScoreGirlsSD2}">
					v2w.push([ ${o.ageInMonths} , ${o.bmi} ]);
				</c:forEach>
	      	</c:when>
	      	<c:otherwise>
		      	<c:forEach var="o" items="${formData.whoMapping.bmiZScoreBoysSD2}">
					v2w.push([ ${o.ageInMonths} , ${o.bmi} ]);
				</c:forEach>
	      	</c:otherwise>
		</c:choose>	
	
		<c:choose>
		  	<c:when test="${formData.gender == 'F'}">
		      	<c:forEach var="o" items="${formData.whoMapping.bmiZScoreGirlsSD3}">
					v3w.push([ ${o.ageInMonths} , ${o.bmi} ]);
				</c:forEach>
		  	</c:when>
		  	<c:otherwise>
		      	<c:forEach var="o" items="${formData.whoMapping.bmiZScoreBoysSD3}">
					v3w.push([ ${o.ageInMonths} , ${o.bmi} ]);
				</c:forEach>
		  	</c:otherwise>
		</c:choose>	

		// setup background areas
	    var markingsWeightTotal = [
	        { yaxis: { to: 50 }}
	    ];

		var optionsBMIForAgeTotal = {
 			xaxis: { 
				min: 60, 
				max: 180, 
				ticks: [[60, "5"], [72, "6"], [84, "7"], [96, "8"], [108, "9"], [120, "10"], [132, "11"], [144, "12"], [156, "13"], [168, "14"], [180, "15"]]
			},
			yaxis: {
 				min: 0,
 				max: 50,
 				tickSize: 5,
 			},
			grid: { markings: markingsWeightTotal },
			points: [{ show: false }, { show: false}, { show: false}, { show: false}, { show: false}, { show: false}, { show: false}, { show: true}],
			lines: { show: true },
			legend: { position: "nw"}
		};

		function plotBMIForAgeGraph() {
			
			var p =$j.plot($j("#obsGraphBMIForAge"), [{ label: "3", color: "#000000", data: v3w }, { label: "2", color: "#FF0000", data: v2w }, { label: "1", color: "#FF6600", data: v1w }, { label: "0", color: "#00FF33", data: v0w }, { label: "-1", color: "#FF6600", data: v1negw }, { label: "-2", color: "#FF0000", data: v2negw },{ label: "-3", color: "#000000", data: v3negw }, { label: "Patient", color: "#0000CC", data: dataBMITotal }], optionsBMIForAgeTotal);
		}
		</c:if>
		
		<c:if test="${!empty formData.obsMapper.weightForLength}">
		var dataWeightForLength = [];
		var sd3WFL = [];
		var sd2WFL = [];
		var sd0WFL = [];
		var sd2negWFL = [];
		var sd3negWFL = [];
		
		<c:forEach var="o" items="${formData.obsMapper.weightForLength}">
			dataWeightForLength.push([ ${o.weight} , ${o.height} ]);
		</c:forEach>
		
		<c:choose>
	      	<c:when test="${formData.gender == 'F'}">
		      	<c:forEach var="o" items="${formData.whoMapping.weightForLengthGirlsSD3}">
					sd3WFL.push([ ${o.weight} , ${o.height} ]);
				</c:forEach>
	      	</c:when>
	      	<c:otherwise>
		      	<c:forEach var="o" items="${formData.whoMapping.weightForLengthBoysSD3}">
					sd3WFL.push([ ${o.weight} , ${o.height} ]);
				</c:forEach>
	      	</c:otherwise>
	    </c:choose>
	    
	    <c:choose>
	      	<c:when test="${formData.gender == 'F'}">
		      	<c:forEach var="o" items="${formData.whoMapping.weightForLengthGirlsSD2}">
					sd2WFL.push([ ${o.weight} , ${o.height} ]);
				</c:forEach>
	      	</c:when>
	      	<c:otherwise>
		      	<c:forEach var="o" items="${formData.whoMapping.weightForLengthBoysSD2}">
					sd2WFL.push([ ${o.weight} , ${o.height} ]);
				</c:forEach>
	      	</c:otherwise>
		</c:choose>
		
	    <c:choose>
	      	<c:when test="${formData.gender == 'F'}">
		      	<c:forEach var="o" items="${formData.whoMapping.weightForLengthGirlsSD0}">
					sd0WFL.push([ ${o.weight} , ${o.height} ]);
				</c:forEach>
	      	</c:when>
	      	<c:otherwise>
		      	<c:forEach var="o" items="${formData.whoMapping.weightForLengthBoysSD0}">
					sd0WFL.push([ ${o.weight} , ${o.height} ]);
				</c:forEach>
	      	</c:otherwise>
		</c:choose>
		
	    <c:choose>
	      	<c:when test="${formData.gender == 'F'}">
		      	<c:forEach var="o" items="${formData.whoMapping.weightForLengthGirlsSD2neg}">
					sd2negWFL.push([ ${o.weight} , ${o.height} ]);
				</c:forEach>
	      	</c:when>
	      	<c:otherwise>
		      	<c:forEach var="o" items="${formData.whoMapping.weightForLengthBoysSD2neg}">
					sd2negWFL.push([ ${o.weight} , ${o.height} ]);
				</c:forEach>
	      	</c:otherwise>
		</c:choose>	
		
	    <c:choose>
	      	<c:when test="${formData.gender == 'F'}">
		      	<c:forEach var="o" items="${formData.whoMapping.weightForLengthGirlsSD3neg}">
					sd3negWFL.push([ ${o.weight} , ${o.height} ]);
				</c:forEach>
	      	</c:when>
	      	<c:otherwise>
		      	<c:forEach var="o" items="${formData.whoMapping.weightForLengthBoysSD3neg}">
					sd3negWFL.push([ ${o.weight} , ${o.height} ]);
				</c:forEach>
	      	</c:otherwise>
		</c:choose>

		// setup background areas
	    var markingsWeightForLength = [
	        { yaxis: { to: 110 } }
	    ];

		var optionsWeightForLength = {
 			xaxis: {
 				min: 0,
 				max: 26,
 				tickSize: 1,
 				},
			yaxis: { 
				min: 40, 
				max: 110, 
				ticks: [[40, ""], [45, "45"], [50, "50"], [55, "55"], [60, "60"], [65, "65"], [70, "70"], [75, "75"], [80, "80"], [85, "85"], [90, "90"], [95, "95"], [100, "100"], [105, "105"], [110, "110"]]
			},
			grid: { markings: markingsWeightForLength },
			points: [{ show: false }, { show: false}, { show: false}, { show: false}, { show: false}, { show: true}],
			lines: { show: true },
			legend: { position: "nw"}
		};

		function plotWeightForLengthGraph() {
			
			var p =$j.plot($j("#obsGraphWeightForLength"), [{ label: "3", color: "#FF0000", data: sd3WFL }, { label: "2", color: "#FF6600", data: sd2WFL }, { label: "0", color: "#00FF33", data: sd0WFL }, { label: "-2", color: "#CC6600", data: sd2negWFL }, { label: "-3", color: "#CC0000", data: sd3negWFL }, { label: "Patient", color: "#0000CC", data: dataWeightForLength } ], optionsWeightForLength);
		}
		</c:if>
		
		<c:if test="${!empty formData.obsMapper.weightForHeight}">
		var dataWeightForHeight = [];
		var sd3WFH = [];
		var sd2WFH = [];
		var sd0WFH = [];
		var sd2negWFH = [];
		var sd3negWFH = [];
		
		<c:forEach var="o" items="${formData.obsMapper.weightForHeight}">
			dataWeightForHeight.push([ ${o.weight} , ${o.height} ]);
		</c:forEach>
		
		<c:choose>
	      	<c:when test="${formData.gender == 'F'}">
		      	<c:forEach var="o" items="${formData.whoMapping.weightForHeightGirlsSD3}">
					sd3WFH.push([ ${o.weight} , ${o.height} ]);
				</c:forEach>
	      	</c:when>
	      	<c:otherwise>
		      	<c:forEach var="o" items="${formData.whoMapping.weightForHeightBoysSD3}">
					sd3WFH.push([ ${o.weight} , ${o.height} ]);
				</c:forEach>
	      	</c:otherwise>
	    </c:choose>
	    
	    <c:choose>
	      	<c:when test="${formData.gender == 'F'}">
		      	<c:forEach var="o" items="${formData.whoMapping.weightForHeightGirlsSD2}">
					sd2WFH.push([ ${o.weight} , ${o.height} ]);
				</c:forEach>
	      	</c:when>
	      	<c:otherwise>
		      	<c:forEach var="o" items="${formData.whoMapping.weightForHeightBoysSD2}">
					sd2WFH.push([ ${o.weight} , ${o.height} ]);
				</c:forEach>
	      	</c:otherwise>
		</c:choose>
		
	    <c:choose>
	      	<c:when test="${formData.gender == 'F'}">
		      	<c:forEach var="o" items="${formData.whoMapping.weightForHeightGirlsSD0}">
					sd0WFH.push([ ${o.weight} , ${o.height} ]);
				</c:forEach>
	      	</c:when>
	      	<c:otherwise>
		      	<c:forEach var="o" items="${formData.whoMapping.weightForHeightBoysSD0}">
					sd0WFH.push([ ${o.weight} , ${o.height} ]);
				</c:forEach>
	      	</c:otherwise>
		</c:choose>
		
	    <c:choose>
	      	<c:when test="${formData.gender == 'F'}">
		      	<c:forEach var="o" items="${formData.whoMapping.weightForHeightGirlsSD2neg}">
					sd2negWFH.push([ ${o.weight} , ${o.height} ]);
				</c:forEach>
	      	</c:when>
	      	<c:otherwise>
		      	<c:forEach var="o" items="${formData.whoMapping.weightForHeightBoysSD2neg}">
					sd2negWFH.push([ ${o.weight} , ${o.height} ]);
				</c:forEach>
	      	</c:otherwise>
		</c:choose>	
		
	    <c:choose>
	      	<c:when test="${formData.gender == 'F'}">
		      	<c:forEach var="o" items="${formData.whoMapping.weightForHeightGirlsSD3neg}">
					sd3negWFH.push([ ${o.weight} , ${o.height} ]);
				</c:forEach>
	      	</c:when>
	      	<c:otherwise>
		      	<c:forEach var="o" items="${formData.whoMapping.weightForHeightBoysSD3neg}">
					sd3negWFH.push([ ${o.weight} , ${o.height} ]);
				</c:forEach>
	      	</c:otherwise>
		</c:choose>

		// setup background areas
	    var markingsWeightForHeight = [
	        { yaxis: { to: 120 } }
	    ];

		var optionsWeightForHeight = {
 			xaxis: {
 				min: 5,
 				max: 34,
 				tickSize: 1,
 				},
			yaxis: { 
				min: 65, 
				max: 120, 
				ticks: [[65, "65"], [70, "70"], [75, "75"], [80, "80"], [85, "85"], [90, "90"], [95, "95"], [100, "100"], [105, "105"], [110, "110"], [115, "115"], [120, "120"]]
			},
			grid: { markings: markingsWeightForHeight },
			points: [{ show: false }, { show: false}, { show: false}, { show: false}, { show: false}, { show: true}],
			lines: { show: true },
			legend: { position: "nw"}
		};

		function plotWeightForHeightGraph() {
			
			var p =$j.plot($j("#obsGraphWeightForHeight"), [{ label: "3", color: "#FF0000", data: sd3WFH }, { label: "2", color: "#FF6600", data: sd2WFH }, { label: "0", color: "#00FF33", data: sd0WFH }, { label: "-2", color: "#CC6600", data: sd2negWFH }, { label: "-3", color: "#CC0000", data: sd3negWFH }, { label: "Patient", color: "#0000CC", data: dataWeightForHeight } ], optionsWeightForHeight);
		}
		</c:if>

		function loadGraphs() {
			$j("#graphs").removeClass('ui-tabs-hide');
			plotHeightGraph();
			<c:if test="${!empty formData.obsMapper.lengthForAge || formData.patient.age < 5}">
			plotLengthGraph();
			</c:if>
			<c:if test="${!empty formData.obsMapper.weightForAgeFromBirth || formData.patient.age < 5}">
			plotWeightBirthGraph();
			</c:if>
			plotWeightGraph();
			plotCd4Graph();
			<c:if test="${!empty formData.obsMapper.cd4PercentObsList || formData.patient.age < 5}">
			plotCd4PercentGraph();
			</c:if>
			<c:if test="${!empty formData.obsMapper.bmiForAge}">
			plotBMIForAgeGraph();
			</c:if>
			<c:if test="${!empty formData.obsMapper.weightForLength}">
			plotWeightForLengthGraph();
			</c:if>
			<c:if test="${!empty formData.obsMapper.weightForHeight}">
			plotWeightForHeightGraph();
			</c:if>
			$j("#graphs").addClass('ui-tabs-hide');					
		}

		window.setTimeout(loadGraphs, 1000);
</script>

<%@ include file="fragments/load.jspf"%>

<span class="view-row"><a href="javascript:window.print()"><strong>Print flowsheet</strong></a></span>
<div id="form-header-section">
	<div class="form-header">FICHE RESUMEE DU PATIENT VIH + (Enfant < 15 ans)</div>
	<div>
		<table id="demographics-table" width="100%">
		     <c:if test="${formData.otherIdentifiers != ''}">
				<tr>
					<td colspan="4"align="left">${formData.otherIdentifiers}</td>
				</tr>
			</c:if>
			<tr>
				<td width="25%"><span class="value-label-big">NOM: </span><span class="value-data-big">${patient.personName.familyName}</span></td>
				<td width="25%"><span class="value-label-big">PRENOM: </span><span class="value-data-big">${patient.personName.givenName}</span></td>
				<td width="25%"><span class="value-label"><span class="value-label">TRACnet No: </span><span class="value-data">${formData.tracnetId}</span></td>
				<td width="25%"><span class="value-label">Nom de la FOSA: </span><span class="value-data">${formData.healthCenter}</span></td>
			</tr>
			<tr>
				<td width="25%" colspan="2"><span class="value-label">Date de naissance: </span><span class="value-date"><openmrs:formatDate date="${patient.birthdate}" format="${FormatDate_General}"/> (jj/mm/aaaa)</span></td>
				<td width="50%" colspan="2"><span class="value-label">Date d’enrôlement: </span> ${formData.programHistory} <span class="value-label"> (jj/mm/aaaa)</span></td>
			</tr>
			<tr>
				<td width="50%" colspan="2"><span class="value-label">Date de début des ARV:  </span><span class="value-date"><openmrs:formatDate date="${formData.arvStartDate}" format="${FormatDate_General}"/></span> <span class="value-label"> (jj/mm/aaaa)</span></td>
				<td width="50%" colspan="2"><span class="value-label">Date du diagnostic positif:  </span><span class="value-date"><openmrs:formatDate date="${formData.positiveDiagnosisDate}" format="${FormatDate_General}"/></span> <span class="value-label"> (jj/mm/aaaa)</span></td>
			</tr>
		</table>
	</div>
</div>

<%@ include file="fragments/tabs.jspf"%>

<div id="regimen-section">
	
	<%@ include file="fragments/arvRegimen.jspf"%>
	
	<div id="tb-regimen-section"> <!-- TRAITEMENT ANTITUBERCULEUX -->
		<table class="section-table">
			<thead>
				<tr>
					<th scope="col" id="col-tb-emr-1" class="section-emr">EMR</th>
					<th scope="col" id="col-tb-info">TRAITEMENT ANTITUBERCULEUX</th>
					<th scope="col" id="col-tb-emr-2" class="section-emr">EMR</th>
					<th scope="col" id="col-tb-phase"></th>
					<th scope="col" id="col-tb-emr-3" class="section-emr">EMR</th>
					<th scope="col" id="col-tb-stop"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="episode" items="${formData.tuberculosisEpisodes}">
				<jsp:useBean id="episode" type="org.openmrs.module.rwandahivflowsheet.impl.pih.TbEpisodeMapping" />
					<tr>
						<td class="section-emr"><c:if test="${!empty episode.episodeDate}">&#x2713;</c:if></td>
						<td>
							<span class="value-label">Date de début: </span><span class="value-date">
							<c:choose>
									<c:when test="${!empty episode.episodeDate}">
										<openmrs:formatDate date="${episode.episodeDate}" format="${FormatDate_General}"/>
									</c:when>
									<c:otherwise>
										${DateTextPlaceHolder}
									</c:otherwise>
								</c:choose>
							 </span>
							<span class="value-label">Crachats: </span>
								<%= UIHelper.getCheckBoxWidget(episode.isSmearPositive(), "Positif") %>
								<%= UIHelper.getCheckBoxWidget(episode.isSmearNegative(), "Négatif") %>
							<br/>
							<span class="value-label">Schema therapetique: </span>
								<%= UIHelper.getCheckBoxWidget(episode.isInitialTreatment(), "Premier") %>
								<%= UIHelper.getCheckBoxWidget(episode.isRetreatment(), "Retraitement") %>
								<%= UIHelper.getCheckBoxWidget(episode.isMdrtb(), "MDR-TB") %>
							<br/>
							
							<span class="value-label">Préciser le traitement anti TB: </span><span class="value-data"><%= UIHelper.formatRegimenDisplaySummary(episode.getInitialDrugOrder()) %></span></td>
						<td class="section-emr"></td>
						<td>
							<span class="value-label">Phase de continuation</span><br/>
								<c:if test="${!empty episode.continuationPhaseDrugOrder}">
									<span class="value-data"><openmrs:formatDate date="${episode.continuationPhaseDrugOrder.startDate}" format="${FormatDate_General}"/><br/>
									(<%= UIHelper.formatRegimenDisplaySummary(episode.getContinuationPhaseDrugOrder()) %>)</span>
								</c:if>
								<c:if test="${empty  episode.continuationPhaseDrugOrder}"><span class="value-date">${DateTextPlaceHolder}</span></c:if>
						</td>
						<td class="section-emr"><c:if test="${drugOrder.discontinued}">&#x2713;</c:if></td>
						<td>
							<span class="value-label">Date d'arrêt: </span><span class="value-date">
								<c:choose>
									<c:when test="${!empty episode.continuationPhaseDrugOrder}">
										<openmrs:formatDate date="${episode.continuationPhaseDrugOrder.discontinuedDate}" format="${FormatDate_General}"/>
									</c:when>
									<c:otherwise>
										${DateTextPlaceHolder}
									</c:otherwise>
								</c:choose>
							</span>&nbsp;&nbsp;&nbsp;
							<span class="value-label">RESULTAT DE TRAITEMENT: </span>
							<br/>
							<%= UIHelper.getCheckBoxWidget(episode.isCured(), "Guéri ") %> (crachat positif au début, date crachat négatif: <c:if test="${empty episode.firstNegativeSmear}">${DateTextPlaceHolder}</c:if><c:if test="${!empty episode.firstNegativeSmear}"><openmrs:formatDate date="${episode.firstNegativeSmear.obsDatetime}" format="${FormatDate_General}"/></c:if>)
							<%= UIHelper.getCheckBoxWidget(episode.isFailed(), "Echec de traitement") %>
							<br/>
							<%= UIHelper.getCheckBoxWidget(episode.isComplete(), "Traitement complet") %>
							<%= UIHelper.getCheckBoxWidget(episode.isAbandoned(), "Abandonné") %>
							<%= UIHelper.getCheckBoxWidget(episode.isTransfered(), "Transféré") %>
							<%= UIHelper.getCheckBoxWidget(episode.isDead(), "Mort") %>
							<%= UIHelper.getCheckBoxWidget(episode.isOther(), "Autre:") %><c:if test="${episode.other}"><span class="value-data">${episode.outcomeOther}</span></c:if>
						</td> 
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div id="prophylacticRegimen"> <!-- TRAITEMENT PROPHYLACTIQUE -->
		<table class="section-table">
			<thead>
				<tr>
					<th scope="col" id="col-prophylactic-emr-1" class="section-emr">EMR</th>
					<th scope="col" colspan="2" id="col-prophylactic-info">TRAITEMENT PROPHYLACTIQUE</th>
					<th scope="col" id="col-prophylactic-emr-2" class="section-emr">EMR</th>
					<th scope="col" colspan="2" id="col-prophylactic-stop"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="prophylaxisepisode" items="${formData.prophylaxisEpisodes}">
				<jsp:useBean id="prophylaxisepisode" type="org.openmrs.module.rwandahivflowsheet.impl.pih.ProphylaxisMapping" />
					<tr>
						<td class="section-emr"><c:if test="${!empty prophylaxisepisode.startDate}">&#x2713;</c:if><c:if test="${empty prophylaxisepisode.startDate}">&nbsp;</c:if></td>
						<td>
							<span class="value-label">Médicament:</span>
							<%= UIHelper.getCheckBoxWidget(prophylaxisepisode.isCotrimoxisole(), "Cotrimoxazole") %>
							<%= UIHelper.getCheckBoxWidget(prophylaxisepisode.isFluconazole(), "Fluconazole") %>
							<%= UIHelper.getCheckBoxWidget(prophylaxisepisode.isIsoniazid(), "Isoniazid") %>
							<br/>
							<%= UIHelper.getCheckBoxWidget(prophylaxisepisode.isDapsone(), "Dapsone") %>
							<%= UIHelper.getCheckBoxWidget(prophylaxisepisode.isProphylaxisOther(), "Autre: ") %> <span class="value-data"><%= UIHelper.formatRegimenDisplaySummary(prophylaxisepisode.getDrugOrder()) %> </span>
						</td>
						<td>
							<span class="value-label">Date de début</span><br/>
							<span class="value-date"><c:if test="${empty prophylaxisepisode.startDate}">${DateTextPlaceHolder}</c:if><c:if test="${!empty prophylaxisepisode.startDate}"><openmrs:formatDate date="${prophylaxisepisode.startDate}" format="${FormatDate_General}"/></c:if></span>
						</td>
						<td class="section-emr"><c:if test="${!empty prophylaxisepisode.stopDate}">&#x2713;</c:if><c:if test="${empty prophylaxisepisode.stopDate}">&nbsp;</c:if></td>
						<td>
							<span class="value-label">Date d’arrêt</span><br/>
							<span class="value-date"><c:if test="${empty prophylaxisepisode.stopDate}">${DateTextPlaceHolder}</c:if><c:if test="${!empty prophylaxisepisode.stopDate}"><openmrs:formatDate date="${prophylaxisepisode.stopDate}" format="${FormatDate_General}"/></c:if></span>
						</td>
						<td>
							<span class="value-label">Raison d’arrêt</span>
							<%= UIHelper.getCheckBoxWidget(prophylaxisepisode.isToxicity(), "Toxicité") %>
							<%= UIHelper.getCheckBoxWidget(prophylaxisepisode.isAbandoned(), "Abandonné") %>
							<%= UIHelper.getCheckBoxWidget(prophylaxisepisode.isOutOfStock(), "Rupture de stock") %>
							<br/>
							<%= UIHelper.getCheckBoxWidget(prophylaxisepisode.isCd4Improved(), "Amelioration de CD4") %>
							<%= UIHelper.getCheckBoxWidget(prophylaxisepisode.isTermine(), "termine") %>
							<%= UIHelper.getCheckBoxWidget(prophylaxisepisode.isReasonForStoppingOther(), "Autre:") %> <c:if test="${prophylaxisepisode.reasonForStoppingOther}"><span class="value-data">${prophylaxisepisode.discontinueReasonOther}</span></c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<%@ include file="fragments/chronicRegimens.jspf"%>
</div>
<div id="history" style="page-break-before: always">


<%@ include file="fragments/allergy.jspf" %>

<%@ include file="fragments/hospitalisations.jspf" %>

<%@ include file="fragments/oi.jspf"%>

<div id="Misc"> <!-- Misc -->
		<table class="section-table">
			<thead>
				<tr>
					<th colspan="5">AUTRES MALADIES CHRONIQUES</th>
				</tr>
				<%@ include file="fragments/chronic.jspf"%>
				
</div>

<%@ include file="fragments/visit_pedi.jspf"%>

<div style="page-break-before: always" id="orders">

	<%@ include file="fragments/lab_pedi.jspf"%>
	<%@ include file="fragments/images_pedi.jspf"%>

</div>
<div id="graphs" class="graphs">
		
		<c:if test="${!empty formData.obsMapper.lengthForAge}">
		<div style="page-break-before: always" id="lengthForAge"> 
		<h1>
			<c:if test="${formData.gender == 'F'}">
				Length/height-for-age GIRLS
			</c:if>

			<c:if test="${formData.gender == 'M'}">
				Length/height-for-age BOYS	
			</c:if>
		</h1>
		<hr />
		<b>Birth to 5 years(z-scores)</b>
		<table class="section-graph">
			<tbody>
				<tr>
					<td align="left">Length/<br />Height (cm)</td>	
					<td align="center">
						<div id="obsGraphLength" class="lengthForAge"></div>
					</td>
				</tr>
				<tr>
					<td></td>
					<td align="center">
						Age(completed months and years)
					</td>
				</tr>
			</tbody>
		</table>
		</div>
		<div style="height: 10px"></div>
		</c:if>
		
		
		<div style="page-break-before: always" id="heightForAge"> 
		<h1>
			<c:if test="${formData.gender == 'F'}">
				Height-for-age GIRLS
			</c:if>

			<c:if test="${formData.gender == 'M'}">
				Height-for-age BOYS	
			</c:if>
		</h1>
		<hr />
		<b>5 to 19 years(z-scores)</b>
		<table class="section-graph">
			<tbody>
				<tr>
					<td align="left">Height (cm)</td>	
					<td align="center">
						<div id="obsGraphHeight" class="heightForAge"></div>
					</td>
				</tr>
				<tr>
					<td></td>
					<td align="center">
						Age(completed months and years)
					</td>
				</tr>
			</tbody>
		</table>
		</div>
		<div style="height: 10px"></div>
		
		<c:if test="${!empty formData.obsMapper.weightForAgeFromBirth || formData.patient.age < 5}">
		<div style="page-break-before: always" id="weightForAgeBirth"> 
		<h1>
			<c:if test="${formData.gender == 'F'}">
				Weight-for-age GIRLS
			</c:if>

			<c:if test="${formData.gender == 'M'}">
				Weight-for-age BOYS	
			</c:if>
		</h1>
		<hr />
		<b>Birth to 5 years(z-scores)</b>
		<table class="section-graph">
			<tbody>
				<tr>
					<td align="left">Weight (kg)</td>	
					<td align="center">
						<div id="obsGraphWeightBirth" class="weightForAgeBirth"></div>
					</td>
				</tr>
				<tr>
					<td></td>
					<td align="center">
						Age(completed months and years)
					</td>
				</tr>
			</tbody>
		</table>
		</div>
		<div style="height: 10px"></div>
		</c:if>
		
		
		<div style="page-break-before: always" id="weightForAge"> 
		<h1>
			<c:if test="${formData.gender == 'F'}">
				Weight-for-age GIRLS
			</c:if>

			<c:if test="${formData.gender == 'M'}">
				Weight-for-age BOYS	
			</c:if>
		</h1>
		<hr />
		<b>5 to 10 years(z-scores)</b>
		<table class="section-graph">
			<tbody>
				<tr>
					<td align="left">Weight (kg)</td>	
					<td align="center">
						<div id="obsGraphWeight" class="weightForAge"></div>
					</td>
				</tr>
				<tr>
					<td></td>
					<td align="center">
						Age(completed months and years)
					</td>
				</tr>
			</tbody>
		</table>
		</div>
		<div style="height: 10px"></div>
		
		<c:if test="${!empty formData.obsMapper.weightForLength}">
		<div style="page-break-before: always" id="weightForLength"> 
		<h1>
			<c:if test="${formData.gender == 'F'}">
				Weight for Length GIRLS
			</c:if>

			<c:if test="${formData.gender == 'M'}">
				Weight for Length BOYS	
			</c:if>
		</h1>
		<hr />
		<b>0 to 2 years(z-scores)</b>
		<table class="section-graph">
			<tbody>
				<tr>
					<td align="left">Height (cm)</td>	
					<td align="center">
						<div id="obsGraphWeightForLength" class="lengthForAge"></div>
					</td>
				</tr>
				<tr>
					<td></td>
					<td align="center">
						Weight (kg)
					</td>
				</tr>
			</tbody>
		</table>
		</div>
		<div style="height: 10px"></div>
		</c:if>
		
		<c:if test="${!empty formData.obsMapper.weightForHeight}">
		<div style="page-break-before: always" id="weightForHeight"> 
		<h1>
			<c:if test="${formData.gender == 'F'}">
				Weight for Height GIRLS
			</c:if>

			<c:if test="${formData.gender == 'M'}">
				Weight for Height BOYS	
			</c:if>
		</h1>
		<hr />
		<b>2 to 5 years(z-scores)</b>
		<table class="section-graph">
			<tbody>
				<tr>
					<td align="left">Height (cm)</td>	
					<td align="center">
						<div id="obsGraphWeightForHeight" class="lengthForAge"></div>
					</td>
				</tr>
				<tr>
					<td></td>
					<td align="center">
						Weight (kg)
					</td>
				</tr>
			</tbody>
		</table>
		</div>
		<div style="height: 10px"></div>
		</c:if>
		
		<c:if test="${!empty formData.obsMapper.bmiForAge}">
		<div style="page-break-before: always" id="bmiForAge"> 
		<h1>
			<c:if test="${formData.gender == 'F'}">
				BMI-for-age GIRLS
			</c:if>

			<c:if test="${formData.gender == 'M'}">
				BMI-for-age BOYS	
			</c:if>
		</h1>
		<hr />
		<b>5 to 15 years(z-scores)</b>
		<table class="section-graph">
			<tbody>
				<tr>
					<td align="left">BMI</td>	
					<td align="center">
						<div id="obsGraphBMIForAge" class="bmiForAge"></div>
					</td>
				</tr>
				<tr>
					<td></td>
					<td align="center">
						Age(completed months and years)
					</td>
				</tr>
			</tbody>
		</table>
		</div>
		<div style="height: 10px"></div>
		</c:if>
		
		<c:if test="${!empty formData.obsMapper.cd4PercentObsList || formData.patient.age < 5}">
		<div style="page-break-before: always" id="cd4PercentGraph"> 
		<h1>COURBE D'EVOLUTION DES CD4%
		</h1>
		<hr /> 
		<table class="section-graph">
			<tbody>
				<tr>
					<td align="left">CD4%</td>	
					<td align="center">
						<div id="obsGraphcd4Percent" class="cd4PercentGraph"></div>
					</td>
				</tr>
			</tbody>
		</table>
		</div>
		<div style="height: 10px"></div>
		</c:if>
		
		
		
		<div style="page-break-before: always" id="cd4Graph"> 
		<h1>COURBE D'EVOLUTION DES CD4
		</h1>
		<hr /> 
		<table class="section-graph">
			<tbody>
				<tr>
					<td align="left">CD4 (cells/mm3)</td>	
					<td align="center">
						<div id="obsGraphcd4Count" class="cd4Graph"></div>
					</td>
				</tr>
			</tbody>
		</table>
		</div>
		<div style="height: 10px"></div>
		
</div>

<%@ include file="templates/footer.jsp"%>
