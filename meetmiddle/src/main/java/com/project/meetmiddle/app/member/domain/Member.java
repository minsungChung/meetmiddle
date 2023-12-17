package com.project.meetmiddle.app.member.domain;


import com.project.meetmiddle.app.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "members")
@Getter
@AllArgsConstructor
@NoArgsConstructor
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
