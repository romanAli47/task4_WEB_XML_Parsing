package by.alikevich.task4.builder;

import by.alikevich.task4.entity.*;
import by.alikevich.task4.exception.XmlBuilderException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;



public class STAXDepositeParser extends AbstractBankDepositeBuilder {

    private XMLInputFactory inputFactory;
    private static final Logger LOGGER = LogManager.getLogger(STAXDepositeParser.class);

    public  STAXDepositeParser()
    {
        inputFactory = XMLInputFactory.newInstance();
    }
    @Override
    public void initBankDeposite(String fileName) throws XmlBuilderException {

        FileInputStream inputStream = null;
        XMLStreamReader reader;
        String name;

        try {

            inputStream = new FileInputStream(new File(fileName));
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (ParserType.DEMAND.getParser().equals(name)) {

                       BankDeposite depositeAccount = buildDeposite(reader, ParserType.DEMAND.getParser());

                         bankDeposite.add(depositeAccount);
                        LOGGER.log(Level.INFO, "Element was add to collection. Current repository:\n" + depositeAccount);
                    } else if (ParserType.URGENT.getParser().equals(name)) {

                        BankDeposite depositeAccount = buildDeposite(reader, ParserType.URGENT.getParser());

                         bankDeposite.add(depositeAccount);
                        LOGGER.log(Level.INFO, "Element was add to collection. Current repository:\n" + depositeAccount);

                    } else if (ParserType.ESTIMATED.getParser().equals(name)) {

                        BankDeposite depositeAccount = buildDeposite(reader,  ParserType.ESTIMATED.getParser());

                        bankDeposite.add(depositeAccount);
                        LOGGER.log(Level.INFO, "Element was add to collection. Current repository:\n" + depositeAccount);

                    } else if (ParserType.ACCUMUTATION.getParser().equals(name)) {

                        BankDeposite depositeAccount = buildDeposite(reader,  ParserType.ACCUMUTATION.getParser());

                         bankDeposite.add(depositeAccount);
                        LOGGER.log(Level.INFO, "Element was add to collection. Current repository:\n" + depositeAccount);

                    } else if (ParserType.SAVING.getParser().equals(name)) {

                        BankDeposite depositeAccount = buildDeposite(reader,  ParserType.SAVING.getParser());

                        bankDeposite.add(depositeAccount);
                        LOGGER.log(Level.INFO, "Element was add to collection. Current repository:\n" + depositeAccount);

                    } else if (ParserType.METAL.getParser().equals(name)) {

                        BankDeposite depositeAccount = buildDeposite(reader,  ParserType.METAL.getParser());

                        bankDeposite.add(depositeAccount);
                        LOGGER.log(Level.INFO, "Element was add to collection. Current repository:\n" + depositeAccount);

                    }
                }
            }
        } catch (XMLStreamException e) {

            throw new XmlBuilderException("StAX parsing error: "+ e);

        } catch (FileNotFoundException e) {

            throw new XmlBuilderException("File " + fileName + " not found "+e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {

                throw new XmlBuilderException("Impossible to close file:  " + fileName +". "+ e);
            }
        }
    }
    private BankDeposite buildDeposite(XMLStreamReader reader , String typeDeposite) throws XMLStreamException {

        BankDeposite depositePart = null;
/*
        String accountId=reader.getAttributeValue(null,"account-id");
        String country=reader.getAttributeValue(null,"country");
        String bankName=reader.getAttributeValue(null,"bankname");
*/

           /* String depositor=reader.getText("depositor");
            String amount = reader.getText("amount").item(0).getTextContent();
            String profitability = reader.getText("profitability").item(0).getTextContent();
            String timeConstraints = reader.getText("timeConstraints").item(0).getTextContent();*/
        switch (typeDeposite) {
            case DEMAND:

                depositePart = new Demand();
                elementInit(reader, depositePart);

                break;
            case URGENT:

                 depositePart = new Urgent();
                elementInit(reader, depositePart);

                break;
            case ESTIMETED:

                 depositePart = new Estimated();
                elementInit(reader, depositePart);

                break;
            case ACCUMUTATION:

                 depositePart = new Accumutation();
                elementInit(reader, depositePart);

                break;
            case SAVING:

                 depositePart = new Saving();
                elementInit(reader, depositePart);

                break;
            case METAL:

                 depositePart = new Metal();
                elementInit(reader, depositePart);

                break;
            default:
                LOGGER.warn("Unknown parameter.");
                break;
        }
        return depositePart;
    }
    private BankDeposite elementInit(XMLStreamReader reader, BankDeposite depositeType) throws XMLStreamException {

        depositeType.setAccountId(Integer.parseInt(reader.getAttributeValue(null,"account-id")));
        depositeType.setCountry(reader.getAttributeValue(null,"country"));
        depositeType.setBankName(reader.getAttributeValue(null,"bankname"));

        String name;
        try {
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();

                    int subType = type;

                    switch (ParserType.valueOf(name.toUpperCase())) {
                        case DEPOSITOR:
                            name = getXMLText(reader);
                            depositeType.setDepositor(name);

                            break;
                        case AMOUNT:
                            name = getXMLText(reader);
                            depositeType.setAmount(Integer.valueOf(name));
                            break;
                        case PROFITABILITY:
                            name = getXMLText(reader);
                            depositeType.setProfitability(Integer.valueOf(name));
                            break;
                        case TIMECONSTRAINTS:
                            name = getXMLText(reader);
                            depositeType.setTimeConstraints(Integer.valueOf(name));
                            break;
                        default:
                            LOGGER.warn("Unknown parameter.");
                            break;
                    }
                } else if(type == XMLStreamConstants.END_ELEMENT) {

                        name = reader.getLocalName();
                        if (name.equals(ParserType.DEMAND.getParser()) || name.equals(ParserType.URGENT.getParser()) ||
                                name.equals(ParserType.ESTIMATED.getParser()) || name.equals(ParserType.ACCUMUTATION.getParser()) ||
                                name.equals(ParserType.SAVING.getParser()) || name.equals(ParserType.METAL.getParser())) {

                            return depositeType;
                        }
                }
            }
        } catch (XMLStreamException e) {
            System.out.println(e);
        }
        throw new XMLStreamException("Unknown XML tag found");
    }
    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
