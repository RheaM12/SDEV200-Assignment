<%@ page import="java.util.Random" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%
    // Number of quiz questions
    int numQuestions = 10;  

    // Check if the form was submitted
    String submitted = request.getParameter("submit");

    if (submitted == null) {
        //  generate random questions
        Random rand = new Random();
        List<Integer> num1List = new ArrayList<>();
        List<Integer> num2List = new ArrayList<>();

        for (int i = 0; i < numQuestions; i++) {
            num1List.add(rand.nextInt(50) + 1); // 1 to 50
            num2List.add(rand.nextInt(50) + 1);
        }

        // Store questions in session
        session.setAttribute("num1List", num1List);
        session.setAttribute("num2List", num2List);
%>
<html>
<head>
    <title>Addition Quiz</title>
</head>
<body>
    <h2>Addition Quiz</h2>
    <form method="post" action="AdditionQuiz.jsp">
        <%
            for (int i = 0; i < numQuestions; i++) {
                int n1 = num1List.get(i);
                int n2 = num2List.get(i);
        %>
            <p>Question <%= (i + 1) %>: <%= n1 %> + <%= n2 %> = 
                <input type="text" name="answer<%= i %>" required>
            </p>
        <%
            }
        %>
        <input type="submit" name="submit" value="Submit Answers">
    </form>
</body>
</html>
<%
    } else {
        // Form submitted: check answers
        List<Integer> num1List = (List<Integer>) session.getAttribute("num1List");
        List<Integer> num2List = (List<Integer>) session.getAttribute("num2List");
        int score = 0;

        %>
        <html>
        <head>
            <title>Quiz Results</title>
        </head>
        <body>
            <h2>Quiz Results</h2>
            <%
                for (int i = 0; i < numQuestions; i++) {
                    int n1 = num1List.get(i);
                    int n2 = num2List.get(i);
                    int correct = n1 + n2;
                    String userAnswerStr = request.getParameter("answer" + i);
                    int userAnswer = 0;
                    try {
                        userAnswer = Integer.parseInt(userAnswerStr);
                    } catch (NumberFormatException e) {
                        userAnswer = -1; // invalid input counts as wrong
                    }

                    if (userAnswer == correct) {
                        score++;
                    }
            %>
                <p>Question <%= (i + 1) %>: <%= n1 %> + <%= n2 %> = <%= correct %> 
                   | Your Answer: <%= userAnswerStr %> 
                   <% if (userAnswer == correct) { %> ✅ Correct <% } else { %> ❌ Wrong <% } %>
                </p>
            <%
                }
            %>
            <h3>Your Total Score: <%= score %> / <%= numQuestions %></h3>
        </body>
        </html>
<%
        // Clear session after quiz
        session.removeAttribute("num1List");
        session.removeAttribute("num2List");
    }
%>