package org.joven.certificate;

import com.ulisesbocchio.jasyptspringboot.EncryptablePropertyDetector;
import com.ulisesbocchio.jasyptspringboot.EncryptablePropertyResolver;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.joven.base.encrypt.CustomDecryptionDetector;
import org.joven.base.encrypt.CustomDecryptionResolver;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEncryptableProperties
@MapperScan(basePackages = {"org.joven.certificate.mapper"})
public class CertificateApplication {

	public static void main(String[] args) {
		SpringApplication.run(CertificateApplication.class, args);
	}

	@Bean(name = "encryptablePropertyDetector")
	public EncryptablePropertyDetector encryptablePropertyDetector() {
		return new CustomDecryptionDetector("(4party)");
	}

	@Bean(name = "encryptablePropertyResolver")
	public EncryptablePropertyResolver encryptablePropertyResolver() {
		return new CustomDecryptionResolver("(4party)", "Encrypt4Partys20");
	}
}
