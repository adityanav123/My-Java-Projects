import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class ComputeInfo {
    double operand1;
    double operand2;
    String operation;

    double result;

    public ComputeInfo() {}

    @Override
    public String toString() {
        return "<" + this.operand1 + " " + this.operation + " " +  this.operand2 + " = " + this.result + ">";
    }

    public void setOperand1(double operand1) {
        this.operand1 = operand1;
    }

    public void setOperand2(double operand2) {
        this.operand2 = operand2;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setResult(double result) {
        this.result = result;
    }
}

class SimpleCalculator {
    private double operand1;
    private double operand2;

    // HISTORY
    public static List<ComputeInfo> history = new ArrayList<>();

    public SimpleCalculator (double a, double b) {
        operand1 = a;
        operand2 = b;
    }

    private void storeToHistory (double result, String op) {
        ComputeInfo operationInformation = new ComputeInfo();
        operationInformation.setOperand1(this.operand1);
        operationInformation.setOperand2(this.operand2);
        operationInformation.setOperation(op);
        operationInformation.setResult(result);
        System.out.println("storing to history : " + operationInformation);

        history.add(operationInformation); // storing to history
    }

    public void add() {
        double result = (this.operand2 + this.operand1);
        System.out.println(this.getOperand1() + " + " + this.getOperand2() + " = " + result);
        // store in history
        storeToHistory(result, "+");
    }

    public void subtract () {
        double result = (this.operand1 - this.operand2);
        System.out.println(this.getOperand1() + " - " + this.getOperand2() + " = " + result);
        // store in history
        storeToHistory(result, "-");
    }

    public void multiply() {
        double result = (this.operand2 * this.operand1);
        System.out.println(this.getOperand1() + " * " + this.getOperand2() + " = " + result);
        // store in history
        storeToHistory(result, "*");
    }

    public void divide() {
        double result = (this.operand1 / this.operand2);
        System.out.println(this.getOperand1() + " / " + this.getOperand2() + " = " + result);
        // store in history
        storeToHistory(result, "/");
    }

    public void getValues() {
        System.out.println("please enter 2 values : ");
        Scanner sc = new Scanner(System.in);
        this.operand1 = sc.nextDouble();
        this.operand2 = sc.nextDouble();
    }

    public double getOperand1() {
        return operand1;
    }

    public double getOperand2() {
        return operand2;
    }
    public void seeComputeHistory() {
        System.out.println("computation history : " + SimpleCalculator.history);
    }

    // fetch operand from history
    public void fetchFromHistory () {
        System.out.println("fetching data from last history, it will be stored in operand1 and operand2 will be as it is.");
        if (history.isEmpty()) {
            System.out.println("history empty, not changing already input values.");
            return;
        }
        this.setOperand1(history.get(history.size()-1).result);
    }

    public void setOperand1(double operand1) {
        this.operand1 = operand1;
    }

    public void setOperand2(double operand2) {
        this.operand2 = operand2;
    }

    public void seeCurrentOperands() {
        if(operand2 != Double.MAX_VALUE)
            System.out.println("Operand 1 = " + this.operand1 + "\nOperand2 = " + this.operand2);
        else
            System.out.println("no operands");

    }
}




public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        SimpleCalculator calculator = new SimpleCalculator(0.0, 0.0);

        double op1 = Double.MAX_VALUE, op2 = Double.MAX_VALUE;

        List<Integer> validChoices = new ArrayList<>(List.of(1, 2, 3, 4));

        while (true) {
            System.out.println("-------------------------------------------------------------------------------------------");
            System.out.println("Enter Choice : ");
            System.out.println("""
                    0.Get New Values [default 0.0, 0.0]
                    1.Addition
                    2.Subtraction
                    3.Multiplication
                    4.Division
                    5.See Computation History
                    6.Fetch Value from history
                    7.See Current Value
                    10.QUIT""");
            System.out.print("[choice] = ");
            int choice = sc.nextInt();

//            if (validChoices.contains(choice)) {
//                System.out.print("enter 2 values : ");
//                op1 = sc.nextDouble();
//                op2 = sc.nextDouble();
//                calculator.setOperand1(op1);
//                calculator.setOperand2(op2);
//            }



            switch (choice) {
                case 0 -> calculator.getValues();
                case 1 -> calculator.add();
                case 2 -> calculator.subtract();
                case 3 -> calculator.multiply();
                case 4 -> calculator.divide();
                case 5 -> calculator.seeComputeHistory();
                case 6 -> calculator.fetchFromHistory();
                case 7 -> calculator.seeCurrentOperands();
                case 10 -> System.exit(1);
                default -> System.out.println("invalid option, try again.");
            }
        }

    }
}