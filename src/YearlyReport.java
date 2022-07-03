import java.util.ArrayList;

public class YearlyReport {
    ArrayList<YearlyRecord> yearlyRecords;
    Reading reading;
    
    YearlyReport(){
        this.yearlyRecords = new ArrayList<>();
        this.reading = new Reading();
    }

    void addRecord() {
        String contentOfFile = reading.readFileContentsOrNull("resources/y.2021.csv");
        String[] lines = contentOfFile.split("\n");
        for (int i = 1; i < lines.length; i++) {
            String[] content = lines[i].split(",");
            int month = Integer.valueOf(content[0]);
            double amount = Integer.valueOf(content[1]);
            boolean isExpense = Boolean.valueOf(content[2]);
            yearlyRecords.add(new YearlyRecord(month, amount, isExpense));
        }
    }
    double getYearIncome(Integer month){
        double income = 0;
        for(YearlyRecord yearlyRecord: yearlyRecords){
            if(!yearlyRecord.isExpense && yearlyRecord.month == month){
                income = yearlyRecord.amount;
            }
        }
        return income;
    }
    double getYearProfit(Integer month){
        double income = 0;
        for(YearlyRecord yearlyRecord: yearlyRecords){
            if(yearlyRecord.isExpense && yearlyRecord.month == month){
                income = yearlyRecord.amount;
            }
        }
        return income;
    }
    public void printYearInfo() {
        if (yearlyRecords.isEmpty()) {
            System.out.println("Файлы не считаны");
            return;
        }
        System.out.println("2022 год");
        double profit = 0;
        double profitAvg = 0;
        double count = 0;
        double income = 0;
        double incomeAvg = 0;
        String month = "";
        for(YearlyRecord yearlyRecord: yearlyRecords){
            if(yearlyRecord.isExpense){
                income += yearlyRecord.amount;
                incomeAvg += yearlyRecord.amount;
            } else {
                profit += yearlyRecord.amount;
                profitAvg += yearlyRecord.amount;
            }
            if (yearlyRecord.month == 1) {
                month = "Январь";
            } else if (yearlyRecord.month == 2) {
                month = "Февраль";
            } else if (yearlyRecord.month == 3) {
                month = "Март";
            }
            count++;
            if(count == 2){
                count = 0;
                System.out.println("Прибыль за месяц: " + month + " равна - " + (profit - income));
                income = 0;
                profit = 0;
            }
        }
        System.out.println("Средний расход за год равен - " + incomeAvg / (yearlyRecords.size()/2));
        System.out.println("Средний доход за год равен - " + profitAvg / (yearlyRecords.size()/2));
    }
}