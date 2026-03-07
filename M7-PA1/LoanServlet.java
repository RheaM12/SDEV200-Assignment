import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/LoanServlet")  
public class LoanServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Get form parameters
            double loanAmount = Double.parseDouble(request.getParameter("loanAmount"));
            double annualInterestRate = Double.parseDouble(request.getParameter("annualInterestRate"));
            int numOfYears = Integer.parseInt(request.getParameter("numOfYears"));

            // Create Loan object
            Loan loan = new Loan(annualInterestRate, numOfYears, loanAmount);

            // Compute payments
            double monthlyPayment = loan.getMonthlyPayment();
            double totalPayment = loan.getTotalPayment();

            // Display results
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><head><title>Loan Payment Result</title></head><body>");
            out.println("<h2>Loan Payment Result</h2>");
            out.printf("<p>Loan Amount: $%.2f</p>%n", loanAmount);
            out.printf("<p>Annual Interest Rate: %.2f%%</p>%n", annualInterestRate);
            out.printf("<p>Number of Years: %d</p>%n", numOfYears);
            out.printf("<p>Monthly Payment: $%.2f</p>%n", monthlyPayment);
            out.printf("<p>Total Payment: $%.2f</p>%n", totalPayment);
            out.println("</body></html>");

        } catch (NumberFormatException e) {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h3>Invalid input. Please enter numeric values!</h3>");
            out.println("</body></html>");
        }
    }
}