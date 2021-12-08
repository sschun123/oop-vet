public class Dog extends Pet {
    private double droolRate;

    Dog(String name, double health, int painLevel, double droolRate) {
        super(name, health, painLevel);
        this.droolRate = droolRate;
    }

    Dog(String name, double health, int painLevel) {
        this(name, health, painLevel, 5.0);
    }

    public double getDroolRate() {
        return droolRate;
    }

    public int treat() {
        super.heal();

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
