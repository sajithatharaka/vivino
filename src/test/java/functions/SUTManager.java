package functions;

import constants.SUT;

public class SUTManager {
    public static String getSUT(String environment) {
        Logs.log("[Running on ENV] "+environment);
           switch (environment) {
           case "PRD":
                environment = SUT.PRD.getURL();
                break;
            default:
                environment = SUT.QA.getURL();
                break;
        }

        return environment;
    }
}
