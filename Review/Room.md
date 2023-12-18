| method sig      | responsibility     | instance vars used | other class methods called | objects used with method calls | lines of code |
|public Room()    | default constructor|		5			|none						 |none							  |		7		  |
|public Room(int  | actual constructor |        5           |none                        | none                           |     7		  |
| width2, 		  |
|int height2,     |
| int id2, 		  |
| boolean start2, |
| int location2,  |
| String 		  |
| direction2)	  |
| public void 	  |     				|                     |  						|    						     |    		  	  |	
|  setSymbols(
| HashMap   
|<String, 
|Character> );    |     sets the symbol |          1           |            none         |    none                       |		3		  |                                                
| public HashMap   
|<String, 
|Character> 
|getSymbols()	  | gets symbols       |          1 		   |         none			|   none						|      3		  |

|public int       |
|getWidth()       |  gets width        |          1			   |         none			|    none						|       3		  |

|public int
|setWidth(int 
|newWidth);		  |  sets width   	   |          1 		   |        none 			|    none 						|       3		  |


|public int       |
|getWidth()       |  gets height        |          1			|         none			|    none						|       3		  |


|public int
|setHeight(int 
|newHeight);	  |  sets height   	   |          1 		   |        none 			|    none 						|       3		  |

|public int       |
|getId()          |  gets getId        |          1			   |         none			|    none						|       3		  |


|public int
|setId(int 
|newid);	      |  sets Id    	   |          1 		   |        none 			|    none 						|       3		  |


|public 
|ArrayList
|<Item> 
|getRoomItems()    |  gets roomItems     |          1			   |         none			|    none					|       3		  |

|public void
|setRoomItems(
|ArrayList
|<Item>)    	  |  sets roomItems     |          1			   |         none			|    none					|       3		  |	


|public 
|ArrayList
|<Item> 
|getRoomDoors()    |  gets roomDoors     |          1			   |         none			|    none					|       3		  |


|public Player    |
|getPlayers()     |  gets Player        |          1			   |         none			|    none					|       3	  	  |	  

|public void       |
|setPlayers(Player
|newPlayer)       |  sets Player        |          1			   |         none			|    none					|       3	  	  |	  


|public int
|getDoor(
|String direction) |  gets the doorsLoc  |          1			   |         none			|    none					|       13		  |


|public void       |
|setDoor(String
|Direction4, 
int 
location2)        |  sets Door          |          1			   |         none			|    none					|       3	  	  |	  

|public void       |
|setDirections
(String
|direction3)      |  sets direction     |          1			   |         none			|    none					|       3	  	  |	

|public void       |
|addDoor
(Door
|newDoor)         | adds door to arr    |          1			   |         none			|    none					|       3	  	  |	


|public boolean    |
|isPlayerinRoom
|()		          | checks if player is 
|				  |		in room			 |          1			   |         none			|    none					|       3	  	  |	



|public void       |
|setBoolean
|(boolean) 		  |  
|				  |		sets boolean	 |          1			   |         none			|    none					|       3	  	  |	


|public void       |
|addItem
|(Item
|toAdd)            | adds Item           |          1			   | getWidth(), getHeight() |    none					|       16	  	  |	
|				   |					 |                         | getXyLocation()



|public void       |
|displayRoom
|()	               | prints board        |          1			   | getDisplayCharacter()    |    none					|       16	  	  |	
|				   |					 |                         | getXyLocation()
|				   |					 |						   | displayRoomDoor()getID() |    



|public void       |
|displayRoomDoor
|()	               | prints board 2      |          3			   | getDisplayCharacter()    |    player			    |       20	  	  |	
|				   |					 |                         | getXyLocation()
|				   |					 |						   | displayRoomDoor()getID() |    
|				   |					 | 						   |  isPlayerInRoom()        |                         |				  |







