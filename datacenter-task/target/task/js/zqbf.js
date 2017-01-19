/**
 * ━━━━━━神兽出没━━━━━━
 * 　　  ┏┓　      ┏┓
 * 　　┏┛┻━━━━━┛┻┓
 * 　　┃　　　　    ┃
 * 　　┃　　　━   ┃
 * 　　┃　┳┛　┗┳　 ┃
 * 　　┃          ┃
 * 　　┃　　 ┻　      ┃
 * 　　┃　　　　　 ┃
 * 　　┗━┓　　    ┏━┛
 * 　　　┃　　　┃神兽保佑, 永无BUG!
 * 　　　┃　　　┃Code is far away from bug with the animal protecting
 * 　　　┃　　　┗━━━┓
 * 　　　┃　　　　　 ┣┓
 * 　　　┃　　　　　 ┏┛
 * 　　　┗┓┓┏━   ┳┓┏┛
 * 　　　  ┃┫┫　    ┃┫┫
 * 　　　  ┗┻┛　    ┗┻┛
 * ━━━━━━感觉萌萌哒━━━━━━
 * @author: zhangcb
 * @date: 2017/01/06
 */
var score = {
		serverTime:		'',			//服务器时间
		allMatches:		[],			//当前期比赛对阵
		lottery:		'all',		//彩种，竞彩、足彩
		currentstage:	'',			//当前期
		//比赛状态 0:未开,1:上半场,2:中场,3:下半场,4,加时，-11:待定,-12:腰斩,-13:中断,-14:推迟,-1:完场，-10取消
		statusMap:		{"0":"未开赛","1":"上半场","2":"中场","3":"下半场","4":"加时","-11":"待定","-12":"腰斩","-13":"中断","-14":"推迟","-1":"完场","-10":"取消"},
		jcstagetpl:		'<li class="$current$" onclick="score.loadMatchs($name$)">'+
					        '<span>$week$</span>'+
					        '<span>$time$</span>'+
				        '</li>',
		zcstagetpl:		'<li class="$current$" onclick="score.loadMatchs($name$)">'+
					        '<span>$name$</span>'+
					    '</li>',
		matchtpl:		'<div id="match_$sid$" class="scoretbox">'+
					'<div class="scorefpart">'+
						'<span class="scdesc mt10">$mtime$</span>'+
						'<span class="scdesc mt10">$sort$ $league$</span>'+
					'</div>'+
					'<div class="scorespart">'+
						'<div class="scoreilist">'+
							'<span class="scoreblck firblck">'+
								'<img class="scimg" src="https://res.daysluck.com/file/logo/scoutFootball/team_$hometeamid$.png" />'+
								'<p class="sdesc">$hometeam$$homeOrder$</p>'+
							'</span>'+
							'<span class="scoreblck scoreconts">'+
								  '<p class="scoredesc mt10 $statusstyle$" id="status_$sid$">$status$</p>'+
								  '<p class="scpoint $scorestyle$" id="score_$sid$">$score$</p>'+
								  '<p class="halfsprt" id="half_$sid$">$half$</p>'+
							'</span>'+
							'<span class="scoreblck thirblck">'+
								'<img class="sceimg" src="https://res.daysluck.com/file/logo/scoutFootball/team_$guestteamid$.png" />'+
								'<p class="sdesc">$guestteam$$guestOrder$</p>'+
							'</span>'+
						'</div>'+
					'</div>'+
				'</div>',
		loadStage:function(type){
			var stagehtml = '';
			score.lottery = type;
			$.ajax({
				async:false,
				url:'/task/jsbf/stage/'+type+'.json?' + new Date().getTime(),
				type: 'GET',
				success: function(data){
					if(!data){
						return;
					}
					
					if(data.length > 0){
						score.currentstage = data[2].name;
						var tpl = score.jcstagetpl;
						if(type == 'all'){
							tpl = score.jcstagetpl;
						}else{
							tpl = score.zcstagetpl;
						}
						for(var i = 0; i < data.length; i++){
							if(data[i].iscurrent == 1){
								score.currentstage = data[i].name;
								data[i].current = 'current';
							}else{
								data[i].current = '';
							}
							stagehtml +=tpl.temp(data[i]);
						}
					}
					if(type == 'all'){
						$(".seledatelist").html(stagehtml);
					}else{
						$(".termlist").html(stagehtml);
					}
					score.loadMatchs(score.currentstage);
				}
			});
		},
		loadMatchs:function(qc){
			var matchhtml = '';
			$.ajax({
				async:false,
				url:'/task/jsbf/'+score.lottery+'/'+qc+'.json?' + new Date().getTime(),
				type: 'GET',
				success: function(data){
					if(!data){
						return;
					}
					score.serverTime = new moment(arguments[2].getResponseHeader("Date")).utc();
					var matchs = data.matchs;
					var flag = false;
					if(matchs.length > 0){
						score.allMatches = matchs;
						for(var i = 0; i < matchs.length; i++){
							var match = matchs[i];
							//比赛状态 0:未开,1:上半场,2:中场,3:下半场,4,加时，-11:待定,-12:腰斩,-13:中断,-14:推迟,-1:完场，-10取消
							var code = match.matchstate;
							if(code == -1 || code == 4){ //完场
								match.status = score.statusMap[match.matchstate];
								match.score = match.homescore + ":" + match.guestscore;
								match.half = "半场" + match.homehalfscore + ":" + match.guesthalfscore;
								match.statusstyle = "orclr";
								match.scorestyle = "orclr";
							}else if(code == -12 || code == -13){  //中断、腰斩
								match.status = score.statusMap[match.matchstate];
								match.score = match.homescore + ":" + match.guestscore;
								match.statusstyle = "orclr";
								match.scorestyle = "orclr";
							}else if(code == 0 || code == -10 || code == -11 || code == -14){  //未开赛、延期、待定
								if(code == 0){
									match.status = "VS";
									flag = true;
								}else{
									match.status = score.statusMap[match.matchstate];
								}
								match.score = "-";
								match.statusstyle = "orclr";
								match.scorestyle = "orclr";
							}else{   //进行中
								var startTime = match.matchtime2;
								var def = score.serverTime.diff(moment.parseZone(startTime))/ 60000;
								var fmtTime = Math.floor(parseFloat(def));
								fmtTime = fmtTime > 1 ? fmtTime : 1;
								if(code == 1){
									
									fmtTime = (fmtTime > 45 ? '45+':fmtTime.toString());
									match.status = '<b class="scround"></b><b class="ftsize scont">'+fmtTime+'</b>';
									match.score = match.homescore + ":" + match.guestscore;
									
								}else if(code == 2){
									match.status = '中场';
									match.score = match.homescore + ":" + match.guestscore;
									match.half = "半场" + match.homehalfscore + ":" + match.guesthalfscore;
								}else if(code == 3){
									fmtTime+=45;
									fmtTime = (fmtTime > 90 ? '90+':fmtTime.toString());
									match.status = '<b class="scround"></b><b class="ftsize scont">'+fmtTime+'</b>';
									match.score = match.homescore + ":" + match.guestscore;
									match.half = "半场" + match.homehalfscore + ":" + match.guesthalfscore;
								}
								match.statusstyle = "";
								match.scorestyle = "";
								flag = true;
							}
							match.mtime = match.matchtime.split(" ")[1].substr(0,5);
							if(score.lottery == 'all'){
								match.sort = match.sort.substr(2,5);
							}
							match.homeOrder = "[" + match.homeOrder + "]";
							match.guestOrder = "[" + match.guestOrder + "]";
							match.hometeam = match.hometeam.substr(0,4);
							match.guestteam = match.guestteam.substr(0,4);
							matchhtml +=score.matchtpl.temp(matchs[i]);
						}
					}
					$(".scoremtlist").html(matchhtml);
					score.bindEvent();
					clearTimeout(window.timeId);
					if(parseInt(qc) == parseInt(score.currentstage) || flag){
						score.changeScore();
					}
				}
			});
		},
		bindEvent:function(){
			var $blcklist=$("#blcklists");
            var blcklists=$blcklist.find(".blck");
            var MoreLocked=$("#MoreLocked");
            var MoreDetail=$("#MoreDetail");
            var MoreLists=$("#MoreLists");
            var sportblcklist=MoreLists.find(".sportblck");
            //console.log(blcklists);
            $blcklist.on("click",".blck",function(){
            	  var obj=$(this);
            	  var tabindex=blcklists.index(obj);
            	  blcklists.eq(tabindex).addClass('current').siblings().removeClass('current');
            	  var trgleiconobj=obj.find(".trgleicon");
            	  if(!trgleiconobj.hasClass('on'))
            	  {
            	  	trgleiconobj.addClass('on');
            	  }
            	  else
            	  {
            	  	trgleiconobj.removeClass('on');
            	  }
            	  MoreLocked.show();
            	  MoreDetail.show();
            	  
            });
            $(".seledatelist, .termlist").on("click","li",function(){
            	$(this).siblings().removeClass('current');
	          	$(this).addClass('current');
	        });
            
            MoreLocked.on("click",function(){
            	  if(MoreDetail.css("display")=="block")
            	  {
            	  	var trgleiconobj=$blcklist.find(".blck.current").find(".trgleicon");
            	  	trgleiconobj.removeClass('on');
            	  	MoreLocked.hide();
            	  	MoreDetail.hide();
            	  }
            });
            MoreLists.on("click",".sportblck",function(){
            	  var obj=$(this);
            	  var tabindex=sportblcklist.index(obj);
            	  var descstr=obj.attr("data-desc");
            	  sportblcklist.removeClass('current');
            	  sportblcklist.eq(tabindex).addClass('current');
            	  if(tabindex == 0){
            		  $(".seledatelist").show();
            		  $(".termlist").hide();
            	  }
            	  else{
            		  $(".seledatelist").hide();
            		  $(".termlist").show();
            	  }
            	  $blcklist.find(".blck.current").find(".jcblckdesc").text(descstr);
            	  if(MoreDetail.css("display")=="block")
            	  {
            	  	var trgleiconobj=$blcklist.find(".blck.current").find(".trgleicon");
            	  	trgleiconobj.removeClass('on');
            	  	MoreLocked.hide();
            	  	MoreDetail.hide();
            	  }
            });
		},
		changeScore:function(){
			var Y = score;
			window.timeId = setInterval(function(){
				$.ajax({
					async:false,
					url:'/task/jsbf/change/change.json?' + new Date().getTime(),
					type: 'GET',
					timeout: 1000,
					success: function(data){
						if(!data){
							return;
						}
						var nowDate = new moment(arguments[2].getResponseHeader("Date")).utc();
						Y.serverTime = nowDate > Y.serverTime?nowDate:Y.serverTime; // 服务器时间
						var datas = data.data;
						var matchs = Y.allMatches;
						for(var j = 0;j<datas.length;j++){
							var row = datas[j];
							for ( var i = 0; i < matchs.length; i++) {
								
								if (matchs[i].sid == row.sid) {  //当前有变化
									if(matchs[i].homeRed != row.homeRed){
										
										matchs[i].homeRed = row.homeRed;
									}
									if(matchs[i].guestRed != row.guestRed){
										
										matchs[i].guestRed = row.guestRed;
									}
									if(matchs[i].homeYellow != row.homeYellow){
										
										matchs[i].homeYellow = row.homeYellow;
									}
									if(matchs[i].guestYellow != row.guestYellow){
										
										matchs[i].guestYellow = row.guestYellow;
									}
									if(row.half != ''){
										var score = row.half.split("-");
										matchs[i].homehalfscore = score[0];
										matchs[i].guesthalfscore = score[1];
									}
									if(matchs[i].homescore <= row.homescore){
										//playSound();
										matchs[i].homescore = row.homescore;
									}
									if(matchs[i].guestscore <= row.guestscore){
										//playSound();
										matchs[i].guestscore = row.guestscore;
									}
									//比赛状态发生变化时
									if(matchs[i].matchstate != row.matchstate){
										if(matchs[i].matchstate > 0 && row.matchstate > 0){
											matchs[i].matchstate = matchs[i].matchstate > row.matchstate?matchs[i].matchstate:row.matchstate;
										}else{
											matchs[i].matchstate = row.matchstate;
										}
									}
									matchs[i].matchtime = row.matchtime;
								}
								//比赛状态 0:未开,1:上半场,2:中场,3:下半场,4,加时，-11:待定,-12:腰斩,-13:中断,-14:推迟,-1:完场，-10取消
								var sid = matchs[i].sid;
								var code = matchs[i].matchstate;
								if(code == -1 || code == 4){ //完场
									$('#status_' + sid).html(Y.statusMap[code]);
									$('#score_' + sid).html(matchs[i].homescore + ":" + matchs[i].guestscore);
									$('#status_' + sid).addClass('orclr');
									$('#score_' + sid).addClass('orclr');
									$('#half_' + sid).html("半场" + matchs[i].homehalfscore + ":" + matchs[i].guesthalfscore);
									//完场的下沉
									$('.scoremtlist').append($('#match_' + sid));
								}else if(code == -12 || code == -13){  //中断、腰斩
									$('#status_' + sid).html(Y.statusMap[code]);
									$('#score_' + sid).html(matchs[i].homescore + ":" + matchs[i].guestscore);
									$('#status_' + sid).addClass('orclr');
									$('#score_' + sid).addClass('orclr');
								}else if(code == 0 || code == -10 || code == -11 || code == -14){  //未开赛、延期、待定
									if(code == 0){
										$('#status_' + sid).html('VS');
									}else{
										$('#status_' + sid).html(Y.statusMap[code]);
									}
									$('#score_' + sid).html("-");
									$('#status_' + sid).addClass('orclr');
									$('#score_' + sid).addClass('orclr');
									
								}else{   //进行中
									var startTime = matchs[i].matchtime;
									var def = Y.serverTime.diff(moment.parseZone(startTime))/ 60000;
									var fmtTime = Math.floor(parseFloat(def));
									fmtTime = fmtTime > 1 ? fmtTime : 1;
									if(code == 1){
										
										fmtTime = (fmtTime > 45 ? '45+':fmtTime.toString());
										$('#status_' + sid).html('<b class="scround"></b><b class="ftsize scont">'+fmtTime+'</b>');
										$('#score_' + sid).html(matchs[i].homescore + ":" + matchs[i].guestscore);
										
									}else if(code == 2){
										$('#status_' + sid).html('中场');
										$('#score_' + sid).html(matchs[i].homescore + ":" + matchs[i].guestscore);
										$('#half_' + sid).html("半场" + matchs[i].homehalfscore + ":" + matchs[i].guesthalfscore);
									}else if(code == 3){
										fmtTime+=45;
										fmtTime = (fmtTime > 90 ? '90+':fmtTime.toString());
										$('#status_' + sid).html('<b class="scround"></b><b class="ftsize scont">'+fmtTime+'</b>');
										$('#score_' + sid).html(matchs[i].homescore + ":" + matchs[i].guestscore);
										$('#half_' + sid).html("半场" + matchs[i].homehalfscore + ":" + matchs[i].guesthalfscore);
									}
									$('#status_' + sid).removeClass('orclr');
									$('#score_' + sid).removeClass('orclr');
								}
							}
						}
						Y.allMatches = matchs;
					}
				});
			}, 3*1000);
		}
};

$(function(){
	score.loadStage("all");
});

String.prototype.temp = function(obj) {
    return this.replace(/\$\w+\$/gi, function (matchs) {
        var returns = obj[matchs.replace(/\$/g, "")];
        return (returns + "") == "undefined" ? "" : returns;
    });
};