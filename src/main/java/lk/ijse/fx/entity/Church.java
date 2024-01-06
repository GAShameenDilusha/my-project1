package lk.ijse.fx.entity;

public class Church {
    private String church_no;
    private int A;
    private int B;
    private int C;
    private int D;

    public Church(String church_no, int a, int b, int c, int d) {
        this.church_no = church_no;
        this.A = a;
        this.B = b;
        this.C = c;
        this.D = d;
    }

    public Church() {
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

    public void setA(int a) {
        this.A = a;
    }

    public int getB() {
        return B;
    }

    public void setB(int b) {
        this.B = b;
    }

    public int getC() {
        return C;
    }

    public void setC(int c) {
        this.C = c;
    }

    public int getD() {
        return D;
    }

    public void setD(int d) {
        this.D = d;
    }
}
