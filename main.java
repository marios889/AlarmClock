import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

class main{
    public static void main(String[] args) {
       Scanner scanner  = new Scanner(System.in);
       LocalTime alarm = null;
       while(alarm == null)
       {
       try{
       System.out.print("Enter The alarm Time (HH:mm am/pm): ");
       String inputTime = scanner.nextLine();
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
       alarm = LocalTime.parse(inputTime,formatter);
       }
       catch(DateTimeParseException e)
       {
            System.out.println("Invalid format use (HH:mm am/pm)");
       }
       }
       String filePath = "my own personal jesus.wav";
       AlarmClock alarmClock = new AlarmClock(alarm,filePath,scanner);
       Thread thread = new Thread(alarmClock);
       thread.start();






      
      
    }
            
                
       
          
}
