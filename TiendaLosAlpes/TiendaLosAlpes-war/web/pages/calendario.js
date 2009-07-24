var curDt = new Date();
function disablementFunction(day){
    if (day.isWeekend) return true;
    if (curDt==undefined){
        curDt = day.date.getDate;
    }
    if (curDt.getTime() - day.date.getTime() < 0) return true; else return true;
}
function disabledClassesProv(day){
    if (curDt.getTime() - day.date.getTime() >= 0) return 'rich-calendar-boundary-dates';
    var res = '';
    if (day.isWeekend) res+='weekendBold ';
    if (day.day%3==0) res+='everyThirdDay';
    return res;
}