var movingObjectName;
var movingObjectType;
var movingObjectTypeID = -1;
var numberOfRows = 0;

var classTime, classTimeFinal = "",classTimePrevious = "";
var teachers, teachersFinal = "",teachersPrevious = "";
var classrooms, classroomsFinal = "",classroomsPrevious = "";
var students, studentsFinal = "",studentsPrevious = "";
var courses, coursesFinal = "",coursesPrevious = "";
var departments, departmentsFinal = "",departmentsPrevious = "";

var currentRoutineStatusTable = document.getElementById("current-routine-status");

var rows;
var lastRow;
var cell;


var pelletsTable,pelletsRows,pelletsLastRow,pelletsCell;

var cellId;
var fields;

var possibility, validationMessage;
var possible_hours, gridNames,gridNamesPrevious,gridNamesFinal, gridNames_local;
var eClasstime, eTeachers, eClassrooms, eStudents, eCourses;
var er;
var Xp, Yp;

var routines;
var codes;
var nor;

var DATA = ["", "", "", "", ""];

function dragStart(event) {
    event.dataTransfer.setData("Text", event.target.id);

    movingObjectName = event.currentTarget.id;
    movingObjectType = event.currentTarget.className;

    switch (movingObjectType) {
        case "teachers":
            teachers = movingObjectName;
            movingObjectTypeID = 1;
            break;
        case "classrooms":
            classrooms = movingObjectName;
            movingObjectTypeID = 2;
            break;

        case "students":
            students = movingObjectName;
            movingObjectTypeID = 3;
            break;
        case "courses":
            courses = movingObjectName;
            movingObjectTypeID = 4;
            break;
    }

//    document.getElementById("demo").innerHTML = "moving object type: " + movingObjectType + " | Moving object name:" + movingObjectName;
}



function dragEnter(event) {
    
    if (!document.getElementById("new-move").disabled) {
//        document.getElementById("demo").innerHTML = "New move not confirmed!";
        return;
    }
    
    try {
        rows = currentRoutineStatusTable.getElementsByTagName('tr');
        lastRow = rows[rows.length - 1];
    } catch (err) {
        alert(err.message);
    }

    if (event.target.className === "grid") {

//        alert(movingObjectTypeID);

        //event.target.style.background = "red";
        //alert("HI");
        classTime = event.target.id;

        try {
        } catch (e) {
            alert(e.message);
        }
        
        Xp = event.pageX;
        Yp = event.pageY;
        $("#msg-container").css({top: Yp-100, left: Xp+80, position:'absolute'});


//        document.getElementById("demo").innerHTML = movingObjectType + " " + movingObjectName + " is on " + classTime;
        
        var i;

        for (i = 1; (i <= 4) && numberOfRows; i++) {
            if (i === movingObjectTypeID) {
                //alert("Skipping for: " + movingObjectTypeID);
                continue;
            }

            cell = lastRow.cells[i];

            DATA[i] = DATA[i] + cell.innerText;
        }


        if (movingObjectTypeID !== 1) {
            teachers = DATA[1];
        }

        if (movingObjectTypeID !== 2) {

            classrooms = DATA[2];

        }

        if (movingObjectTypeID !== 3) {
            students = DATA[3];
        }


        if (movingObjectTypeID !== 4) {

            courses = DATA[4];
        }


        $.ajax({
            type: 'POST',
            url: 'Validation',
            data: "classTime=" + classTime + "&teachers=" + teachers + "&classrooms=" + classrooms + "&students=" + students + "&courses=" + courses + "&type=" + movingObjectType + "&departmentsFinal=" + departmentsFinal,

            success: function (data, status, jqXHR) {
                
                document.getElementById('validation').innerText = jqXHR.getResponseHeader('msg');
                
                
                
                possibility = jqXHR.getResponseHeader('pos');
                validationMessage = jqXHR.getResponseHeader('msg');
                gridNames = jqXHR.getResponseHeader('gridNames');
                departments = jqXHR.getResponseHeader('dept');
                
                gridNames_local = gridNames.split(',');
                
                possible_hours = jqXHR.getResponseHeader('possible_hours');
                
                
                if(possibility == 0){
                    document.getElementById('msg-container').style.visibility = "visible";
                }

                //document.getElementById("demo").innerHTML = "gridNames: " + gridNames + " | possible_hours: " + possible_hours+ " | possibility: " + possibility;

                
                var x;
                
                if (possibility == 1) {
                    for(x = 0; x < possible_hours; x++){
                        //alert(gridNames_local[x]);
                        document.getElementById(gridNames_local[x]).style.backgroundColor = "green";
                    }
                } else {
                    for (x = 0; x < possible_hours; x++) {
                        document.getElementById(gridNames_local[x]).style.backgroundColor = "red";
                    }
                }

                DATA[0] = "";
                DATA[1] = "";
                DATA[2] = "";
                DATA[3] = "";
                DATA[4] = "";
            },

            Error: function () {
                alert("error occured while dragged into the grid");
            }
        });

    }
}

