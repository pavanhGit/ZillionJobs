package comproject.jobApp.dto;

public class ReviewDto {
    private String title;
    private String description;
    private double rating;

    public ReviewDto(String title, String description, double rating) {
        this.title = title;
        this.description = description;
        this.rating = rating;
    }

    public ReviewDto() {
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double getRating() {
        return rating;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
