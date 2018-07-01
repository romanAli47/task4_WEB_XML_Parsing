package by.alikevich.task4.builder;

public enum ParserType {

    BANKS("banks"), DEMAND("demand"), URGENT("urgent"), ESTIMATED("estimated"), ACCUMUTATION("accumutation"),
    SAVING("saving"), METAL("metal"),
    BANK_NAME("bankname"), ACCOUNT_ID("account-id"), COUNTRY("country"), DEPOSITOR("depositor"), AMOUNT("amount"),
    PROFITABILITY("profitability"), TIMECONSTRAINTS("timeConstraints"), DEFAULT_PARAM;

    private String parser;

    ParserType(){}

    ParserType(String parser) {

        this.parser = parser;
    }

    public String getParser() {

        return parser;
    }

    public static ParserType checkTag(String value){

        for(ParserType parserType : ParserType.values()){

            if(parserType.parser.equals(value)){
                return parserType;
            }
        }
        return DEFAULT_PARAM;
    }
}
