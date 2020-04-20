var nOfBatches = 0;
var batches = "";

var courses, teachers, classrooms;
var hours;

var routineTable;
var newRow, routineRows;

var i, x, d;
var B, l = 0, m = 0;
var k;

var routines;
var codes;
var nor;

var studentTable, teacherTable, classroomTable;
var one, two, three;

var DBFINAL;


var A = ["SUN_8AM", "SUN_9AM", "SUN_10AM", "SUN_11AM", "SUN_12PM", "SUN_2PM", "SUN_3PM", "SUN_4PM", "MON_8AM", "MON_9AM", "MON_10AM", "MON_11AM", "MON_12PM", "MON_2PM", "MON_3PM", "MON_4PM", "TUE_8AM", "TUE_9AM", "TUE_10AM", "TUE_11AM", "TUE_12PM", "TUE_2PM", "TUE_3PM", "TUE_4PM", "WED_8AM", "WED_9AM", "WED_10AM", "WED_11AM", "WED_12PM", "WED_2PM", "WED_3PM", "WED_4PM", "THU_8AM", "THU_9AM", "THU_10AM", "THU_11AM", "THU_12PM", "THU_2PM", "THU_3PM", "THU_4PM"];
var days = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday"];


function pelletLoadRow(tableID, type, value) {

    if (type == "teachers") {
        var selector;
        var opt = document.createElement('option');

        selector = document.getElementById('teachers');

        opt.appendChild(document.createTextNode(value));
        opt.value = value;
        selector.appendChild(opt);
    }

    if (type == "students") {
        var selector;
        var opt = document.createElement('option');

        selector = document.getElementById('students');

        opt.appendChild(document.createTextNode(value));
        opt.value = value;
        selector.appendChild(opt);
    }

    if (type == "classrooms") {
        var selector;
        var opt = document.createElement('option');

        selector = document.getElementById('classrooms');

        opt.appendChild(document.createTextNode(value));
        opt.value = value;
        selector.appendChild(opt);
    }

}

function pellets_T_Loader(T, nt) {
    var arr = T.split(',');
    var i;

    for (i = 0; i < nt; i++) {
        pelletLoadRow("TEACHERS", "teachers", arr[i]);
    }
}

function pellets_S_Loader(S, ns) {
    var arr = S.split(',');
    var i;

    for (i = 0; i < ns; i++) {
        pelletLoadRow("STUDENTS", "students", arr[i]);
    }
}

function pellets_C_Loader(C, nc) {
    var arr = C.split(',');
    var i;

    for (i = 0; i < nc; i++) {
        pelletLoadRow("CLASSROOMS", "classrooms", arr[i]);
    }
}


function pelletsLoader() {
    var T, S, C, CO, nt, ns, nc, nco;

    $('#teachers').empty();
    $('#classrooms').empty();
    $('#students').empty();

    $.ajax({
        type: 'POST',
        url: 'PelletsLoaderRoutine',

        success: function (data, status, jqXHR) {
            T = jqXHR.getResponseHeader('T');
            S = jqXHR.getResponseHeader('S');
            C = jqXHR.getResponseHeader('C');
            CO = jqXHR.getResponseHeader('CO');

            nt = jqXHR.getResponseHeader('nt');
            ns = jqXHR.getResponseHeader('ns');
            nc = jqXHR.getResponseHeader('nc');
            nco = jqXHR.getResponseHeader('nco');

            pellets_T_Loader(T, nt);
            pellets_S_Loader(S, ns);
            pellets_C_Loader(C, nc);
        },

        Error: function () {
            alert("Error occurred while cancelling the classes!");
        }
    });
}

function preLoader() {

//    alert(getCookie("db_code"));

    $.ajax({
        type: 'POST',
        url: 'RoutineProjects_Loader',

        success: function (data, status, jqXHR) {
            routines = jqXHR.getResponseHeader('routines');
            codes = jqXHR.getResponseHeader('codes');
            nor = jqXHR.getResponseHeader('nor');

            routinesLoader();
        },

        Error: function () {
            alert("Error occurred while cancelling the classes!");
        }
    });
}


