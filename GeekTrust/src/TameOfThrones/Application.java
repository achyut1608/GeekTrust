package TameOfThrones;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 
 * @author Achyut Thaker
 * 
 * problem
 * King shan wants to rule the southeros and want send to secret message to the other 
 * five kingdoms and conditions of the message is :
 * 1). key for message is number of length of kingdom's name 
 * 2). secret message should contain encrypted character of the emblem
 * 3). encryption of emblem character is add key to the character 
 * 	like for AIR Kingdom should contain the character 'OWL' so if we want to send the code ROZO
 *   O+3->R,W+3->Z,L+3->O in between we can add any character and message can contain spaces also
 * 4). we have to provide five input in given below format ->
 * 	i/p AIR ROZO,LAND taweopiourplhe    
 * 5). king shan wants atleast 3 more allies to become the king of southeros if he got 3 more allies
 * then we have print in given below format
 *  o/p SPACE AIR LAND ICE
 *  if he doesn;t get 3 more allies then we have to  print 'NONE'
 *
 */

public class Application {
	
	// storing kingdom and emblem in hashmap
	private Map<String,String> kingdomEmblem = new HashMap<>();
		
	public static void main(String[]args) {
		Application application = new Application();
		application.sendingMessage();
	}
	
	/*
	 * initialize the class and providing the key values in 
	 *  kingdom as key & emblem as value
	 */
	
	public Application(){
		
		kingdomEmblem.put("space","gorilla");
		kingdomEmblem.put("land","panda");
		kingdomEmblem.put("water","octopus");
		kingdomEmblem.put("ice","mammoth");
		kingdomEmblem.put("air","owl");	
		kingdomEmblem.put("fire", "dragon");
	
	}
	
	/*
	 *getting kingdom name and encrypted message from user and forward to check 
	 *whether it contains only character(A-Z,a-Z)  
	 * 
	 */
	
	public void sendingMessage() {
		Scanner scan = new Scanner(System.in);
		
		int allyCount=1;
		String [] ally = new String[6];
		ally[0] = "SPACE";
	
		//getting kingdom name and encrypted message from user
		for(int i=0;i<5;i++) {
			
			String kingdomEncryptedMessage = scan.nextLine();
			String[]str = kingdomEncryptedMessage.split(" ");
			String kingdom = "",cryptedMessage="";
			
			//give first value to the kingdom
			kingdom = str[0];
			
			if(str.length<=2) 
				cryptedMessage = str[1];
			else
				cryptedMessage = removeSpace(str);
				
			//converted both kingdom and encrypted message into lower case 
			kingdom = kingdom.toLowerCase();
			cryptedMessage = cryptedMessage.toLowerCase();
			
			/*
			 * checking both the string contain only characters and
			 * if it's contains valid character then decrypt the message 
			 * while providing the valid key if successfully decrypt the message 
			 * increase the count of ally
			 */ 
			if(checkValidKingdomAndMessage(kingdom, cryptedMessage)) {
				String decryptedMessage = decryptingMessage(kingdom, cryptedMessage);
				if(ally(kingdom,decryptedMessage)) {
					ally[allyCount] = kingdom.toUpperCase();
					++allyCount;
				}
				
			} else 
				break;
			
						
		}
		
		// if ally count is more than 3 than space king shan would be the king of southeros
		if(allyCount>=4) {
			for(int i=0;i<allyCount;i++) 
				System.out.print(ally[i]+" ");
			
		} else
			System.out.println("NONE");
	}
	
	// removing the white space if it has any
	private String removeSpace(String[] str) {
		StringBuilder newCryptedMessage= new StringBuilder("");
		
		for(int i=1;i<str.length;i++) 
			newCryptedMessage.append(str[i]);
		
		return newCryptedMessage.toString();
	}
		
	private boolean checkValidKingdomAndMessage(String kingdom,String cryptedMessage) {
		boolean isValidKingdomAndMessage = false;
		
		// checking if message has only one character or empty message
		if(kingdom.length()<=1)
			System.out.println("Not valid kingdom");
		
		// checking if message has only one character or empty message
		if(cryptedMessage.length()<=1)
			System.out.println("not valid message");
		
		/*
		 * checking user has enter the valid kingdom provided the five kingdom user can't provide 
		 * the space kingdom it is competing kingdom  
		 */
		if(validKingdom(kingdom)&&validMessage(cryptedMessage))
			isValidKingdomAndMessage = true;
			
		return isValidKingdomAndMessage;
	}
	
