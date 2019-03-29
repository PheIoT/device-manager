/*
 * Copyright (c) 2018. For PheIot Group.
 */

package com.pheiot.phecloud.pd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
//@ServletComponentScan
@EnableJpaAuditing
@ComponentScan(basePackages = {"com.pheiot"})
public class PDServerApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(PDServerApplication.class, args);
	}

}
