<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<%@ page import="java.util.*" %>
<%@ page import="org.openmrs.*" %>
<%@ page import="org.openmrs.module.rwandaadulthivflowsheet.mapper.*" %>
<%@ page import="org.openmrs.module.rwandaadulthivflowsheet.web.*" %>

<%@ include file="templates/include.jsp"%>
<%@ include file="templates/header.jsp"%>

<!-- Create htmlInclude tag that takes media -->

<c:set var="FormatDate_General" value="dd/MM/yyyy"/>
<c:set var="DateTextPlaceHolder" value="____/____/______"/>
<openmrs:globalProperty key="rwandaadulthivflowsheet.ConceptId_cd4count" var="ConceptId_cd4count"/>
<openmrs:globalProperty key="rwandaadulthivflowsheet.ConceptId_ViralLoad" var="ConceptId_ViralLoad"/>
<openmrs:globalProperty key="rwandaadulthivflowsheet.Form_NewAllergy" var="Form_NewAllergy"/>
<openmrs:globalProperty key="rwandaadulthivflowsheet.Form_NewHospitalisations" var="Form_NewHospitalisations"/>
<openmrs:globalProperty key="rwandaadulthivflowsheet.Form_NewOI" var="Form_NewOI"/>
<openmrs:globalProperty key="rwandaadulthivflowsheet.Form_NewProblem" var="Form_NewProblem"/>
<openmrs:globalProperty key="rwandaadulthivflowsheet.Form_NewVisit" var="Form_NewVisit"/>
<openmrs:globalProperty key="rwandaadulthivflowsheet.Form_NewLab" var="Form_NewLab"/>
<openmrs:globalProperty key="rwandaadulthivflowsheet.Form_NewImage" var="Form_NewImage"/>

<openmrs:htmlInclude file="${pageContext.request.contextPath}/scripts/jquery-ui/js/jquery-ui-1.7.2.custom.min.js"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/scripts/jquery-ui/css/redmond/jquery-ui-1.7.2.custom.css" media="screen" />
		
<openmrs:htmlInclude file="${pageContext.request.contextPath}/moduleResources/rwandaadulthivflowsheet/flot/excanvas.min.js"/>
<openmrs:htmlInclude file="${pageContext.request.contextPath}/moduleResources/rwandaadulthivflowsheet/flot/jquery.flot.min.js"/>

<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/moduleResources/rwandaadulthivflowsheet/adulthivflowsheet.css" media="screen" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/moduleResources/rwandaadulthivflowsheet/adulthivflowsheetPrint.css" media="print" />



<c:set var="formData" value="${formData}"/>
<jsp:useBean id="formData" type="org.openmrs.module.rwandaadulthivflowsheet.web.model.AdultHivFlowsheetFormData" />

<div id="displayEncounterPopup">
	<div id="displayEncounterPopupLoading"><spring:message code="general.loading"/></div>
	<iframe id="displayEncounterPopupIframe" width="100%" height="100%" marginWidth="0" marginHeight="0" frameBorder="0" scrolling="auto"></iframe>
</div>

<script type="text/javascript">
	$j(document).ready(function() {
		$j('#displayEncounterPopup').dialog({
				title: 'dynamic',
				autoOpen: false,
				draggable: false,
				resizable: false,
				width: '95%',
				modal: true,
				open: function(a, b) { $j('#displayEncounterPopupLoading').show(); }
		});
	});

	function loadUrlIntoEncounterPopup(title, urlToLoad) {
		$j("#displayEncounterPopupIframe").attr("src", urlToLoad);
		$j('#displayEncounterPopup')
			.dialog('option', 'title', title)
			.dialog('option', 'height', $j(window).height() - 50) 
			.dialog('open');
		$j('#displayEncounterPopupLoading').hide();
	}
</script>

<script type="text/javascript">
function loadUrlIntoEncounterPopup(title, url, reloadOnClose) {
	var elem = $j('#displayEncounterPopup');
	elem.dialog('option', 'title', title)
		.dialog('option', 'height', $j(window).height() - 50);
	if (reloadOnClose) {
		//$j( "#flowsheet-tabs" ).tabs( "option", "cookie", { expires: 1 } );
		//+ '#' +$j("#flowsheet-tabs").tabs('option', 'selected')
		//document.location='#'+(ui.index+1);
		elem.dialog('option', 'close', function(event, ui) { window.location.hash = $j("#flowsheet-tabs").tabs('option', 'selected'); window.location.reload(); });
	} 
	elem.dialog('open');
	$j("#displayEncounterPopupIframe").attr("src", url+'&inPopup=true&closeAfterSubmission=closeEncounterPopup');
	$j('#displayEncounterPopupLoading').hide();
}

function closeEncounterPopup() {
	$j('#displayEncounterPopup').dialog('close');
}

function showEncounterPopup(title, encId) {
	loadUrlIntoEncounterPopup(title, openmrsContextPath + '/module/htmlformentry/htmlFormEntry.form?inPopup=true&encounterId=' + encId, false);
}

