var timeout         = 500;
var closetimer		= 0;
var web_menuitem    = 0;

function web_hor_open()
{	web_hor_canceltimer();
	web_hor_close();
	web_menuitem = $(this).find('ul').eq(0);
	web_menuitem.css('visibility', 'visible');
	web_menuitem.css({display: "block"});
}

function web_hor_close()
{	
	if(web_menuitem){
		web_menuitem.css('visibility', 'hidden');
	}
}

function web_hor_timer()
{	closetimer = window.setTimeout(web_hor_close, timeout);}

function web_hor_canceltimer()
{	if(closetimer)
	{	window.clearTimeout(closetimer);
		closetimer = null;}}

$(document).ready(function()
		{	$('#hor_nav  > li').bind('mouseover', web_hor_open);
			$('#hor_nav  > li').bind('mouseout',  web_hor_timer);});


document.onclick = web_hor_close;

