<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Panel</title>
    </head>
    <body style="background-color: #000000" onload="pelletsLoader()">

        <div id="mySidenav" class="sidenav">
            <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
            <a href="RoutineDesigner.jsp">Routine Designer</a>
            <a href="ClassRoutine.jsp">Class Routine</a>
        </div>
        <span style="font-size:30px;cursor:pointer;color: #ffff00" onclick="openNav()">&#9776;</span>

        <h2 align="center" style="color: #ffff00"><strong>Admin Panel</strong></h2>
        

        
        <div id="mother-container" style="background-color: white">
            
            
            
            <div class="container">
                <div class="row">
                    <h4>Configure New Objects</h4>
                    <div class="input-group">
                        <input type="radio" name="config-selector" id="departments" onclick="radio_departments()"/>
                        <label for="departments"><span><i></i>New Department</span></label>

                        <input type="radio" name="config-selector" id="teacher" onclick="radio_teachers()"/>
                        <label for="teacher"><span><i></i>New Teacher</span></label>

                        <input type="radio" name="config-selector" id="classroom" onclick="radio_classrooms()"/>
                        <label for="classroom"><span><i></i>New Classroom</span></label>

                        <input type="radio" name="config-selector" id="student" onclick="radio_students()"/>
                        <label for="student"><span><i></i>New Batch</span></label>

                        <input type="radio" name="config-selector" id="course" onclick="radio_courses()"/>
                        <label for="course"><span><i></i>New Course</span></label>
                        
                        <input type="radio" name="config-selector" id="routines" onclick="radio_routines()"/>
                        <label for="routines"><span><i></i>New Routine</span></label>
                    </div>


                    <div class="creator" id="D">
                        <h4>Department Details</h4>
                        <div class="input-group input-group-icon">
                            <label for="department-name"><span><i></i>Name</span></label>
                            <input type="text" placeholder="Department Name/ShortCode" maxlength="4" id="department-name"/>
                            <button id="department-creator" onclick="departmentAdmin()">Create</button>
                        </div>
                    </div>

                    <div class="creator" id="T">
                        <h4>Teacher Details</h4>
                        <div class="input-group input-group-icon">
                            
                            <label for="teacher-name"><span><i></i>Name</span></label>
                            <input type="text" placeholder="Teacher Name/ShortCode" maxlength="4" id="teacher-name"/>
                            
                            <label for="teacher-full-Name"><span><i></i>Full Name</span></label>
                            <input type="text" placeholder="Full Name" maxlength="100" id="teacher-full-Name"/>
                            
                            <label for="teacher-designation"><span><i></i>Designation</span></label>
                            <input type="text" placeholder="Mobile" maxlength="100" id="teacher-designation"/>
                            
                            <label for="teacher-email"><span><i></i>Email</span></label>
                            <input type="text" placeholder="Email" maxlength="100" id="teacher-email"/>
                            
                            <label for="teacher-mobile"><span><i></i>Mobile</span></label>
                            <input type="text" placeholder="Mobile" maxlength="15" id="teacher-mobile"/>
                            
                            <button id="teacher-creator" onclick="teacherAdmin()">Create</button>
                        </div>
                    </div>

                    <div class="creator" id="C">
                        <h4>Classroom Details</h4>
                        <div class="input-group input-group-icon">
                            <label for="classroom-name"><span><i></i>Name</span></label>
                            <input type="text" placeholder="Classroom Name/ShortCode" maxlength="10" id="classroom-name"/>

                            <label for="classroom-capacity"><span><i></i>Capacity</span></label>
                            <input type="number" placeholder="Classroom Capacity" max="1000" id="classroom-capacity"/>
                            <button id="classroom-creator" onclick="classroomAdmin()">Create</button>
                        </div>
                    </div>
                    
                    
                    <div class="creator" id="R">
                        <h4>Routine Details</h4>
                        <div class="input-group input-group-icon">
                            <label for="routine-from"><span><i></i>From</span></label>
                            <input type="text" placeholder="Jan-2020" maxlength="20" id="routine-from"/>

                            <label for="routine-to"><span><i></i>To</span></label>
                            <input type="text" placeholder="Jun-2020" maxlength="20" id="routine-to"/>

                            <div class="fields">
                                <label for="routine-TEACHERS"><span><i></i>Select Teachers</span></label>
                                <table id="routine-TEACHERS">
                                    <thead>
                                    <th class="routine-th">Teachers</th>
                                    <th class="routine-th"><input type="checkbox" id="all-teachers" onclick="checkAllTeachers()"/></th>
                                    </thead>
                                </table>
                            </div>


                            <div class="fields">
                                <label for="routine-CLASSROOMS"><span><i></i>Select Classrooms</span></label>
                                <table id="routine-CLASSROOMS">
                                    <thead>
                                    <th class="routine-th">Classrooms</th>
                                    <th class="routine-th"><input type="checkbox" id="all-classrooms" onclick="checkAllClassrooms()"/></th>
                                    </thead>                                    
                                </table>
                            </div>
                            
                            <div class="fields">
                                <label for="routine-STUDENTS"><span><i></i>Select Batches</span></label>
                                <table id="routine-STUDENTS">
                                    <thead>
                                    <th class="routine-th">Batches</th>
                                    <th class="routine-th">#OfStudents</th>
                                    <th class="routine-th"><input type="checkbox" id="all-students" onclick="checkAllStudents()()"/></th>
                                    </thead>
                                </table>
                            </div>
                            

                            <div class="fields">
                                <label for="routine-COURSES"><span><i></i>Select Courses</span></label>
                                <table id="routine-COURSES">
                                    <thead>
                                    <th class="routine-th">courses</th>
                                    <th class="routine-th">Assigned teacher</th>
                                    <th class="routine-th"><input type="checkbox" id="all-courses" onclick="checkAllCourses()()"/></th>
                                    </thead>
                                </table>
                            </div>

                            <button id="routine-creator" onclick="routineAdmin()">Create</button>
                        </div>
                    </div>

                    <div class="creator" id="S">

                        <h4>Student Details</h4>
                        <div class="input-group input-group-icon">

                            <div class="fields">
                                <label for="student-session"><span><i></i>Session</span></label>
                                <input type="text" placeholder="Session. e.g 201213" maxlength="10" id="student-session"/>
                            </div>

                            
                            <div class="fields">
                            <label for="student-semester"><span><i></i>Semester</span></label>
                            <select id="student-semester">
                                <option value="select">Select Semester</option>
                                <option value="1-1">1-1</option>
                                <option value="1-2">1-2</option>
                                <option value="2-1">2-1</option>
                                <option value="2-2">2-2</option>
                                <option value="3-1">3-1</option>
                                <option value="3-2">3-2</option>
                                <option value="4-1">4-1</option>
                                <option value="4-2">4-2</option>
                                <option value="M-1">M-1</option>
                                <option value="M-2">M-2</option>
                            </select>
                            </div>