function showEntryPopup(title, formId) {
	loadUrlIntoEncounterPopup(title, openmrsContextPath + '/module/htmlformentry/htmlFormEntry.form?inPopup=true&personId=' + ${patient.id} + '&formId=' + formId, true);
}
</script>
<script type="text/javascript">
		var data${ConceptId_cd4count} = [];
		<%
		//List<Obs> cd4List = formData.getCd4ObsList();
		//for(int index = 0; index < cd4List.size(); index++) {
		//	out.println("data"+AdultHivFlowsheetFormData.ConceptId_CD4+".push([ "+index+" , "+ cd4List.get(index).getValueNumeric()+" ]);");
		//}
		%>
		<c:forEach var="o" items="${formData.obsMapper.cd4ObsList}">
			data${ConceptId_cd4count}.push([ ${o.date.time} , ${o.cd4.valueNumeric} ]);
		</c:forEach>

		// setup background areas
	    var markings${ConceptId_cd4count} = [
//	        { color: '#f6f6f6', yaxis: { from: 2000 } },
	        { color: '#FFD2F2', yaxis: { to: 350 } }
//	        { color: '#000', lineWidth: 1, yaxis: { from: 2, to: 2 } },
//	        { color: '#000', lineWidth: 1, yaxis: { from: 8, to: 8 } }
	    ];

		var options${ConceptId_cd4count} = {
			<%= UIHelper.getCd4XAxis(formData) %>,
			//xaxis: { mode: "time", timeformat: "%d/%m/%y", },
			//xaxis: { 
			//	min: 0,
			//	max: 13,
			//	ticks: [
					<%
			//			for(int index = 0; index < 14; index++) {
			//				if(index > 0)
			//					out.print(", ");
			//				if(index < cd4List.size())
			//					out.print("["+index+", \""+formData.formatDate(AdultHivFlowsheetFormData.FormatDate_General, cd4List.get(index).getObsDatetime())+"\"]");
			//				else
			//					out.print("["+index+", \""+AdultHivFlowsheetFormData.DateTextPlaceHolder+"\"]");
			//			}
					%>
			//		] 
			//},
			yaxis: { 
				min: 0, 
				max: 3000, 
				ticks: [[0, "0"], [100, ""], [200, ""], [300, ""], [400, ""], [500, "500"], [600, ""], [700, ""], [800, ""], [900, ""], [1000, "1000"], [1100, ""], [1200, ""], [1300, ""], [1400, ""], [1500, "1500"], [1600, ""], [1700, ""], [1800, ""], [1900, ""], [2000, "2000"], [2100, ""], [2200, ""], [2300, ""], [2400, ""], [2500, "2500"], [2600, ""], [2700, ""], [2800, ""], [2900, ""], [3000, "3000"]]
			},
			grid: { markings: markings${ConceptId_cd4count} },
			points: { show: true },
			lines: { show: true }
		};

		var dataBMI = [];
		var index = 1;
		<c:forEach var="o" items="${formData.obsMapper.weightObsList}">
			dataBMI.push([ ${o.obsDatetime.time} , ${o.valueNumeric} ]);
		</c:forEach>

		// setup background areas
    	var markingsBMI = [
			{ color: '#FFD2F2', yaxis: { from: 16 * ${formData.obsMapper.latestHeight.valueNumeric} /100 * ${formData.obsMapper.latestHeight.valueNumeric} /100, to: 18.5 * ${formData.obsMapper.latestHeight.valueNumeric} /100 * ${formData.obsMapper.latestHeight.valueNumeric} /100  } },
	        { color: '#ff6347', yaxis: { to: 16 * ${formData.obsMapper.latestHeight.valueNumeric} /100 * ${formData.obsMapper.latestHeight.valueNumeric} /100  } }
    	];

		var optionsBMI = {
				<%= UIHelper.getBMIXAxis(formData) %>,
//				xaxis: { 
//					min: 1,
//					max: 21,
//					ticks: [[1, ""], [2, ""], [3, ""], [4, ""], [5, ""], [6, ""], [7, ""], [8, ""], [9, ""], [10, ""], [11, ""], [12, ""], [13, ""], [14, ""], [15, ""], [16, ""], [17, ""], [18, ""], [19, ""], [20, ""], [21, ""]]
//				},
			yaxis: { 
				min: 20, 
				max: 100, 
				ticks: [[20, ""], [22.5, ""], [25, "25"], [27.5, ""], [30, "30"], [32.5, ""], [35, "35"], [37.5, ""], [40, "40"], [42.5, ""], [45, "45"], [47.5, ""], [50, "50"], [52.5, ""], [55, "55"], [57.5, ""], [60, "60"], [62.5, ""], [65, "65"], [67.5, ""], [70, "70"], [72.5, ""], [75, "75"], [77.5, ""], [80, "80"], [82.5, ""], [85, "85"], [87.5, ""], [90, "90"], [92.5, ""], [95, "95"], [97.5, ""], [100, ""], [16 * ${formData.obsMapper.latestHeight.valueNumeric} /100 * ${formData.obsMapper.latestHeight.valueNumeric} /100, "BMI 16&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"], [18.5 * ${formData.obsMapper.latestHeight.valueNumeric} /100 * ${formData.obsMapper.latestHeight.valueNumeric} /100, "BMI 18.5&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"]]
		},
		grid: { markings: markingsBMI },
		points: { show: true },
		lines: { show: true }
	};


		function plotCd4Graph() {
			var p =$j.plot($j("#obsGraph${ConceptId_cd4count}"), [{ label: "", data: data${ConceptId_cd4count} }], options${ConceptId_cd4count});
//			$j.each(p.getData()[0].data, function(i, el){
//				  var o = p.pointOffset({x: el[0], y: el[1]});
//				  $j('<div class="data-point-label">' + el[1] + '</div>').css( {
//				    position: 'absolute',
//				    left: o.left + 4,
//				    top: o.top - 43,
//				    display: 'none'
//				  }).appendTo(p.getPlaceholder()).fadeIn('slow');
//				});
		}

		function plotBMIGraph() {
			var p =$j.plot($j("#bmiGraphGraph"), [{ label: "", data: dataBMI }], optionsBMI);
		}

		function loadGraphs() {
			$j("#graphs").removeClass('ui-tabs-hide');
			plotCd4Graph();
			plotBMIGraph();
			$j("#graphs").addClass('ui-tabs-hide');					
		}

		//function registerTabListener() {
		//	$j('#flowsheet-tabs').bind('tabsshow', function(event, ui) {
		//        if(ui.index == 4)
		//        	loadGraphs();
		//	});
		//}

		window.setTimeout(loadGraphs, 1000);
		//window.setTimeout(registerTabListener, 500);
</script>

<script type="text/javascript">
	$j(function() {
		var indexToLoad = 0;
		try {
			if(window.location.hash != '') {
				//get the index
				indexToLoad = window.location.hash.substr(1, window.location.hash.length);
			}
		} catch(e){}
		$j("#flowsheet-tabs").tabs({ selected: indexToLoad });
		$j("#flowsheet-tabs").tabs().addClass('ui-tabs-vertical ui-helper-clearfix');
		$j("#flowsheet-tabs li").removeClass('ui-corner-top').addClass('ui-corner-left');
	});
</script>

<div id="form-header-section">
	<div class="form-header">FICHE RESUMEE DU PATIENT - ADULTE</div>
	<div>
		<table id="demographics-table" width="100%">
			<tr>
				<td width="25%"><span class="value-label">NOM: </span><span class="value-data">${patient.personName.familyName}</span></td>
				<td width="25%"><span class="value-label">PRENOM: </span><span class="value-data">${patient.personName.givenName}</span></td>
				<td width="25%"><span class="value-label"><span class="value-label">TRACnet#: </span><span class="value-data">${formData.tracnetId}</span></td>
				<td width="25%"><span class="value-label">Nom de la FOSA: </span><span class="value-data">${formData.healthCenter}</span></td>
			</tr>
			<tr>
				<td width="25%" colspan="1"><span class="value-label">Date de naissance: </span><span class="value-date"><openmrs:formatDate date="${patient.birthdate}" format="${FormatDate_General}"/></span></td>
				<td width="25%" colspan="1">
					<c:if test="${!empty formData.arvTreatmentGroup}">
							<span class="value-label">Treatment Group:</span> <span class="value-data">${formData.arvTreatmentGroup}</span>
			        </c:if>
			    </td>
				<td width="25%" colspan="1">
					<span class="value-label">Taille: </span><span class="value-data"><openmrs:format obsValue="${formData.obsMapper.latestHeight}"/> cm </span><span class="value-date"><openmrs:formatDate date="${formData.obsMapper.latestHeight.obsDatetime}" format="${FormatDate_General}"/></span>
				</td>
				<td width="25%" align="left" valign="top" rowspan="2">
					<table width="100%">
					<c:forEach var="identifier" items="${patient.activeIdentifiers}" varStatus="status">
							<c:if test="${identifier.identifier != formData.tracnetId && identifier.identifierType != 'HIVEMR-V1'}">
								<tr><td><span> ${identifier.identifierType}:</span></td><td> <span class="value-data">${identifier.identifier}</span></td></tr>
							</c:if>
					</c:forEach>
					</table>
				</td>
			</tr>
			<tr>
				<td width="50%" colspan="2"><span class="value-label">Date d’enrôlement: </span><span class="value-date"><openmrs:formatDate date="${formData.adultHIVProgram.dateEnrolled}" format="${FormatDate_General}"/></span> <span class="value-label"> (jj/mm/aaaa)</span></td>
				<td width="25%" colspan="1"><span class="value-label">Date début ARV:  </span><span class="value-date"><openmrs:formatDate date="${formData.arvStartDate}" format="${FormatDate_General}"/></span> <span class="value-label"> (jj/mm/aaaa)</span></td>
			</tr>
		</table>
	</div>
</div>

<div id="flowsheet-tabs">
	<ul>
			<li><a href="#regimen-section">Regimens</a></li>
			<li><a href="#history">History</a></li>
			<li><a href="#visits">Visits</a></li>
			<li><a href="#orders">Results</a></li>
			<li><a href="#graphs">Graphs</a></li>
	</ul>