function dragLeave(event) {
    var x;
//    var gridNames_local = gridNames.split(',');
    
    if (event.target.className === "grid") {
        //event.target.style.background = "#ccffff";
        
        for (x = 0; x < possible_hours; x++) {
            document.getElementById(gridNames_local[x]).style.backgroundColor = "#ccffff";
        }
        
        document.getElementById('msg-container').style.visibility = "hidden";
           
//        document.getElementById('validation').innerHTML = "";
//        document.getElementById('demo').innerHTML = "";
    }
}

function allowDrop(event) {
    event.preventDefault();
}

/***********/
function insert(type, value) {
    currentRoutineStatusTable = document.getElementById('current-routine-status');

    rows = currentRoutineStatusTable.getElementsByTagName('tr');
    lastRow = rows[rows.length - 1];


    cell = lastRow.cells[0];
    cell.innerHTML = classTime;

    switch (type) {
        case "teachers":
            cellId = lastRow.cells.length - 6;
            break;
        case "classrooms":
            cellId = lastRow.cells.length - 5;
            break;

        case "students":
            cellId = lastRow.cells.length - 4;
            break;
        default:
            cellId = lastRow.cells.length - 3;
            break;
    }

    cell = lastRow.cells[cellId];
    cell.innerHTML = value;
    
    if (departments !== "CSE") {
        
        document.getElementById('demo').innerHTML = departments;
        
        if (type === "teachers") {
            
            lastRow.cells[2].innerHTML = "";
            lastRow.cells[4].innerHTML = "";
            classrooms = "";
            courses = "";
            
        } else if (type === "classrooms") {
            
            lastRow.cells[1].innerHTML = "";
            lastRow.cells[4].innerHTML = "";
            
            teachers = "";
            courses = "";
            
        } else if (type === "courses") {
            
            lastRow.cells[1].innerHTML = departments;
            lastRow.cells[2].innerHTML = departments;
            
            teachers = departments;
            classrooms = departments;
        }
    }else{
        if((departmentsFinal !== "CSE") && type === "courses"){
            lastRow.cells[1].innerHTML = "";
            lastRow.cells[2].innerHTML = "";
            teachers = "";
            classrooms = "";
        }
    }
    
    classTimePrevious = classTimeFinal;
    teachersPrevious = teachersFinal;
    classroomsPrevious = classroomsFinal;
    studentsPrevious = studentsFinal;
    coursesPrevious = coursesFinal;
    gridNamesPrevious = gridNamesFinal;
    departmentsPrevious = departmentsFinal;
    
    classTimeFinal = classTime;
    teachersFinal = teachers;
    classroomsFinal = classrooms;
    studentsFinal = students;
    coursesFinal = courses;
    gridNamesFinal = gridNames;
    departmentsFinal = departments;
}