<!--                            <div class="fields">
                                <label for="student-count"><span><i></i>Number of Students</span></label>
                                <input type="number" placeholder="Number of students" maxlength="10" id="student-count"/>
                            </div>-->
                            

                            <div class="fields">
                                <label for="student-section"><span><i></i>Section(If any)</span></label>
                                <select id="student-section">
                                    <option value="No">No</option>
                                    <option value="A">A</option>
                                    <option value="B">B</option>
                                    <option value="C">C</option>
                                    <option value="D">D</option>
                                </select>
                            </div>
                            <button id="student-creator" onclick="studentAdmin()">Create</button>
                        </div>
                    </div>                    

                    <div class="creator" id="CO">
                        <h4>Course Details</h4>
                        <div class="input-group input-group-icon">
                            <label for="course-name"><span><i></i>Course Code</span></label>
                            <input type="text" placeholder="Course Code" maxlength="10" id="course-name"/>
                            
                            
                            <label for="course-fullname"><span><i></i>Name</span></label>
                            <input type="text" placeholder="Course Name" maxlength="100" id="course-fullname"/>
                            
                            
                            <div class="fields">
                                <label for="course-credtits"><span><i></i>Credits</span></label>
                                <select id="course-credits">
                                    <option selected="true" value="select">Select Course Credits</option>
                                    <option value="1">1</option>
                                    <option value="1.5">1.5</option>
                                    <option value="2">2</option>
                                    <option value="2.5">2.5</option>
                                    <option value="3">3</option>
                                    <option value="3.5">3.5</option>
                                    <option value="4">4</option>
                                    <option value="4.5">4.5</option>
                                    <option value="5">5</option>
                                    <option value="5.5">5.5</option>
                                    <option value="6">6</option>
                                    <option value="6.5">6.5</option>
                                    <option value="7">7</option>
                                    <option value="7.5">7.5</option>
                                    <option value="8">8</option>
                                    <option value="8.5">8.5</option>
                                    <option value="9">9</option>
                                    <option value="9.5">9.5</option>
                                    <option value="10">10</option>
                                </select>
                            </div>
                            

                            <div class="fields">
                                <label for="course-hours"><span><i></i>Hours Per Class</span></label>
                                <select id="course-hours">
                                    <option selected="true" value="select">Select Hours/class</option>
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                </select>
                            </div>
                            
                            <div class="fields">
                                <label for="course-classes"><span><i></i>Classes per week</span></label>
                                <select id="course-classes">
                                    <option selected="true" value="select">Select Class/week</option>
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                </select>
                            </div>
                            
                            

                            <div class="fields">
                                <label for="course-type"><span><i></i>Course Type</span></label>

                                <select id="course-type" onchange="courseTypeController()">
                                    <option selected="true" value="select">Select Course Type</option>
                                    <option value="Major">Major</option>
                                    <option value="Non-Major">Non-Major</option>
                                </select>
                            </div>
                            
                            <div class="fields">
                                <label for="course-modality"><span><i></i>Course Modality</span></label>

                                <select id="course-modality">
                                    <option selected="true" value="select">Select Course Modality</option>
                                    <option value="Theory">Theory</option>
                                    <option value="Lab">Lab</option>
                                </select>
                            </div>

                            <div class="fields" id="COT" hidden="true">
                                <label for="course-teacher"><span><i></i>Course Teacher</span></label>

                                <select id="course-teacher">
                                    <option selected="true" value="select">Select Teacher</option>
                                </select>
                            </div>

                            <div class="fields" id="CD" hidden="true">
                                <label for="course-department"><span><i></i>Department to offer the course</span></label>

                                <select id="course-department">
                                    <option selected="true" value="select">Select  Department</option>
                                </select>
                            </div>

                            <button id="course-creator" onclick="courseAdmin()">Create</button>
                        </div>    
                    </div>
                    
                    
                </div>
            </div>            
            
            
            <div class="pellet-container">
                <div class="row">
                    <h4>Existing Objects</h4>
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

                        <table class="pellets" id="DEPARTMENTS">
                            <thead>
                            <th>Departments</th>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>
            
            
        </div>
    </body>

    <link rel="stylesheet" type="text/css" href="css-js/css_Admin.css">
    <script src="css-js/jqueryGoogle.min.js" type="text/javascript"></script>
    <script src="css-js/admin_javascript.js" type="text/javascript"></script>

</html>