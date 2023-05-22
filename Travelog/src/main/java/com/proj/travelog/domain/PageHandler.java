package com.proj.travelog.domain;

public class PageHandler {

    private int totalCnt;       // 총 게시물 개수
    private int pageSize;       // 한 페이지의 크기
    private int navSize = 10;   // 페이지 내비게이션의 크기
    private int totalPage;      // 전체 페이지의 개수
    private int page;           // 현재 페이지
    private int beginPage;      // 내비게이션의 첫번째 페이지
    private int endPage;        // 내비게이션의 마지막 페이지
    private boolean showPrev;   // 이전 페이지로 이동하는 링크를 보여줄 것인지의 여부
    private boolean showNext;   // 다음 페이지로 이동하는 링크를 보여줄 것인지의 여부


    // 페이지 계산하는데 필요한 파라미터는 3가지는 totalCnt, page, pageSize
    public PageHandler(int totalCnt, int page, int pageSize) {
        this.totalCnt= totalCnt;
        this.page = page;
        this.pageSize = pageSize;


        // 남으면 페이지 1개 추가 (반올림 =>    정수 / 정수 하면 반올림 의미 X)
        totalPage = (int)Math.ceil(totalCnt / (double)pageSize);

        // 네비게이션의 첫번째 페이지 ->    (page - 1) / 10(navSize) * 10(navSize) + 1
        beginPage = (page - 1) / navSize * navSize + 1;

        // 네비게이션의 마지막 페이지       (둘 중에 작은 값. 삼항 연산자로도 사용 가능)
        endPage = Math.min(beginPage + navSize - 1, totalPage);

        // 이전 페이지로 갈지 말지 (beginPage 가 1이 아니면 됨)
        showPrev = beginPage != 1;

        // 다음 페이지로 갈지 말지 (endPage 가 totalPage 가 아니면 됨)
        showNext = endPage != totalPage;
    };


    // 총 게시글 개수랑 page 값만 준 경우에는 pageSize 를 10으로
    public PageHandler(int totalCnt, int page) {
        this(totalCnt, page, 10);
    }

    // 페이지 네비게이션을 프린트하는 메서드
    void print() {
        System.out.println("page = " + page);
        System.out.print(showPrev ? "[PREV] " : "");      // showPrev 가 참이면 [PREV] 출력

        for(int i = beginPage; i <= endPage; i++) {
            System.out.print(i + " ");
        }

        System.out.println(showNext ? "[NEXT]" : "");
    }


    public int getTotalCnt() {
        return totalCnt;
    }

    public void setTotalCnt(int totalCnt) {
        this.totalCnt = totalCnt;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getNavSize() {
        return navSize;
    }

    public void setNavSize(int navSize) {
        this.navSize = navSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
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
                ", pageSize=" + pageSize +
                ", navSize=" + navSize +
                ", totalPage=" + totalPage +
                ", page=" + page +
                ", beginPage=" + beginPage +
                ", endPage=" + endPage +
                ", showPrev=" + showPrev +
                ", showNext=" + showNext +
                '}';
    }
}