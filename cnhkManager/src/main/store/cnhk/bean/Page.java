package store.cnhk.bean;

public class Page {
    private Integer pageSize; //页面大小
    private Integer pageNo; //当前页
    private Integer startRow; //起始行
    private Integer totalPage; //总页数
    private Integer totalCount; //总条数

    public Page(Integer pageSize, Integer pageNo) {
        this.pageSize = pageSize;
        this.pageNo = pageNo;
        this.startRow = (pageNo - 1) * pageSize;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getStartRow() {
        return startRow;
    }


    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}