<div id="regimen-section">
	<div id="arv-regimen-section"> <!-- TRAITEMENT ANTIRETROVIRAUX POUR TOUTE LA VIE -->
		${formData.arvRegimenHtmlTable}
	</div>
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
				<jsp:useBean id="episode" type="org.openmrs.module.rwandaadulthivflowsheet.impl.pih.TbEpisodeMapping" />
					<tr>
						<td class="section-emr"><c:if test="${!empty episode.episodeDate}">&#x2713;</c:if></td>
						<td>
							<span class="value-label">Commencer: </span><span class="value-date"><openmrs:formatDate date="${episode.episodeDate}" format="${FormatDate_General}"/> </span>
							<span class="value-label">Crachats: </span>
								<%= UIHelper.getCheckBoxWidget(episode.isSmearPositive(), "Positif") %>
								<%= UIHelper.getCheckBoxWidget(episode.isSmearNegative(), "Négatif") %>
							<br/>
							<span class="value-label">Schéma thérapeutique: </span>
								<%= UIHelper.getCheckBoxWidget(episode.isInitialTreatment(), "Premier") %>
								<%= UIHelper.getCheckBoxWidget(episode.isRetreatment(), "Retraitement") %>
								<%= UIHelper.getCheckBoxWidget(episode.isMdrtb(), "MDR-TB") %>
							<br/>
							
							<span class="value-label">Marquer le traitement Anti-TB: </span><span class="value-data"><%= UIHelper.formatRegimenDisplaySummary(episode.getInitialDrugOrder()) %></span></td>
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
							<span class="value-label">Arrêt: </span><span class="value-date">
								<c:choose>
									<c:when test="${!empty episode.continuationPhaseDrugOrder}">
										<openmrs:formatDate date="${episode.continuationPhaseDrugOrder.discontinuedDate}" format="${FormatDate_General}"/>
									</c:when>
									<c:otherwise>
										${DateTextPlaceHolder}
									</c:otherwise>
								</c:choose>
							</span>&nbsp;&nbsp;&nbsp;
							<span class="value-label">ISSUE DE TRAITEMENT: </span>
							<br/>
							<%= UIHelper.getCheckBoxWidget(episode.isCured(), "Guéri ") %> (crachat positif au début, date crachat négatif: <c:if test="${empty episode.firstNegativeSmear}">${DateTextPlaceHolder}</c:if><c:if test="${!empty episode.firstNegativeSmear}"><openmrs:formatDate date="${episode.firstNegativeSmear.obsDatetime}" format="${FormatDate_General}"/></c:if>)
							<%= UIHelper.getCheckBoxWidget(episode.isFailed(), "Echec de traitement") %>
							<br/>
							<%= UIHelper.getCheckBoxWidget(episode.isComplete(), "Traitement complet") %>
							<%= UIHelper.getCheckBoxWidget(episode.isAbandoned(), "Abandonné") %>
							<%= UIHelper.getCheckBoxWidget(episode.isTransfered(), "Transféré") %>
							<%= UIHelper.getCheckBoxWidget(episode.isDead(), "Décès") %>
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
				<jsp:useBean id="prophylaxisepisode" type="org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ProphylaxisMapping" />
					<tr>
						<td class="section-emr"><c:if test="${!empty prophylaxisepisode.startDate}">&#x2713;</c:if><c:if test="${empty prophylaxisepisode.startDate}">&nbsp;</c:if></td>
						<td>
							<span class="value-label">Medicament:</span>
							<%= UIHelper.getCheckBoxWidget(prophylaxisepisode.isCotrimoxisole(), "Cotrimoxazole") %>
							<%= UIHelper.getCheckBoxWidget(prophylaxisepisode.isFluconazole(), "Fluconazole") %>
							<%= UIHelper.getCheckBoxWidget(prophylaxisepisode.isDapsone(), "Dapsone") %>
							<br/>
							<%= UIHelper.getCheckBoxWidget(prophylaxisepisode.isProphylaxisOther(), "Autre: ") %> <span class="value-data"><%= UIHelper.formatRegimenDisplaySummary(prophylaxisepisode.getDrugOrder()) %> </span>
						</td>
						<td>
							<span class="value-label">Date de début</span><br/>
							<span class="value-date"><c:if test="${empty prophylaxisepisode.drugOrder}">${DateTextPlaceHolder}</c:if><c:if test="${!empty prophylaxisepisode.drugOrder}"><openmrs:formatDate date="${prophylaxisepisode.startDate}" format="${FormatDate_General}"/></c:if></span>
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
							<%= UIHelper.getCheckBoxWidget(prophylaxisepisode.isReasonForStoppingOther(), "Autre:") %> <c:if test="${prophylaxisepisode.reasonForStoppingOther}"><span class="value-data">${prophylaxisepisode.discontinueReasonOther}</span></c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div id="chronicRegimen"> <!-- AUTRES MEDICAMENTS PRIS CHRONIQUEMENT > 1 MOIS -->
		<table class="section-table">
			<thead>
				<tr>
					<th colspan="6">AUTRES MEDICAMENTS PRIS CHRONIQUEMENT &gt; 1 MOIS</th>
				</tr>
				<tr>
					<th scope="col" id="col-chronic-emr-1" class="section-emr">EMR</th>
					<th scope="col" id="col-chronic-info">Médicament</th>
					<th scope="col" id="col-chronic-start-date">Date de début</th>					
					<th scope="col" id="col-chronic-emr-2" class="section-emr">EMR</th>
					<th scope="col" id="col-chronic-stop-date">Date d’arrêt</th>
					<th scope="col" id="col-chronic-stop-comment">Commentaire</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="otherMedication" items="${formData.otherMedications}">
				<jsp:useBean id="otherMedication" type="org.openmrs.DrugOrder" />
					<tr>
						<td class="section-emr"><c:if test="${!empty otherMedication.startDate}">&#x2713;</c:if><c:if test="${empty otherMedication.startDate}">&nbsp;</c:if></td>
						<td><span class="value-data"><%= UIHelper.formatRegimenDisplaySummary(otherMedication) %></span></td>
						<td>
							<span class="value-date"><c:if test="${!empty otherMedication.startDate}"><openmrs:formatDate date="${otherMedication.startDate}" format="${FormatDate_General}"/></c:if><c:if test="${empty otherMedication.startDate}">${DateTextPlaceHolder}</c:if></span>
						</td>
						<td class="section-emr"><c:if test="${!empty otherMedication.discontinuedDate || !empty otherMedication.autoExpireDate}">&#x2713;</c:if><c:if test="${empty otherMedication.discontinuedDate && empty otherMedication.autoExpireDate}">&nbsp;</c:if></td>
						<td>
							<c:if test="${empty otherMedication.discontinuedDate && empty otherMedication.autoExpireDate}">
								<span class="value-date">${DateTextPlaceHolder}</span>
							</c:if>
							<c:if test="${!empty otherMedication.discontinuedDate && empty otherMedication.autoExpireDate}">
								<span class="value-date"><openmrs:formatDate date="${otherMedication.discontinuedDate}" format="${FormatDate_General}"/></span>
							</c:if>
							<c:if test="${empty otherMedication.discontinuedDate && !empty otherMedication.autoExpireDate}">
								<span class="value-date"><openmrs:formatDate date="${otherMedication.autoExpireDate}" format="${FormatDate_General}"/></span>
							</c:if>
						</td>
						<td><span class="value-data">${otherMedication.discontinuedReason.name}</span></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<div id="history" style="page-break-before: always">
