<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page import="org.openmrs.web.WebConstants" %>
<%
	pageContext.setAttribute("msg", session.getAttribute(WebConstants.OPENMRS_MSG_ATTR));
	pageContext.setAttribute("msgArgs", session.getAttribute(WebConstants.OPENMRS_MSG_ARGS));
	pageContext.setAttribute("err", session.getAttribute(WebConstants.OPENMRS_ERROR_ATTR));
	pageContext.setAttribute("errArgs", session.getAttribute(WebConstants.OPENMRS_ERROR_ARGS));
	session.removeAttribute(WebConstants.OPENMRS_MSG_ATTR);
	session.removeAttribute(WebConstants.OPENMRS_MSG_ARGS);
	session.removeAttribute(WebConstants.OPENMRS_ERROR_ATTR);
	session.removeAttribute(WebConstants.OPENMRS_ERROR_ARGS);
%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<openmrs:htmlInclude file="${pageContext.request.contextPath}/scripts/jquery/jquery-1.3.2.min.js"/>
		<script type="text/javascript">
			var $j = jQuery.noConflict(); 
		</script>

		<openmrs:htmlInclude file="${pageContext.request.contextPath}/openmrs.js"/>
		<openmrs:htmlInclude file="${pageContext.request.contextPath}/openmrs.css"/>
		<openmrs:htmlInclude file="${pageContext.request.contextPath}/style.css"/>

		<script type="text/javascript">
			/* variable used in js to know the context path */
			var openmrsContextPath = '${pageContext.request.contextPath}';
			$j(document).ready(function() {
				$j('#popup').dialog({
					title: '',
					autoOpen: false,
					draggable: false,
					resizable: false,
					width: '95%',
					modal: true
				});
			});
		</script>

		<c:choose>
			<c:when test="${!empty pageTitle}">
				<title>${pageTitle}</title>
			</c:when>
			<c:otherwise>
				<title><spring:message code="openmrs.title"/></title>
			</c:otherwise>
		</c:choose>
	</head>

	<body>
		<div id="pageBody">
			<div id="popup">
				<iframe id="popup-iframe" width="100%" height="100%" marginwidth="0" marginheight="0" frameborder="0" scrolling="auto"></iframe>
			</div>
			<div id="header">
				<table width="100%"><tr>
					<td>
					</td>
					<td align="right" class="small">
						<openmrs:authentication/>
						<c:if test="${not empty authenticatedUser}">
							logged in as
							<a href="javascript:showPopup('<spring:message code="rwandaadulthivflowsheet.userPrefs.title" javaScriptEscape="true"/>', openmrsContextPath + 'module/rwandaadulthivflowsheet/userPrefs.form', true);">
								${authenticatedUser.username}
							</a>
							<a href='${pageContext.request.contextPath}/logout'><spring:message code="header.logout" /></a>
						</c:if>
					</td>
				</tr></table>
			</div>
			<div id="content">
				<c:if test="${msg != null}">
					<div id="openmrs_msg"><spring:message code="${msg}" text="${msg}" arguments="${msgArgs}" /></div>
				</c:if>
				<c:if test="${err != null}">
					<div id="openmrs_error"><spring:message code="${err}" text="${err}" arguments="${errArgs}"/></div>
				</c:if>
