package com.aisher.helf.api.controller;


import com.aisher.helf.api.request.FoodRegisterReq;
import com.aisher.helf.api.request.ShareBoardLikeReq;
import com.aisher.helf.api.request.ShareBoardRegisterReq;
import com.aisher.helf.api.response.ShareBoardAllRes;
import com.aisher.helf.api.response.ShareBoardFindRes;
import com.aisher.helf.api.service.ShareBoardService;
import com.aisher.helf.common.auth.UserDetails;
import com.aisher.helf.common.model.response.BaseResponseBody;
import com.aisher.helf.db.entity.Food;
import com.aisher.helf.db.entity.ShareBoard;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * 스터디 모집 게시판 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "스터디 모집 게시판 API", tags = { "ShareBoard" })
@RestController
@RequestMapping("/api/shareboard")
public class ShareBoardController {
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    ShareBoardService shareboardService;

    // 공유 게시글 생성
    @PostMapping("/register")
    @ApiOperation(value = "게시글 등록", notes = "<strong>공유 식단 일지 정보</strong>를 공유 게시판에 통해 등록한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> registerShareBoard(
            @RequestBody @ApiParam(value="게시글 등록", required = true) ShareBoardRegisterReq shareBoardRegisterReq) {

        ShareBoard shareBoard = shareboardService.registerShareBoard(shareBoardRegisterReq);
        if(shareBoard == null) {
            ResponseEntity.status(400).body(BaseResponseBody.of(400, "Bad Request"));
        }
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }


    /** 공유 게시글 전체 조회 입니다. + pagination
     *  page랑 size랑 sort는 url에 담아서 넘겨줘야 함.
     *  PageableDefault 에 page 는 기본 0이 처음 페이지 시작 / 인자로 받는 page는 1이 맨 처음 페이지 이다.
     *  */
    @ApiOperation(value="공유 게시글 전체 조회(token)", notes="<strong>공유 게시글을 전체 조회를</strong>시켜줍니다.")
    @ApiResponses({ @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")})
    @GetMapping("/findAll")
    public ResponseEntity<Page<ShareBoardAllRes>> findAllShareBoard(@PageableDefault(page = 0, size =2, sort = "boardNo", direction = Sort.Direction.DESC) Pageable pageable, @ApiIgnore Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        String userId = userDetails.getUsername();

        System.out.println(pageable.getSort());
        Page<ShareBoard> shareBoards = shareboardService.findAllShareBoard(pageable);
        Page<ShareBoardAllRes> shareBoardAllRes = shareboardService.findInfoShareBoard(shareBoards, userId);
        return ResponseEntity.status(200).body(shareBoardAllRes);
    }

    @GetMapping("/find/{boardNo}")
    @ApiOperation(value ="공유 게시글 상세  조회", notes ="해당 boardNo 공유 게시판 상세 정보 출력")
    @ApiResponses({ @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류") })
    public ResponseEntity<List<ShareBoardFindRes>> findShareBoardInfo(@PathVariable Long boardNo) {
        List<ShareBoardFindRes> shareboardInfo = shareboardService.findByShareBoardId(boardNo);
        return new ResponseEntity<List<ShareBoardFindRes>>(shareboardInfo, HttpStatus.OK);
    }

    @PostMapping("/like")
    @ApiOperation(value="공유 게시판 좋아요", notes="공유 게시글을 좋아요한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = BaseResponseBody.class),
    })
    public ResponseEntity<? extends BaseResponseBody> shareBoardLike(@RequestBody @ApiParam(required = true) ShareBoardLikeReq req) {
        shareboardService.setLikeList(req.getUserId(), req.getBoardNo());
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "SUCCESS"));
    }

    // 내가 기록한 식단이 이미 공유 되어있는지 체크
    @GetMapping("/diaryNoCheck/{diaryNo}")
    @ApiOperation(value = "내가 기록한 식단을 공유 게시판에 이미 공유 되어있는지 체크", notes = "기록한 식단 공유 버튼 클릭 시 공유 게시판에 <strong> [ false : 공유된게 있을경우 ], [ true : 공유된게 없을 경우 ]<strong> ")
    @ApiResponses({ @ApiResponse(code = 200, message = "성공"),
                    @ApiResponse(code = 401, message = "인증 실패"),
                    @ApiResponse(code = 404, message = "사용자 없음"),
                    @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<Boolean> diaryNoCheck(@PathVariable("diaryNo") int diaryNo) {

        if (shareboardService.checkDiaryNo(diaryNo) == true) {
            System.out.println("내 식단을 공유한 적이 없으니 공유 가능하다.!");
            return ResponseEntity.status(200).body(shareboardService.checkDiaryNo(diaryNo));
        } else System.out.println("내 식단을 공유한 적이 있으니 공유 불가능 하다!");
        return ResponseEntity.status(200).body(shareboardService.checkDiaryNo(diaryNo));
    }
}
