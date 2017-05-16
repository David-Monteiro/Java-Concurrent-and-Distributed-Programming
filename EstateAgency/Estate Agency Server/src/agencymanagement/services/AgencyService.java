package agencymanagement.services;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService(endpointInterface = "agencymanagement.services.AgencyPublisher")
@SOAPBinding(style = Style.RPC)
public interface AgencyService {
	
	//public String [] displayProperties();
	
	@WebMethod String getManagedProperties();
	
	@WebMethod String getAuctionDate (String propertyName);
	
	@WebMethod boolean isAuctionActive (String auctionName);
	
	@WebMethod String monitorAuction(String auctionName);
	
	@WebMethod String makeABid (String propertyName, int bidValue, String bidderName);
	
	//No need for marshaling and unmarshaling since all return values are supported by jax-WS
	

}
