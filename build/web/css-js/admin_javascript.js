function radio_teachers(){
    document.getElementById("T").style.display = 'block';
    document.getElementById("C").style.display = 'none';
    document.getElementById("S").style.display = 'none';
    document.getElementById("CO").style.display = 'none';
    document.getElementById("D").style.display = 'none';
    document.getElementById("R").style.display = 'none';
}

function radio_routines(){
    document.getElementById("T").style.display = 'none';
    document.getElementById("C").style.display = 'none';
    document.getElementById("S").style.display = 'none';
    document.getElementById("CO").style.display = 'none';
    document.getElementById("D").style.display = 'none';
    document.getElementById("R").style.display = 'block';
}

function radio_departments(){
    document.getElementById("T").style.display = 'none';
    document.getElementById("C").style.display = 'none';
    document.getElementById("S").style.display = 'none';
    document.getElementById("CO").style.display = 'none';
    document.getElementById("D").style.display = 'block';
    document.getElementById("R").style.display = 'none';
}

function radio_classrooms(){
    document.getElementById("T").style.display = 'none';
    document.getElementById("C").style.display = 'block';
    document.getElementById("S").style.display = 'none';
    document.getElementById("CO").style.display = 'none';
    document.getElementById("D").style.display = 'none';
    document.getElementById("R").style.display = 'none';
}

function radio_students(){
    document.getElementById("T").style.display = 'none';
    document.getElementById("C").style.display = 'none';
    document.getElementById("S").style.display = 'block';
    document.getElementById("CO").style.display = 'none';
    document.getElementById("D").style.display = 'none';
    document.getElementById("R").style.display = 'none';
}

function radio_courses(){
    document.getElementById("T").style.display = 'none';
    document.getElementById("C").style.display = 'none';
    document.getElementById("S").style.display = 'none';
    document.getElementById("CO").style.display = 'block';
    document.getElementById("D").style.display = 'none';
    document.getElementById("R").style.display = 'none';
}




function pelletLoadRow(tableID,type,value) {
    var table = document.getElementById(tableID);
    var newRow = table.insertRow();
    
    newRow.innerHTML = "<tr><td><div class='"+type+"' id='"+value+"'>"+value+"</div></td></tr>";
    
    
    if (tableID != 'DEPARTMENTS') {
        var table = document.getElementById("routine-" + tableID);
        var newRow = table.insertRow();
    }

    
    if(tableID == "TEACHERS"){
        newRow.innerHTML = "<tr><td class='routine-td'>"+value+"</td><td class='routine-td'><input type='checkbox' class='routine-check-teachers' id='tea-check-"+value+"' onclick='uncheckTeachersMain(event)'/></td></tr>";
    }
    
    
    if(tableID == "CLASSROOMS"){
        newRow.innerHTML = "<tr><td class='routine-td'>"+value+"</td><td class='routine-td'><input type='checkbox' class='routine-check-classrooms' id='cla-check-"+value+"' onclick='uncheckClassroomsMain(event)'/></td></tr>";
    }
    
    if(tableID == "STUDENTS"){
        newRow.innerHTML = "<tr><td class='routine-td'>"+value+"</td><td class='routine-td'><input type='number' id='count-"+value+"' placeholder='#students' maxlength='4'></td><td class='routine-td'><input type='checkbox' class='routine-check-students' id='stu-check-"+value+"' onclick='uncheckStudentsMain(event)'/></td></tr>";
    }
    
    if(tableID == "COURSES"){
        newRow.innerHTML = "<tr><td class='routine-td'>"+value+"</td><td class='routine-td'>Plz assign teacher</td><td class='routine-td'><input type='checkbox' class='routine-check-courses' id='cou-check-"+value+"' onclick='assignTeacher(event)'/></td></tr>";
    }
    
    if (type == "teachers") {
        var selector;
        var opt = document.createElement('option');

        selector = document.getElementById('course-teacher');

        opt.appendChild(document.createTextNode(value));
        opt.value = value;
        selector.appendChild(opt);
    }
    
    if (type == "departments") {
        var selector;
        var opt = document.createElement('option');

        selector = document.getElementById('course-department');

        opt.appendChild(document.createTextNode(value));
        opt.value = value;
        selector.appendChild(opt);
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
        url: 'PelletsLoaderAdmin',

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
            deptLoader();
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
            
            var i = 0;
//
            var result = D.split(',');
//            
////            alert(result.length);
//            var selD;
//
//            selD = document.getElementById('course-department');            
//            
            for (i = 0; i < nod; i++) {
                pelletLoadRow("DEPARTMENTS","departments",result[i]);
//                var o = document.createElement('option');
//                o.appendChild(document.createTextNode(result[j]));              
//                o.value = result[j++];
//                
//                try {
//                    selD.appendChild(o);
//                } catch (e) {
//                    alert("Problem");
//                }
            }
        },

        Error: function () {
            alert("Error occurred while loading departments!");
        }
    });
}

