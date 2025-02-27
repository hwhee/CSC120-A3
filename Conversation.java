import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

/**
 * includes all attributes, constructor, and methods to run the chatbot. Methods include the chat function, the respond function, and the print transcript funtion.
 * @author Hanna Wheeler
 * @version February 20th, 2025
 */
class Conversation implements Chatbot { 

  // Attributes 
  int rounds;
  String[] transcript;
  int transcript_index;
  Random random = new Random();
  String[] canned_responses = {"That's awesome.", "Coooooool", "No way!!", "Mm-hmm", "Swag", "Right...", "Aw man!"};
  
  /**
   * Constructor for chatbot
   */
  Conversation() {
    this.transcript = new String[0]; // placeholder size for array
    this.transcript_index = 0;
  }

  /**
   * Starts and runs the conversation with the user
   */
  public void chat() {
    
    Scanner input = new Scanner(System.in);
    System.out.println("How many rounds?");
    int rounds = input.nextInt(); // after asking user for rounds of conversation desired, reads the input and stores the int in "rounds"
    input.nextLine();
    transcript = new String[(rounds+1)*2]; // creates array to store each round of conversation in of the transcript. Array is the desired length of conversation.
    if (rounds>0){
      System.out.println("Oh, hey! What's on your mind"); // starts conversation
      transcript[transcript_index++] = "Hey, what's on your mind"; // adds start of conversation to transcript array
    }
    

    //loop goes on for as many rounds as user asked for, getting user input each time and then giving a response, adding everything to the transcript array
    for (int i = 0; i<rounds; i++){
      String user_input = input.nextLine();
      transcript[transcript_index++] = user_input;
      String bot_response = respond(user_input);
      System.out.println(bot_response);
      transcript[transcript_index++] = bot_response;
    }

    //prints goodbye and adds it to the transcript
    System.out.println("Ok... um... bye!");
    transcript[transcript_index++] = "Ok... um... bye!";

    //closes transcript
    input.close();

  }

  /**
   * Prints transcript of conversation
   */
  public void printTranscript() {
    System.out.println("\n TRANSCRIPT:");
    for (int i = 0; i<transcript_index; i++){ 
      System.out.println(transcript[i]);
    }
    
  }

  /**
   * Gives appropriate response (mirrored or canned) to user input
   * @param inputString the users last line of input
   * @return modifiedSentence mirrored response to user input or canned_responses canned response to user input 
   */
  public String respond(String inputString) {

    // split the inputString into a list of words using .split(" ")
    String[] words = inputString.split(" ");

    boolean changed = false;

    for (int i = 0; i < words.length; i++ ){
      if (words[i].equalsIgnoreCase("I")){
        words[i] = "you";
        changed = true;
      } else if (words[i].equalsIgnoreCase("me")){
        words[i] = "you";
        changed = true;
      } else if (words[i].equalsIgnoreCase("am")){
        words[i] = "are";
        changed = true;
      } else if (words[i].equalsIgnoreCase("you")){
        words[i] = "I";
        changed = true;
      } else if (words[i].equalsIgnoreCase("my")){
        words[i] = "your";
        changed = true;
      } else if (words[i].equalsIgnoreCase("your")){
        words[i] = "my";
        changed = true;
      }
    }

    if (changed){
      ArrayList<String> sentence = new ArrayList<>();
      for (String word : words){
          sentence.add(word);
      }
      String modifiedSentence = String.join(" ", sentence);
      return modifiedSentence;
      
    } else {
      return canned_responses[random.nextInt(canned_responses.length)];
    }
    
  }

/**
 * main runs all code, calling each different method
 * @param arguments
 */
  public static void main(String[] arguments) {

    Conversation myConversation = new Conversation();
    myConversation.chat();
    myConversation.printTranscript();

  }
}