function routinesLoader() {

    //alert(routines +"    "+codes);

    var r = routines.split(',');
    var c = codes.split(',');
    var i = 0;
    for (i = 0; i < nor; i++) {
        var opt = document.createElement('option');
        var selector = document.getElementById('db_codes');

        opt.appendChild(document.createTextNode(r[i]));
        opt.value = c[i];

        selector.appendChild(opt);
    }
}


function generateRoutine() {

    B = batches.split(',');


    for (d = 0; d < 5; d++)
    {
        for (i = 0; i < nOfBatches; i++) {
            k = 0;

            routineTable.insertRow();
            routineRows = routineTable.getElementsByTagName('tr');
            newRow = routineRows[routineRows.length - 1];

            newRow.insertCell(k++).innerHTML = days[d];
            newRow.insertCell(k++).innerHTML = B[i];

            for (x = d * 8; x <= (d * 8) + 7; x++) {
                master();
            }

        }
    }

}

//function master() {
//    for (x = d * 8; x <= (d * 8) + 7; x++) {
//
//        setTimeout(function () {
//            $.ajax({
//                type: 'POST',
//                url: 'RoutineMaster',
//                data: "students=" + B[i] + "&time=" + A[x],
//                success: function (data, status, jqXHR) {
//                    courses = jqXHR.getResponseHeader('courses');
//                    teachers = jqXHR.getResponseHeader('teachers');
//                    classrooms = jqXHR.getResponseHeader('classrooms');
//                    hours = jqXHR.getResponseHeader('hours');
//
////                        if(hours > 0){
////                            //alert("Greater " + m++);
////                        }else{
////                            //alert("less " + m++);
////                        }
//
//
//                    if (hours > 0) {
//                        newRow.insertCell(k++).innerHTML = "<td colspan='" + hours + "'><div class='routine-data'>" + courses + "<br>" + teachers + "<br>" + classrooms + "</div></td>";
//                        x += hours - 1;
//                    } else {
//                        newRow.insertCell(k++).innerHTML = "<td><div class='routine-data'>" + courses + "<br>" + teachers + "<br>" + classrooms + "</div></td>";
//                    }
//
//                },
//
//                Error: function () {
//                    alert("Error occurred while calling RoutineMaster");
//                }
//            });
//        }, 1000);
//    }
//}

function master() {

//    alert(B[i] + "   " + A[x]);

    $.ajax({
        async: false,
        type: 'POST',
        url: 'RoutineMaster',
        data: "students=" + B[i] + "&time=" + A[x],
        success: function (data, status, jqXHR) {
            courses = jqXHR.getResponseHeader('courses');
            teachers = jqXHR.getResponseHeader('teachers');
            classrooms = jqXHR.getResponseHeader('classrooms');
            hours = jqXHR.getResponseHeader('hours');

//                        if(hours > 0){
//                            //alert("Greater " + m++);
//                        }else{
//                            //alert("less " + m++);
//                        }


            if (hours > 1) {
                newRow.insertCell(k++).innerHTML = "<td colspan='" + hours + "'><div class='routine-data'>" + courses + "<br>" + teachers + "<br>" + classrooms + "</div></td>";
                x += hours - 1;
                newRow.cells[k - 1].colSpan = "" + hours + "";
//                var col;
//                
//                for(col = x; col >= 1; col--){
//                    newRow.insertCell(k++);
//                }


            } else {
                newRow.insertCell(k++).innerHTML = "<td><div class='routine-data'>" + courses + "<br>" + teachers + "<br>" + classrooms + "</div></td>";
            }

        },

        Error: function () {
            alert("Error occurred while calling RoutineMaster");
        }
    });

}


