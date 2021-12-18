import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Clinic {
    File patientFile;
    int day;

    Clinic(File file) {
        this.patientFile = file;
        this.day = 1;
    }

    Clinic(String fileName) {
        this(new File(fileName));
    }

    public String nextDay(File f) throws FileNotFoundException, InvalidPetException {
        Scanner fileScan = null;
        Scanner input = new Scanner(System.in);
        String[] tokens = null;
        String line = null;
        String name;
        String type;
        String time;
        String appointment = "";

        try {
            fileScan = new Scanner(f);
    
            while(fileScan.hasNextLine()) {
                line = fileScan.nextLine();
                System.out.println(line);
                tokens = line.split(",");
                name = tokens[0];
                type = tokens[1];
                time = tokens[3];
                if (!type.equals("Dog") && !type.equals("Cat")) {
                    throw new InvalidPetException();
                }
                System.out.printf("Consultation for %s the %s at %s.\n What is the health of %s?\n", name, type, time, name);
    
                double health = 0.0;
                try {
                    health = input.nextDouble();
                } catch(InputMismatchException e) {
                    input.nextLine();
                }
    
                System.out.printf("On a scale of 1 to 10, how much pain is %s in right now?\n", name);
                int pain = 0;
                try {
                    pain = input.nextInt();
                } catch(InputMismatchException e) {
                    input.nextLine();
                }
                
                Pet pet;
                switch (type) {
                    case "Cat":
                        pet = new Cat(name, health, pain, Integer.parseInt(tokens[2]));
                        pet = (Cat) pet;
                        break;
                    case "Dog":
                        pet = new Dog(name, health, pain, Double.parseDouble(tokens[2]));
                        pet = (Dog) pet;
                        break;
                    default:
                        throw new InvalidPetException();
                }
    
                pet.speak();
                int timeout = pet.treat();
    
                String dayEnd = Clinic.addTime(time, timeout);
                
                appointment += String.format("%s,%s,%s,Day %s,%s,%s,%.1f,%d\n", name, type, tokens[2], day, time, dayEnd, health, pain);
            }
        } finally {
            fileScan.close();
            input.close();
        }
        day += 1;
        return appointment;
    }

    String nextDay(String fileName) throws FileNotFoundException, InvalidPetException {
        return this.nextDay(new File(fileName));
    }

    static protected String addTime(String timeIn, int treatmentTime) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("hhmm");
        try {
            cal.setTime(sdf.parse(timeIn));
        } catch (ParseException exception) {
            exception.printStackTrace();
        }
        cal.add(Calendar.MINUTE, treatmentTime);
        return String.format("%02d%02d", cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE));
    }

    boolean addToFile(String patientInfo) {
        PrintWriter filePrint = null;
        try {
            filePrint = new PrintWriter(this.patientFile);
            filePrint.println(patientInfo);
        } 
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
        }
        finally {
            if (filePrint != null) {
                filePrint.close();
            }
        }
        return true;
    }
}
