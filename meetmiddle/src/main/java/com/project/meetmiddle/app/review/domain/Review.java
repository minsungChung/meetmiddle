package com.project.meetmiddle.app.review.domain;

import java.time.LocalDateTime;

import com.project.meetmiddle.app.member.domain.Member;
import com.project.meetmiddle.app.store.domain.Store;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String content;

	@ManyToOne
	private Store store;

	@ManyToOne
	private Member member;

	private int rating;

	private LocalDateTime createdDate;

	private LocalDateTime updatedDate;

	private String status;
}