function drop(event) {
    event.preventDefault();
//    event.target.style.background = "#ccffff";
    
    document.getElementById('msg-container').style.visibility = "hidden";

    for (x = 0; x < possible_hours; x++) {
        document.getElementById(gridNames_local[x]).style.backgroundColor = "#ccffff";
    }
    
    if (!document.getElementById("new-move").disabled) {
        alert("Please confirm new move first!");
        return;
    }

    if (possibility == 1) {
//        document.getElementById("demo").innerHTML = movingObjectType + " " + movingObjectName + " Dropped on " + classTime;
        insert(movingObjectType, movingObjectName);
    } else {
        alert(validationMessage);
    }
}



function new_move() {
    currentRoutineStatusTable = document.getElementById('current-routine-status');
    var newRow = currentRoutineStatusTable.insertRow();

    newRow.innerHTML = "<tr><td></td><td></td><td></td><td></td><td></td><td><button onclick='UNDO()' class='btn'><i class='fa fa-undo'></i></button></td><td><button onclick='acceptRecords()' class='btn'><i class='fa fa-check'></i></button></td></tr>";

    numberOfRows++;

    document.getElementById("new-move").disabled = true;
    document.getElementById("Clear-Recent-Moves").disabled = false;    
//    document.getElementById("accept").disabled = false;
    
    document.getElementById("new-move").style.background = '#cccccc';
    document.getElementById("Clear-Recent-Moves").style.background = '#ccff66';
}

function clear_move() {

    currentRoutineStatusTable = document.getElementById("current-routine-status");
    var rowCount = currentRoutineStatusTable.rows.length;
    currentRoutineStatusTable.deleteRow(rowCount - 1);
    
    numberOfRows--;
    
    document.getElementById("new-move").disabled = false;
    document.getElementById("Clear-Recent-Moves").disabled = true;
//    document.getElementById("accept").disabled = true;
    document.getElementById("new-move").style.background = '#ccff66';
    document.getElementById("Clear-Recent-Moves").style.background = '#cccccc';
    
}

function acceptRecords() {

    rows = currentRoutineStatusTable.getElementsByTagName('tr');
    lastRow = rows[rows.length - 1];
    cellId;

    var i;

    fields = ["Time", "Teacher", "Classroom", "Student Batch", "Course"];

    for (i = 1; i <= 4; i++) {
        if (lastRow.cells[i].innerText === "") {
            alert("Please assign " + fields[i] + "!");
            return;
        }
    }
    
//    teachers = lastRow.cells[1].innerText;
//    classrooms = lastRow.cells[2].innerText;
//    students = lastRow.cells[3].innerText;
//    courses = lastRow.cells[4].innerText;
    
    $.ajax({
        type: 'POST',
        url: 'BookingMaster',
        data: "classTime=" + gridNamesFinal + "&teachers=" + teachersFinal + "&classrooms=" + classroomsFinal + "&students=" + studentsFinal + "&courses=" + coursesFinal+"",

        success: function () {
            cell = lastRow.cells[5];
            cell.innerHTML = "<button class='btn' onclick='EDIT(event)'><i class='fa fa-pencil-square-o'></i></button>";
            
            cell = lastRow.cells[6];
            cell.innerHTML = "<button class='btn' onclick='DELETE(event)'><i class='fa fa-trash'></i></button>";            
        },

        Error: function () {
            alert("error occured while inserting into database!");
        }
    });
    
    document.getElementById("new-move").disabled = false;
    document.getElementById("Clear-Recent-Moves").disabled = true;
//    document.getElementById("accept").disabled = true;
    document.getElementById("new-move").style.background = '#ccff66';
    document.getElementById("Clear-Recent-Moves").style.background = '#cccccc';
//    document.getElementById("demo").innerHTML = "Accepted: " + gridNames + "|"+ teachers + "|"+ students + "|"+ classrooms + "|"+ courses+ "|"+possible_hours;
}


