package lk.ijse.fx.dto;

public class ChurchDto {
    private String church_no;
    private int A;
    private int B;
    private int C;
    private int D;

    public ChurchDto() {
    }

    public ChurchDto(String church_no, int A, int B, int C, int D) {
        this.church_no = church_no;
        this.A = A;
        this.B = B;
        this.C = C;
        this.D = D;
    }

    public String getChurch_no() {
        return church_no;
    }

    public void setChurch_no(String church_no) {
        this.church_no = church_no;
    }

    public int getA() {
        return A;
    }

    public void setA(int A) {
        this.A = A;
    }

    public int getB() {
        return B;
    }

    public void setB(int B) {
        this.B = B;
    }

    public int getC() {
        return C;
    }

    public void setC(int C) {
        this.C = C;
    }

    public int getD() {
        return D;
    }

    public void setD(int D) {
        this.D = D;
    }

    @Override
    public String toString() {
        return  church_no;
    }
}
