# 할 일

## 애플리케이션 초기화
- DB 테이블이 존재하지 않는다면 테이블 생성을 시도한다.
- 초기화가 시작되면 일단 지정된 파일 경로 `app.fb.path.root` 로부터 DB 파일 읽기 시도
    * DB 가 없으면 최초 실행이므로 파일 초기화를 진행한다.
    * DB 가 있어도 파일 초기화를 진행해야 하는게 DB 와 실제 물리 파일이 다를 수도 있기 때문임.
        - 단 시간이 너무 오래 걸리면 안되기 때문에 간단한 스캔 작업을 진행한다.

## 파일 초기화
- 일단 root 내부의 모든 디렉토리를 recursive 하게 돌면서 상황 파악한다.
- leaf directory 라면 해당 파일 내에 video, txt, image 를 수집
    * image 파일들을 읽어서 `fb_image` 에 upsert 한다.
    * video 파일들을 읽어서 `fb_video` 에 upsert 한다.
        - insert 작업이라면 ffmpeg 분석을 시도한다. width, height, fps 등을 업데이트 해야 한다.
        - update 작업이라면 ffmpeg 분석 필요 없음
    * txt 파일을 읽어서 분석한 다음 `fb_directory.description`, `fb_casting`, `fb_genre` 를 upsert 한다.
- DirectoryTree 에 모든 상황을 recursive 하게 업데이트 한다.

## 이벤트 엑션
- Tree 의 아이템을 선택했을 경우:
    * 해당 작품의 이미지를 gui 에 노출시킨다.
    * 해당 작품의 데이터를 읽어서 라벨 등에 노출시킨다. casting, 줄거리, 장르 등등..
    * 재생 버튼을 눌렀을 때 해당 작품의 비디오 파일이 실행되도록 이벤트 걸어준다.
- genre 의 목록을 선택했을 경우:
    * Tree 의 목록을 선택된 casting 과 선택된 genre 의 목록만으로 업데이트 해준다.
- casting 의 목록을 선택했을 경우:
    * Tree 의 목록을 선택된 casting 과 선택된 genre 의 목록만으로 업데이트 해준다. (gnere 선택시와 같은 동작임)

## Leaf 추가 액션
- 추가 버튼을 누르면 새 창 열어서 파일 추가 할 수 있음
    * directory_id 입력
    * 비디오 파일 경로 입력 (드래그 앤 드롭)
    * 이미지 파일 경로 입력 (드래그 앤 드롭)
    * 장르 입력 (자동 완성 가능할까?)
    * casting 입력 (자동 완성 가능할까?)
- 완료 버튼을 누를시
    * 파일들을 디렉토리로 이동 시도
    * txt 파일을 생성한다.
    * DB 에 해당 정보 집어 넣음
        - 중복 데이터라면 에러
    * 창 닫음

# 고민
- UI 를 어떻게 그릴 것인가? 기본 Java Swing 으로 위의 내용들이 표현 가능한가?
- 상태를 알려주는 status bar 하나 정도 있으면 좋겠다.
