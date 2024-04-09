package fpoly.edu.boqhpd0840.lab5.models;

public class Page<T> {
    private T data;
    private int curentPage,totalPage;

    public  Page(){

    }

    public Page(T data, int curentPage, int totalPage) {
        this.data = data;
        this.curentPage = curentPage;
        this.totalPage = totalPage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCurentPage() {
        return curentPage;
    }

    public void setCurentPage(int curentPage) {
        this.curentPage = curentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
