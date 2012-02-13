<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>

<sx:tabbedpanel id="mainNavBar" beforeSelectTabNotifyTopics="/navBarBefore"
	selectedTab="%{selectedTab}">
	<sx:div id="Astreintes" label="Astreintes du jour" theme="ajax"
		labelposition="top">
	</sx:div>
	<sx:div id="Personnels" label="Annuaire du personnels" theme="ajax">
	</sx:div>
	<sx:div id="PlanningAstreintes" label="Planning des astreintes"
		theme="ajax">
	</sx:div>
</sx:tabbedpanel>

<script type="text/javascript">
	dojo.event.topic
			.subscribe(
					'/navBarBefore',
					function(event, tab, mainNavBar) {

						if (tab.widgetId == "Personnels") {
							document.forms['filtreForm'].action = 'showPersonnel.action';
							document.forms['filtreForm'].submit();
						}

						if (tab.widgetId == "PlanningAstreintes") {
							document.forms['filtreForm'].action = 'showPlanningAstreintes.action';
							document.forms['filtreForm'].submit();
						}

						if (tab.widgetId == "Astreintes") {
							document.forms['filtreForm'].action = 'mainView.action';
							document.forms['filtreForm'].submit();
						}
					});
</script>



