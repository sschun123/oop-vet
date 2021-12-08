public abstract class Pet {
    private String name;
    private double health;
    private int painLevel;

    Pet(String name, double health, int painLevel) {
        this.name = name;
        this.health = health > 1.0 ? 1.0 : health;
        if (painLevel > 10) {
            this.painLevel = 10;
        }
        else if (painLevel < 1) {
            this.painLevel = 1;
        }
        else {
            this.painLevel = painLevel;
        }
    }

    public String getName() {
        return this.name;
    }
    public double getHealth() {
        return this.health;
    }
    public int getPainLevel() {
        return this.painLevel;
    }

    abstract int treat();

    public void speak() {
        String phrase = String.format("Hello! My name is %s", this.name);

        if (this.painLevel > 5) {
            phrase = phrase.toUpperCase();
        }
        System.out.println(phrase);
    }

    public boolean equals(Object o) {
        if (o instanceof Pet) {
            Pet p = (Pet) o;
            return p.name == this.name;
        }
        return false;
    }

    protected void heal() {
        this.health = 1.0;
        this.painLevel = 1;
    }
}
