package by.alikevich.task4.controller;

import by.alikevich.task4.builder.BankBuilderFactory;
import by.alikevich.task4.builder.SAXDepositeParser;
import by.alikevich.task4.entity.BankDeposite;
import by.alikevich.task4.exception.XmlBuilderException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/XMLServlet")
public class MainController extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(MainController.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        processRequest(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        processRequest(request,response);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String xmlTypeParser = req.getParameter("parser");

        ServletContext context = req.getServletContext();
        String filePath =context.getRealPath("/data/banks.xml");

       // String filePath = "/Users/Romanalikevich/Desktop/task4/data/banks.xml";
        try {
            LinkedList<BankDeposite> deposites = BankBuilderFactory.parserCreator(xmlTypeParser, filePath);
            req.setAttribute("parserValue", xmlTypeParser);
            req.setAttribute("bankDeposite", deposites);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/result.jsp");
            dispatcher.forward(req, resp);
        } catch (XmlBuilderException e) {

            LOGGER.error("Servlet exception: "+ e);
        }
    }
}
