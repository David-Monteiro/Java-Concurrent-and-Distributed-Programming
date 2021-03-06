
package agencymanagement.services;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "AgencyServiceImpl", targetNamespace = "http://services.agencymanagement/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface AgencyServiceImpl {


    /**
     * 
     */
    @WebMethod
    @RequestWrapper(localName = "handlerInitialiser", targetNamespace = "http://services.agencymanagement/", className = "agencymanagement.services.HandlerInitialiser")
    @ResponseWrapper(localName = "handlerInitialiserResponse", targetNamespace = "http://services.agencymanagement/", className = "agencymanagement.services.HandlerInitialiserResponse")
    @Action(input = "http://services.agencymanagement/AgencyServiceImpl/handlerInitialiserRequest", output = "http://services.agencymanagement/AgencyServiceImpl/handlerInitialiserResponse")
    public void handlerInitialiser();

    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getManagedProperties", targetNamespace = "http://services.agencymanagement/", className = "agencymanagement.services.GetManagedProperties")
    @ResponseWrapper(localName = "getManagedPropertiesResponse", targetNamespace = "http://services.agencymanagement/", className = "agencymanagement.services.GetManagedPropertiesResponse")
    @Action(input = "http://services.agencymanagement/AgencyServiceImpl/getManagedPropertiesRequest", output = "http://services.agencymanagement/AgencyServiceImpl/getManagedPropertiesResponse")
    public String getManagedProperties();

    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "monitorAuction", targetNamespace = "http://services.agencymanagement/", className = "agencymanagement.services.MonitorAuction")
    @ResponseWrapper(localName = "monitorAuctionResponse", targetNamespace = "http://services.agencymanagement/", className = "agencymanagement.services.MonitorAuctionResponse")
    @Action(input = "http://services.agencymanagement/AgencyServiceImpl/monitorAuctionRequest", output = "http://services.agencymanagement/AgencyServiceImpl/monitorAuctionResponse")
    public String monitorAuction(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     */
    @WebMethod
    @RequestWrapper(localName = "handlerFinaliser", targetNamespace = "http://services.agencymanagement/", className = "agencymanagement.services.HandlerFinaliser")
    @ResponseWrapper(localName = "handlerFinaliserResponse", targetNamespace = "http://services.agencymanagement/", className = "agencymanagement.services.HandlerFinaliserResponse")
    @Action(input = "http://services.agencymanagement/AgencyServiceImpl/handlerFinaliserRequest", output = "http://services.agencymanagement/AgencyServiceImpl/handlerFinaliserResponse")
    public void handlerFinaliser();

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "isAuctionActive", targetNamespace = "http://services.agencymanagement/", className = "agencymanagement.services.IsAuctionActive")
    @ResponseWrapper(localName = "isAuctionActiveResponse", targetNamespace = "http://services.agencymanagement/", className = "agencymanagement.services.IsAuctionActiveResponse")
    @Action(input = "http://services.agencymanagement/AgencyServiceImpl/isAuctionActiveRequest", output = "http://services.agencymanagement/AgencyServiceImpl/isAuctionActiveResponse")
    public boolean isAuctionActive(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getAuctionDate", targetNamespace = "http://services.agencymanagement/", className = "agencymanagement.services.GetAuctionDate")
    @ResponseWrapper(localName = "getAuctionDateResponse", targetNamespace = "http://services.agencymanagement/", className = "agencymanagement.services.GetAuctionDateResponse")
    @Action(input = "http://services.agencymanagement/AgencyServiceImpl/getAuctionDateRequest", output = "http://services.agencymanagement/AgencyServiceImpl/getAuctionDateResponse")
    public String getAuctionDate(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "makeABid", targetNamespace = "http://services.agencymanagement/", className = "agencymanagement.services.MakeABid")
    @ResponseWrapper(localName = "makeABidResponse", targetNamespace = "http://services.agencymanagement/", className = "agencymanagement.services.MakeABidResponse")
    @Action(input = "http://services.agencymanagement/AgencyServiceImpl/makeABidRequest", output = "http://services.agencymanagement/AgencyServiceImpl/makeABidResponse")
    public String makeABid(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        String arg2);

}
