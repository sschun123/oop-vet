public class Cat extends Pet {
    private int miceCaught;

    Cat(String name, double health, int painLevel, int miceCaught) {
        super(name, health, painLevel);
        this.miceCaught = miceCaught < 0 ? 0 : miceCaught;
    }

    Cat(String name, double health, int painLevel) {
        this(name, health, painLevel, 0);
    }

    public int getMiceCaught() {
        return miceCaught;
    }

    public int treat() {
        int minutes;
        if (miceCaught < 4) {
            minutes = (int) Math.ceil((this.getPainLevel() * 2) / this.getHealth());
        }
        else if (miceCaught >= 4 && miceCaught <= 7) {
            minutes = (int) Math.ceil(this.getPainLevel() / this.getHealth());
        }
        else {
            minutes = (int) Math.ceil(this.getPainLevel() / this.getHealth() * 2);
        }
        super.heal();
        return minutes;
    }

    public void speak() {
        super.speak();
        String meows = new String(new char[this.getPainLevel()]).replace("\0", "meow ");

        if (this.getPainLevel() > 5) {
            meows = meows.toUpperCase();
        }
        System.out.println(meows);
    }

    public boolean equals(Object o) {
        if (o instanceof Cat) {
            Cat d = (Cat) o;
            return super.equals(o) && d.getMiceCaught() == this.getMiceCaught();
        }
        return false;
    }
}