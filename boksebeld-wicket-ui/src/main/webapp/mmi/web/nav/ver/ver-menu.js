var timeout         = 500;
var closetimer		= 0;
var web_menuitem    = 0;

function web_vert_menu_open(){
	web_vert_menu_canceltimer();
	web_vert_menu_close();
	web_menuitem = $(this).find('ul').eq(0)
	web_menuitem.css('visibility', 'visible');
	web_menuitem.css({display: "block"});
}

function web_vert_menu_close(){
	if(web_menuitem){
		web_menuitem.css('visibility', 'hidden');
		web_menuitem.css({display: "none"});
	}	
}
	


function web_vert_menu_timer(){	
	closetimer = window.setTimeout(web_vert_menu_close, timeout);}

function web_vert_menu_canceltimer(){
	if(closetimer)
	{	window.clearTimeout(closetimer);
		closetimer = null;}}

$(document).ready(function(){
					$('#vertical-dynamic > li').bind('mouseover', web_vert_menu_open);
					$('#vertical-dynamic > li').bind('mouseout',  web_vert_menu_timer);
						}
);

document.onclick = web_vert_menu_close;

