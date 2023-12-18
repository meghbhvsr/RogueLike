| method sig      | responsibility     | instance vars used | other class methods called | objects used with method calls | lines of code |
|public Rogue
|	Parser()    | default constructor|		0			|none						 |none							  |		2		  |

|public Rogue
|Parser(String Filename)    | actual constructor|		0			|none						 |none				|		2	     |

|public Array
|List<Map<
String, String>
|> getDoors()      | gets doors     	 |        1           |none                        | none                           |     3	   | 		  																												|	

|public Map        | next Room            |        2           |none                        | none                           |     3		  |
|nextRoom();	  																												|
|	

|public Map        | next item            |        2           |none                        | none                           |     3		  |
|nextItem();	  																												|
|

|public Character  | gets symbols        |        1           |none                        | none                           |     3		  |
|getSymbol(
|int
|conRoom);		  																															|
|

|public int     | gets num of items     |        1           |none                        | none                           |     3		  |
|getNumOfItems();
| 		  																												|
|

|public int     | gets num of rooms     |        1           |none                        | none                           |     3		  |
|getNumOfRooms();
| 		  																												|
|

|public void     | parses JSON        |        0          |   parse2()                        | none                           |     14		  |
|parse();		  																															|
|


|public void     | parses JSON2        |        0          |extractRoomInfo(),extractItemInfo() | none                           |     16	|
|parse2();		  											extractSymbolInfo()																											|
|

|private void    | extracts symbols     |        2           |none                        | none                           |     9		  |
|extractSymbol
|Info(JSONObject 
|symbolsJSON)																											|
|	


|private void    | extracts rooms     |        2           |none                        | none                           |     9		  |
|extractRoom
|Info(JSONObject 
|symbolsJSON)																											|
|

|private void    | extracts items     |        2           |none                        | none                           |     8		  |
|extractItem
|Info(JSONObject 
|symbolsJSON)																											|
|

|private Map<    | creates single room    |        3           |singleRoomDoors()        | none                           |     19		  |
|String,String>
|singleRoom(
|JSONObject
|roomJson)																										|
|
|private Map<    | creates doors in room    |        1          |none			         | none                           |     11		  |
|String,String>
|singleRoom
|Doors(
|JSONObject
|roomJson,
|JSONArray
|jsonArray);																										|
|


|private Map<    | gets the loot(parses)    |        0          |none                        | none                           |     11		  |
|String,String>
|itemPosition
|(JSONObject 
|lootJSON,
|String roomId);																											|
|

|private Map<    | parses the items        |        1          |none                        | none                           |     20		  |
|String,String>
|singleItem
|(JSONObject 
|itemsJSON);																											|

