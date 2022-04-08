package com.aisher.helf.api.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;

import com.aisher.helf.api.request.UserRegisterReq;
import com.aisher.helf.api.request.UserUpdateReq;
import com.aisher.helf.api.request.WeightHistoryrRegisterReq;
import com.aisher.helf.api.response.WeightHistoryTenRecordRes;
import com.aisher.helf.api.service.WeightHistoryService;
import com.aisher.helf.common.auth.UserDetails;
import com.aisher.helf.common.model.response.BaseResponseBody;
import com.aisher.helf.db.entity.User;
import com.aisher.helf.db.entity.WeightHistory;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import springfox.documentation.annotations.ApiIgnore;
/**
 * 사용자 체중 히스토리 관련 API 요청 처리를 위한 컨트롤러 정의.
*/
@Api(value = "사용자 체중 히스토리 관련 API", tags = { "WeightHistory" })
@RestController
@RequestMapping("/api/weight/history")
public class WeightHistoryController {
	public static final Logger logger = LoggerFactory.getLogger(WeightHistoryController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	WeightHistoryService weighthistoryService;
	
	/** 사용자 daily 몸무게 등록 */
	@PostMapping("/register/weight")
	@ApiOperation(value = "사용자 daily 몸무게 history 등록 (token)", notes = "<strong> 사용자 daily 몸무게 history 등록-> 만약, daily 정보가 입력되어 있는경우 daily 정보 몸무게 정보 수정</strong>")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"),
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "사용자 없음"),
					@ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity<WeightHistory> registerWeightHistory(
			@ApiIgnore Authentication authentication,
			@RequestBody @ApiParam(value="daily 몸무게 정보", required = true) WeightHistoryrRegisterReq registerInfo
			)
	{
		System.out.println(registerInfo.toString());
		UserDetails userDetails = (UserDetails)authentication.getDetails();
		String userId = userDetails.getUsername();
		registerInfo.setUserId(userId);
		System.out.println(userId);
		System.out.println(registerInfo.toString());
		WeightHistory UserWeightHistory = null;
		try{
			UserWeightHistory = weighthistoryService.registerWeightHistory(registerInfo);
		}catch(NoSuchElementException e){
			e.printStackTrace();
			return new ResponseEntity<WeightHistory>(UserWeightHistory,HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<WeightHistory>(UserWeightHistory,HttpStatus.OK);
	}
	/** 사용자가 까먹고 기록하지 못한 history 몸무게 등록 */
	@PostMapping("/select/register/weight")
	@ApiOperation(value = "사용자가 까먹고 기록하지 못한 history 몸무게 등록 (token)", notes = "<strong> 사용자가 까먹고 기록하지 못한 history 몸무게 등록</strong>")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"),
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "사용자 없음"),
					@ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity<? extends BaseResponseBody> selectRegisterWeightHistory(
			@ApiIgnore Authentication authentication,
			@RequestBody @ApiParam(value="daily 몸무게 정보", required = true) WeightHistoryrRegisterReq registerInfo
	)
	{
		System.out.println(registerInfo.toString());
		UserDetails userDetails = (UserDetails)authentication.getDetails();
		String userId = userDetails.getUsername();
		registerInfo.setUserId(userId);
		System.out.println(userId);
		System.out.println(registerInfo.toString());
		WeightHistory UserWeightHistory = weighthistoryService.selectRegisterWeightHistory(registerInfo);
		if(UserWeightHistory == null){
			return ResponseEntity.status(400).body(BaseResponseBody.of(401, "Fail"));
		}
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
	// 몸무게 history에 등록된 날짜와 몸무게를 입력하여 해당 몸무게 history 날짜의 몸무게를 수정.
	@ApiOperation(value = "몸무게 history에 등록된 날짜와 몸무게를 입력하여 해당 몸무게 history 날짜의 몸무게를 수정(token)", notes = "해당 몸무게 history 정보 수정")
	@PutMapping("/update")
	public ResponseEntity<String> updateUser(
					@ApiIgnore Authentication authentication,
					@RequestBody @ApiParam(value="daily 몸무게 정보", required = true) WeightHistoryrRegisterReq registerInfo) throws Exception
	{
		UserDetails userDetails = (UserDetails)authentication.getDetails();
		String userId = userDetails.getUsername();
		registerInfo.setUserId(userId);
		WeightHistory UserWeightHistory;
		try {
			LocalDate date = LocalDate.parse(registerInfo.getCreatedAt(), DateTimeFormatter.ISO_DATE);
			UserWeightHistory = weighthistoryService.getUserByUserWeightHistory(userId,date );
		}catch(NoSuchElementException E) {
			return  ResponseEntity.status(500).body("해당 WeightHistory 정보가 없어서 정보 수정 실패");
		}
		weighthistoryService.updateWeightHistory(registerInfo);
		System.out.println("해당 WeightHistory 정보 수정 성공");
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	@ApiOperation(value = "daily 몸무게 히스토리 정보 삭제(token)", notes = "daily 몸무게 히스토리 정보 삭제")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "해당 회원 없음")})
	@DeleteMapping("/remove/weight")
	public ResponseEntity<String> deleteWeightHistory(
			@RequestParam @ApiParam(value="daily 날짜", required = true) String createAt,
			@ApiIgnore Authentication authentication) throws Exception {
		boolean result;
		UserDetails userDetails = (UserDetails)authentication.getDetails();
		String userId = userDetails.getUsername();
		try {
			LocalDate date = LocalDate.parse(createAt, DateTimeFormatter.ISO_DATE);
			WeightHistory weightHistory = weighthistoryService.getUserByUserWeightHistory(userId,date);
			result = weighthistoryService.deleteByWeightHistory(weightHistory);
		}catch(NoSuchElementException E) {
			logger.debug("daily 몸무게 히스토리 정보 삭제 실패");
			return  ResponseEntity.status(500).body("해당 WeightHistory 정보 없어서 daily 몸무게 히스토리 정보 삭제 실패");
		}
		logger.debug("daily 몸무게 히스토리 정보 삭제 성공");
		return ResponseEntity.status(200).body("daily 몸무게 히스토리 정보 삭제 성공");
	}

	/** 해당 유저 최근 10개 레코드 검색 */
	@GetMapping("/search/ten/weight")
	@ApiOperation(value = "사용자 Weight history 최근 10개 레코드 조회(token)", notes = "<strong>사용자 Weight history 최근 10개 레코드</strong>를 조회합니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"),
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "사용자 없음"),
					@ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity<List<WeightHistoryTenRecordRes>> getTenWeightHistory(
				    @ApiIgnore Authentication authentication)
	{
		UserDetails userDetails = (UserDetails)authentication.getDetails();
		String userId = userDetails.getUsername();
		List<WeightHistoryTenRecordRes> weightHistory = weighthistoryService.getTenWeightHistory(userId);
		return new ResponseEntity<List<WeightHistoryTenRecordRes>>(weightHistory,HttpStatus.OK);
	}
}