<div id="allergies"> <!-- ALLERGIES ET EFFETS SECONDAIRES -->
		<table class="section-table">
			<thead>
				<tr>
					<th colspan="5">ALLERGIES ET EFFETS SECONDAIRES</th>
				</tr>
				<tr>
					<th scope="col" id="col-allergies-emr-1" class="section-emr">EMR</th>
					<th scope="col" id="col-allergies-view" class="view-row">View</th>
					<th scope="col" id="col-allergies-date">Date</th>
					<th scope="col" id="col-allergies-med">Médicament soupconé</th>					
					<th scope="col" id="col-allergies-effect">Effet</th>
					<th scope="col" id="col-allergies-measures">Mesures prises</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="allergy" items="${formData.obsMapper.allergies}">
					<c:if test="${!allergy.doNotShow}">
					<tr <c:if test="${not allergy.emr}">class="empty-row"</c:if>>
						<td class="section-emr"><c:if test="${allergy.emr}">&#x2713;</c:if></td>
						<td class="view-row" align="center">
							<c:if test="${allergy.encounter != null}">
							<c:set var="viewEncounterUrl" value="${pageContext.request.contextPath}/module/htmlformentry/htmlFormEntry.form?encounterId=${allergy.encounter.encounterId}&formId=${Form_NewAllergy}&mode=EDIT"/>
							<a href="javascript:void(0)" onClick="loadUrlIntoEncounterPopup('<openmrs:format encounter="${allergy.encounter}"/>', '${viewEncounterUrl}', true); return false;">
								<img src="${pageContext.request.contextPath}/images/file.gif" title="<spring:message code="general.view"/>" border="0" align="top" />
							</a>
							</c:if>
						</td>
						<td>
							<span class="value-date"><c:choose><c:when test="${allergy.emr}"><openmrs:formatDate date="${allergy.adverseEffectDate}" format="${FormatDate_General}"/></c:when><c:otherwise>${DateTextPlaceHolder}</c:otherwise></c:choose></span>
						</td>
						<td>
							<span class="value-data">${allergy.medicationsString}</span>
						</td>
						<td>
							<jsp:useBean id="allergy" type="org.openmrs.module.rwandaadulthivflowsheet.mapper.Allergy" />
							<%= UIHelper.getCheckBoxWidget(allergy.isEffectAnaphylaxis(), "Anaphylaxie") %>
							<%= UIHelper.getCheckBoxWidget(allergy.isEffectRashMinor(), "Eruption cutanée légère") %>
							<%= UIHelper.getCheckBoxWidget(allergy.isEffectRashModerate(), "Eruption cutanée modérée") %>
							<%= UIHelper.getCheckBoxWidget(allergy.isEffectRashSevere(), "Eruption cutanée sévère (desquamant)") %>
							<br/>
							<%= UIHelper.getCheckBoxWidget(allergy.isEffectNausea(), "Nausées") %>
							<%= UIHelper.getCheckBoxWidget(allergy.isEffectVomiting(), "Vomissements") %>
							<%= UIHelper.getCheckBoxWidget(allergy.isEffectJaundice(), "Ictère") %>
							<%= UIHelper.getCheckBoxWidget(allergy.isEffectPeripheralNeuropathy(), "Neuropathie") %> 
							<%= UIHelper.getCheckBoxWidget(allergy.isEffectAnemia(), "Anémie") %>
							<%= UIHelper.getCheckBoxWidget(allergy.isEffectLacticAcidosis(), "Acidose Lactique") %>
							<br/>
							<%= UIHelper.getCheckBoxWidget(allergy.isEffectHepatitis(), "Hépatite") %>
							<%= UIHelper.getCheckBoxWidget(allergy.isEffectNightmares(), "Cauchemars") %>
							<%= UIHelper.getCheckBoxWidget(allergy.isEffectLipodystrophy(), "Lipodystrophie") %> 
							<%= UIHelper.getCheckBoxWidget(allergy.isEffectOther(), "Autres (a specifier):") %><c:if test="${allergy.effectOther}"><span class="value-data">${allergy.effectOtherString}</span></c:if>
						</td>
						<td>
							<div><%= UIHelper.getCheckBoxWidget(allergy.isActionTakenTreatmentStop(), "Arret du medicament") %></div>
							<div><%= UIHelper.getCheckBoxWidget(allergy.isActionTakenChangeOfDose(), "Changement de dose") %></div>
							<div><%= UIHelper.getCheckBoxWidget(allergy.isActionTakenSymptomatic(), "Traitement symptomatique") %></div>
							<div><%= UIHelper.getCheckBoxWidget(allergy.isActionTakenOther(), "Autre: ") %><c:if test="${allergy.actionTakenOther}"><span class="value-data">${allergy.actionTakenOtherString}</span></c:if></div>
						</td>
					</tr>
					</c:if>
				</c:forEach>
				<tr class="new-item-button-row">
					<td colspan="5">
						<center><button onClick="showEntryPopup('New Allergy', ${Form_NewAllergy}); return false;">Nouveau</button></center>
					</td>
				</tr>
			</tbody>
		</table>
</div>
<div id="hospitalizations"> <!-- HOSPITALISATIONS -->
		<table class="section-table">
			<thead>
				<tr>
					<th colspan="5">HOSPITALISATIONS:</th>
				</tr>
				<tr>
					<th scope="col" id="col-hospital-emr-1" class="section-emr">EMR</th>
					<th scope="col" id="col-hospital-view" class="view-row">View</th>
					<th scope="col" id="col-hospital-diagnostic">Diagnostic</th>
					<th scope="col" id="col-hospital-date">Date:</th>					
					<th scope="col" id="col-hospital-duration">Durée</th>
					<th scope="col" id="col-hospital-comment">Commentaire</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="hospitalization" items="${formData.obsMapper.hospitalizations}">
					<tr <c:if test="${not hospitalization.emr}">class="empty-row"</c:if>>
						<td class="section-emr"><c:if test="${hospitalization.emr}">&#x2713;</c:if></td>
						<td class="view-row" align="center">
							<c:if test="${hospitalization.encounter != null}">
							<c:set var="viewEncounterUrl" value="${pageContext.request.contextPath}/module/htmlformentry/htmlFormEntry.form?encounterId=${hospitalization.encounter.encounterId}&formId=${Form_NewHospitalisations}&mode=EDIT"/>
							<a href="javascript:void(0)" onClick="loadUrlIntoEncounterPopup('<openmrs:format encounter="${hospitalization.encounter}"/>', '${viewEncounterUrl}', true); return false;">
								<img src="${pageContext.request.contextPath}/images/file.gif" title="<spring:message code="general.view"/>" border="0" align="top" />
							</a>
							</c:if>
						</td>
						<td>
							<jsp:useBean id="hospitalization" type="org.openmrs.module.rwandaadulthivflowsheet.mapper.Hospitalization" />
							<%= UIHelper.getCheckBoxWidget(hospitalization.isMalaria(), "Paludisme") %>
							<%= UIHelper.getCheckBoxWidget(hospitalization.isPneumonia(), "Pneumonie") %>
							<%= UIHelper.getCheckBoxWidget(hospitalization.isGastroenteritis(), "Gastroentérite") %>
							<%= UIHelper.getCheckBoxWidget(hospitalization.isMalnutrition(), "Anémie") %>
							<%= UIHelper.getCheckBoxWidget(hospitalization.isMeningitis(), "Méningite") %>
							<br/>
							<%= UIHelper.getCheckBoxWidget(hospitalization.isOther(), "Autre:") %><c:if test="${hospitalization.other}"><span class="value-data">${hospitalization.diagnosisOtherString}</span></c:if>
						</td>
						<td>
							<span class="value-date"><c:choose><c:when test="${hospitalization.emr}"><openmrs:formatDate date="${hospitalization.admittanceDate}" format="${FormatDate_General}"/></c:when><c:otherwise>${DateTextPlaceHolder}</c:otherwise></c:choose></span>
						</td>
						<td><u>${hospitalization.durationDays}</u>jours <u>${hospitalization.durationWeeks}</u>semaines <u>${hospitalization.durationMonths}</u>mois</td>
						<td>
							<span class="value-data">${hospitalization.comments}</span>
						</td>
					</tr>
				</c:forEach>
				<tr class="new-item-button-row">
					<td colspan="6">
						<center><button onClick="showEntryPopup('New Hospitalization', ${Form_NewHospitalisations}); return false;">Nouveau</button></center>
					</td>
				</tr>
			</tbody>
		</table>
