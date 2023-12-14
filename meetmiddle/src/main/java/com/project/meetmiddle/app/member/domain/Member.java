package com.project.meetmiddle.app.member.domain;


import com.project.meetmiddle.app.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "members")
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {


	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "nickname", nullable = false)
	private String nickname;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "profile_image_url", nullable = false)
	private String profileImageUrl;

	@Column(name = "birth_date", nullable = false)
	private String birthdate;

}