function remover(){
    if (confirm("Cancel All Classes?")) {
        $("#TEACHERS").find("tr:not(:first)").remove();
        $("#CLASSROOMS").find("tr:not(:first)").remove();
        $("#STUDENTS").find("tr:not(:first)").remove();
        $("#COURSES").find("tr:not(:first)").remove();
        $("#choose-student").find("option:not(:first)").remove();
        $("#choose-classroom").find("option:not(:first)").remove();
        $("#choose-teacher").find("option:not(:first)").remove();
        $('#edit-teachers').empty();
        $('#edit-classrooms').empty();
        $('#edit-students').empty();
        $('#edit-courses').empty();
        $("#current-routine-status").find("tr:not(:first)").remove();
        
        $.ajax({
            type: 'POST',
            url: 'Remover',

            success: function () {
//                document.getElementById("demo").innerHTML = "All classes are canceled";
                
                loader();
            },

            Error: function () {
                alert("Error occurred while cancelling the classes!");
            }
        });
    } else {
//        document.getElementById("demo").innerHTML = "Classes are not canclled";
    }
}


function LoadRow(){
    currentRoutineStatusTable = document.getElementById('current-routine-status');
    var newRow = currentRoutineStatusTable.insertRow();

    newRow.innerHTML = "<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>";

    numberOfRows++;
}

function loadInsert(){
    currentRoutineStatusTable = document.getElementById('current-routine-status');
    rows = currentRoutineStatusTable.getElementsByTagName('tr');
    lastRow = rows[rows.length - 1];
    
    cell = lastRow.cells[0];
    cell.innerHTML = classTime;
    
    cell = lastRow.cells[1];
    cell.innerHTML = teachers;
    
    cell = lastRow.cells[2];
    cell.innerHTML = classrooms;
    
    cell = lastRow.cells[3];
    cell.innerHTML = students;
    
    cell = lastRow.cells[4];
    cell.innerHTML = courses;
    
    cell = lastRow.cells[5];
    cell.innerHTML = "<button class='btn' onclick='EDIT(event)' ><i class='fa fa-pencil-square-o'></i></button>";
    
    cell = lastRow.cells[6];
    cell.innerHTML = "<button class='btn' onclick='DELETE(event)'><i class='fa fa-trash'></i></button>";
}

function pelletLoadRow(tableID,type,value) {
    var table = document.getElementById(tableID);
    var newRow = table.insertRow();
    newRow.innerHTML = "<tr><td><div class='"+type+"' id='"+value+"' draggable='true' ondragstart='dragStart(event)'>"+value+"</div></td></tr>";
    
    var opt = document.createElement('option');
    var selector;
    
    opt.appendChild(document.createTextNode(value));
    opt.value = value;
    
    switch (type) {
        case "teachers":
        {
            selector = document.getElementById('choose-teacher');
            selector.appendChild(opt);
            break;
        }

        case "students":
        {
            selector = document.getElementById('choose-student');
            selector.appendChild(opt);
            break;
        }
        case "classrooms":
        {
            selector = document.getElementById('choose-classroom');
            selector.appendChild(opt);
            break;
        }
    }
    
    var optE = document.createElement('option');
    var selectorE;
    
    optE.appendChild(document.createTextNode(value));
    optE.value = value;
    
    switch (type) {
        case "teachers":
        {
            selectorE = document.getElementById('edit-teachers');
            selectorE.appendChild(optE);
            break;
        }

        case "students":
        {
            selectorE = document.getElementById('edit-students');
            selectorE.appendChild(optE);
            break;
        }
        case "classrooms":
        {
            selectorE = document.getElementById('edit-classrooms');
            selectorE.appendChild(optE);
            break;
        }
        case "courses":
        {
            selectorE = document.getElementById('edit-courses');
            selectorE.appendChild(optE);
            break;
        }
    }
    
}

