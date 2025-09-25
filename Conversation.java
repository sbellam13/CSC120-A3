import java.util.Scanner;
class Conversation implements ConversationRequirements {

  // Attributes 
  private int numRounds;
  private String[] transcript;
  private Scanner input;
  private static String[] words = {"I", "me", "am", "you", "my", "your", "are", "I'm", "you're"};
  private static String[] replacementWords = {"you", "you", "are", "I", "your", "my", "am", "you're", "I'm"};

  /**
   * Constructor 
   */
  Conversation() {
    numRounds = -1;
    input = new Scanner(System.in);
  }

  /**
   * Starts and runs the conversation with the user
   */
  public void chat() {
    // Creates String variables as placeholders for later use
    String currentInput = "";
    String currentOutput = "";

    // Ask for number of rounds
    System.out.print("How many rounds would you like to play? ");
    while(numRounds == -1){
      try{  // catches non integer input
        numRounds = input.nextInt();
      } catch(java.util.InputMismatchException e){
        System.out.println("That's not a number! Try Again. ");
      }
      input.nextLine(); // To prevent errors since input.nextInt() doesn't read \n
    }

    transcript = new String[numRounds*2 + 1];
    System.out.println(); // Adds in a paragraph break

    System.out.println("Hi there! What's on your mind? ");
    transcript [0] = "Hi there! What's on your mind? \n"; // Update transcript

    for(int i = 0; i < numRounds; i ++){
      currentInput = input.nextLine();  // gets uer input
      transcript[2*i+1] = currentInput + "\n";  // adds to transcript

      currentOutput = respond(currentInput); // creates responce
      System.out.println(currentOutput);
      transcript[2*i] = currentOutput + "\n"; // Update transcript
    }

    System.out.println("See ya!\n");
    transcript [transcript.length - 1] =  "See ya!\n";  // Updates transcript

    input.close();
  }

  /**
   * Prints transcript of conversation
   */
  public void printTranscript() {
    System.out.println("TRANSCRIPT:\n");
    for(int i = 0; i < transcript.length; i ++){
      System.out.print(transcript[i]);
    }
  }

  /**
   * Gives appropriate response (mirrored or canned) to user input
   * @param inputString the users last line of input
   * @return mirrored or canned response to user input  
   */
  public String respond(String inputString) {
    if(inputString.equals("")){ // If empty string returns canned response
      return "Nothing to say? ";  
    }

    String returnString = ""; // creates retrun string 
    String originalString = inputString;  // saves a copy of the original input for comparison

    // Splits String along spaces and removes them into an array of String objects
    String[] wordArray = inputString.split(" ");  

    for(int i = 0; i < wordArray.length; i ++){ // iterates through the words in the input
      for(int j = 0; j < words.length; j ++){ // iterates through the mirror words
        if(wordArray[i].compareToIgnoreCase(words[j]) == 0){  // checks if the mirror word is in the String object
          if(Character.isUpperCase(wordArray[i].charAt(0))){  // checks if the word is uppercase
            wordArray[i] = replacementWords[j].substring(0, 1).toUpperCase() + replacementWords[j].substring(1);
          }
          else{ // lowercase case
            wordArray[i] = replacementWords[j];
          }
          break;  // to prevent changing a word that has already been changed 
        }
      }
    }

    for(int i = 0; i < wordArray.length; i ++){ // compiles all the Strings into one Sring
      returnString += wordArray[i]; 
      if(i != wordArray.length - 1){ returnString += " ";}  // don't want an extra space at the end
    }

    if(inputString.endsWith(" ")){  // if there was a space at the end in the input we add that back in here
      returnString += " ";
    }
    
    if (returnString.equals(originalString)){ // check if the return string is the same and if so return a canned answer
      return "Ok!";
    }
    return returnString;
  }

  public static void main(String[] arguments) {

    Conversation myConversation = new Conversation();
    myConversation.chat();
    myConversation.printTranscript();

  }
}
