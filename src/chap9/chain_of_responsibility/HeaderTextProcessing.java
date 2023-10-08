package chap9.chain_of_responsibility;

public class HeaderTextProcessing extends ProcessingObject<String> {

    @Override
    protected String handleWork(String input) {
        return "From Hyun: " + input;
    }
}
