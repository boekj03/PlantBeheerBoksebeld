$(function() {
	
	$.datepicker.regional['nl'] = {
			monthNames: ['januari', 'februari', 'maart', 'april', 'mei', 'juni',
			'juli', 'augustus', 'september', 'oktober', 'november', 'december'],
			//monthNamesShort: ['jan', 'feb', 'maa', 'apr', 'mei', 'jun',
			//'jul', 'aug', 'sep', 'okt', 'nov', 'dec'],
			dayNames: ['zondag', 'maandag', 'dinsdag', 'woensdag', 'donderdag', 'vrijdag', 'zaterdag'],
			//dayNamesShort: ['zon', 'maa', 'din', 'woe', 'don', 'vri', 'zat'],
			dayNamesMin: ['zo', 'ma', 'di', 'wo', 'do', 'vr', 'za'],
			dateFormat: 'dd-mm-yy', firstDay: 1,
			renderer: $.datepicker.defaultRenderer,
			prevText: 'Vorige', prevStatus: 'Bekijk de vorige maand',
			//prevJumpText: '«', nextJumpStatus: 'Bekijk het vorige jaar',
			nextText: 'Volgende', nextStatus: 'Bekijk de volgende maand',
			//nextJumpText: '»', nextJumpStatus: 'Bekijk het volgende jaar',
			//currentText: 'Vandaag', currentStatus: 'Bekijk de huidige maand',
			//todayText: 'Vandaag', todayStatus: 'Bekijk de huidige maand',
			//clearText: 'Wissen', clearStatus: 'Wis de huidige datum',
			//closeText: 'Sluiten', closeStatus: 'Sluit zonder verandering',
			//yearStatus: 'Bekijk een ander jaar', monthStatus: 'Bekijk een andere maand',
			//weekText: 'Wk', weekStatus: 'Week van het jaar',
			//dayStatus: 'dd-mm-yyyy', defaultStatus: 'Kies een datum',
			isRTL: false
		};
	
	$.datepicker.setDefaults($.datepicker.regional['nl']);
	
});