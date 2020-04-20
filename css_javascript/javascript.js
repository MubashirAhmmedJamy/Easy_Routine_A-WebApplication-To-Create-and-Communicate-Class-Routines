
                            var pellet_name;
                            var pellet_class;
                            var grid_name;

//                            $(function () {
//                                $(".pellet-item").draggable({
//                                    opacity: 0.4,
//                                    revert: true
//                                });
//                                
//                                $(".grid").droppable({
//                                    drop: function (event, ui) {                                      
//                                        pellet_name = ui.draggable.attr("id")
//                                    }
//                                    
//                                });
//                            });
//                            
                            function dragStart(event) {
                                event.dataTransfer.setData("Text", event.target.id);
                                pellet_name = event.currentTarget.id;
                                pellet_class = event.currentTarget.className;
                                
                                document.getElementById("demo").innerHTML = pellet_class +" "+ pellet_name + " is being moved...";
                            }

                            function dragEnter(event) {
                                if (event.target.className == "grid") {
                                    grid_name = event.target.id;
                                    document.getElementById("demo").innerHTML = pellet_class +" "+  pellet_name + " is on " + grid_name;
                                    event.target.style.background = "red";

                                }
                            }

                            function dragLeave(event) {
                                if (event.target.className == "grid") {
                                    //document.getElementById("demo").innerHTML = "Left the dropzone";
                                    event.target.style.background = "#ccffff";
                                }
                            }

                            function allowDrop(event) {
                                event.preventDefault();
                            }
                            /***********/
                            function insert(type,value){
                                var tableRef = document.getElementById('current-routine-status');

                                // Insert a row in the table at the last row
                                var newRow   = tableRef.insertRow();
                                // Insert a cell in the row at index 0
                                //var newText  = document.createTextNode(value);
                                newRow.innerHTML = "<tr><td></td><td>"+value+"</td><td></td><td></td></tr>";
                                
                                // Append a text node to the cell

//                                var newCell = newRow.insertCell(1);
//                                newCell.appendChild(value);
                            }
                            
                            /***********/

                            function drop(event) {
                                event.preventDefault();
                                event.target.style.background = "#ccffff";
                                //var name = event.target.id;
                                document.getElementById("demo").innerHTML = pellet_class +" "+ pellet_name + " Dropped on " + grid_name;
                                insert(pellet_class,pellet_name);
                                
                            }
                            
                            function new_move(){
                                var tableRef = document.getElementById('current-routine-status');

                                // Insert a row in the table at the last row
                                var newRow   = tableRef.insertRow();
                                // Insert a cell in the row at index 0
                                
                                newRow.innerHTML = "<tr><td></td><td></td><td></td><td></td></tr>";
                                
                            }