package concepts;

public abstract class Animal implements Eating {
    public static final String CATEGORY = "domestic";
    private String name;

    protected abstract String getSound();

    public Animal(String name){
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}