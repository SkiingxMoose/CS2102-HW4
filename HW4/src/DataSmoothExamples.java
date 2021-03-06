
import java.util.Arrays;
import java.util.LinkedList;

import tester.TestMethod;
import tester.Tester;

class DataSmoothExamples {
    IDataSmoothProbs D;

    DataSmoothExamples(IDataSmoothProbs D) {
        this.D = D;
    }

    @TestMethod
    boolean testGiven(Tester t) {

        LinkedList<PHR> phrs = getInputData();

        LinkedList<Double> answers = getOutputData();
        return t.checkExpect(D.dataSmooth(phrs), answers);
    }

    @TestMethod
    public boolean testZeroesInput(Tester t) {
        // List of four items, each with hr of 0.
        LinkedList<PHR> phrs = new LinkedList<PHR>(
                Arrays.asList(new PHR("1", 3, 3, 0), new PHR("1", 3, 3, 0),
                        new PHR("1", 3, 3, 0), new PHR("1", 3, 3, 0)));

        LinkedList<Double> answers = new LinkedList<Double>(
                Arrays.asList(0d, 0d, 0d, 0d));

        return t.checkExpect(D.dataSmooth(phrs), answers);
    }

    @TestMethod
    boolean testConservationOfInput(Tester t) {
        LinkedList<PHR> phrs = getInputData();
        LinkedList<Double> answers = getOutputData();

        if (!t.checkExpect(D.dataSmooth(phrs), answers)) {
            return t.fail();
        }

        return t.checkExpect(D.dataSmooth(phrs), answers);
    }

    @TestMethod
    public boolean testSingleAndDoubleInput(Tester t) {
        LinkedList<PHR> phrs = new LinkedList<PHR>(
                Arrays.asList(new PHR("name", 3, 5, 100)));
        LinkedList<Double> answers = new LinkedList<Double>(
                Arrays.asList(100d));

        if (!t.checkExpect(D.dataSmooth(phrs), answers)) {
            return t.fail();
        }

        // Now add something to both lists:
        phrs.add(new PHR("name2", 4, 2, 110));
        answers.add(110d);
        return t.checkExpect(D.dataSmooth(phrs), answers);
    }

    /** 95 98.33 96 97 105 */
    private LinkedList<Double> getOutputData() {
     // @formatter:off
        return new LinkedList<Double>(Arrays.asList(
                95.,
                ((95 + 102 + 98) / 3.),
                96.,
                97.,
                105.));
     // @formatter:on
    }

    /** 95 102 98 88 105 */
    private LinkedList<PHR> getInputData() {
     // @formatter:off
        return new LinkedList<PHR>(Arrays.asList(
                new PHR("1", 3,3,  95),
                new PHR("1", 3,3,  102),
                new PHR("1", 3,3,  98),
                new PHR("1", 3,3,  88),
                new PHR("1", 3,3,  105)
                ));
     // @formatter:on
    }

}