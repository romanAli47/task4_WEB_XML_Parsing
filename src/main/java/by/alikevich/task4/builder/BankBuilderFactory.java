package by.alikevich.task4.builder;

import by.alikevich.task4.entity.BankDeposite;
import by.alikevich.task4.exception.XmlBuilderException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;

public class BankBuilderFactory {

    private static final Logger LOGGER = LogManager.getLogger(BankBuilderFactory.class);

    private enum ParserType {
       SAX, StAX, DOM
   }

     public static LinkedList<BankDeposite> parserCreator(String parserType,  String filePath) throws XmlBuilderException {

         AbstractBankDepositeBuilder depositeBuilder = null;
         ParserType parser = ParserType.valueOf(parserType);
         LinkedList<BankDeposite> bankDeposite;

         switch (parser) {

             case SAX:

                 depositeBuilder = new SAXDepositeParser();
                 break;
             case DOM:

                 depositeBuilder = new DOMDepositeParser();
                 break;
             case StAX:

                 depositeBuilder = new STAXDepositeParser();
                 break;

                 default:
                     LOGGER.warn("IlligalArgumentException invalid argument");
                     break;
         }
         depositeBuilder.initBankDeposite(filePath);
         bankDeposite = depositeBuilder.findAllBankDeposite();

         return bankDeposite;
     }

    public static LinkedList<BankDeposite> getDemand(LinkedList<BankDeposite> deposites) {

        LinkedList<BankDeposite> newspapers=new LinkedList<>();

        for (BankDeposite demand : deposites) {

            String className=(demand.getClass().getSimpleName());

            if ("Demand".equals(className)) {

                newspapers.add(demand);
            }
        }
        return newspapers;
    }

}