	private boolean validKingdom(String kingdom) {
		boolean isValidKingdom = false;
		
		//checking if user has enter space kingdom or not
		if(kingdom.equalsIgnoreCase("space")) {
			System.out.println("Space is competing Kingdom");
			return false;
		}
		//if below is the kingdom then return true else return false
		if(kingdom.equals("air")||kingdom.equals("land")||kingdom.equals("ice")||kingdom.equals("fire")||kingdom.equals("water"))
			isValidKingdom = true;
		else
			System.out.println("Not valid kingdom");
		
		return isValidKingdom;
				
	}
	
	/*
	 * checking crypted message that it only contains between ((a-z),(A-z)) characters
	 */
	private boolean validMessage(String cryptedMessage) {
		boolean isValidMessage = true;
		
		for(int i=0;i<cryptedMessage.length();i++) {
			
			char character = cryptedMessage.charAt(i);
		
			if(character<=65 && character>=90) {
				System.out.println("invalid encrypted Message");
				isValidMessage = false;
				break;
			}
			
		}
		
		return isValidMessage;
	}
	
	/*
	 * here we are providing the key to decrypt the message 
	 * like ice,air has 3 character then key is '3',
	 * land,fire has 4 character then key is '4',
	 * and water has '5' character then key is '5'
	 */
	private String decryptingMessage(String kingdom,String cryptedMessage) {
		int kingdomCharacterLength = kingdom.length();
		String decryptedMessage ="";
		
		if(kingdomCharacterLength==3) {
			decryptedMessage = decryptMessage(3, cryptedMessage);
		} else if(kingdomCharacterLength==4) {
			decryptedMessage = decryptMessage(4, cryptedMessage);
		} else if(kingdomCharacterLength==5) {
			decryptedMessage = decryptMessage(5, cryptedMessage);
		}
		return decryptedMessage;
		
	}
	
	/*
	 * decrypting the encryptedMessage by provided the key 
	 * 
	 */
	private String decryptMessage(int key,String cryptedMessage) {
		//storing the characters  one by one in stringbuilder
		StringBuilder decryptedMessage = new StringBuilder("");
		
		for(int i=0;i<cryptedMessage.length();i++) {
			char cryptedChar = cryptedMessage.charAt(i);
			
			// getting the decryptedCharacter by subtracting the key
			char decryptedChar = (char) (cryptedChar-key);
			
			// append the character by adding to the String builder
			decryptedMessage.append(decryptedChar);
		}
		return decryptedMessage.toString();
	}
	/*
	 * getting the emblem of kingdom 
	 * and comparing the emblem and decryptedMessage 
	 * if decryptedmessage contains then emblem of kingdom then it is ally to the space kingdom
	 */
	private boolean ally(String kingdom,String decryptedMessage) {
		boolean isAlly = false;
		String emblem = "";
		
		switch(kingdom) {
			case "air":
				emblem = kingdomEmblem.get(kingdom);
				break;
			case "water":
				emblem = kingdomEmblem.get(kingdom);
				break;
			case "ice":
				emblem = kingdomEmblem.get(kingdom);
				break;
			case "fire":
				emblem = kingdomEmblem.get(kingdom);
				break;
			case "land":
				emblem = kingdomEmblem.get(kingdom);
				break;
		}
		
		
		if(checkMessage(emblem, decryptedMessage))
			isAlly = true;
		else
			isAlly = false;
		
		return  isAlly;
	}
	
	/*
	 * checking the decrypted message contains the emblem or not if it is contains then it would 
	 * be count as ally
	 */
	private boolean checkMessage(String emblem,String decryptedMessage) {
		boolean isAlly = true;
		// putting the each character of emblem and decrypted message in map 
		Map<Character,Integer> emblemMap = getDataInMap(emblem);
		Map<Character,Integer> decryptedMessageMap = getDataInMap(decryptedMessage);
		
		/*
		 * comparing the both map to check that decrypted message contains the 
		 * emblem character or not if it contains then it would be ally else it
		 * won't be ally 
		 */
		for(Map.Entry<Character,Integer> entry:emblemMap.entrySet()) {
			char key = entry.getKey();
			if(decryptedMessageMap.containsKey(key)) {
				if(entry.getValue()>decryptedMessageMap.get(key)) {
					isAlly = false;
					break;
				} else {
					isAlly = true;
				}
			} else
				isAlly = false;
		}
		
		return isAlly;
		
	}
	
	// counting each character and putting in map 
	private HashMap<Character,Integer> getDataInMap(String str){
		HashMap<Character,Integer> map = new HashMap<>();
	
		for(char ch:str.toCharArray()) {
			if(map.containsKey(ch))
				map.replace(ch, map.get(ch)+1);
			else
				map.put(ch,1);
		}
		
		return map;
	}
	
}