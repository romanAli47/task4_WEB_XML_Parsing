package by.alikevich.task4.builder;

import by.alikevich.task4.entity.BankDeposite;
import by.alikevich.task4.exception.XmlBuilderException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;
public class SAXDepositeParser extends AbstractBankDepositeBuilder {

    private static final Logger LOGGER = LogManager.getLogger(SAXDepositeParser.class);

    private ParserHandler handler;

    public SAXDepositeParser() {

        handler = new ParserHandler();
    }
    @Override
    public void initBankDeposite(String fileName) throws XmlBuilderException {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {

            SAXParser parser = factory.newSAXParser();
            parser.parse(new File(fileName), handler);
            bankDeposite = handler.getBankDeposite();
        } catch (ParserConfigurationException | SAXException e) {

            LOGGER.error("Error creating xml reader.",e);
            throw new RuntimeException(e);
        } catch (IOException e) {

            throw new XmlBuilderException("Error in I/O stream: "+ e +" with file: ",fileName);
        }
    }
}
