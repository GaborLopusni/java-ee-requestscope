package com.servlet.example;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

@WebServlet(urlPatterns = "/encrypt")
public class EncryptServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String firstName = (String) request.getAttribute("fname");
        String lastName = (String) request.getAttribute("lname");
        String vowels = "AEIOUaeiou";
        Random random;

        StringBuilder sb = new StringBuilder(firstName);
        sb.append(lastName);
        String reversedName = sb.reverse().toString();
        char[] unencryptedName = reversedName.toCharArray();

        for(int i = 0; i < unencryptedName.length; i++) {
            if(vowels.lastIndexOf(unencryptedName[i]) > -1) {
                random = new Random();
                int randomNumber = random.nextInt(95 - 35) + 35;
                unencryptedName[i] = (char) randomNumber;
            }
        }
        String encryptedName = new String(unencryptedName);

        StringBuilder htmlText = new StringBuilder("<html>");
        htmlText.append("<body>");
        htmlText.append("<h1>Developer Starter Page</h1>");
        htmlText.append("<p>Encrypted User Name: </p>");
        htmlText.append(encryptedName);
        htmlText.append("<p>Database connection is:");
        htmlText.append((String)request.getServletContext().getAttribute("dbConnection"));
        htmlText.append("</p>");
        htmlText.append("</body>");
        htmlText.append("</html>");
        PrintWriter writer = response.getWriter();

        writer.write(String.valueOf(htmlText));

        writer.close();
    }
}
