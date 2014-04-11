package com.ailk.axis;

import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

public class AxisRPCClient {

	private String service_url;
	private String namespaceString;



	public AxisRPCClient(String service_url, String namespaceString) {
		super();
		this.service_url = service_url;
		this.namespaceString = namespaceString;
	}

	public String execute(Object[] params, String method) throws AxisFault {

		RPCServiceClient client = new RPCServiceClient();

		Options options = client.getOptions();
		options.setTimeOutInMilliSeconds(600000L);
		EndpointReference targetEPR = new EndpointReference(service_url);
		options.setTo(targetEPR);

		//Class[] classes = new Class[] { String.class };
		QName qName = new QName(namespaceString, method);

		String result = (String) client.invokeBlocking(qName, params, new Class[] { String.class })[0];

		return result;

	}

	/**
	 * @return the namespaceString
	 */
	public String getNamespaceString() {
		return namespaceString;
	}

	/**
	 * @param namespaceString
	 *            the namespaceString to set
	 */
	public void setNamespaceString(String namespaceString) {
		this.namespaceString = namespaceString;
	}

	/**
	 * @return the service_url
	 */
	public String getService_url() {
		return service_url;
	}

	/**
	 * @param service_url
	 *            the service_url to set
	 */
	public void setService_url(String service_url) {
		this.service_url = service_url;
	}
}