function load() {
    //alert("Working");

    document.getElementById('r').hidden = false;

    $("#routine").find("tr:not(:first)").remove();

    $body = $("body");

    $(document).on({
        ajaxStart: function () {
            $body.addClass("loading");
        },
        ajaxStop: function () {
            $body.removeClass("loading");
        }
    });

    routineTable = document.getElementById('routine');

    $.ajax({
        type: 'POST',
        url: 'StudentManager',
        success: function (data, status, jqXHR) {
            //alert("Getting batches1");
            nOfBatches = jqXHR.getResponseHeader('ns');
            batches = jqXHR.getResponseHeader('stu');
            //alert("Getting batches");
            //document.getElementById('Demo1').innerHTML = "Count: " + nOfBatches + "  batches: "+batches;
            generateRoutine();
        },

        Error: function () {
            alert("Error occurred while creating a course!");
        }
    });


}

function newRoutineRow() {
    routineTable = document.getElementById('routine').insertRow();
    newRow = routineTable.insertRow();
}

function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
}

function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
}

function All() {
    var selector = document.getElementById("db_codes");
    var db_code = selector.options[selector.selectedIndex].value;

    if (document.getElementById('all').checked) {

        if (db_code == "select") {
            alert("Please select a routine first");
        } else {
            load();
        }
    }
}


function db_codeManager() {

//    document.getElementById("new-move").disabled = false;
//    document.getElementById("Clear-Recent-Moves").disabled = true;
////    document.getElementById("accept").disabled = true;
//    document.getElementById("new-move").style.background = '#ccff66';
//    document.getElementById("Clear-Recent-Moves").style.background = '#cccccc';

    var selector = document.getElementById("db_codes");
    var code = selector.options[selector.selectedIndex].value;

    DBFINAL = code;


//    alert(code);




    if (code == "select") {
        alert("You need to select a routine!");
        return;
    } else {
        $.ajax({
            type: 'POST',
            url: 'RoutinesManager',
            data: "db_code=" + code,
            success: function () {
                pelletsLoader()();
            },

            Error: function () {
                alert("Error occurred while setting routine name!");
            }
        });
    }
}

function onlyStudents() {

//    alert("Students working");

    if (document.getElementById('only-studetns').checked) {

        if (DBFINAL == "select") {
            alert("Please select a routine first");
        } else {
            
            $("#routine-S").find("tr:not(:first)").remove();

            try {
                document.getElementById("s").hidden = false;

                studentTable = document.getElementById("routine-S");

                var selector = document.getElementById("students");
                var st = selector.options[selector.selectedIndex].value;

                document.getElementById('lebel-routine-S').innerHTML = "Class routine for the students of semester " + st + " (" + DBFINAL + ")";
//        alert(st);
            } catch (e) {
                alert("Found error at the begining");
            }

            for (d = 0; d < 5; d++)
            {
                k = 0;

                studentTable.insertRow();
                var studentRows = studentTable.getElementsByTagName('tr');
                var newStuROw = studentRows[studentRows.length - 1];

//        alert("New row added");


                newStuROw.insertCell(k++).innerHTML = days[d];

                for (x = d * 8; x <= (d * 8) + 7; x++) {

                    ParamMaster(st, "student");

                    if (hours > 1) {
                        newStuROw.insertCell(k++).innerHTML = "<td colspan='" + hours + "'><div class='routine-data'>" + one + "<br>" + two + "<br>" + three + "</div></td>";
                        x += hours - 1;
                        newStuROw.cells[k - 1].colSpan = "" + hours + "";

                    } else {
                        newStuROw.insertCell(k++).innerHTML = "<td><div class='routine-data'>" + one + "<br>" + two + "<br>" + three + "</div></td>";
                    }
                }

            }
        }
    }else{
        document.getElementById("s").hidden = true;
    }
}