</div>
<div id="io"> <!-- INFECTIONS OPPORTUNISTES -->
		<table class="section-table">
			<thead>
				<tr>
					<th colspan="5">INFECTIONS OPPORTUNISTES</th>
				</tr>
				<tr>
					<th scope="col" id="col-io-emr-1" class="section-emr">EMR</th>
					<th scope="col" id="col-io-view" class="view-row">View</th>
					<th scope="col" id="col-io-info">Diagnostic:</th>
					<th scope="col" id="col-io-date">Date:</th>
					<th scope="col" id="col-io-stage">Stade OMS:</th>
					<th scope="col" id="col-io-comment">Commentaire</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="oi" items="${formData.obsMapper.ois}">
					<c:if test="${!oi.doNotShow}">
						<tr <c:if test="${not oi.emr}">class="empty-row"</c:if>>
							<td class="section-emr"><c:if test="${oi.emr}">&#x2713;</c:if></td>
							<td class="view-row" align="center">
								<c:if test="${oi.encounter != null}">
								<c:set var="viewEncounterUrl" value="${pageContext.request.contextPath}/module/htmlformentry/htmlFormEntry.form?encounterId=${oi.encounter.encounterId}&formId=${Form_NewOI}&mode=EDIT"/>
								<a href="javascript:void(0)" onClick="loadUrlIntoEncounterPopup('<openmrs:format encounter="${oi.encounter}"/>', '${viewEncounterUrl}', true); return false;">
									<img src="${pageContext.request.contextPath}/images/file.gif" title="<spring:message code="general.view"/>" border="0" align="top" />
								</a>
								</c:if>
							</td>
							<td>
								<span class="value-data"><openmrs:format obsValue="${oi.diagnosis}"/></span>
							</td>
							<td>
								<span class="value-date"><c:choose><c:when test="${oi.emr}"><openmrs:formatDate date="${oi.opportunisticInfectionDate}" format="${FormatDate_General}"/></c:when><c:otherwise>${DateTextPlaceHolder}</c:otherwise></c:choose></span>
							</td>
							<td><openmrs:format concept="${oi.stage}"/></td>
							<td>
								<span class="value-data"><c:if test="${oi.emr}">${oi.comments}</c:if></span>
							</td>
						</tr>
					</c:if>	
				</c:forEach>
				<tr class="new-item-button-row">
					<td colspan="5">
						<center><button onClick="showEntryPopup('New OI', ${Form_NewOI}); return false;">Nouveau</button></center>
					</td>
				</tr>
			</tbody>
		</table>
</div>
<div id="Misc"> <!-- Misc -->
		<table class="section-table">
			<thead>
				<tr class="new-item-button-row">
					<th colspan="5">CHRONIQUE</th>
				</tr>
				<tr>
					<th scope="col" id="col-misc-emr-1" class="section-emr">EMR</th>
					<th scope="col" id="col-misc-view" class="view-row">View</th>
					<th scope="col" id="col-misc-info">Diagnostic:</th>
					<th scope="col" id="col-misc-date">Date:</th>
					<th scope="col" id="col-misc-comment">Commentaire</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="problem" items="${formData.obsMapper.problems}">
					<c:if test="${!problem.doNotShow}">
					<tr <c:if test="${not problem.emr}">class="empty-row"</c:if>>
						<td class="section-emr"><c:if test="${problem.emr}">&#x2713;</c:if></td>
						<td class="view-row" align="center">
							<c:if test="${problem.encounter != null}">
							<c:set var="viewEncounterUrl" value="${pageContext.request.contextPath}/module/htmlformentry/htmlFormEntry.form?encounterId=${problem.encounter.encounterId}&formId=${Form_NewProblem}&mode=EDIT"/>
							<a href="javascript:void(0)" onClick="loadUrlIntoEncounterPopup('<openmrs:format encounter="${problem.encounter}"/>', '${viewEncounterUrl}', true); return false;">
								<img src="${pageContext.request.contextPath}/images/file.gif" title="<spring:message code="general.view"/>" border="0" align="top" />
							</a>
							</c:if>
						</td>
						<td>
							<jsp:useBean id="problem" type="org.openmrs.module.rwandaadulthivflowsheet.mapper.Problem" />
							<%= UIHelper.getCheckBoxWidget(problem.isAsthma(), "Asthme") %>
							<%= UIHelper.getCheckBoxWidget(problem.isDiabetes(), "Diabète") %>
							<%= UIHelper.getCheckBoxWidget(problem.isEpilepsy(), "Epilepsie") %>
							<%= UIHelper.getCheckBoxWidget(problem.isHeartFailure(), "Cardiopathie") %>
							<%= UIHelper.getCheckBoxWidget(problem.isRenalFailure(), "Insuffisance rénale") %>
							<%= UIHelper.getCheckBoxWidget(problem.isOther(), "Autre:") %>
							<span class="value-data"><c:if test="${problem.showOther}"><openmrs:format obsValue="${problem.diagnosis}"/></c:if></span>
						</td>
						<td>
							<span class="value-date"><c:choose><c:when test="${problem.emr}"><openmrs:formatDate date="${problem.obsDate}" format="${FormatDate_General}"/></c:when><c:otherwise>${DateTextPlaceHolder}</c:otherwise></c:choose></span>
						</td>
						<td>
							<span class="value-data"><c:if test="${problem.emr}">${problem.comments}</c:if></span>
						</td>
					</tr>
					</c:if>
				</c:forEach>
				<tr class="new-item-button-row">
					<td colspan="5">
						<center><button onClick="showEntryPopup('New Problem', ${Form_NewProblem}); return false;">Nouveau</button></center>
					</td>
				</tr>
			</tbody>
		</table>
