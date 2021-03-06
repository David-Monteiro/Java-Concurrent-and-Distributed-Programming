
package agencymanagement.services;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "AgencyServiceImplService", targetNamespace = "http://services.agencymanagement/", wsdlLocation = "http://127.0.0.1:8080/EstateAgency?wsdl")
public class AgencyServiceImplService
    extends Service
{

    private final static URL AGENCYSERVICEIMPLSERVICE_WSDL_LOCATION;
    private final static WebServiceException AGENCYSERVICEIMPLSERVICE_EXCEPTION;
    private final static QName AGENCYSERVICEIMPLSERVICE_QNAME = new QName("http://services.agencymanagement/", "AgencyServiceImplService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://127.0.0.1:8080/EstateAgency?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        AGENCYSERVICEIMPLSERVICE_WSDL_LOCATION = url;
        AGENCYSERVICEIMPLSERVICE_EXCEPTION = e;
    }

    public AgencyServiceImplService() {
        super(__getWsdlLocation(), AGENCYSERVICEIMPLSERVICE_QNAME);
    }

    public AgencyServiceImplService(WebServiceFeature... features) {
        super(__getWsdlLocation(), AGENCYSERVICEIMPLSERVICE_QNAME, features);
    }

    public AgencyServiceImplService(URL wsdlLocation) {
        super(wsdlLocation, AGENCYSERVICEIMPLSERVICE_QNAME);
    }

    public AgencyServiceImplService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, AGENCYSERVICEIMPLSERVICE_QNAME, features);
    }

    public AgencyServiceImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public AgencyServiceImplService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns AgencyServiceImpl
     */
    @WebEndpoint(name = "AgencyServiceImplPort")
    public AgencyServiceImpl getAgencyServiceImplPort() {
        return super.getPort(new QName("http://services.agencymanagement/", "AgencyServiceImplPort"), AgencyServiceImpl.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns AgencyServiceImpl
     */
    @WebEndpoint(name = "AgencyServiceImplPort")
    public AgencyServiceImpl getAgencyServiceImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://services.agencymanagement/", "AgencyServiceImplPort"), AgencyServiceImpl.class, features);
    }

    private static URL __getWsdlLocation() {
        if (AGENCYSERVICEIMPLSERVICE_EXCEPTION!= null) {
            throw AGENCYSERVICEIMPLSERVICE_EXCEPTION;
        }
        return AGENCYSERVICEIMPLSERVICE_WSDL_LOCATION;
    }

}
