package by.alikevich.task4;

import by.alikevich.task4.builder.BankBuilderFactory;
import by.alikevich.task4.entity.BankDeposite;
import by.alikevich.task4.exception.XmlBuilderException;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.LinkedList;

public class DOMBuilderTest {

    private LinkedList<BankDeposite> demand;
    private LinkedList<BankDeposite> deposites;
    private String filePath = "/Users/Romanalikevich/Desktop/task4/src/main/webapp/data/banks.xml";

    @Test
    public void papersArrayTest() throws XmlBuilderException {

            int EXPECTED = 17;

            deposites = BankBuilderFactory.parserCreator("DOM", filePath);

            Assert.assertEquals(deposites.size(),EXPECTED);
    }

    @Test
    public void demandArrayTest() throws XmlBuilderException {

            int EXPECTED = 3;

            deposites = BankBuilderFactory.parserCreator("DOM", filePath);

            demand = BankBuilderFactory.getDemand(deposites);

            Assert.assertEquals(demand.size(),EXPECTED);
    }
}
