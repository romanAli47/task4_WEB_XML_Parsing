package by.alikevich.task4.builder;

import by.alikevich.task4.entity.*;
import org.apache.logging.log4j.Level;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DOMDepositeParser extends AbstractBankDepositeBuilder{

    private static final Logger LOGGER = LogManager.getLogger(DOMDepositeParser.class);
    private DocumentBuilder documentBuilder;

    public DOMDepositeParser(){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try{
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {

            LOGGER.error("Exception while creating document builder for DOM.",e);
            throw new RuntimeException(e);
        }
    }
    @Override
    public void initBankDeposite(String fileName) {

        try {
            File XmlFile = new File(fileName);
            Document document = documentBuilder.parse(XmlFile);
            document.getDocumentElement().normalize();

            NodeList demandList = document.getElementsByTagName(ParserType.DEMAND.getParser());
            buildDeposite(demandList,ParserType.DEMAND.getParser());

            NodeList urgentList = document.getElementsByTagName(ParserType.URGENT.getParser());
            buildDeposite(urgentList,ParserType.URGENT.getParser());

            NodeList estimetedList = document.getElementsByTagName(ParserType.ESTIMATED.getParser());
            buildDeposite(estimetedList,ParserType.ESTIMATED.getParser());

            NodeList accumutationList = document.getElementsByTagName(ParserType.ACCUMUTATION.getParser());
            buildDeposite(accumutationList,ParserType.ACCUMUTATION.getParser());

            NodeList savingList = document.getElementsByTagName(ParserType.SAVING.getParser());
            buildDeposite(savingList,ParserType.SAVING.getParser());

            NodeList matalList = document.getElementsByTagName(ParserType.METAL.getParser());
            buildDeposite(matalList,ParserType.METAL.getParser());

        } catch (SAXException | IOException e) {

            LOGGER.error("Exception while parsing devices.",e);
            throw new RuntimeException(e);

        }
    }

    private void buildDeposite(NodeList nodeList, String typeDeposite) {

        for(int i = 0; i < nodeList.getLength(); i++) {

            Node nNode = nodeList.item(i);
            Element element = (Element) nNode;

            String accountId=element.getAttribute("account-id");
            String country=element.getAttribute("country");
            String bankName=element.getAttribute("bankname");
            String depositor=element.getElementsByTagName("depositor").item(0).getTextContent();
            String amount = element.getElementsByTagName("amount").item(0).getTextContent();
            String profitability = element.getElementsByTagName("profitability").item(0).getTextContent();
            String timeConstraints = element.getElementsByTagName("timeConstraints").item(0).getTextContent();

            switch (typeDeposite) {
                case DEMAND:

                    BankDeposite demand = new Demand(depositor, Integer.parseInt(amount), Integer.parseInt(profitability), Integer.valueOf(timeConstraints),
                           country, Integer.parseInt(accountId), bankName);

                    bankDeposite.add(demand);
                    break;
                case URGENT:

                    BankDeposite urgent = new Urgent(depositor, Integer.parseInt(amount), Integer.parseInt(profitability), Integer.valueOf(timeConstraints),
                            country, Integer.parseInt(accountId), bankName);

                    bankDeposite.add(urgent);
                    break;
                case ESTIMETED:

                    BankDeposite estimated = new Estimated(depositor, Integer.parseInt(amount), Integer.parseInt(profitability), Integer.valueOf(timeConstraints),
                            country, Integer.parseInt(accountId), bankName);

                    bankDeposite.add(estimated);
                    break;
                case ACCUMUTATION:

                    BankDeposite accumutation = new Accumutation(depositor, Integer.parseInt(amount), Integer.parseInt(profitability), Integer.valueOf(timeConstraints),
                            country, Integer.parseInt(accountId), bankName);

                    bankDeposite.add(accumutation);
                    break;
                case SAVING:

                    BankDeposite saving = new Saving(depositor, Integer.parseInt(amount), Integer.parseInt(profitability), Integer.valueOf(timeConstraints),
                            country, Integer.parseInt(accountId), bankName);

                    bankDeposite.add(saving);
                    break;
                case METAL:

                    BankDeposite metal = new Metal(depositor, Integer.parseInt(amount), Integer.parseInt(profitability), Integer.valueOf(timeConstraints),
                            country, Integer.parseInt(accountId), bankName);

                    bankDeposite.add(metal);
                    break;
                    default:
                        LOGGER.warn("Unknown parameter.");
                        break;
            }
        }
    }

}
