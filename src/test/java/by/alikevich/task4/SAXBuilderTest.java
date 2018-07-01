package by.alikevich.task4;

import by.alikevich.task4.builder.BankBuilderFactory;
import by.alikevich.task4.entity.BankDeposite;
import by.alikevich.task4.exception.XmlBuilderException;
import org.testng.Assert;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.testng.AssertJUnit.fail;

public class SAXBuilderTest {

    private LinkedList<BankDeposite> deposites;

    @Test
    public void papersArrayTest() throws XmlBuilderException {

            int EXPECTED=17;

            String filePath = "data/banks.xml";
            deposites = BankBuilderFactory.parserCreator("SAX", filePath);

            Assert.assertEquals(deposites.size(),EXPECTED);
    }
}