function teacherAdmin() {
    var TN = document.getElementById("teacher-name").value;
    var TFN = document.getElementById("teacher-full-Name").value;
    var TD = document.getElementById("teacher-designation").value;
    var TE = document.getElementById("teacher-email").value;
    var TM = document.getElementById("teacher-mobile").value;
    
    
    //alert("Hey hey!");
    if (TN === "") {
        alert("Teacher name is empty!");
    }else if(TFN === ""){
        alert("Fullname is empty!");
    }else if(TD === ""){
        alert("Designation is empty!");
    }else if(TE === ""){
        alert("Email is empty!");
    }else if(TM === ""){
        alert("Mobile number is empty!");
    }else {
//        alert("Calling Teacher Admin");
        $.ajax({
            type: 'POST',
            url: 'TeacherAdmin',
            data: "name=" + TN + "&designation=" + TD+ "&email=" + TE+ "&fullName=" + TFN+ "&mobile=" + TM,
            success: function () {
                pelletLoadRow("TEACHERS", "teachers", TN);
            },

            Error: function () {
                alert("Error occurred while creating teacher!");
            }
        });
    }
}

function classroomAdmin() {
    var CN = document.getElementById("classroom-name").value;
    var CC = document.getElementById("classroom-capacity").value;

    if (CN === "") {
        alert("Please insert classroom name");
    } else if (CC === "") {
        alert("Please insert classroom capacity");
    } else {
        $.ajax({
            type: 'POST',
            url: 'ClassroomAdmin',
            data: "name=" +CN+"&cap="+CC,
            success: function () {
                pelletLoadRow("CLASSROOMS","classrooms", CN);
            },

            Error: function () {
                alert("Error occurred while creating Classroom!");
            }
        });
    }
}

function studentAdmin() {
    var session = document.getElementById("student-session").value;
//    var count = document.getElementById("student-count").value;
    
    var semester_selector = document.getElementById("student-semester");
    var semester = semester_selector.options[semester_selector.selectedIndex].value;
    
    var section_selector = document.getElementById("student-section");
    var section = section_selector.options[section_selector.selectedIndex].value;

    if (session === "") {
        alert("Please insert session");
    } else if (semester === "select") {
        alert("Please select semester");
    } else {
        
//        alert("Creating student");

        $.ajax({
            type: 'POST',
            url: 'StudentAdmin',
            data: "session=" + session + "&semester=" + semester + "&section=" + section,
            success: function (data, status, jqXHR) {
                
                var nam = jqXHR.getResponseHeader('nam');
                pelletLoadRow("STUDENTS", "students", nam);
                
            },

            Error: function () {
                alert("Error occurred while creating Students!");
            }
        });
    }
    
}


function courseTypeController(){
    var selector = document.getElementById("course-type");
    var type = selector.options[selector.selectedIndex].value;
    
    if(type === "Major"){
        document.getElementById("CD").hidden = true;
//        document.getElementById("COT").hidden = false;
    }else{
        document.getElementById("CD").hidden = false;
//        document.getElementById("COT").hidden = true;
    }
}

