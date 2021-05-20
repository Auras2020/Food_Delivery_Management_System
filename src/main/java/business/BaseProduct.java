package business;

public class BaseProduct extends MenuItem{

    private String title;
    private double rating;
    private int calories;
    private int protein;
    private int fat;
    private int sodium;
    private int price;

    public BaseProduct(String title, double rating,int calories, int protein, int fat, int sodium, int price){
        super(title, price);
        this.rating=rating;
        this.calories=calories;
        this.protein=protein;
        this.fat=fat;
        this.sodium=sodium;
    }

    @Override
    public int computePrice() {
        return this.getPrice();
    }

    public String toString(){
        return "Produsul " + this.getTitle() + " rating " + rating + " calories " + calories + " proteins " + protein
                + " fat " + fat + " sodium " + sodium + " price " + this.getPrice() + "\n";
    }

    public double getRating() {
        return rating;
    }

    public int getCalories() {
        return calories;
    }

    public int getProtein() {
        return protein;
    }

    public int getFat() {
        return fat;
    }

    public int getSodium() {
        return sodium;
    }
}
