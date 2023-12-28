package lk.ijse.fx.dto;

public class VehicleDto {
    private String ChurchFatherId;
    private String Date;
    private String Category;
    private String Discription;

    public VehicleDto(){
    }

    public VehicleDto(String churchFatherId, String date, String category, String discription) {
        this.ChurchFatherId = churchFatherId;
        this.Date = date;
        this.Category = category;
        this.Discription = discription;
    }

    public String getChurchFatherId() {
        return ChurchFatherId;
    }

    public void setChurchFatherId(String chuchFatherId) {
        ChurchFatherId = chuchFatherId;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getDiscription() {
        return Discription;
    }

    public void setDiscription(String discription) {
        Discription = discription;
    }

    @Override
    public String toString() {
        return "VehicleDto{" +
                "ChuchFatherId='" + ChurchFatherId + '\'' +
                ", Date='" + Date + '\'' +
                ", Category='" + Category + '\'' +
                ", Discription='" + Discription + '\'' +
                '}';
    }
}
