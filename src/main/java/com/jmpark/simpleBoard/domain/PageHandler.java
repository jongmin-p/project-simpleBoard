package com.jmpark.simpleBoard.domain;

public class PageHandler {

    // 페이지 네비게이션을 테스트 해보기 위해 이 클래스 만든거임.

    private int totalCnt;            // 전체 게시글 개수
    private int totalPage;           // 전체 페이지 개수

    private int pageSize;            // 한 페이지의 크기 (한 페이지 내 게시글 개수)
    private int naviSize = 10;       // 페이지 네비게이션의 크기        1 ~ 10, 11 ~ 20 등

    private int page;                // 현재 페이지
    private int beginPage;           // 네비게이션의 첫번째 페이지
    private int endPage;             // 네비게이션의 마지막 페이지

    private boolean showPrev;        // 이전 페이지로 이동하는 링크를 보여줄 것인지의 여부
    private boolean showNext;        // 다음 페이지로 이동하는 링크를 보여줄 것인지의 여부


    // 생성자  (페이지를 계산하는 데 필요한 3가지가 있다.     totalCnt, page(현재 페이지), pageSize)
    public PageHandler(int totalCnt, int page, int pageSize) {
        this.totalCnt = totalCnt;
        this.page     = page;
        this.pageSize = pageSize;


        // 1. totalPage 구하기    (나눴을 때 게시글이 남을 수도 있으니까 올림으로 해주기)
        totalPage     = (int)Math.ceil(totalCnt / (double)pageSize);

        // 2-1. beginPage 구하기    (네비게이션의 첫번째 페이지)
        beginPage     = (page - 1) / naviSize * naviSize + 1;

        // 2-2. endPage 구하기      (Math.min() 이용해서 둘 중에 작은거 선택     -   삼항 연산자로 써도 됨)
        endPage       = Math.min(beginPage + naviSize - 1, totalPage);

        // 3-1. 이전 페이지로 가는 버튼을 보여줄 지 말 지 결정 (beginPage 가 1페이지만 아니면 됨)
        showPrev      = beginPage != 1;

        // 3-2. 다음 페이지로 가는 버튼을 보여줄 지 말 지 결정 (endPage 가 totalPage 만 아니면 됨)
        showNext      = endPage != totalPage;
    }


    // totalCnt 하고 page 값만 준 경우에는   pageSize 를 10으로 주도록
    public PageHandler(int totalCnt, int page) {
        this(totalCnt, page, 10);           // 클래스 내 다른 생성자 호출 this()
    }


    // 페이지 네비게이션을 출력하는 메서드
    void print() {
        System.out.println("\n현재 페이지 ->   page = " + page);

        System.out.print(showPrev ? "[Prev]  " : "");       // 이전 버튼 보여주는게 true 이면, 이전 페이지로 가는 링크 보여줌.

        for (int i = beginPage; i <= endPage; i++) {
            System.out.print(i + "  ");
        }

        System.out.println(showNext ? "[Next]\n" : "");     // 다음 버튼 보여주는게 true 이면, 다음 페이지로 가는 링크 보여줌.
    }


    public int getTotalCnt() {
        return totalCnt;
    }

    public void setTotalCnt(int totalCnt) {
        this.totalCnt = totalCnt;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getNaviSize() {
        return naviSize;
    }

    public void setNaviSize(int naviSize) {
        this.naviSize = naviSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getBeginPage() {
        return beginPage;
    }

    public void setBeginPage(int beginPage) {
        this.beginPage = beginPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public boolean isShowPrev() {
        return showPrev;
    }

    public void setShowPrev(boolean showPrev) {
        this.showPrev = showPrev;
    }

    public boolean isShowNext() {
        return showNext;
    }

    public void setShowNext(boolean showNext) {
        this.showNext = showNext;
    }


    @Override
    public String toString() {
        return "PageHandler{" +
                "totalCnt=" + totalCnt +
                ", totalPage=" + totalPage +
                ", pageSize=" + pageSize +
                ", naviSize=" + naviSize +
                ", page=" + page +
                ", beginPage=" + beginPage +
                ", endPage=" + endPage +
                ", showPrev=" + showPrev +
                ", showNext=" + showNext +
                '}';
    }
}