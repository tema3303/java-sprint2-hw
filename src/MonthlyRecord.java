public class MonthlyRecord {
    String itemName;
    Boolean isExpense;
    int quantity;
    double sumOfOne;

    public MonthlyRecord(String itemName,boolean isExpense,int quantity, double sumOfOne){
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
    }

    @Override
    public String toString() {
        return
                "itemName='" + itemName + '\'' +
                ", isExpense=" + isExpense +
                ", quantity=" + quantity +
                ", sumOfOne=" + sumOfOne +
                '}';
    }

}
