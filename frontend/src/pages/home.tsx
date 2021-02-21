import React from "react";
import { Link } from "react-router-dom";
import { Helmet } from "react-helmet-async";
import styled from "styled-components";
import fireguma from "../styles/img/icon_fireguma_max.svg";
import { Header } from "../components/main-header";
import newIcon from "../styles/img/icon_new.svg";
import openIcon from "../styles/img/icon_small_open.svg";
import paperTexture from "../styles/img/watercolor-paper-texture.png";

const FAKE_MAIN_GOGUMA_DATA = [
  {
    id: 1,
    title: "여러분들,, 이게 진짜 맞나요..?",
    content:
      "제 친한친구와 남자친구가 바람을 피는 것 같아요.. 내일 또 학교에서 마주쳐야하는데 도대체어떻게 해야 좋을까요.. ㅠㅠ",
  },
  {
    id: 2,
    title: "여러분들,, 이게 진짜 맞나요..?",
    content:
      "제 친한친구와 남자친구가 바람을 피는 것 같아요.. 내일 또 학교에서 마주쳐야하는데 도대체어떻게 해야 좋을까요.. ㅠㅠ",
  },
];

const FAKE_RECENT_GOGUMA_DATA = [
  { id: 1, title: "제 얘기좀 듣고 가세요;;" },
  { id: 2, title: "니 알바 아니라는 남자친구, 헤어질까요?" },
  { id: 3, title: "인생 역대급 인연을 만나고 있습니다..." },
  { id: 4, title: "최준ㅋㅋㅋ 영상아세요?" },
  { id: 5, title: "눈치 없는 남자친구 ㅠ……어쩌죠" },
];

const FAKE_BURNING_GOGUMA_DATA = [
  { id: 1, title: "저 앞으로 연애 안합니다. 진 지 해 요." },
  { id: 2, title: "공백 포함 30자만 입력 가능해서 이거 이상은 안보임." },
  { id: 3, title: "인생 역대급 인연을 만나고 있습니다..." },
  { id: 4, title: "최준ㅋㅋㅋ영상아세요?" },
  { id: 5, title: "눈치 없는 남자친구 ㅠ……어쩌죠" },
];

const FAKE_TOP_GOGUMA_DATA = [
  { id: 1, title: "저 앞으로 연애 안합니다. 진 지 해 요." },
  { id: 2, title: "공백 포함 30자만 입력 가능해서 이거 이상은 안보임." },
  { id: 3, title: "인생 역대급 인연을 만나고 있습니다..." },
  { id: 4, title: "최준ㅋㅋㅋ 영상아세요?" },
  { id: 5, title: "눈치 없는 남자친구 ㅠ……어쩌죠" },
];

const CardsContainer = styled.div`
  height: 80%;
  background: url(${paperTexture});
  padding: 0 17px;
`;

const CardsSlider = styled.div`
  vertical-align: middle;
  overflow-x: scroll;
  grid-template-columns: repeat(2, auto);
  grid-gap: 0 9px;
  display: grid;
  cursor: grab;
  overflow: auto;
  &::-webkit-scrollbar {
    display: none;
  }
`;

const Card = styled.div`
  height: 70%;
  width: 84%;
`;

const CardType = styled.div`
  font-family: "Spoqa Han Sans Neo", "sans-serif";
  font-size: 10px;
  color: #727272;
  padding: 0;
  margin: 0;
`;

const CardEmoji = styled.img`
  width: 50px;
  height: 50px;
  padding-top: 53px;
`;

const CardTitle = styled.div`
  font-family: "Gaegu", cursive;
  font-size: 30px;
  line-height: 36px;
  color: #2f2f2f;
  word-break: keep-all;
  padding-top: 3%;
  width: 64%;
`;

const CardContents = styled.div`
  font-family: "Spoqa Han Sans Neo", "sans-serif";
  font-size: 14px;
  line-height: 24px;
  color: #000000;
  word-break: keep-all;
  padding-top: 12%;
  width: 68%;
`;

const SectionContainer = styled.div`
  height: 100%;
  padding: 0 7%;
`;

const Section = styled.div`
  margin: 15% auto;
`;

const SectionTitleContainer = styled.div`
  border-bottom: 1.5px solid #8c5cdd;
`;

