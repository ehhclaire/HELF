import styled from "styled-components";
import ReactTooltip from "react-tooltip";
import { blockColor, device, pointColor } from "../../style/variables";

const Container = styled.div`
  width: 90vw;
  max-width: 900px;
`;


const MyPageProfileBlock = styled.div`
  display: flex;
  justify-content: center;
  @media ${device.TabletPortrait} {
    flex-direction: column;
  }
  #profileIcon {
    display: flex;
    align-items: center;
    justify-content: center;
    margin: 0 10px;
  }
  #profileNickname {
    font-weight: bold;
    font-size: 30px;
    margin: 5px;
  }
  #profileEmail {
    font-size: 20px;
    margin: 5px;
  }
`;

const MyPageIconBlock = styled.div`
  display: inline-block;
  width: 50px;
  height: 50px;
  font-size: 40px;
  border-radius: 50%;
  background-color: ${blockColor};
`;

const MyPageProfileButton = styled.button`
  margin-top: 25px;
  padding: 5px 10px;
  font-size: 17px;
  background-color: #2e7d32;
  border: 3px solid #2e7d32;
  border-radius: 5px;
  color: #fff;
  :hover {
    transform: scale(1.1);
  }
`;

const DeleteButton = styled.button`
  margin-top: 80px;
  margin-left: 2px;
  margin-right: 2px;
  padding: 4px 12px;
  font-size: 15px;
  background-color: #fff;
  border: 3px solid #1E2F23;
  border-radius: 5px;
  color: #1E2F23;
  :hover {
    transform: scale(1.1);
  }
`;


const MyPageMenuWrapper = styled.div`
  height: 50px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
  padding: 0 10%;
  font-size: 20px;
  ul li {
    cursor: pointer;
    margin: 0 15px;
    float: left;
    padding-bottom: 4px;
    border-bottom: 2px solid
      ${(props) => (props.check === 1 ? pointColor : "none")};
    @media ${device.TabletPortrait} {
    }
  }
`;

/** custom css start */
const Title = styled.div`
  font-weighit: bold;
  font-size: 28px;
  margin-bottom: 25px;
`;

const EmptyText = styled.div`
  margin-bottom: 40px;
`;

const fontNormal = {
fontFamily: 'KOTRA_GOTHIC',
}

/** 마이페이지 상단 프로필 */
const MyPageProfileWrapper = styled.div`
  background-color: #f2f7f4;
  border-radius: 8px;
  width: 100%;
  height: 100%;
  padding: 20px;
  box-shadow: 4px 4px 4px 2px rgba(0, 0, 0, 0.25);
  @media screen and (max-width: 768px) {
    width: 100%;
  }
  @media screen and (max-width: 480px) {
    width: 100%;
  }
`;

const center = {
  display: 'flex', 
  justifyContent: 'center', 
  alignItems: 'center',
}


const ProfileImage = styled.img`
  border-radius: 20px;
  width: 60%;
  height: 60%;
  @media screen and (max-width: 768px) {
    width: 90%;
    height: 90%;
  }
`;

const UserInfo = styled.div`
  margin-left: 15px;
  text-align: left;
`;

const NameInfo = styled.div`
  font-size: 40px;
`;

const BirthdayInfo = styled.div`
  font-size: 25px;
  margin-top: 20px;
`;

const EmailInfo = styled.div`
  font-size: 25px;
`;

const PhysicalInfo = styled.div`
  font-size: 20px;
  margin-top: 10px;
`;


const editBox = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: 600,
  bgcolor: "background.paper",
  border: "none",
  borderRadius: "10px",
  boxShadow: 24,
  p: 4,
};

const editInput = {
  backgroundColor: '#fff',
}


/** Modal */
const modalTitle = {
  fontFamily: 'KOTRA_BOLD-Bold',
  fontSize: '36px',
};

const modalBody = {
  fontFamily: 'KOTRA_GOTHIC',
  fontSize: '16px',
};

const fontBold = {
  fontFamily: 'KOTRA_BOLD-Bold',
}


const ModalBodyWrapper = styled.div`
  margin-top: 15px;
  margin-bottom: 25px;
`;

const ButtonWrapper = styled.div`
  margin-top: 20px;
  text-align: right;
  font-family: KOTRA_BOLD-Bold;
`;

const ConfirmButton = styled.button`
  width: 70px;
  background-color: #2e7d32;
  color: #fff;
  border: 2px solid #2e7d32;
  border-radius: 10px;
  padding: 5px 10px;
  margin-right: 5px;
  :hover {
    transform: scale(1.1);
  }
  `;
  
  const CancelButton = styled.button`
  width: 70px;
  background-color: #fff;
  color: #2e7d32;
  border: 2px solid #2e7d32;
  border-radius: 10px;
  padding: 5px 10px;
`;


