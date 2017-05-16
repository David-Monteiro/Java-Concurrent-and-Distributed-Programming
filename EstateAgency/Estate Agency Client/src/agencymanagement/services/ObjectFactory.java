
package agencymanagement.services;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the agencymanagement.services package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _HandlerInitialiser_QNAME = new QName("http://services.agencymanagement/", "handlerInitialiser");
    private final static QName _HandlerFinaliser_QNAME = new QName("http://services.agencymanagement/", "handlerFinaliser");
    private final static QName _MakeABid_QNAME = new QName("http://services.agencymanagement/", "makeABid");
    private final static QName _MakeABidResponse_QNAME = new QName("http://services.agencymanagement/", "makeABidResponse");
    private final static QName _MonitorAuctionResponse_QNAME = new QName("http://services.agencymanagement/", "monitorAuctionResponse");
    private final static QName _GetAuctionDate_QNAME = new QName("http://services.agencymanagement/", "getAuctionDate");
    private final static QName _GetAuctionDateResponse_QNAME = new QName("http://services.agencymanagement/", "getAuctionDateResponse");
    private final static QName _IsAuctionActive_QNAME = new QName("http://services.agencymanagement/", "isAuctionActive");
    private final static QName _IsAuctionActiveResponse_QNAME = new QName("http://services.agencymanagement/", "isAuctionActiveResponse");
    private final static QName _GetManagedProperties_QNAME = new QName("http://services.agencymanagement/", "getManagedProperties");
    private final static QName _GetManagedPropertiesResponse_QNAME = new QName("http://services.agencymanagement/", "getManagedPropertiesResponse");
    private final static QName _HandlerInitialiserResponse_QNAME = new QName("http://services.agencymanagement/", "handlerInitialiserResponse");
    private final static QName _HandlerFinaliserResponse_QNAME = new QName("http://services.agencymanagement/", "handlerFinaliserResponse");
    private final static QName _MonitorAuction_QNAME = new QName("http://services.agencymanagement/", "monitorAuction");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: agencymanagement.services
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetAuctionDateResponse }
     * 
     */
    public GetAuctionDateResponse createGetAuctionDateResponse() {
        return new GetAuctionDateResponse();
    }

    /**
     * Create an instance of {@link HandlerInitialiser }
     * 
     */
    public HandlerInitialiser createHandlerInitialiser() {
        return new HandlerInitialiser();
    }

    /**
     * Create an instance of {@link HandlerFinaliser }
     * 
     */
    public HandlerFinaliser createHandlerFinaliser() {
        return new HandlerFinaliser();
    }

    /**
     * Create an instance of {@link MakeABid }
     * 
     */
    public MakeABid createMakeABid() {
        return new MakeABid();
    }

    /**
     * Create an instance of {@link MakeABidResponse }
     * 
     */
    public MakeABidResponse createMakeABidResponse() {
        return new MakeABidResponse();
    }

    /**
     * Create an instance of {@link MonitorAuctionResponse }
     * 
     */
    public MonitorAuctionResponse createMonitorAuctionResponse() {
        return new MonitorAuctionResponse();
    }

    /**
     * Create an instance of {@link GetAuctionDate }
     * 
     */
    public GetAuctionDate createGetAuctionDate() {
        return new GetAuctionDate();
    }

    /**
     * Create an instance of {@link HandlerFinaliserResponse }
     * 
     */
    public HandlerFinaliserResponse createHandlerFinaliserResponse() {
        return new HandlerFinaliserResponse();
    }

    /**
     * Create an instance of {@link MonitorAuction }
     * 
     */
    public MonitorAuction createMonitorAuction() {
        return new MonitorAuction();
    }

    /**
     * Create an instance of {@link IsAuctionActive }
     * 
     */
    public IsAuctionActive createIsAuctionActive() {
        return new IsAuctionActive();
    }

    /**
     * Create an instance of {@link IsAuctionActiveResponse }
     * 
     */
    public IsAuctionActiveResponse createIsAuctionActiveResponse() {
        return new IsAuctionActiveResponse();
    }

    /**
     * Create an instance of {@link GetManagedProperties }
     * 
     */
    public GetManagedProperties createGetManagedProperties() {
        return new GetManagedProperties();
    }

    /**
     * Create an instance of {@link GetManagedPropertiesResponse }
     * 
     */
    public GetManagedPropertiesResponse createGetManagedPropertiesResponse() {
        return new GetManagedPropertiesResponse();
    }

    /**
     * Create an instance of {@link HandlerInitialiserResponse }
     * 
     */
    public HandlerInitialiserResponse createHandlerInitialiserResponse() {
        return new HandlerInitialiserResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HandlerInitialiser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.agencymanagement/", name = "handlerInitialiser")
    public JAXBElement<HandlerInitialiser> createHandlerInitialiser(HandlerInitialiser value) {
        return new JAXBElement<HandlerInitialiser>(_HandlerInitialiser_QNAME, HandlerInitialiser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HandlerFinaliser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.agencymanagement/", name = "handlerFinaliser")
    public JAXBElement<HandlerFinaliser> createHandlerFinaliser(HandlerFinaliser value) {
        return new JAXBElement<HandlerFinaliser>(_HandlerFinaliser_QNAME, HandlerFinaliser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MakeABid }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.agencymanagement/", name = "makeABid")
    public JAXBElement<MakeABid> createMakeABid(MakeABid value) {
        return new JAXBElement<MakeABid>(_MakeABid_QNAME, MakeABid.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MakeABidResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.agencymanagement/", name = "makeABidResponse")
    public JAXBElement<MakeABidResponse> createMakeABidResponse(MakeABidResponse value) {
        return new JAXBElement<MakeABidResponse>(_MakeABidResponse_QNAME, MakeABidResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MonitorAuctionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.agencymanagement/", name = "monitorAuctionResponse")
    public JAXBElement<MonitorAuctionResponse> createMonitorAuctionResponse(MonitorAuctionResponse value) {
        return new JAXBElement<MonitorAuctionResponse>(_MonitorAuctionResponse_QNAME, MonitorAuctionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAuctionDate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.agencymanagement/", name = "getAuctionDate")
    public JAXBElement<GetAuctionDate> createGetAuctionDate(GetAuctionDate value) {
        return new JAXBElement<GetAuctionDate>(_GetAuctionDate_QNAME, GetAuctionDate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAuctionDateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.agencymanagement/", name = "getAuctionDateResponse")
    public JAXBElement<GetAuctionDateResponse> createGetAuctionDateResponse(GetAuctionDateResponse value) {
        return new JAXBElement<GetAuctionDateResponse>(_GetAuctionDateResponse_QNAME, GetAuctionDateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsAuctionActive }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.agencymanagement/", name = "isAuctionActive")
    public JAXBElement<IsAuctionActive> createIsAuctionActive(IsAuctionActive value) {
        return new JAXBElement<IsAuctionActive>(_IsAuctionActive_QNAME, IsAuctionActive.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsAuctionActiveResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.agencymanagement/", name = "isAuctionActiveResponse")
    public JAXBElement<IsAuctionActiveResponse> createIsAuctionActiveResponse(IsAuctionActiveResponse value) {
        return new JAXBElement<IsAuctionActiveResponse>(_IsAuctionActiveResponse_QNAME, IsAuctionActiveResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetManagedProperties }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.agencymanagement/", name = "getManagedProperties")
    public JAXBElement<GetManagedProperties> createGetManagedProperties(GetManagedProperties value) {
        return new JAXBElement<GetManagedProperties>(_GetManagedProperties_QNAME, GetManagedProperties.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetManagedPropertiesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.agencymanagement/", name = "getManagedPropertiesResponse")
    public JAXBElement<GetManagedPropertiesResponse> createGetManagedPropertiesResponse(GetManagedPropertiesResponse value) {
        return new JAXBElement<GetManagedPropertiesResponse>(_GetManagedPropertiesResponse_QNAME, GetManagedPropertiesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HandlerInitialiserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.agencymanagement/", name = "handlerInitialiserResponse")
    public JAXBElement<HandlerInitialiserResponse> createHandlerInitialiserResponse(HandlerInitialiserResponse value) {
        return new JAXBElement<HandlerInitialiserResponse>(_HandlerInitialiserResponse_QNAME, HandlerInitialiserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HandlerFinaliserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.agencymanagement/", name = "handlerFinaliserResponse")
    public JAXBElement<HandlerFinaliserResponse> createHandlerFinaliserResponse(HandlerFinaliserResponse value) {
        return new JAXBElement<HandlerFinaliserResponse>(_HandlerFinaliserResponse_QNAME, HandlerFinaliserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MonitorAuction }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.agencymanagement/", name = "monitorAuction")
    public JAXBElement<MonitorAuction> createMonitorAuction(MonitorAuction value) {
        return new JAXBElement<MonitorAuction>(_MonitorAuction_QNAME, MonitorAuction.class, null, value);
    }

}
