
package package1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the package1 package. 
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

    private final static QName _AddClient_QNAME = new QName("http://package1/", "addClient");
    private final static QName _AddClientResponse_QNAME = new QName("http://package1/", "addClientResponse");
    private final static QName _Hello_QNAME = new QName("http://package1/", "hello");
    private final static QName _HelloResponse_QNAME = new QName("http://package1/", "helloResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: package1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddClient }
     * 
     */
    public AddClient createAddClient() {
        return new AddClient();
    }

    /**
     * Create an instance of {@link AddClientResponse }
     * 
     */
    public AddClientResponse createAddClientResponse() {
        return new AddClientResponse();
    }

    /**
     * Create an instance of {@link Hello }
     * 
     */
    public Hello createHello() {
        return new Hello();
    }

    /**
     * Create an instance of {@link HelloResponse }
     * 
     */
    public HelloResponse createHelloResponse() {
        return new HelloResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddClient }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddClient }{@code >}
     */
    @XmlElementDecl(namespace = "http://package1/", name = "addClient")
    public JAXBElement<AddClient> createAddClient(AddClient value) {
        return new JAXBElement<AddClient>(_AddClient_QNAME, AddClient.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddClientResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddClientResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://package1/", name = "addClientResponse")
    public JAXBElement<AddClientResponse> createAddClientResponse(AddClientResponse value) {
        return new JAXBElement<AddClientResponse>(_AddClientResponse_QNAME, AddClientResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Hello }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Hello }{@code >}
     */
    @XmlElementDecl(namespace = "http://package1/", name = "hello")
    public JAXBElement<Hello> createHello(Hello value) {
        return new JAXBElement<Hello>(_Hello_QNAME, Hello.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link HelloResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://package1/", name = "helloResponse")
    public JAXBElement<HelloResponse> createHelloResponse(HelloResponse value) {
        return new JAXBElement<HelloResponse>(_HelloResponse_QNAME, HelloResponse.class, null, value);
    }

}
