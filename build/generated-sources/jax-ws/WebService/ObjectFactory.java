
package WebService;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the WebService package. 
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

    private final static QName _Hello_QNAME = new QName("http://WebServices/", "hello");
    private final static QName _HelloResponse_QNAME = new QName("http://WebServices/", "helloResponse");
    private final static QName _RegistrarPasajero_QNAME = new QName("http://WebServices/", "RegistrarPasajero");
    private final static QName _RegistrarPasajeroResponse_QNAME = new QName("http://WebServices/", "RegistrarPasajeroResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: WebService
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RegistrarPasajeroResponse }
     * 
     */
    public RegistrarPasajeroResponse createRegistrarPasajeroResponse() {
        return new RegistrarPasajeroResponse();
    }

    /**
     * Create an instance of {@link Hello }
     * 
     */
    public Hello createHello() {
        return new Hello();
    }

    /**
     * Create an instance of {@link RegistrarPasajero }
     * 
     */
    public RegistrarPasajero createRegistrarPasajero() {
        return new RegistrarPasajero();
    }

    /**
     * Create an instance of {@link HelloResponse }
     * 
     */
    public HelloResponse createHelloResponse() {
        return new HelloResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Hello }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WebServices/", name = "hello")
    public JAXBElement<Hello> createHello(Hello value) {
        return new JAXBElement<Hello>(_Hello_QNAME, Hello.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WebServices/", name = "helloResponse")
    public JAXBElement<HelloResponse> createHelloResponse(HelloResponse value) {
        return new JAXBElement<HelloResponse>(_HelloResponse_QNAME, HelloResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistrarPasajero }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WebServices/", name = "RegistrarPasajero")
    public JAXBElement<RegistrarPasajero> createRegistrarPasajero(RegistrarPasajero value) {
        return new JAXBElement<RegistrarPasajero>(_RegistrarPasajero_QNAME, RegistrarPasajero.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistrarPasajeroResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WebServices/", name = "RegistrarPasajeroResponse")
    public JAXBElement<RegistrarPasajeroResponse> createRegistrarPasajeroResponse(RegistrarPasajeroResponse value) {
        return new JAXBElement<RegistrarPasajeroResponse>(_RegistrarPasajeroResponse_QNAME, RegistrarPasajeroResponse.class, null, value);
    }

}
