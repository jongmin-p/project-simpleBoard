package com.proj.travelog.signup.web;

import lombok.Data;

@Data
public class User {
	private String id;
	private String pwd;
	private String name;
	private String phone;
	private String email;
}
