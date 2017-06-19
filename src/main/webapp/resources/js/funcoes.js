function validarDlg1(xhr, status, args) {
	var validarDlg1 = args.validarDlg1;

	if (validarDlg1) {
		dlg1.show();
	}
}

function validarDlg2(xhr, status, args) {
	var validarDlg2 = args.validarDlg2;
	
	if (validarDlg2) {
		dlg2.show();
	}
}

var menu_timeout = 0;
var menu_tempo_fechar = 500;
var menu_tempo_fechamento = 100;

$(window).bind('beforeunload', function() {
    $('#nonAjaxLoad').show(); 
});

$(document).ready(function() {
	$('#nonAjaxLoad').hide();
	$(".ui-steps-item").eq(0).find('.ui-steps-title').css('padding-top', '30px');
	$(".ui-steps-item").eq(1).find('.ui-steps-title').css('padding-top', '30px');
	$(".ui-steps-item").eq(2).find('.ui-steps-title').css('padding-top', '20px');
	$(".ui-steps-item").eq(3).find('.ui-steps-title').css('padding-top', '20px');
	
	$("#menu").on("mouseover", function(){
		clearTimeout(menu_timeout);
	});

	$("#menu .menu-itens").on("mouseout", function(){
		var $this = $(this);
		var $menu = $this.closest("#menu");

		menu_timeout = setTimeout(function(){
			$menu.find(".menu-itens").slideUp(menu_tempo_fechamento, function(){
				setTimeout(function(){
					$menu.removeClass('aberto');
					$menu.find(".menu-itens").removeAttr('style');	
				}, 5);
			});
		}, menu_tempo_fechar);
	});

	$(".menu-icone").on("click", function(){
		var $this = $(this);
		var $menu = $this.closest("#menu");

		if($menu.hasClass('aberto')){
			$menu.removeClass('aberto');
		}else{
			$menu.addClass("aberto");
		}
	});
});

function mascara(o,f){ 
	v_obj=o 
	v_fun=f 
	setTimeout("execmascara()",1) 
} 
function execmascara(){ 
	v_obj.value=v_fun(v_obj.value) 
} 
function moeda(v){ 
	v=v.replace(/\D/g,"");
	v=v.replace(/(\d{1})(\d{15})$/,"$1.$2")
	v=v.replace(/(\d{1})(\d{11})$/,"$1.$2")
	v=v.replace(/(\d{1})(\d{8})$/,"$1.$2")
	v=v.replace(/(\d{1})(\d{5})$/,"$1.$2") 
	v=v.replace(/(\d{1})(\d{1,2})$/,"$1,$2")
	return v; 
}

$(document).ready(
function() {
    PrimeFaces.locales['pt_BR'] = {
        closeText : 'Fechar',
        prevText : 'Anterior',
        nextText : 'Pr\u00F3ximo',
        currentText : 'Come\u00E7o',
        monthNames : [ 'Janeiro', 'Fevereiro', 'Mar\u00E7o', 'Abril',
                'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro',
                'Outubro', 'Novembro', 'Dezembro' ],
        monthNamesShort : [ 'Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun',
                'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez' ],
        dayNames : [ 'Domingo', 'Segunda', 'Ter\u00E7a', 'Quarta',
                'Quinta', 'Sexta', 'S\u00E1bado' ],
        dayNamesShort : [ 'Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex',
                'Sáb' ],
        dayNamesMin : [ 'D', 'S', 'T', 'Q', 'Q', 'S', 'S' ],
        weekHeader : 'Semana',
        firstDay : 1,
        isRTL : false,
        showMonthAfterYear : false,
        yearSuffix : '',
        timeOnlyTitle : 'S\u00F3 Horas',
        timeText : 'Tempo',
        hourText : 'Hora',
        minuteText : 'Minuto',
        secondText : 'Segundo',
        currentText : 'Data Atual',
        ampm : false,
        month : 'M\u00EAs',
        week : 'Semana',
        day : 'Dia',
        allDayText : 'Todo Dia'
    };
});