function courseAdmin() {
    var name = document.getElementById("course-name").value;
    var fullName = document.getElementById("course-fullname").value;

    var selector = document.getElementById("course-hours");
    var hour = selector.options[selector.selectedIndex].value;
    
    var selector = document.getElementById("course-credits");
    var credits = selector.options[selector.selectedIndex].value;
    
    var selector = document.getElementById("course-classes");
    var classPerWeek = selector.options[selector.selectedIndex].value;
    
    var selector = document.getElementById("course-modality");
    var modality = selector.options[selector.selectedIndex].value;
    

    selector = document.getElementById("course-type");
    var type = selector.options[selector.selectedIndex].value;

//    selector = document.getElementById("course-teacher");
//    var assignedTeacher = selector.options[selector.selectedIndex].value;

    var selector = document.getElementById("course-department");
    var department = selector.options[selector.selectedIndex].value;

    if (name === "") {
        alert("Please provide course code!");
        return;
    } else if (fullName === "") {
        alert("Please provide full name of the class!");
        return;
    } else if (credits === "select") {
        alert("Please provie number of credits!");
        return;
    } else if (hour === "select") {
        alert("Please select hours/class!");
        return;
    } else if (classPerWeek === "select") {
        alert("Please mention how many classes per week!");
        return;
    } else if (type === "select") {
        alert("Please select course type!");
        return;
    } else if (modality === "select") {
        alert("Please select course modality!");
        return;
    }

    if (type === "Non-Major") {
        if (department === "select") {
            alert("Please select department to offer non major course!");
            return;
        }
    } else {
        department = "CSE";
    }

    $.ajax({
        type: 'POST',
        url: 'CourseAdmin',
        data: "code=" + name + "&hour=" + hour + "&classPerWeek=" + classPerWeek + "&credits=" + credits + "&type=" + type + "&department=" + department + "&fullname=" + fullName + "&modality=" + modality,
        success: function () {
            pelletLoadRow("COURSES","courses", name);
        },

        Error: function () {
            alert("Error occurred while creating a course!");
        }
    });
}



function departmentAdmin(){
    var DN = document.getElementById("department-name").value;
    //alert("Hey hey!");
    if (DN === "") {
        alert("Department name is empty!");
    } else {
//        alert("Calling Teacher Admin");
        $.ajax({
            type: 'POST',
            url: 'DeptAdmin',
            data: "name=" + DN,
            success: function () {
                pelletLoadRow("DEPARTMENTS", "departments", DN);
            },

            Error: function () {
                alert("Error occurred while creating teacher!");
            }
        });
    }
}

function routineAdmin(){
    
//    alert("Routine Admin working");
    
    var TC = document.getElementById('routine-TEACHERS');
    var CR = document.getElementById('routine-CLASSROOMS');
    var ST = document.getElementById('routine-STUDENTS');
    var CO = document.getElementById('routine-COURSES');
    var from = document.getElementById("routine-from").value;
    var to = document.getElementById("routine-to").value;
    
    if(from == ""){
        alert("Please mention from when the routine will be active");
        return;
    }
    
    if(to == ""){
        alert("Please mention untill when the routine will be active");
        return;
    }
    
    var selectedTeachers = "";
    var selectedClassrooms = "";
    var selectedStudents = "";
    var selected_no_of_Students = "";
    var selectedCourses = "";
    var selected_assigned_Courses = "";
    var tem;
    var i, tem_count;    
    
    var rows = TC.getElementsByTagName('tr');
    
    try {
        for (i = 1; i < rows.length; i++) {
            tem = rows[i].cells[0].innerHTML;
            
//            alert(tem);
//            alert(document.getElementById("tea-check-" + tem).checked);

            if (document.getElementById("tea-check-" + tem).checked) {
                selectedTeachers += tem + ",";
            }
        }
    } catch (e) {
        alert("Error found with teachers");
    }

    
    var rows = CR.getElementsByTagName('tr');
    
    try {
        for(i = 1; i < rows.length; i++){
        tem = rows[i].cells[0].innerHTML;
        
        if(document.getElementById("cla-check-"+tem).checked){
           selectedClassrooms += tem + ",";
        }
    }
    } catch (e) {
        alert("Error found with classes");
    }

    
    
    var rows = ST.getElementsByTagName('tr');
    try {
        for (i = 1; i < rows.length; i++) {
            tem = rows[i].cells[0].innerHTML;
            tem_count = document.getElementById("count-" + tem).value;

            if (document.getElementById("stu-check-" + tem).checked) {
                if (tem_count == "") {
                    alert("Please provide number of students of batch " + tem);
                    return;
                }
                
                selectedStudents += tem + ",";
                selected_no_of_Students += tem_count +",";
            }
        }
    } catch (e) {
        alert("Error found with students");
    }



    var rows = CO.getElementsByTagName('tr');

    try {
        for (i = 1; i < rows.length; i++) {
            tem = rows[i].cells[0].innerHTML;
            
            if (document.getElementById("cou-check-" + tem).checked) {
                selectedCourses += tem + ",";

                var selector = document.getElementById("assign-" + tem);
                selected_assigned_Courses += selector.options[selector.selectedIndex].value + ",";
            }
        }
    } catch (e) {
        alert("Error found with courses");
    }
    
    
    $.ajax({
        type: 'POST',
        url: 'RoutineAdmin',
        data: "from=" + from + "&teachers=" + selectedTeachers + "&classrooms=" + selectedClassrooms + "&students=" + selectedStudents + "&courses=" + selectedCourses + "&to=" + to + "&counts=" + selected_no_of_Students + "&assignedTeachers=" + selected_assigned_Courses,
        success: function () {
            //pelletLoadRow("DEPARTMENTS", "departments", DN);
        },

        Error: function () {
            alert("Error occurred while creating teacher!");
        }
    });

//    alert(selectedTeachers+"\n"+selectedClassrooms+"\n"+selectedStudents+"\n"+selectedCourses+"\n"+selected_no_of_Students+"\n"+selected_assigned_Courses);

}


