<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Class Routine</title>
    </head>
    <body onload="preLoader()">

        <div id="mySidenav" class="sidenav">
            <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
            <a href="AdminPanel.jsp">Admin Panel</a>
            <a href="RoutineDesigner.jsp">Routine Designer</a>
        </div>

        <span style="font-size:30px;cursor:pointer;color: #000000" onclick="openNav()">&#9776;</span>


        <div id="driver" style="background-color: #ccff66; border: 2px solid black">
            <div>
                <select id="db_codes" onchange="db_codeManager()">
                    <option value="select">Select A Routine</option>
                </select>

<!--                <button id="db" onclick="db_codeManager()">Confirm</button>    -->
            </div>
            
            
            <p align="center"><strong>See Class Routine</strong></p>

            <div class="A">
                <label class="container"><strong>See Routine for ALL</strong>
                    <span class="checkmark"></span>
                    <input type="checkbox" id="all" onclick="All()"/>                    
                </label>
            </div>


            <div class="A">
                <select id="students">
                    <option value="select">Select A Batch</option>
                </select>               

                <label class="container">Students
                    <input type="checkbox" id="only-studetns" onclick="onlyStudents()"/>
                    <span class="checkmark"></span>
                </label> 
            </div>



            <div class="A">
                <select id="teachers">
                    <option value="select">Select A Teacher</option>
                </select>

                <label class="container">Teachers
                    <input type="checkbox" id="only-teachers" onclick="onlyTeachers()"/>
                    <span class="checkmark"></span>
                </label>
            </div>


            <div class="A">
                <select id="classrooms">
                    <option value="select">Select Classroom</option>
                </select>

                <label class="container">Classrooms
                    <input type="checkbox" id="only-classrooms" onclick="onlyClassrooms()"/>
                    <span class="checkmark"></span>
                </label>
            </div>

        </div>

        <div id="r" hidden="true">
            <h1 id="lebel-routine" style="text-align: center"></h1>
            <table id="routine" style="margin-left:auto;margin-right:auto">
                <thead>
                <th>Day</th>
                <th>Semester</th>
                <th>8AM</th>
                <th>9AM</th>
                <th>10AM</th>
                <th>11AM</th>
                <th>12PM</th>
                <th>2PM</th>
                <th>3PM</th>
                <th>4PM</th>
                </thead>
            </table>
        </div>


        <div id="t" hidden="true">

            <h1 id="lebel-routine-T" style="text-align: center"></h1>

            <table id="routine-T" style="margin-left:auto;margin-right:auto">
                <thead>
                <th>Day</th>
                <th>8AM</th>
                <th>9AM</th>
                <th>10AM</th>
                <th>11AM</th>
                <th>12PM</th>
                <th>2PM</th>
                <th>3PM</th>
                <th>4PM</th>
                </thead>
            </table>
        </div>

        <div id="s" hidden="true">        
            <h1 id="lebel-routine-S" style="text-align: center"></h1>
            <table id="routine-S" style="margin-left:auto;margin-right:auto">
                <thead>
                <th>Day</th>
                <th>8AM</th>
                <th>9AM</th>
                <th>10AM</th>
                <th>11AM</th>
                <th>12PM</th>
                <th>2PM</th>
                <th>3PM</th>
                <th>4PM</th>
                </thead>
            </table>
        </div>

        <div id="c" hidden="true">
            <h1 id="lebel-routine-C" style="text-align: center"></h1>
            <table id="routine-C" style="margin-left:auto;margin-right:auto">
                <thead>
                <th>Day</th>
                <th>8AM</th>
                <th>9AM</th>
                <th>10AM</th>
                <th>11AM</th>
                <th>12PM</th>
                <th>2PM</th>
                <th>3PM</th>
                <th>4PM</th>
                </thead>
            </table>            
        </div>


        <p id="Demo1"></p><br>
        <p id="Demo2"></p><br>
        <p id="Demo3"></p><br>
        <p id="Demo4"></p><br>
        <p id="Demo5"></p><br>

        <div class="modal"><!-- Place at bottom of page --></div>
        <link rel="stylesheet" type="text/css" href="css-js/classRoutine.css">
        <script src="css-js/routineJavascript.js" type="text/javascript"></script>
        <script src="css-js/jqueryGoogle.min.js" type="text/javascript"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </body>
</html>