const SectionTitle = styled.div`
  font-family: "Spoqa Han Sans Neo", "sans-serif";
  font-size: 18px;
  color: #000000;
`;

const SectionDescription = styled.div`
  font-family: "Spoqa Han Sans Neo", "sans-serif";
  font-size: 12px;
  color: #595959;
  padding-bottom: 10px;
`;

const SectionSeeMore = styled.span`
  color: #8c5cdd;
  float: right;
`;

const ScrollText = styled.p`
  font-family: "Spoqa Han Sans Neo", "sans-serif";
  font-size: 12px;
  color: #8c5cdd;
  text-align: center;
`;

const GogumaListItem = styled.div`
  font-size: 15px;
`;

const GogumaList = styled.div``;

const GogumaSlide = styled.div`
  margin-top: 17px;
  vertical-align: middle;
  overflow-x: scroll;
  grid-template-columns: repeat(6, auto);
  grid-gap: 0 9px;
  display: grid;
  cursor: grab;
  overflow: auto;
  &::-webkit-scrollbar {
    display: none;
  }
`;

const GogumaSlideItem = styled.div`
  font-family: "Spoqa Han Sans Neo", "sans-serif";
  font-size: 16px;
  border: 1px solid #e8e8e8;
  border-radius: 20px;
  width: 164px;
  height: 120px;
  display: inline-grid;
  text-align: center;
  word-break: keep-all;
  vertical-align: middle;
  cursor: grab;
  overflow: auto;
  text-align: center;
`;

const GogumaSlideItemText = styled.p`
  margin: auto 21px;
`;

export const Home: React.FC = () => {
  return (
    <>
      <Helmet>
        <title>go!guma</title>
      </Helmet>
      <Header />
      <CardsContainer>
        <CardsSlider>
          {FAKE_MAIN_GOGUMA_DATA.map(item => (
            <Card key={item.id}>
              <CardEmoji src={fireguma} />
              <CardType>불타는 고구마!</CardType>
              <CardTitle>{item.title}</CardTitle>
              <CardContents>{item.content}</CardContents>
            </Card>
          ))}
        </CardsSlider>
        <ScrollText>아래로 스크롤!</ScrollText>
        <img src={openIcon} style={{ margin: "auto" }} />
        <Link to="/ask">
          <img
            src={newIcon}
            style={{
              width: "60px",
              height: "60px",
            }}
          />
        </Link>
      </CardsContainer>
      <SectionContainer>
        <Section>
          <SectionTitleContainer>
            <SectionTitle>불타는 고구마</SectionTitle>
            <SectionDescription>
              실시간으로 불타오르는 인기 글이에요.
              <Link to="/goguma-list/popular">
                <SectionSeeMore>더보기</SectionSeeMore>
              </Link>
            </SectionDescription>
          </SectionTitleContainer>
          <GogumaList>
            {FAKE_BURNING_GOGUMA_DATA.map(item => (
              <GogumaListItem key={item.id}>{item.title}</GogumaListItem>
            ))}
          </GogumaList>
        </Section>
        <Section>
          <SectionTitleContainer>
            <SectionTitle>갓 구운 고구마</SectionTitle>
            <SectionDescription>
              가장 최근에 등록된 답답한 고구마 글들이에요.
              <Link to="/goguma-list/resent">
                <SectionSeeMore>더보기</SectionSeeMore>
              </Link>
            </SectionDescription>
          </SectionTitleContainer>
          <GogumaList>
            {FAKE_RECENT_GOGUMA_DATA.map(item => (
              <GogumaListItem key={item.id}>{item.title}</GogumaListItem>
            ))}
          </GogumaList>
        </Section>
        <Section>
          <SectionTitleContainer>
            <SectionTitle>명예 고구마</SectionTitle>
            <SectionDescription>
              전설급의 고구마사연들이 모여있어요!
              <Link to="/goguma-list/honor">
                <SectionSeeMore>더보기</SectionSeeMore>
              </Link>
            </SectionDescription>
          </SectionTitleContainer>
          <GogumaSlide>
            {FAKE_TOP_GOGUMA_DATA.map(item => (
              <GogumaSlideItem key={item.id}>
                <GogumaSlideItemText>{item.title}</GogumaSlideItemText>
              </GogumaSlideItem>
            ))}
          </GogumaSlide>
        </Section>
      </SectionContainer>
    </>
  );
};