</div>
</div>
<div style="page-break-before: always" id="visits"> <!-- RESUME DES VISITES -->
		<span class="form-header">RESUME DES VISITES</span>
		<table class="section-table">
			<thead>
				<tr>
					<th class="section-emr">EMR</th>
					<th scope="col" class="view-row">View</th>
					<th>Date de visite</th>
					<th>Poids</th>
					<th>Etat du patient</th>
					<th>IO Actuelle?</th>
					<th>IST Actuelle?</th>
					<th>TB Screening</th>
					<th>Contraception</th>
					<th>Enceinte</th>
					<th colspan="2">Adherence ARVs</th>
					<th>Explication pour les doses manqués</th>
				</tr>
				<tr>
					<th scope="col" id="col-visits-1" class="section-emr"></th>
					<th scope="col" id="col-visits-view">&nbsp;</th>
					<th scope="col" id="col-visits-2">jj/mm/aaaa</th>
					<th scope="col" id="col-visits-3">(kg)</th>
					<th scope="col" id="col-visits-4">A=activités normaux; B=effectue des activités avec aide; C=alités</th>
					<th scope="col" id="col-visits-5">(si une nouvelle infection entrer dans le tableau au-dessus)</th>
					<th scope="col" id="col-visits-6">(si oui voir codes en-dessous)</th>
					<th scope="col" id="col-visits-7"></th>
					<th scope="col" id="col-visits-8">A=Abstinence; C=condoms; PF=Planification familiale; R=rien</th>
					<th scope="col" id="col-visits-9">Oui/Non</th>
					<th scope="col" id="col-visits-10"># de doses manqués/30jrs</th>
					<th scope="col" id="col-visits-11">&lt;3 = &gt;95% = Tres bon<br>4-8= 85-94% = bon<br>&gt;9= &lt;85% = Faible</th>
					<th scope="col" id="col-visits-12">(codes en-dessous)</th>
				</tr>
			</thead>
			<tfoot>
				<tr>
					<td colspan="13">
						CODES IST: EU=Ecoulement urétral; EV = Ecoulement Vaginal; UG=Ulcérations génitales ; DPF = Douleurs pelviennes chez la femme ; BI= Bubon Inguinal ; GDS= Gonflement douloureux du scrotum ; VV= Végétations Vénériennes ; CNN= Conjonctivite purulente du nouveau né.
						<br/>
						CODES pour dose explication de doses manquées : 1. Toxicité-Effets secondaires ; 2. Grossesse ; 3. Faible adhésion ; 4.Maladie/hospitalisation ; 5. Rupture de stock ; 6.Autres
					</td>
				</tr>
			</tfoot>
			<tbody>
				<c:forEach var="visitRow" items="${formData.obsMapper.visits}">
					<jsp:useBean id="visitRow" type="org.openmrs.module.rwandaadulthivflowsheet.mapper.VisitGroup" />
					<tr <c:if test="${not visitRow.emr}">class="empty-row"</c:if>>
						<td class="section-emr"><c:if test="${visitRow.emr}">&#x2713;</c:if></td>
						<td class="view-row" align="center">
							<c:if test="${visitRow.encounter != null}">
							<c:set var="viewEncounterUrl" value="${pageContext.request.contextPath}/module/htmlformentry/htmlFormEntry.form?encounterId=${visitRow.encounter.encounterId}&formId=${Form_NewVisit}&mode=EDIT"/>
							<a href="javascript:void(0)" onClick="loadUrlIntoEncounterPopup('<openmrs:format encounter="${visitRow.encounter}"/>', '${viewEncounterUrl}', true); return false;">
								<img src="${pageContext.request.contextPath}/images/file.gif" title="<spring:message code="general.view"/>" border="0" align="top" />
							</a>
							</c:if>
						</td>
						<td><span class="value-date"><c:choose><c:when test="${visitRow.emr}"><openmrs:formatDate date="${visitRow.date}" format="${FormatDate_General}"/></c:when><c:otherwise>${DateTextPlaceHolder}</c:otherwise></c:choose></span></td>
						<td><span class="value-data"><openmrs:format obsValue="${visitRow.weight}"/></span></td>
						<td><span class="value-data">${visitRow.functionalAbilityOfThePatientString}</span></td>
						<td>
							<%= UIHelper.getCheckBoxWidget(visitRow.isOpportunisticInfectionFalse(), "Non") %> 
							<%= UIHelper.getCheckBoxWidget(visitRow.isOpportunisticInfectionTrue(), "Oui: ") %>
							<c:if test="${!empty visitRow.opportunisticInfectionForDisplay}">
								<br/><span class="value-data">&nbsp;<openmrs:format obsValue="${visitRow.opportunisticInfectionForDisplay}"/></span>
							</c:if>
						</td>
						<td>
							<%= UIHelper.getCheckBoxWidget(visitRow.isStiFalse(), "Non") %> 
							<%= UIHelper.getCheckBoxWidget(visitRow.isStiTrue(), "Oui: ") %>
						</td>
						<td>
							<%= UIHelper.getCheckBoxWidget(visitRow.isTBScreeningPositive(), "Pos") %> 
							<%= UIHelper.getCheckBoxWidget(visitRow.isTBScreeningNegative(), "Neg") %>
						</td>
						<td><span class="value-data"> ${visitRow.familyPlanningString} </span></td>
						<td>
							<%= UIHelper.getCheckBoxWidget(visitRow.isPregnancyStatusNo(), "Non") %> 
							<%= UIHelper.getCheckBoxWidget(visitRow.isPregnancyStatusYes(), "Oui") %>
						</td>
						<td><span class="value-data"><openmrs:format obsValue="${visitRow.numberOfMissedDosesInThePastMonth}"/></span></td>
						<td><span class="value-data">${visitRow.numberOfMissedDosesInThePastMonthString}</span></td>
						<td><span class="value-data"><openmrs:format obsValue="${visitRow.reasonForPoorAdherenceToAntiRetoroviralTherapy}"/></span></td>
					</tr>
				</c:forEach>
				<tr class="new-item-button-row">
					<td colspan="13">
						<center><button onClick="showEntryPopup('New Visit', ${Form_NewVisit}); return false;">Nouveau</button></center>
					</td>
				</tr>
			</tbody>
		</table>
</div>
<div style="page-break-before: always" id="orders">
<div id="labs"> <!-- SUIVI BIOLOGIQUE -->
		<table class="section-table">
			<thead>
				<tr>
					<th colspan="17">SUIVI BIOLOGIQUE</th>
				</tr>
				<tr>
					<th scope="col" id="col-labs-1" class="section-emr">EMR</th>
					<th scope="col" id="col-labs-view" class="view-row">View</th>
					<th scope="col" id="col-labs-2">Date d’examen</th>
					<th scope="col" id="col-labs-3">Hb<br/>(g/dl)</th>
					<th scope="col" id="col-labs-4">Ht<br/>(%)</th>
					<th scope="col" id="col-labs-5">GB<br/>(x10<sup>9</sup>/l)</th>
					<th scope="col" id="col-labs-6">Neutro<br/>(x10<sup>9</sup>/l)</th>
					<th scope="col" id="col-labs-7">Lympho<br/>(x10<sup>9</sup>/l)</th>
					<th scope="col" id="col-labs-8">Plt<br/>(x10<sup>9</sup>/l)</th>
					<th scope="col" id="col-labs-9">SGOT<br/>(iu/l)</th>
					<th scope="col" id="col-labs-10">SGPT<br/>(iu/l)</th>
					<th scope="col" id="col-labs-11">Creat.<br/>(µmol./l)</th>
					<th scope="col" id="col-labs-12">Glucose<br/>(mg/dl)</th>
					<th scope="col" id="col-labs-13">RPR(annual)</th>
					<th scope="col" id="col-labs-14">Autre<br/>examen</th>
					<th scope="col" id="col-labs-15">CD4 (cells/mm<sup>3</sup>)</th>
					<th scope="col" id="col-labs-16">Charge Virale<br/>(copies/ml)</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="labRow" items="${formData.obsMapper.labsTable}">
					<tr <c:if test="${not labRow.emr}">class="empty-row"</c:if>>
						<td class="section-emr"><c:if test="${labRow.emr}">&#x2713;</c:if></td>
						<td class="view-row" align="center">
							<c:if test="${labRow.encounter != null}">
							<c:set var="viewEncounterUrl" value="${pageContext.request.contextPath}/module/htmlformentry/htmlFormEntry.form?encounterId=${labRow.encounter.encounterId}&formId=${Form_NewLab}&mode=EDIT"/>
							<a href="javascript:void(0)" onClick="loadUrlIntoEncounterPopup('<openmrs:format encounter="${labRow.encounter}"/>', '${viewEncounterUrl}', true); return false;">
								<img src="${pageContext.request.contextPath}/images/file.gif" title="<spring:message code="general.view"/>" border="0" align="top" />
							</a>
							</c:if>
						</td>
						<td><span class="value-date"><c:choose><c:when test="${labRow.emr}"><openmrs:formatDate date="${labRow.date}" format="${FormatDate_General}"/></c:when><c:otherwise>${DateTextPlaceHolder}</c:otherwise></c:choose></span></td>
						<td><span class="value-data"><openmrs:format obsValue="${labRow.hb}"/></span></td>
						<td><span class="value-data"><openmrs:format obsValue="${labRow.ht}"/></span></td>
						<td><span class="value-data"><openmrs:format obsValue="${labRow.gb}"/></span></td>
						<td><span class="value-data"><openmrs:format obsValue="${labRow.neutro}"/></span></td>
						<td><span class="value-data"><openmrs:format obsValue="${labRow.lympho}"/></span></td>
						<td><span class="value-data"><openmrs:format obsValue="${labRow.plt}"/></span></td>
						<td><span class="value-data"><openmrs:format obsValue="${labRow.sgot}"/></span></td>
						<td><span class="value-data"><openmrs:format obsValue="${labRow.sgpt}"/></span></td>
						<td><span class="value-data"><openmrs:format obsValue="${labRow.creat}"/></span></td>
						<td><span class="value-data"><openmrs:format obsValue="${labRow.glucose}"/></span></td>
						<td>
							<jsp:useBean id="labRow" type="org.openmrs.module.rwandaadulthivflowsheet.mapper.LabGroup" />
							<%= UIHelper.getCheckBoxWidget(labRow.isRprNegative(), "Nég") %>
							<%= UIHelper.getCheckBoxWidget(labRow.isRprPositive(), "Pos") %>
							<%= UIHelper.getCheckBoxWidget(labRow.isRprNotMade(), "Pas fait") %>
						</td>
						<td><span class="value-data">${labRow.otherTests}</span></td>
						<td><span class="value-data"><openmrs:format obsValue="${labRow.cd4}"/></span></td>
						<td><span class="value-data"><c:choose><c:when test="${labRow.viralLoad != null && labRow.viralLoad.valueNumeric == 39.9}">&lt;40</c:when><c:otherwise><openmrs:format obsValue="${labRow.viralLoad}"/></c:otherwise></c:choose></span></td>
					</tr>
				</c:forEach>
				<tr class="new-item-button-row">
					<td colspan="17">
						<center><button onClick="showEntryPopup('New Lab', ${Form_NewLab}); return false;">Nouveau</button></center>
					</td>
				</tr>
			</tbody>
			
		</table>
