| method sig      | responsibility     | instance vars used | other class methods called | objects used with method calls | lines of code |
|public Rogue()   | default constructor|		0			|none						 |none							  |		2		  |

|public Rogue(    | actual constructor |        1           |addRoom,addDoor,addItem     | none                           |     28		  |
|RogueParser 	  |                    |                    | linkDoor
|theDungeonInfo0  |	  				   |

| public void 	  |     				|                     |  						|    						     |    		  	  |	
|  setPlayer(
| Player  
| player2);       |     sets the player |          1           |none				     |    none				       |		3		  |
|															   |                                                
| public void
|addRoom(Map
|<String, String> 
|toAdd, ArrayList
|<Door> drs)  	  | adds rooms         |          1 		   |         none			|   room. object     			|      14		  |

|public void 
addDoor(Map
<String, 
String> door)     |  add door          |          1			   |         none			|    door object				|       9		  |

|public void 
addDoor2(Door d)  |  add door 2   	   |          1 		   |        none 			|    door object 				|       18		  |


|public void      |
|linkDoor()       | links doors        |          1			   |         none			 |    room object			    |       10	      |


|public void 
addItem(Map<String
,String> toAdd)
 throws Impossible
 PositionException)|  addItem   	   |          2 		   |        none 			|    room object,item object 	|       20	   	  |

|public void      |
|makeMove()       |  makes move        |          1			   |         makeMove2		|    player object				|       16		  |


|public void
|makeMove2(Point 
|pLoc);	          |   makes move    	|          1 		   |makeMoveItems, makeMoveDoor|    player 				    |       20		  |


|public String
|makeMoveDoor()   | makes door move     |          1			   |         none			|room object  player object	|       18		  |

|public void
|makeMoveItems(
|Room rm)   	  |  makes item move    |          2			   |   makeMoveItems2()		|item object  player object	|       18		  |	


|public void 
|makeMoveItems2() |  makes door move2    |          0			   |seperateItemsByType(it)	|    room object			|       6         |
|				  |						 |						    seperateItemsByType2(it);


|public void       |
|removeItems(Item i)|  removes Items      |          1			   |         none			|    none					|       3	  	  |	  

|public String[] 
makeMoveInventory() |returns string ofitem|          1			   |         none			|    item object			|       7	  	  |	  


|public void 
seperateItems
ByType(Item it) |  seperates item        |          3			   |         none			|    Item					|       13		  |


|public void 
|seperateItems2
|ByType(Item it) |  seperates item        |          3			   |         none			|    Item					|       13		  |

|public String    |
|getNextDisplay() |  gets next display  |          1			   |         none			|    none					|       12	  	  |	

|public void       |
|tossTheItem
(Item it)          | tosses item        |          1			   |         none			|    Item object			|       6	  	  |	


|public ArrayList
<Item> get
Inventory()	       | gets inventory       |		in room			 |          none			|   Item object			    |       3	  	  |	



|public void       |
|setBoolean
|(boolean) 		  |  
|				  |		sets boolean	 |          1			   |         none			|    none					|       3	  	  |	


|public HashMap
<String, 
Character> 
getSymbols()       | gets symbols        |          1			   | none					 |    none					|       3	  	  |	
|				   |					 |                         | 



| public ArrayList
<Room> getRooms()  | gets rooms          |          1			   | none				    |    none			        |       3	       | 	
|				   |					 |                         | 
|				   |					 |						   |  						|    



| public ArrayList
<Item> getitems()  | gets items          |          1			   | none				    |    none			        |       3	       | 	
|				   |					 |                         | 
|				   |					 |						   |  						|    


| public Player
| getPlayer()      | gets player         |          1			   | none				    |    none			        |       3	       | 	
|				   |					 |                         | 
|				   |					 |						   |  						|    




