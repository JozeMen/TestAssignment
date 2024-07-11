import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.spi.UriParam;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@UriEndpoint(scheme = "mydb", title = "MyDB", syntax = "mydb:uri")
public class MyEndpoint {
    @UriParam
    private String uri;

    public MyEndpoint(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void doWork() throws Exception {
        CamelContext camel = new DefaultCamelContext();
        DataSource dataSource = new DriverManagerDataSource(
                uri
        );
        camel.getRegistry().bind("myDbs", dataSource);
        camel.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("timer://myTimer?period=5000")
                        .setBody(simple("select * from company"))
                        .to("jdbc:myDbs")
                        .process(exchange -> {
                            System.out.println("\n" + exchange.getIn().getBody() + "\n" );
                        });
            }
        });
        camel.start();
        Thread.sleep(3000);
    }
}
