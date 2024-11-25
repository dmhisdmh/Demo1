package com.example.demo.models;
import lombok.*;


@Data
@AllArgsConstructor
@ToString
@Builder
public class Customer {
	private String id;
	private String name;
	private String phoneNumber;
	private String email;
	
	
	
}
