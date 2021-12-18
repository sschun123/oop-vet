public class Dog extends Pet {
    private double droolRate;

    // public static void main(String[] args) {
    //     Dog carl = new Dog("carl", 0.36, 7, 10);
    //     carl.speak();
    //     int minutes = carl.treat();
    //     System.out.println(minutes);
    //     carl.speak();
    //     System.out.println(carl.equals(carl));
    // }

    Dog(String name, double health, int painLevel, double droolRate) {
        super(name, health, painLevel);
        this.droolRate = droolRate <= 0 ? 0.5 : droolRate;
    }

    Dog(String name, double health, int painLevel) {
        this(name, health, painLevel, 5.0);
    }

    public double getDroolRate() {
        return droolRate;
    }

    public int treat() {
        double minutes;
        if (droolRate < 3.5) {
            minutes = (this.getPainLevel() * 2) / this.getHealth();
        }
        else if (droolRate >= 3.5 && droolRate <= 7.5) {
            minutes = this.getPainLevel() / this.getHealth();
        }
        else {
            minutes = this.getPainLevel() / (this.getHealth() * 2);
        }
        super.heal();

        return (int) Math.ceil(minutes);
    }

    public void speak() {
        super.speak();
        String barks = new String(new char[this.getPainLevel()]).replace("\0", "bark ");

        if (this.getPainLevel() > 5) {
            barks = barks.toUpperCase();
        }
        System.out.println(barks);
    }

    public boolean equals(Object o) {
        if (o instanceof Dog) {
            Dog d = (Dog) o;
            return super.equals(o) && d.getDroolRate() == this.getDroolRate();
        }
        return false;
    }
}
