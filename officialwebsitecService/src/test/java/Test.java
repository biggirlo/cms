import java.net.InetAddress;
import java.net.UnknownHostException;

public class Test {

    @org.junit.Test
    public void getHost() throws UnknownHostException {

        String host = InetAddress.getLocalHost().getCanonicalHostName();
        System.out.println(host);
    }
}
