package com.project.meetmiddle.app.member.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String nickname;

	private String email;

	private String birthdate;

	private String token;

	private LocalDateTime createdDate;

	private String status;
}
