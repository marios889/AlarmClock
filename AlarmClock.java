import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AlarmClock implements Runnable{
    private final LocalTime alarmClock;
    private final String filePath;
    private final Scanner scanner;
    

    public AlarmClock(LocalTime alarmClock,String filePath,Scanner scanner) {
        this.alarmClock = alarmClock;
        this.filePath = filePath;
        this.scanner = scanner;
    }
    @Override
    public void run()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
        LocalTime now = LocalTime.now();
        String formattedTime;
        while(now.isBefore(alarmClock))
        {
            now = LocalTime.now();
            formattedTime = now.format(formatter);
            try{
                Thread.sleep(1000);
                System.out.printf("\r%s",formattedTime);
            }
            catch(Exception e)
            {
                System.out.println("Something went Wrong");
            }
        }
       playSound(filePath);
        
    } 
    private void playSound(String filePath)
    {
        File audiofile = new File(filePath);
        try(AudioInputStream inputsteam = AudioSystem.getAudioInputStream(audiofile);) {
            Clip clip = AudioSystem.getClip();
            clip.open(inputsteam);
            clip.start();
            System.out.println("\n*Alarm Working* \nYou can Click Enter To Stop");
            String input = scanner.nextLine();
            clip.close();
            scanner.close();
            

        } catch (UnsupportedAudioFileException e) {
           System.out.println("Audio format not supported");
        } catch (IOException e) {
           System.out.println("Something Wrong");
        } catch (LineUnavailableException e) {
           System.out.println("Line failed to Open");
        }
    }
}