function pellets_T_Loader(T,nt){
    var arr = T.split(',');
    var i;
    
    for(i = 0; i < nt; i++){
        pelletLoadRow("TEACHERS","teachers", arr[i]);
    }
}

function pellets_S_Loader(S,ns){
    var arr = S.split(',');
    var i;
    
    for(i = 0; i < ns; i++){
        pelletLoadRow("STUDENTS","students", arr[i]);
    }
}

function pellets_C_Loader(C,nc){
    var arr = C.split(',');
    var i;
    
    for(i = 0; i < nc; i++){
        pelletLoadRow("CLASSROOMS","classrooms", arr[i]);
    }
}

function pellets_CO_Loader(CO,nco){
    var arr = CO.split(',');
    var i;
    
    for(i = 0; i < nco; i++){
        pelletLoadRow("COURSES","courses", arr[i]);
    }
}


function pelletsLoader() {
    var T, S, C, CO, nt, ns, nc, nco;
    
    $.ajax({
        type: 'POST',
        url: 'PelletsLoader',

        success: function (data, status, jqXHR) {
            T = jqXHR.getResponseHeader('T');
            S = jqXHR.getResponseHeader('S');
            C = jqXHR.getResponseHeader('C');
            CO = jqXHR.getResponseHeader('CO');
            
            nt = jqXHR.getResponseHeader('nt');
            ns = jqXHR.getResponseHeader('ns');
            nc = jqXHR.getResponseHeader('nc');
            nco = jqXHR.getResponseHeader('nco');
            
            pellets_T_Loader(T,nt);
            pellets_S_Loader(S,ns);
            pellets_C_Loader(C,nc);
            pellets_CO_Loader(CO,nco);
        },

        Error: function () {
            alert("Error occurred while cancelling the classes!");
        }
    });
}

function deptLoader() {
    $.ajax({
        type: 'POST',
        url: 'DeptLoader',

        success: function (data, status, jqXHR) {
//                document.getElementById("demo").innerHTML = "Welcome to Routine Designer!";
            var D = jqXHR.getResponseHeader('D');
            var nod = jqXHR.getResponseHeader('nod');
            
//            alert(D +"  "+nod);
            
            var i, j = 0;

            var result = D.split(',');
            
//            alert(result.length);
            var selC;
            var selT;

            selT = document.getElementById('edit-teachers');
            selC = document.getElementById('edit-classrooms');
            
            
            for (i = 1; i <= nod; i++) {
                var o = document.createElement('option');
                o.appendChild(document.createTextNode(result[j]));              
                o.value = result[j++];
                
                try {
                    selC.appendChild(o);
                    selT.appendChild(o);
                    
                } catch (e) {
                    alert("Problem");
                }
            }
            
            j = 0;
            
            for (i = 1; i <= nod; i++) {
                var o = document.createElement('option');
                o.appendChild(document.createTextNode(result[j]));
                o.value = result[j++];

                try {
                    selC.appendChild(o);
                } catch (e) {
                    alert("Problem");
                }
            }
        },

        Error: function () {
            alert("Error occurred while loading departments!");
        }
    });
}


function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "Not Set";
}

function setCookie(cname, cvalue) {
  
  document.cookie = cname + "=" + cvalue + ";path=/";
}


