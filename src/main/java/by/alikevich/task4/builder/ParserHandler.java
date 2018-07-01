package by.alikevich.task4.builder;

import by.alikevich.task4.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.EnumSet;
import java.util.LinkedList;

public class ParserHandler extends DefaultHandler {

    private static final Logger LOGGER = LogManager.getLogger(ParserHandler.class);
    private BankDeposite deposite;
    private LinkedList<BankDeposite> bankDeposite = new LinkedList<>();
    private String qName;

    public LinkedList<BankDeposite> getBankDeposite() {
        return bankDeposite;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) {

        this.qName = qName;

        if (qName.equals(ParserType.DEMAND.getParser())) {

            deposite = new Demand();
        } else if (qName.equals(ParserType.URGENT.getParser())) {

            deposite = new Urgent();
        } else if (qName.equals(ParserType.ESTIMATED.getParser())) {

            deposite = new Estimated();
        } else if (qName.equals(ParserType.ACCUMUTATION.getParser())) {

            deposite = new Accumutation();
        } else if (qName.equals(ParserType.SAVING.getParser())) {

            deposite = new Saving();
        } else if (qName.equals(ParserType.METAL.getParser())) {

            deposite = new Metal();
        } else {
            LOGGER.warn("Unknown parameter." + qName);
        }

        for (int index = 0; index < attrs.getLength(); index++) {

            String attribute = attrs.getLocalName(index);
            String value = attrs.getValue(index);

            attributeFactory(attribute, value);
        }
    }

    private void attributeFactory(String attribute, String value) {

            if (attribute.equals(ParserType.BANK_NAME.getParser())) {

                deposite.setBankName(value);
            } else if (attribute.equals(ParserType.ACCOUNT_ID.getParser())) {
                deposite.setAccountId(Integer.parseInt(value));
            } else if (attribute.equals(ParserType.COUNTRY.getParser())) {
                deposite.setCountry(value);
            } else {
                LOGGER.warn("Unknown parameter.");
            }
    }

    @Override
    public void characters(char[] ch, int start, int length) {


        String value = new String(ch, start, length).trim();
        if(!value.equals("")) {
            if (qName.equals(ParserType.DEPOSITOR.getParser())) {

                deposite.setDepositor(value);
            } else if (qName.equals(ParserType.AMOUNT.getParser())) {

                deposite.setAmount(Integer.parseInt(value));
            } else if (qName.equals(ParserType.PROFITABILITY.getParser())) {

                deposite.setProfitability(Integer.parseInt(value));
            } else if (qName.equals(ParserType.TIMECONSTRAINTS.getParser())) {

                deposite.setTimeConstraints(Integer.parseInt(value));
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {

        if (qName.equals(ParserType.DEMAND.getParser()) || qName.equals(ParserType.URGENT.getParser()) ||
        qName.equals(ParserType.ESTIMATED.getParser()) || qName.equals(ParserType.ACCUMUTATION.getParser()) ||
        qName.equals(ParserType.SAVING.getParser()) || qName.equals(ParserType.METAL.getParser())) {

            bankDeposite.add(deposite);
        }
    }

    @Override
    public void warning(SAXParseException e) throws SAXException {

        LOGGER.warn("warning   : " + e.getMessage());
    }

    @Override
    public void error(SAXParseException e) throws SAXException {

        LOGGER.warn("error   : " + e.getMessage());
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {

        LOGGER.warn("fatalError   : " + e.getMessage());
    }
}
