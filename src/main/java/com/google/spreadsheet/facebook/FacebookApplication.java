package com.google.spreadsheet.facebook;

import com.google.spreadsheet.facebook.jobs.PageSchedule;
import com.google.spreadsheet.facebook.jobs.PostSchedule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@Slf4j
@EnableScheduling
@EntityScan(basePackages = {"com.google.spreadsheet.facebook.model"})
@EnableJpaRepositories(basePackages = {"com.google.spreadsheet.facebook.repository"})
public class FacebookApplication
//		implements CommandLineRunner
{
//	@Autowired
//	PageSchedule pageSchedule;
//	@Autowired
//	PostSchedule postSchedule;
	public static void main(String[] args) {
		SpringApplication.run(FacebookApplication.class, args);
	}

}
