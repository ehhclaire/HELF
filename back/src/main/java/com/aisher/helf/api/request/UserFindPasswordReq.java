package com.aisher.helf.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;

/**
 * 유저 비밀번호 찾기 API ([POST] /api/email/send/{user_email}) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("UserFindPasswordPostRequest")
public class UserFindPasswordReq {

	@ApiModelProperty(name="유저 ID", example="ssafy1")
	private String userId;

	@ApiModelProperty(name="유저 Name", example="kimssafy")
	private String userName;

	@ApiModelProperty(name="유저 Email", example="ssafy1@ssafy.com")
	@Email(message = "이메일 형식이 아닙니다.")
	private String userEmail;
}