function checkAllTeachers() {
    if (document.getElementById("all-teachers").checked) {
        var x = document.getElementsByClassName('routine-check-teachers');
        var i;

        for (i = 0; i < x.length; i++) {
            x[i].checked = true;
        }
    }
}


function checkAllCourses() {
    if (document.getElementById("all-courses").checked) {
        var x = document.getElementsByClassName('routine-check-courses');
        var i;

        for (i = 0; i < x.length; i++) {
            x[i].checked = true;

            var ast = x[i].closest('tr');
            var courseName = ast.cells[0].innerHTML;


            $(ast.cells[1]).html("<select id='assign-" + courseName + "'>" + document.getElementById('course-teacher').innerHTML + document.getElementById('course-department').innerHTML + "</select>");
            $("#" + "assign-" + courseName).find('option[value=select]').remove();

        }
    }
}


function checkAllStudents() {
    if (document.getElementById("all-students").checked) {
        var x = document.getElementsByClassName('routine-check-students');
        var i;

        for (i = 0; i < x.length; i++) {
            x[i].checked = true;
        }
    }
}


function checkAllClassrooms() {
    if (document.getElementById("all-classrooms").checked) {
        var x = document.getElementsByClassName('routine-check-classrooms');
        var i;

        for (i = 0; i < x.length; i++) {
            x[i].checked = true;
        }
    }
}

function uncheckTeachersMain(event){
    var ast = event.currentTarget.closest('tr');
    var teacherName = ast.cells[0].innerHTML;

    
    if (document.getElementById("tea-check-"+teacherName).checked == false){
        document.getElementById('all-teachers').checked = false;
    }
}

function uncheckClassroomsMain(event){
    var ast = event.currentTarget.closest('tr');
    var classroomName = ast.cells[0].innerHTML;

    
    if (document.getElementById("cla-check-"+classroomName).checked == false){
        document.getElementById('all-classrooms').checked = false;
    }
}

function uncheckStudentsMain(event){
    var ast = event.currentTarget.closest('tr');
    var studentName = ast.cells[0].innerHTML;

    
    if (document.getElementById("stu-check-"+studentName).checked == false){
        document.getElementById('all-students').checked = false;
    }
}

function assignTeacher(event){
    var ast = event.currentTarget.closest('tr');
    var courseName = ast.cells[0].innerHTML;

    
    if (document.getElementById("cou-check-"+courseName).checked){
        
        $(ast.cells[1]).html("<select id='assign-" + courseName + "'>" + document.getElementById('course-teacher').innerHTML + document.getElementById('course-department').innerHTML + "</select>");
        $("#" + "assign-" + courseName).find('option[value=select]').remove();
    }else{
        document.getElementById('all-courses').checked = false;
        ast.cells[1].innerHTML = "Plz assign teacher";
    }
}

function openNav() {
  document.getElementById("mySidenav").style.width = "250px";
}

function closeNav() {
  document.getElementById("mySidenav").style.width = "0";
}