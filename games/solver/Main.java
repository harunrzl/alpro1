//import org.apache.log4j.BasicConfigurator;
//import org.apache.log4j.Logger;

public class Main
{
    private final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args)
    {
        BasicConfigurator.configure();

        Solver solver = new Solver(4,
                        "FXIE" +
                        "AMLO" +
                        "EWBX" +
                        "ASTU");
        solver.solve();

    }
}