</div>
<div id="images"> <!-- SUIVI IMAGERIE -->
		<table class="section-table">
			<thead>
				<tr>
					<th colspan="5">SUIVI IMAGERIE</th>
				</tr>
				<tr>
					<th scope="col" id="col-images-emr-1" class="section-emr">EMR</th>
					<th scope="col" id="col-images-view" class="view-row">View</th>
					<th scope="col" id="col-images-type">Type d’image</th>
					<th scope="col" id="col-images-date">Date</th>
					<th scope="col" id="col-images-outcome">Conclusion clinique</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="image" items="${formData.obsMapper.images}">
					<tr <c:if test="${not image.emr}">class="empty-row"</c:if>>
						<td class="section-emr"><c:if test="${image.emr}">&#x2713;</c:if></td>
						<td class="view-row" align="center">
							<c:if test="${image.encounter != null}">
							<c:set var="viewEncounterUrl" value="${pageContext.request.contextPath}/module/htmlformentry/htmlFormEntry.form?encounterId=${image.encounter.encounterId}&formId=${Form_NewImage}&mode=EDIT"/>
							<a href="javascript:void(0)" onClick="loadUrlIntoEncounterPopup('<openmrs:format encounter="${image.encounter}"/>', '${viewEncounterUrl}', true); return false;">
								<img src="${pageContext.request.contextPath}/images/file.gif" title="<spring:message code="general.view"/>" border="0" align="top" />
							</a>
							</c:if>
						</td>
						<td>
							<jsp:useBean id="image" type="org.openmrs.module.rwandaadulthivflowsheet.mapper.Image" />
							<%= UIHelper.getCheckBoxWidget(image.isChestXRay(), "Cliche Thorax") %> 
							<%= UIHelper.getCheckBoxWidget(image.isCTHead(), "CT head") %> 
							<%= UIHelper.getCheckBoxWidget(image.isOther(), "Autre (spécifier):") %>
							<c:if test="${image.emr}"><span class="value-data">${image.imageTypeString}</span></c:if>
						</td>
						<td>
							<span class="value-date"><c:choose><c:when test="${image.emr}"><openmrs:formatDate date="${image.obsDate}" format="${FormatDate_General}"/></c:when><c:otherwise>${DateTextPlaceHolder}</c:otherwise></c:choose></span>
						</td>
						<td>
							<span class="value-data">
								<c:if test="${image.comments != null}">
									<c:forEach var="comment" items="${image.comments}" varStatus="status">
									    <c:if test="${status.index > 0}"> | </c:if>
									 	<openmrs:format obsValue="${comment}"/> 
									</c:forEach>
								</c:if>
							</span>
						</td>
					</tr>
				</c:forEach>
				<tr class="new-item-button-row">
					<td colspan="5">
						<center><button onClick="showEntryPopup('New Image', ${Form_NewImage}); return false;">Nouveau</button></center>
					</td>
				</tr>
			</tbody>
		</table>
