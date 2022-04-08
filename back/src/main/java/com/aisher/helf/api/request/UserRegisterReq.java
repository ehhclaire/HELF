package com.aisher.helf.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Email;
import java.time.LocalDate;

/**
 * 유저 회원가입 API ([POST] /api/user) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("UserRegisterPostRequest")
public class UserRegisterReq {

	@ApiModelProperty(name="유저 ID", example="ssafy1")
	private String userId;

	@ApiModelProperty(name="유저 Password", example="ssafy11!")
	private String userPassword;

	@ApiModelProperty(name="유저 Name", example="kimssafy")
	private String userName;

	@ApiModelProperty(name="유저 Email", example="ssafy@ssafy.com")
	@Email(message = "이메일 형식이 아닙니다.")
	private String userEmail;
}
