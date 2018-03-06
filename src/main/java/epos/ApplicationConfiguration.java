package epos;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"epos.checkout", "epos.pricing"})
public class ApplicationConfiguration {

}