</div>
</div>
<div id="graphs">
	<div style="page-break-before: always" id="cd4Graph"> <!-- COURBE D'EVOLUTION DES CD4 -->
		<table class="section-graph">
			<thead>
				<tr>
					<th align="left">CD4 (cells/mm3)</th>				
					<th align="left">COURBE D'EVOLUTION DES CD4</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="2" align="center">
						<div id="obsGraph${ConceptId_cd4count}" class="cd4Graph"></div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div style="height: 10px"></div>
	<div style="page-break-before: always" id="bmiGraph"> <!-- Courbe d’évolution du BMI -->
 		<table border="0" cellspacing="0">
 			<tr>
 				<td>
			<table border="1" cellspacing="0" class="bmiLegend">
				<tr>
					<th>
						BMI<br/>
						(kg/m<sup style="font-size: 6px">2</sup>)
					</th>
					<th colspan="2">
						= <u style="font-size: 6px">poids en kilogrammes</u><br/>
						&nbsp;&nbsp;&nbsp;Taille en metres<sup style="font-size: 6px">2</sup>
					</th>
				</tr>
				<tr>
					<th>
					</th>
					<th colspan="2">
						BMI
					</th>
				</tr>
				<tr>
					<td></td><td>16</td><td>18.5</td>
				</tr>
				<tr>
					<td>Taille (cm)</td><td colspan="2">Poids en Kg</td>
				</tr>
				<TR>
					<TD>140</TD>
					<TD>31</TD>
					<TD>36</TD>
				</TR>
				<TR>
					<TD>141</TD>
					<TD>32</TD>
					<TD>37</TD>
				</TR>
				<TR>
					<TD>142</TD>
					<TD>32</TD>
					<TD>37</TD>
				</TR>
				<TR>
					<TD>143</TD>
					<TD>33</TD>
					<TD>38</TD>
				</TR>
				<TR>
					<TD>144</TD>
					<TD>33</TD>
					<TD>38</TD>
				</TR>
				<TR>
					<TD>145</TD>
					<TD>34</TD>
					<TD>39</TD>
				</TR>
				<TR>
					<TD>146</TD>
					<TD>34</TD>
					<TD>39</TD>
				</TR>
				<TR>
					<TD>147</TD>
					<TD>35</TD>
					<TD>40</TD>
				</TR>
				<TR>
					<TD>148</TD>
					<TD>35</TD>
					<TD>41</TD>
				</TR>
				<TR>
					<TD>149</TD>
					<TD>36</TD>
					<TD>41</TD>
				</TR>
				<TR>
					<TD>150</TD>
					<TD>36</TD>
					<TD>42</TD>
				</TR>
				<TR>
					<TD>151</TD>
					<TD>36</TD>
					<TD>42</TD>
				</TR>
				<TR>
					<TD>152</TD>
					<TD>37</TD>
					<TD>43</TD>
				</TR>
				<TR>
					<TD>153</TD>
					<TD>37</TD>
					<TD>43</TD>
				</TR>
				<TR>
					<TD>154</TD>
					<TD>38</TD>
					<TD>44</TD>
				</TR>
				<TR>
					<TD>155</TD>
					<TD>38</TD>
					<TD>44</TD>
				</TR>
				<TR>
					<TD>156</TD>
					<TD>39</TD>
					<TD>45</TD>
				</TR>
				<TR>
					<TD>157</TD>
					<TD>39</TD>
					<TD>46</TD>
				</TR>
				<TR>
					<TD>158</TD>
					<TD>40</TD>
					<TD>46</TD>
				</TR>
				<TR>
					<TD>159</TD>
					<TD>40</TD>
					<TD>47</TD>
				</TR>
				<TR>
					<TD>160</TD>
					<TD>41</TD>
					<TD>47</TD>
				</TR>
				<TR>
					<TD>161</TD>
					<TD>41</TD>
					<TD>48</TD>
				</TR>
				<TR>
					<TD>162</TD>
					<TD>42</TD>
					<TD>49</TD>
				</TR>
				<TR>
					<TD>163</TD>
					<TD>43</TD>
					<TD>49</TD>
				</TR>
				<TR>
					<TD>164</TD>
					<TD>43</TD>
					<TD>50</TD>
				</TR>
				<TR>
					<TD>165</TD>
					<TD>44</TD>
					<TD>50</TD>
				</TR>
				<TR>
					<TD>166</TD>
					<TD>44</TD>
					<TD>51</TD>
				</TR>
				<TR>
					<TD>167</TD>
					<TD>45</TD>
					<TD>52</TD>
				</TR>
				<TR>
					<TD>168</TD>
					<TD>45</TD>
					<TD>52</TD>
				</TR>
				<TR>
					<TD>169</TD>
					<TD>46</TD>
					<TD>53</TD>
				</TR>
				<TR>
					<TD>170</TD>
					<TD>46</TD>
					<TD>53</TD>
				</TR>
				<TR>
					<TD>171</TD>
					<TD>47</TD>
					<TD>54</TD>
				</TR>
				<TR>
					<TD>172</TD>
					<TD>47</TD>
					<TD>55</TD>
				</TR>
				<TR>
					<TD>173</TD>
					<TD>48</TD>
					<TD>55</TD>
				</TR>
				<TR>
					<TD>174</TD>
					<TD>48</TD>
					<TD>56</TD>
				</TR>
				<TR>
					<TD>175</TD>
					<TD>49</TD>
					<TD>57</TD>
				</TR>
				<TR>
					<TD>176</TD>
					<TD>50</TD>
					<TD>57</TD>
				</TR>
				<TR>
					<TD>177</TD>
					<TD>50</TD>
					<TD>58</TD>
				</TR>
				<TR>
					<TD>178</TD>
					<TD>51</TD>
					<TD>59</TD>
				</TR>
				<TR>
					<TD>179</TD>
					<TD>51</TD>
					<TD>59</TD>
				</TR>
				<TR>
					<TD>180</TD>
					<TD>52</TD>
					<TD>60</TD>
				</TR>
				<TR>
					<TD>181</TD>
					<TD>52</TD>
					<TD>61</TD>
				</TR>
				<TR>
					<TD>182</TD>
					<TD>53</TD>
					<TD>61</TD>
				</TR>
				<TR>
					<TD>183</TD>
					<TD>54</TD>
					<TD>62</TD>
				</TR>
				<TR>
					<TD>184</TD>
					<TD>54</TD>
					<TD>63</TD>
				</TR>
				<TR>
					<TD>185</TD>
					<TD>55</TD>
					<TD>63</TD>
				</TR>
				<TR>
					<TD>186</TD>
					<TD>55</TD>
					<TD>64</TD>
				</TR>
				<TR>
					<TD>187</TD>
					<TD>56</TD>
					<TD>65</TD>
				</TR>
				<TR>
					<TD>188</TD>
					<TD>57</TD>
					<TD>65</TD>
				</TR>
				<TR>
					<TD>189</TD>
					<TD>57</TD>
					<TD>66</TD>
				</TR>
				<TR>
					<TD>190</TD>
					<TD>58</TD>
					<TD>67</TD>
				</TR>
				<TR>
					<TD>191</TD>
					<TD>58</TD>
					<TD>67</TD>
				</TR>
				<TR>
					<TD>192</TD>
					<TD>59</TD>
					<TD>68</TD>
				</TR>
				<TR>
					<TD>193</TD>
					<TD>60</TD>
					<TD>69</TD>
				</TR>
				<TR>
					<TD>194</TD>
					<TD>60</TD>
					<TD>70</TD>
				</TR>
				<TR>
					<TD>195</TD>
					<TD>61</TD>
					<TD>70</TD>
				</TR>
				<TR>
					<TD>196</TD>
					<TD>61</TD>
					<TD>71</TD>
				</TR>
				<TR>
					<TD>197</TD>
					<TD>62</TD>
					<TD>72</TD>
				</TR>
				<TR>
					<TD>198</TD>
					<TD>63</TD>
					<TD>73</TD>
				</TR>
				<TR>
					<TD>199</TD>
					<TD>63</TD>
					<TD>73</TD>
				</TR>
				<TR>
					<TD>200</TD>
					<TD>64</TD>
					<TD>74</TD>
				</TR>
			</table>
			</td>
			<td>
			<table class="section-graph">
				<tr>
						<td>
							<table class="section-graph">
								<thead>
									<tr>
										<th align="left" width="5%"></th>				
										<th width="99%"><center><u>Courbe d’évolution du BMI</u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Taille <u>&nbsp;&nbsp;&nbsp;<openmrs:format obsValue="${formData.obsMapper.latestHeight}"/>&nbsp;&nbsp;&nbsp;</u> cm</center></th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>Poids (kg)</td>										
										<td>
											<div id="bmiGraphGraph" class="bmiGraph"></div>
										</td>
										<td></td>
									</tr>
									<tr>
									<td></td><td>Date (jj/mm/aaaa)</td><td></td>
								</tbody>
							</table>
						</td>
				</tr>
				<tr>
						<td style="border: 1px solid #000000; font-size: 8px;">
								<b><u>Instructions pour dessiner les courbes:</u></b><br/>
									1. Prend la taille du patient à sa première visite.<br/>
									2. Regarde dans le tableau le poids correspondant à cette taille dans la première colonne du BMI 16.<br/>
									3. Dessine une ligne horizontale dans la courbe du BMI partant du poids trouvé et mentionne le BMI 16 à la fin de cette ligne.<br/>
									4. Répète les opérations 2 et 3 maintenant avec le BMI 18.5<br/>
									5. Les deux lignes doivent être de différente couleur.<br/>
									6. A chaque visite il faut prendre le poids du patient et marquer les signes dans la courbe suivant les dates de visite.<br/>
									7. A partir de la variation de la courbe formée par ces signes maintenant tu peux connaitre si le BMI du patient est supérieur à 18.5 ou à 16, ou s’il est inferieur à ces valeurs.<br/>
									8. BMI &lt; 18.5 soutien nutritionnel nécessaire, BMI &lt;16 malnutrition sévère, envisager l'admission et un soutien nutritionnel.<br/>
						</td>
					</tr>
			</table>
			</td>
			</tr>
		</table>
	</div>
</div>
</div>

<%@ include file="templates/footer.jsp"%>
