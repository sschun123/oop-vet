public class InvalidPetException extends Exception {
    InvalidPetException() {
        super("Your pet is invalid!");
    }

    InvalidPetException(String s) {
        super(s);
    }
}