function preLoader() {

//    alert(getCookie("db_code"));

    $.ajax({
        type: 'POST',
        url: 'Loader',

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

    if (getCookie("db_code") == "Not Set") {
        document.getElementById('nodata-left').hidden = false;
        document.getElementById('nodata-Right').hidden = false;
        document.getElementById('LEFT').hidden = true;
        document.getElementById('RIGHT').hidden = true;
    } else {
        loader();
    }
}

function loader(){
    $("#TEACHERS").find("tr:not(:first)").remove();
    $("#CLASSROOMS").find("tr:not(:first)").remove();
    $("#STUDENTS").find("tr:not(:first)").remove();
    $("#COURSES").find("tr:not(:first)").remove();
    $("#choose-student").find("option:not(:first)").remove();
    $("#choose-classroom").find("option:not(:first)").remove();
    $("#choose-teacher").find("option:not(:first)").remove();
    $('#edit-teachers').empty();
    $('#edit-classrooms').empty();
    $('#edit-students').empty();
    $('#edit-courses').empty();
    $("#current-routine-status").find("tr:not(:first)").remove();
    
    document.getElementById('nodata-left').hidden = true;
    document.getElementById('nodata-Right').hidden = true;
    document.getElementById('LEFT').hidden = false;
    document.getElementById('RIGHT').hidden = false;
    
    $.ajax({
            type: 'POST',
            url: 'Loader',

            success: function (data, status, jqXHR) {
//                document.getElementById("demo").innerHTML = "Welcome to Routine Designer!";
                var status = jqXHR.getResponseHeader('status');
                var noc = jqXHR.getResponseHeader('noc');
//                alert(jqXHR.getResponseHeader('routines'));
//                routines = jqXHR.getResponseHeader('routines');
//                codes = jqXHR.getResponseHeader('codes');
//                nor = jqXHR.getResponseHeader('nor');
                
                var i,j = 0;
                
                var result = status.split(',');
                
                for (i = 1; i <= noc; i++){
                    classTime = result[j++];
                    teachers = result[j++];
                    classrooms = result[j++];
                    students = result[j++];
                    courses = result[j++];
                    LoadRow();
                    loadInsert();
                }
                //alert("calling routines");
//                routinesLoader();
            },

            Error: function () {
                alert("Error occurred while cancelling the classes!");
            }
        });
        
        pelletsLoader();
        deptLoader();
}

function db_codeManager() {
    
    document.getElementById("new-move").disabled = false;
    document.getElementById("Clear-Recent-Moves").disabled = true;
//    document.getElementById("accept").disabled = true;
    document.getElementById("new-move").style.background = '#ccff66';
    document.getElementById("Clear-Recent-Moves").style.background = '#cccccc';
    
    var selector = document.getElementById("db_codes");
    var code = selector.options[selector.selectedIndex].value;
    
    

    if (code == "select") {
        alert("Please select a routine to create");
        return;
    } else {
        $.ajax({
            type: 'POST',
            url: 'RoutinesManager',
            data: "db_code=" + code,
            success: function () {
                setCookie("db_code", code);
//                alert("New value of cookie: " + getCookie("db_code"));
                loader();
            },

            Error: function () {
                alert("Error occurred while setting routine name!");
            }
        });
    }
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


function printRoutine() {
    $.ajax({
        type: 'POST',
        url: 'Printer',

        success: function () {
//            document.getElementById("demo").innerHTML = "Routine Printed!";
        },

        Error: function () {
            alert("Error occurred while cancelling the classes!");
        }
    });
}

function chooser() {
    var selectBox = document.getElementById("choose");
    var selectedValue = selectBox.options[selectBox.selectedIndex].value;

    switch (selectedValue) {
        case "print-by":
            document.getElementById("choose-classroom").hidden = true;
            document.getElementById("choose-student").hidden = true;
            document.getElementById("choose-teacher").hidden = true;
            document.getElementById("print-by-parameter").style.display = "none";
            break;
        case "teacher":
            document.getElementById("choose-classroom").hidden = true;
            document.getElementById("choose-student").hidden = true;
            document.getElementById("choose-teacher").hidden = false;
            document.getElementById("print-by-parameter").style.display = "block";
            break;
        case "classroom":
            document.getElementById("choose-classroom").hidden = false;
            document.getElementById("choose-student").hidden = true;
            document.getElementById("choose-teacher").hidden = true;
            document.getElementById("print-by-parameter").style.display = "block";
            break;
        case "student":
            document.getElementById("choose-classroom").hidden = true;
            document.getElementById("choose-student").hidden = false;
            document.getElementById("choose-teacher").hidden = true;
            document.getElementById("print-by-parameter").style.display = "block";
            break;
    }
}


function printByParameter(){
    var selectBox = document.getElementById("choose");
    var sT = document.getElementById("choose-teacher");
    var sS = document.getElementById("choose-student");
    var sC = document.getElementById("choose-classroom");
    
    var printBy = selectBox.options[selectBox.selectedIndex].value;
    var param;
    
    
    switch (printBy) {
        case "teacher":
            param = sT.options[sT.selectedIndex].value;
            break;
        case "classroom":
            param = sC.options[sC.selectedIndex].value;
            break;
        case "student":
            param = sS.options[sS.selectedIndex].value;
            break;
    }
    
//    alert(param + " " + printBy);
    
    if (param === "select") {
        alert("Please select " + printBy);
    } else {
        $.ajax({
            type: 'POST',
            url: 'PrinterByParameter',
            data: "printBy=" + printBy + "&param=" + param,
            success: function () {
//                document.getElementById("demo").innerHTML = "Routine Printed!";
            },

            Error: function () {
                alert("Error occurred while printing with parameter!");
            }
        });
    }
}

function EDIT(event){
    er = event.currentTarget.closest('tr');
    
    eClasstime = er.cells[0].innerHTML;
    eTeachers = er.cells[1].innerHTML;
    eClassrooms = er.cells[2].innerHTML;
    eStudents = er.cells[3].innerHTML;
    eCourses = er.cells[4].innerHTML;

    try {
        $(er.cells[0]).html("<select id='eCLASSTIME'>" + document.getElementById('edit-classtime').innerHTML + "</select>");
        $(er.cells[1]).html("<select id='eTEACHERS'>" + document.getElementById('edit-teachers').innerHTML + "</select>");
        $(er.cells[2]).html("<select id='eCLASSROOMS'>" + document.getElementById('edit-classrooms').innerHTML + "</select>");
        $(er.cells[3]).html("<select id='eSTUDENTS'>" + document.getElementById('edit-students').innerHTML + "</select>");
        $(er.cells[4]).html("<select id='eCOURSES'>" + document.getElementById('edit-courses').innerHTML + "</select>");
        $(er.cells[5]).html("<button id='cancel' onclick='cancelEdit()'><i class='fa fa-window-close'></i></button>");
        $(er.cells[6]).html("<button id='confirm' onclick='confirmEdit()'><i class='fa fa-check'></i></button>");
        
        $('#eCLASSTIME').val(eClasstime);
        $('#eTEACHERS').val(eTeachers);
        $('#eCLASSROOMS').val(eClassrooms);
        $('#eSTUDENTS').val(eStudents);
        $('#eCOURSES').val(eCourses);

        var x = document.getElementsByClassName('btn');
        
        //alert(x.length);
        var i;
        
        for(i = 0; i < x.length; i++){
            x[i].disabled = true;
        }
        
    } catch (e) {
        alert("Editing is not possible");
    }
}

function DELETE(event) {
//    var x = document.getElementsByClassName('btn');
//    var i;
//
//    for (i = 0; i < x.length; i++) {
//        x[i].disabled = false;
//        x[i].style.backgroundColor = "black";
//    }
}

function cancelEdit(){
    var x = document.getElementsByClassName('btn');
//    alert(x.length);
    var i;

    for (i = 0; i < x.length; i++) {
        try {
            x[i].disabled = false;
        } catch (e) {
            alert("Problem with " + i);
        }

    }    
    
    $(er.cells[0]).html(eClasstime);
    $(er.cells[1]).html(eTeachers);
    $(er.cells[2]).html(eClassrooms);
    $(er.cells[3]).html(eStudents);
    $(er.cells[4]).html(eCourses);
    $(er.cells[5]).html("<button class='btn' onclick='EDIT(event)' ><i class='fa fa-pencil-square-o'></i></button>");
    $(er.cells[6]).html("<button class='btn' onclick='DELETE(event)'><i class='fa fa-trash'></i></button>");    
}


function confirmEdit() {
    var previous = eClasstime + "," + eTeachers + "," + eClassrooms + "," + eStudents + "," + eCourses;

    var select = document.getElementById("eCLASSTIME");
    var now = select.options[select.selectedIndex].value + ",";


    var select = document.getElementById("eTEACHERS");
    now += select.options[select.selectedIndex].value + ",";


    var select = document.getElementById("eCLASSROOMS");
    now += select.options[select.selectedIndex].value + ",";

    var select = document.getElementById("eSTUDENTS");
    now += select.options[select.selectedIndex].value + ",";

    var select = document.getElementById("eCOURSES");
    now += select.options[select.selectedIndex].value;

//    alert(previous + "\n" + now);

    if (previous === now) {
        alert("No change in values detected!");
    } else {

        $.ajax({
            type: 'POST',
            url: 'EditValidation',
            data: "previous=" + previous + "&now=" + now,
            success: function (data, status, jqXHR) {
//                alert("one");

                var possi = jqXHR.getResponseHeader('pos');
                var vali = jqXHR.getResponseHeader('msg');
                
//                alert("two");

                if (possi === "1") {
                    
//                    alert("three");
                    
                    try {
                        var A = now.split(',');
                    } catch (e) {
                        alert("Problem with splitting");
                    }

                    try {
//                        alert("A");
                        $(er.cells[0]).html(A[0]);
                        $(er.cells[1]).html(A[1]);
                        $(er.cells[2]).html(A[2]);
                        $(er.cells[3]).html(A[3]);
                        $(er.cells[4]).html(A[4]);
                        $(er.cells[5]).html("<button class='btn' onclick='EDIT(event)' ><i class='fa fa-pencil-square-o'></i></button>");
                        $(er.cells[6]).html("<button class='btn' onclick='DELETE(event)'><i class='fa fa-trash'></i></button>");
                    } catch (e) {
                        alert("Problem with cell changing");
                    }



                    try {
                        var x = document.getElementsByClassName('btn');
                    } catch (e) {
                        alert("Problem with btn selection");
                    }

                    //    alert(x.length);
                    var i;

                    for (i = 0; i < x.length; i++) {
                        try {
                            x[i].disabled = false;
                        } catch (e) {
                            alert("Problem with " + i);
                        }
                    }

//                    alert(vali + "\nThe class was edited\n" + possi);
                } else {
                    alert(vali + "\nScheduled class could not be edited\n" + possi);
                }
            },

            Error: function () {
                alert("Something wrong happened during edit operation. That's all we know!");
            }
        });
    }
}

function UNDO(){
    currentRoutineStatusTable = document.getElementById('current-routine-status');
    rows = currentRoutineStatusTable.getElementsByTagName('tr');
    lastRow = rows[rows.length - 1];
    
    cell = lastRow.cells[0];
    cell.innerHTML = classTimePrevious;
    
    cell = lastRow.cells[1];
    cell.innerHTML = teachersPrevious;
    
    cell = lastRow.cells[2];
    cell.innerHTML = classroomsPrevious;
    
    cell = lastRow.cells[3];
    cell.innerHTML = studentsPrevious;
    
    cell = lastRow.cells[4];
    cell.innerHTML = coursesPrevious;
    
    classTimeFinal = classTimePrevious;
    teachersFinal = teachersPrevious;
    classroomsFinal = classroomsPrevious;
    studentsFinal = studentsPrevious;
    coursesFinal = coursesPrevious;
    gridNamesFinal = gridNamesPrevious;
    departmentsFinal = departmentsPrevious;
}

function openNav() {
  document.getElementById("mySidenav").style.width = "250px";
}

function closeNav() {
  document.getElementById("mySidenav").style.width = "0";
}