function onlyTeachers() {

//    alert("Students working");

    if (document.getElementById('only-teachers').checked) {

        if (DBFINAL == "select") {
            alert("Please select a routine first");
        } else {
            
            $("#routine-T").find("tr:not(:first)").remove();

            try {
                document.getElementById("t").hidden = false;

                studentTable = document.getElementById("routine-T");

                var selector = document.getElementById("teachers");
                var st = selector.options[selector.selectedIndex].value;

                document.getElementById('lebel-routine-T').innerHTML = "Class routine for the Teacher " + st + " (" + DBFINAL + ")";
//        alert(st);
            } catch (e) {
                alert("Found error at the begining");
            }

            for (d = 0; d < 5; d++)
            {
                k = 0;

                studentTable.insertRow();
                var studentRows = studentTable.getElementsByTagName('tr');
                var newStuROw = studentRows[studentRows.length - 1];

//        alert("New row added");


                newStuROw.insertCell(k++).innerHTML = days[d];

                for (x = d * 8; x <= (d * 8) + 7; x++) {

                    ParamMaster(st, "teacher");

                    if (hours > 1) {
                        newStuROw.insertCell(k++).innerHTML = "<td colspan='" + hours + "'><div class='routine-data'>" + one + "<br>" + two + "<br>" + three + "</div></td>";
                        x += hours - 1;
                        newStuROw.cells[k - 1].colSpan = "" + hours + "";

                    } else {
                        newStuROw.insertCell(k++).innerHTML = "<td colspan='1'><div class='routine-data'>" + one + "<br>" + two + "<br>" + three + "</div></td>";
                        newStuROw.cells[k - 1].colSpan = "1";
                    }
                }

            }
        }
    }else{
        document.getElementById("t").hidden = true;
    }
}


function onlyClassrooms() {

//    alert("Students working");

    if (document.getElementById('only-classrooms').checked) {

        if (DBFINAL == "select") {
            alert("Please select a routine first");
        } else {
            
            $("#routine-C").find("tr:not(:first)").remove();

            try {
                document.getElementById("c").hidden = false;

                studentTable = document.getElementById("routine-C");

                var selector = document.getElementById("classrooms");
                var st = selector.options[selector.selectedIndex].value;

                document.getElementById('lebel-routine-C').innerHTML = "Class routine of classroom " + st + " (" + DBFINAL + ")";
//        alert(st);
            } catch (e) {
                alert("Found error at the begining");
            }

            for (d = 0; d < 5; d++)
            {
                k = 0;

                studentTable.insertRow();
                var studentRows = studentTable.getElementsByTagName('tr');
                var newStuROw = studentRows[studentRows.length - 1];

//        alert("New row added");


                newStuROw.insertCell(k++).innerHTML = days[d];

                for (x = d * 8; x <= (d * 8) + 7; x++) {

                    ParamMaster(st, "classroom");

                    if (hours > 1) {
                        newStuROw.insertCell(k++).innerHTML = "<td colspan='" + hours + "'><div class='routine-data'>" + one + "<br>" + two + "<br>" + three + "</div></td>";
                        x += hours - 1;
                        newStuROw.cells[k - 1].colSpan = "" + hours + "";

                    } else {
                        newStuROw.insertCell(k++).innerHTML = "<td><div class='routine-data'>" + one + "<br>" + two + "<br>" + three + "</div></td>";
                    }
                }

            }
        }
    }else{
        document.getElementById("c").hidden = true;
    }
}



function ParamMaster(para, index) {

//    alert(B[i] + "   " + A[x]);

//alert("param master wotking " + para +"  "+ index+"  "+A[x]);

    $.ajax({
        async: false,
        type: 'POST',
        url: 'RoutineContext',
        data: "param=" + para + "&time=" + A[x] + "&index=" + index,
        success: function (data, status, jqXHR) {
            one = jqXHR.getResponseHeader('one');
            two = jqXHR.getResponseHeader('two');
            three = jqXHR.getResponseHeader('three');
            hours = jqXHR.getResponseHeader('hours');
//            alert("Successful");
        },

        Error: function () {
            alert("Error occurred while calling RoutineMaster");
        }
    });

}