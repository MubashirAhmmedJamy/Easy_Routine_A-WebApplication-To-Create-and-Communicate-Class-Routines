<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Routine Designer</title>
    </head>

    <body onload="preLoader()">
        
        <div id="mySidenav" class="sidenav">
            <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
            <a href="AdminPanel.jsp">Admin Panel</a>
            <a href="ClassRoutine.jsp">Class Routine</a>
        </div>
        
        <span style="font-size:30px;cursor:pointer;color: #ffff00" onclick="openNav()">&#9776;</span>
        

<!--        <div id="myModal" class="modal">
             Modal content 
            <div class="modal-content">
                <span class="close">&times;</span>
                <p>Which Routine You want to create?</p>
                <select id="routines">
                </select>
            </div>
        </div>-->
        
        <!--<h4 align="center" style="color: #ffff00">Routine Designer</h4>-->
        
        
        <div class="row">

            <div class="column-left" style="background-color:#99ff99">
                
                <p id="nodata-left" hidden="true">No data available yet</p>

                <div id="LEFT" hidden="true">
                    <h2 align="Center">Current Routine Status</h2>
                    <div class="line"></div>
                    <div>
                        <button class="move" onclick="new_move()" id="new-move">New Move</button>
                        <button class="move" onclick="clear_move()" id="Clear-Recent-Moves" disabled="true">Clear Recent Moves</button>
                        <button id="remove" onclick="remover()">Cancel All Classes</button>
                    </div>

                    <div id="tableWrapper">
                        <table id="current-routine-status" class="crs">
                            <thead>
                            <th>Time</th>
                            <th>Teacher</th>
                            <th>Classroom</th>
                            <th>Batch</th>
                            <th>Course</th>
                            <th>Edit</th>
                            <th>Delete</th>
                            </thead>
                        </table>                    
                    </div>




                    <button onclick="printRoutine()" id="print">Print Routine</button>


                    <div id="dialog" style="background: #ccff00">

                        <select id="choose" onchange="chooser()">
                            <option value="print-by" selected="true" onclick="printBy()">Print By</option>
                            <option value="teacher" onclick="printByTeacher()">Teacher</option>
                            <option value="classroom" onclick="printByClassroom()">Classroom</option>
                            <option value="student" onclick="printByStudent()">Student</option>
                        </select>

                        <select id="choose-teacher" hidden="true">
                            <option value="select" selected="true" >Select Teacher</option>
                        </select>


                        <select id="choose-student" hidden="true">
                            <option value="select" selected="true" >Select Student</option>
                        </select>

                        <select id="choose-classroom" hidden="true">
                            <option value="select" selected="true" >Select Classroom</option>
                        </select>
                        <button id="print-by-parameter" style="display: none" onclick="printByParameter()">Print</button>

                    </div>
                </div>


            </div>

            <div class="column-mid" style="background-color:#ffcccc">
                <h2 align="center">Week Days & Classes</h2>
                <div class="line"></div>

                <div>
                    <select id="db_codes">
                        <option value="select">Select a Routine to Create</option>
                    </select>
                </div>
                
                <button id="db" onclick="db_codeManager()">Confirm</button>



                <table class="class-scheduler">
                    <thead>
                    <th>Hour</th>
                    <th>Sunday</th>
                    <th>Monday</th>
                    <th>Tuesday</th>
                    <th>Wednesday</th>
                    <th>Thursday</th>
                    </thead>

                    <tr>
                        <td class="tdg">08:00-08:50</td>
                        <td class="tdg"><div class="grid" id="SUN_8AM" ondrop="drop(event)" ondragenter="dragEnter(event)" ondragleave="dragLeave(event)" ondragover="allowDrop(event)"></div></td>
                        <td class="tdg"><div class="grid" id="MON_8AM" ondrop="drop(event)" ondragenter="dragEnter(event)" ondragleave="dragLeave(event)" ondragover="allowDrop(event)"></div></td>
                        <td class="tdg"><div class="grid" id="TUE_8AM" ondrop="drop(event)" ondragenter="dragEnter(event)" ondragleave="dragLeave(event)" ondragover="allowDrop(event)"></div></td>
                        <td class="tdg"><div class="grid" id="WED_8AM" ondrop="drop(event)" ondragenter="dragEnter(event)" ondragleave="dragLeave(event)" ondragover="allowDrop(event)"></div></td>
                        <td class="tdg"><div class="grid" id="THU_8AM" ondrop="drop(event)" ondragenter="dragEnter(event)" ondragleave="dragLeave(event)" ondragover="allowDrop(event)"></div></td>
                    </tr>

                    <tr>
                        <td class="tdg">09:00-09:50</td>
                        <td class="tdg"><div class="grid" id="SUN_9AM" ondrop="drop(event)" ondragenter="dragEnter(event)" ondragleave="dragLeave(event)" ondragover="allowDrop(event)"></div></td>
                        <td class="tdg"><div class="grid" id="MON_9AM" ondrop="drop(event)" ondragenter="dragEnter(event)" ondragleave="dragLeave(event)" ondragover="allowDrop(event)"></div></td>
                        <td class="tdg"><div class="grid" id="TUE_9AM" ondrop="drop(event)" ondragenter="dragEnter(event)" ondragleave="dragLeave(event)" ondragover="allowDrop(event)"></div></td>
                        <td class="tdg"><div class="grid" id="WED_9AM" ondrop="drop(event)" ondragenter="dragEnter(event)" ondragleave="dragLeave(event)" ondragover="allowDrop(event)"></div></td>
                        <td class="tdg"><div class="grid" id="THU_9AM" ondrop="drop(event)" ondragenter="dragEnter(event)" ondragleave="dragLeave(event)" ondragover="allowDrop(event)"></div></td>
                    </tr>

                    <tr>
                        <td class="tdg">10:00-10:50</td>
                        <td class="tdg"><div class="grid" id="SUN_10AM" ondrop="drop(event)" ondragenter="dragEnter(event)" ondragleave="dragLeave(event)" ondragover="allowDrop(event)"></div></td>
                        <td class="tdg"><div class="grid" id="MON_10AM" ondrop="drop(event)" ondragenter="dragEnter(event)" ondragleave="dragLeave(event)" ondragover="allowDrop(event)"></div></td>
                        <td class="tdg"><div class="grid" id="TUE_10AM" ondrop="drop(event)" ondragenter="dragEnter(event)" ondragleave="dragLeave(event)" ondragover="allowDrop(event)"></div></td>
                        <td class="tdg"><div class="grid" id="WED_10AM" ondrop="drop(event)" ondragenter="dragEnter(event)" ondragleave="dragLeave(event)" ondragover="allowDrop(event)"></div></td>
                        <td class="tdg"><div class="grid" id="THU_10AM" ondrop="drop(event)" ondragenter="dragEnter(event)" ondragleave="dragLeave(event)" ondragover="allowDrop(event)"></div></td>
                    </tr>

                    <tr>
                        <td class="tdg">11:00-11:50</td>
                        <td class="tdg"><div class="grid" id="SUN_11AM" ondrop="drop(event)" ondragenter="dragEnter(event)" ondragleave="dragLeave(event)" ondragover="allowDrop(event)"></div></td>
                        <td class="tdg"><div class="grid" id="MON_11AM" ondrop="drop(event)" ondragenter="dragEnter(event)" ondragleave="dragLeave(event)" ondragover="allowDrop(event)"></div></td>
                        <td class="tdg"><div class="grid" id="TUE_11AM" ondrop="drop(event)" ondragenter="dragEnter(event)" ondragleave="dragLeave(event)" ondragover="allowDrop(event)"></div></td>
                        <td class="tdg"><div class="grid" id="WED_11AM" ondrop="drop(event)" ondragenter="dragEnter(event)" ondragleave="dragLeave(event)" ondragover="allowDrop(event)"></div></td>
                        <td class="tdg"><div class="grid" id="THU_11AM" ondrop="drop(event)" ondragenter="dragEnter(event)" ondragleave="dragLeave(event)" ondragover="allowDrop(event)"></div></td>
                    </tr>

                    <tr>
                        <td class="tdg">12:00-12:50</td>
                        <td class="tdg"><div class="grid" id="SUN_12PM" ondrop="drop(event)" ondragenter="dragEnter(event)" ondragleave="dragLeave(event)" ondragover="allowDrop(event)"></div></td>
                        <td class="tdg"><div class="grid" id="MON_12PM" ondrop="drop(event)" ondragenter="dragEnter(event)" ondragleave="dragLeave(event)" ondragover="allowDrop(event)"></div></td>
                        <td class="tdg"><div class="grid" id="TUE_12PM" ondrop="drop(event)" ondragenter="dragEnter(event)" ondragleave="dragLeave(event)" ondragover="allowDrop(event)"></div></td>
                        <td class="tdg"><div class="grid" id="WED_12PM" ondrop="drop(event)" ondragenter="dragEnter(event)" ondragleave="dragLeave(event)" ondragover="allowDrop(event)"></div></td>
                        <td class="tdg"><div class="grid" id="THU_12PM" ondrop="drop(event)" ondragenter="dragEnter(event)" ondragleave="dragLeave(event)" ondragover="allowDrop(event)"></div></td>
                    </tr>

                    <tr>
                        <td class="tdg">02:00-02:50</td>
                        <td class="tdg"><div class="grid" id="SUN_2PM" ondrop="drop(event)" ondragenter="dragEnter(event)" ondragleave="dragLeave(event)" ondragover="allowDrop(event)"></div></td>
                        <td class="tdg"><div class="grid" id="MON_2PM" ondrop="drop(event)" ondragenter="dragEnter(event)" ondragleave="dragLeave(event)" ondragover="allowDrop(event)"></div></td>
                        <td class="tdg"><div class="grid" id="TUE_2PM" ondrop="drop(event)" ondragenter="dragEnter(event)" ondragleave="dragLeave(event)" ondragover="allowDrop(event)"></div></td>
                        <td class="tdg"><div class="grid" id="WED_2PM" ondrop="drop(event)" ondragenter="dragEnter(event)" ondragleave="dragLeave(event)" ondragover="allowDrop(event)"></div></td>
                        <td class="tdg"><div class="grid" id="THU_2PM" ondrop="drop(event)" ondragenter="dragEnter(event)" ondragleave="dragLeave(event)" ondragover="allowDrop(event)"></div></td>
                    </tr>

                    <tr>
                        <td class="tdg">03:00-03:50</td>
                        <td class="tdg"><div class="grid" id="SUN_3PM" ondrop="drop(event)" ondragenter="dragEnter(event)" ondragleave="dragLeave(event)" ondragover="allowDrop(event)"></div></td>
                        <td class="tdg"><div class="grid" id="MON_3PM" ondrop="drop(event)" ondragenter="dragEnter(event)" ondragleave="dragLeave(event)" ondragover="allowDrop(event)"></div></td>
                        <td class="tdg"><div class="grid" id="TUE_3PM" ondrop="drop(event)" ondragenter="dragEnter(event)" ondragleave="dragLeave(event)" ondragover="allowDrop(event)"></div></td>
                        <td class="tdg"><div class="grid" id="WED_3PM" ondrop="drop(event)" ondragenter="dragEnter(event)" ondragleave="dragLeave(event)" ondragover="allowDrop(event)"></div></td>
                        <td class="tdg"><div class="grid" id="THU_3PM" ondrop="drop(event)" ondragenter="dragEnter(event)" ondragleave="dragLeave(event)" ondragover="allowDrop(event)"></div></td>
                    </tr>

                    <tr>
                        <td class="tdg">04:00-04:50</td>
                        <td class="tdg"><div class="grid" id="SUN_4PM" ondrop="drop(event)" ondragenter="dragEnter(event)" ondragleave="dragLeave(event)" ondragover="allowDrop(event)"></div></td>
                        <td class="tdg"><div class="grid" id="MON_4PM" ondrop="drop(event)" ondragenter="dragEnter(event)" ondragleave="dragLeave(event)" ondragover="allowDrop(event)"></div></td>
                        <td class="tdg"><div class="grid" id="TUE_4PM" ondrop="drop(event)" ondragenter="dragEnter(event)" ondragleave="dragLeave(event)" ondragover="allowDrop(event)"></div></td>
                        <td class="tdg"><div class="grid" id="WED_4PM" ondrop="drop(event)" ondragenter="dragEnter(event)" ondragleave="dragLeave(event)" ondragover="allowDrop(event)"></div></td>
                        <td class="tdg"><div class="grid" id="THU_4PM" ondrop="drop(event)" ondragenter="dragEnter(event)" ondragleave="dragLeave(event)" ondragover="allowDrop(event)"></div></td>
                    </tr>

                </table>

                <div id="msg-container" >
                    <div class="tooltip" id="validation-container">
                        <span class="tooltiptext" id="validation"></span>
                    </div>
                </div>


            </div>

            <div class="column-right" style="background-color: #00cccc">
                
                <p id="nodata-Right" hidden="true">No data available yet</p>
                
                <div id="RIGHT" hidden="true">
                    <h2 align="center">Pellets</h2>
                    <div class="line"></div>

                    <div id="pellet-container" >
                        <table class="pellets" id="TEACHERS">
                            <thead>
                            <th>Teachers</th>
                            </thead>
                        </table>

                        <table class="pellets" id="CLASSROOMS">
                            <thead>
                            <th>Classrooms</th>
                            </thead>
                        </table>

                        <table class="pellets" id="STUDENTS">
                            <thead>
                            <th>Batches</th>
                            </thead>
                        </table>

                        <table class="pellets" id="COURSES">
                            <thead>
                            <th>Courses</th>
                            </thead>
                        </table>
                    </div>

                    <br>


                    <div id="msg">
                        <p id="demo" style="font-size: medium"></p><br><br>
                        <!--<p id="validation" style="font-size: medium"></p>-->
                    </div>


                </div>
            </div>            
        </div>

        <select id="edit-classtime" hidden="true">
            <option value"SUN_8AM">SUN_8AM</option>
            <option value"SUN_9AM">SUN_9AM</option>
            <option value"SUN_10AM">SUN_10AM</option>
            <option value"SUN_11AM">SUN_11AM</option>
            <option value"SUN_12PM">SUN_12PM</option>
            <option value"SUN_2PM">SUN_2PM</option>
            <option value"SUN_3PM">SUN_3PM</option>
            <option value"SUN_4PM">SUN_4PM</option>
            <option value"MON_8AM">MON_8AM</option>
            <option value"MON_9AM">MON_9AM</option>
            <option value"MON_10AM">MON_10AM</option>
            <option value"MON_11AM">MON_11AM</option>
            <option value"MON_12PM">MON_12PM</option>
            <option value"MON_2PM">MON_2PM</option>
            <option value"MON_3PM">MON_3PM</option>
            <option value"MON_4PM">MON_4PM</option>
            <option value"TUE_8AM">TUE_8AM</option>
            <option value"TUE_9AM">TUE_9AM</option>
            <option value"TUE_10AM">TUE_10AM</option>
            <option value"TUE_11AM">TUE_11AM</option>
            <option value"TUE_12PM">TUE_12PM</option>
            <option value"TUE_2PM">TUE_2PM</option>
            <option value"TUE_3PM">TUE_3PM</option>
            <option value"TUE_4PM">TUE_4PM</option>
            <option value"WED_8AM">WED_8AM</option>
            <option value"WED_9AM">WED_9AM</option>
            <option value"WED_10AM">WED_10AM</option>
            <option value"WED_11AM">WED_11AM</option>
            <option value"WED_12PM">WED_12PM</option>
            <option value"WED_2PM">WED_2PM</option>
            <option value"WED_3PM">WED_3PM</option>
            <option value"WED_4PM">WED_4PM</option>
            <option value"THU_8AM">THU_8AM</option>
            <option value"THU_9AM">THU_9AM</option>
            <option value"THU_10AM">THU_10AM</option>
            <option value"THU_11AM">THU_11AM</option>
            <option value"THU_12PM">THU_12PM</option>
            <option value"THU_2PM">THU_2PM</option>
            <option value"THU_3PM">THU_3PM</option>
            <option value"THU_4PM">THU_4PM</option>
        </select>
        
        <select id="edit-teachers" hidden="true">
        </select>
        
        <select id="edit-classrooms" hidden="true">
        </select>
        
        <select id="edit-students" hidden="true">
        </select>

        <select id="edit-courses" hidden="true">
        </select>

    </body>

    <script src="css-js/javascript.js" type="text/javascript"></script>
    <script src="css-js/jqueryGoogle.min.js" type="text/javascript"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="css-js/css.css">
</html>
