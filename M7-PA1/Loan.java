public class Loan {
    private double annualInterestRate;
    private int numberOfYears;
    private double loanAmount;

    // Constructor
    public Loan(double annualInterestRate, int numberOfYears, double loanAmount) {
        this.annualInterestRate = annualInterestRate;
        this.numberOfYears = numberOfYears;
        this.loanAmount = loanAmount;
    }

    // Compute monthly payment
    public double getMonthlyPayment() {
        double monthlyInterestRate = annualInterestRate / 1200;
        return loanAmount * monthlyInterestRate / 
               (1 - Math.pow(1 + monthlyInterestRate, -numberOfYears * 12));
    }

    // Compute total payment
    public double getTotalPayment() {
        return getMonthlyPayment() * numberOfYears * 12;
    }
}