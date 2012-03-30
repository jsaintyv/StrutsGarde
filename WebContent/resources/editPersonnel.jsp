	<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<html>

<head>
<link href="<s:url value="/resources/main.css"/>" rel="stylesheet"
	type="text/css" />
<title><s:text name="application.title" />
</title>
<sx:head />
</head>
<body>
	<div class="titleDiv">
		<s:text name="application.title" />
	</div>

	<s:form>

		<s:hidden name="personnel.personnelId" />
		<h3>Informations:</h3>
		<ul>
			<li><s:text name="label.firstname" />:<s:textfield
					name="personnel.nom" size="18" />
			</li>
			<li><s:text name="label.lastname" />:<s:textfield
					name="personnel.prenom" size="18" />
			</li>
			<li><s:text name="label.phone" />:<s:textfield
					name="personnel.telPerso" size="18" />
			</li>
			<li><s:text name="label.phoneCellular" />:<s:textfield
					name="personnel.telMobile" size="18" />
			</li>
			<li><s:text name="label.phoneWork" />:<s:textfield
					name="personnel.telPro" size="18" />
			</li>
			<li><s:text name="label.comment" />:<s:textfield
					name="personnel.commentaire" size="18" />
			</li>
		</ul>


		<h3>Affectations possible aux:</h3>
		<ul>
			<s:iterator value="personnel.affectations">
				<li><s:property value='nom' /> - <s:url
						id="supprimerActiviteAuPersonnel"
						action="supprimerActiviteAuPersonnel">
						<s:param name="activiteIdToHandle" value="activiteId" />
						<s:param name="personnel.personnelId"
							value="#request.personnel.personnelId" />
					</s:url> <s:a href="%{supprimerActiviteAuPersonnel}">Supprimer</s:a></li>
			</s:iterator>
		</ul>
		<div>
			Ajouter :
			<s:url id="jsonActivite" value="JSONActiviteListDropDown.action" />
			<sx:autocompleter name='activiteToAdd' href='%{jsonActivite}'
				autoComplete="true" keyName="activiteIdToHandle"
				loadOnTextChange="true" loadMinimumCount="1"></sx:autocompleter>
			<s:submit action="ajouterActiviteAuPersonnel" key="label.add"
				cssClass="butStnd" />
		</div>

		<div id="barButton">
			<s:submit action="savePersonnel" key="label.save" cssClass="butStnd" />
			<s:submit action="showPersonnel" key="label.cancel"
				cssClass="butStnd" />
		</div>
	</s:form>



</body>
</html>