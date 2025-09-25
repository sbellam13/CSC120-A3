import java.util.Scanner;
class Conversation implements ConversationRequirements {

  // Attributes 
  private int numRounds;
  private String[] transcript;
  private Scanner input;
  private static String[] words = {"I", "me", "am", "you", "my", "your", "are"};
  private static String[] replacementWords = {"you", "you", "are", "I", "your", "my", "am"};

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
    numRounds = input.nextInt();
    transcript = new String[numRounds*2 + 1];
    input.nextLine(); // To prevent errors since input.nextInt() doesn't read \n
    System.out.println(); // Adds in a paragraph break

    System.out.println("Hi there! What's on your mind? ");
    transcript [0] = "Hi there! What's on your mind? \n"; // Update transcript

    for(int i = 0; i < numRounds; i ++){
      currentInput = input.nextLine();
      transcript[2*i+1] = currentInput + "\n";

      currentOutput = respond(currentInput);
      System.out.println(currentOutput);
      transcript[2*i] = currentOutput + "\n"; // Update transcript
    }

    System.out.println("See ya!\n");
    transcript [transcript.length - 1] =  "See ya!";
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
    if(inputString.equals("")){
      return "Nothing to say? ";
    }

    String returnString = "";
    String originalString = inputString;
    inputString = inputString.replaceAll("I'm", "I am");
    inputString = inputString.replaceAll("You're", "You are");
    inputString = inputString.replaceAll("you're", "you are");

    String[] wordArray = inputString.split(" ");

    for(int i = 0; i < wordArray.length; i ++){
      for(int j = 0; j < words.length; j ++){
        if(wordArray[i].compareToIgnoreCase(words[j]) == 0){
          if(Character.isUpperCase(wordArray[i].charAt(0))){
            wordArray[i] = replacementWords[j].substring(0, 1).toUpperCase() + replacementWords[j].substring(1);
          }
          else{
            wordArray[i] = replacementWords[j];
          }
          break;
        }
      }
    }

    for(int i = 0; i < wordArray.length; i ++){
      returnString += wordArray[i];
      if(i != wordArray.length - 1){ returnString += " ";}
    }

    if(inputString.charAt(inputString.length() - 1) == ' '){
      returnString += " ";
    }
    
    if (returnString.equals(originalString)){
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
