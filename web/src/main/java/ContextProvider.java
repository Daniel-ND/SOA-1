import service.LabWorkService;
import service.LabWorkServiceBean;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class ContextProvider {
    private final Context context = createInitialContext();

    public LabWorkServiceBean lookupLabWorkService() {
        LabWorkServiceBean lookup = null;
        try {
            // The app name is the EAR name of the deployed EJB without .ear suffix.
            // Since we haven't deployed the application as a .ear, the app name for
            // us will be an empty string
            final String appName = "";
            final String moduleName = "ejb-1.0-jar-with-dependencies";
            final String distinctName = "";
            final String beanName = LabWorkServiceBean.class.getSimpleName();
            final String viewClassName = LabWorkService.class.getName();
            final String toLookup = String.format("ejb:%s/%s/%s/%s!%s", appName, moduleName, distinctName, beanName, viewClassName);
            lookup = (LabWorkServiceBean) context.lookup(toLookup);
        } catch (NamingException e) {
            e.printStackTrace();
        }

        return lookup;
    }

    private Context createInitialContext() {
        InitialContext initialContext = null;
        try {
            Properties prop = new Properties();
            prop.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
            prop.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
            prop.put(Context.PROVIDER_URL, "http-remoting://127.0.0.1:38564");
            initialContext = new InitialContext(prop);
        } catch (NamingException e) {
            e.printStackTrace();
        }

        return initialContext;
    }
}