/** 찜 목록 */
const LikeListStyle = styled.div`
  .total {
    background-color: transparent;
    border: transparent;
    z-index: 2;
    position: absolute;
    text-align: left;
    margin: 1% auto;
    .icon {
      color: red;
      margin-left: 300%;
      :hover {
        transform: scale(1.3);
        cursor: pointer;
      }
    }
  }
  img {
    width: 200px;
    height: 200px;
    display: block;
    margin: auto;
    border-radius: 20px;
  }
`;

/** 체중 기록 */
const ButtonGroup = styled.div`
  margin-top: 40px;
  margin-bottom: 80px;
`;

const WeightButton = styled.button`
  margin-top: 20px;
  margin-left: 2px;
  margin-right: 2px;
  padding: 2px 10px;
  font-size: 15px;
  background-color: #1E2F23;
  border: 3px solid #1E2F23;
  border-radius: 5px;
  color: #fff;
  :hover {
    transform: scale(1.1);
  }
`;


/* 영양 성분 통계 */
const NutritionStatus = styled.div`
  font-size: 10px;
  margin-top: 15px;
  font-family: 'KOTRA_GOTHIC';
`;

const MessageWrapper = styled.div`
  // margin: 0 auto;
  padding: 5px 0px;
`;

const LackMessage = styled.button`
  margin: 30px 2px 0px 2px;
  background-color: #f0df00;
  color: #fff;
  font-weight: bold;
  border: none;
  border-radius: 5px;
  padding: 5px 10px;
  cursor: pointer;
`;
  
const NormalMessage = styled.button`
  margin: 30px 2px 0px 2px;
  background-color: #43a047;
  color: #fff;
  font-weight: bold;
  border: none;
  padding: 5px 10px;
  border-radius: 5px;
  cursor: pointer;
`;
  
const TooMuchMessage = styled.button`
  margin: 30px 2px 0px 2px;
  background-color: #c94c4c;
  color: #fff;
  font-weight: bold;
  border: none;
  padding: 5px 10px;
  border-radius: 5px;
  cursor: pointer;
`;

/* 운동 기록 */
const ReactTooltipStyled = styled(ReactTooltip)`
  &.place-right {
    background-color: white;
    color: black;
    font-size: 10px;
  }
`;

const ExerciseHistoryTotal = styled.div`
  margin-bottom: 5%;
  .line {
    display: block;
    font-size: 0;
  }
  .cell {
    height: 15px;
    width: 15px;
    border-radius: 2px;
    margin: 1px;
    font-size: 10px;
    display: inline-grid;
  }
  .months {
    font-size: 10px;
    width: 900px;
    margin-right: 4%;
  }
  .fill-0 {
    background-color: #ebedf0;
  }
  .fill-1 {
    background-color: #aae68a;
  }
  .fill-2 {
    background-color: rgb(113, 197, 131);
  }
  .fill-3 {
    background-color: rgb(15, 126, 61);
  }
  .fill-4 {
    background-color: rgb(10, 88, 42);
  }
  .fill-5 {
    background-color: rgb(7, 63, 29);
  }
`;

const ShareBoardLikeListStyle = styled.div`
  .total {
    background-color: transparent;
    border: transparent;
    z-index: 2;
    position: absolute;
    text-align: left;
    margin: 1% auto;
    .icon {
      color: red;
      margin-left: 50%;
      :hover {
        transform: scale(1.3);
        cursor: pointer;
      }
    }
  }
  img {
    width: 200px;
    height: 200px;
    display: block;
    margin: auto;
    border-radius: 20px;
  }
`;

export {
  Container,
  MyPageMenuWrapper,
  MyPageProfileWrapper,
  MyPageIconBlock,
  MyPageProfileBlock,
  MyPageProfileButton,
  WeightButton,
  editBox,
  ModalBodyWrapper,
  ButtonWrapper,
  ConfirmButton,
  CancelButton,
  MessageWrapper,
  LackMessage,
  NormalMessage,
  TooMuchMessage,
  Title,
  LikeListStyle,
  DeleteButton,
  ButtonGroup,
  fontNormal,
  NutritionStatus,
  ExerciseHistoryTotal,
  ShareBoardLikeListStyle ,
  ReactTooltipStyled,
  UserInfo,
  NameInfo,
  BirthdayInfo,
  EmailInfo,
  PhysicalInfo,
  EmptyText,
  ProfileImage,
  center,
  modalTitle,
  modalBody,
  editInput,
  fontBold,
};
