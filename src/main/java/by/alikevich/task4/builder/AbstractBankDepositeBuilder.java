package by.alikevich.task4.builder;

import by.alikevich.task4.entity.BankDeposite;
import by.alikevich.task4.exception.XmlBuilderException;

import java.util.LinkedList;

public abstract class AbstractBankDepositeBuilder {

    protected LinkedList<BankDeposite> bankDeposite;
    static final String DEMAND = "demand";
    static final String URGENT = "urgent";
    static final String ESTIMETED = "estimated";
    static final String ACCUMUTATION = "accumutation";
    static final String SAVING = "saving";
    static final String METAL = "metal";

    public AbstractBankDepositeBuilder() {

        bankDeposite = new LinkedList<>();
    }

    public LinkedList<BankDeposite> findAllBankDeposite() {
        return bankDeposite;
    }

    public abstract void initBankDeposite(String fileName) throws XmlBuilderException;
}
