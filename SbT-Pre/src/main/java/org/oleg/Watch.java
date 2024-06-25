package org.oleg;
@AWatch(hour = 12, description = "OlegMangal")
@AWatch(hour = 15, description = "JustOleg")
public class Watch {
    private int hour;
    private String description;
    public Watch(int hour, String description){
        this.hour = hour;
        this.description = description;
    }
    public Watch(String description){
        AWatch[] w = this.getClass().getAnnotationsByType(AWatch.class);
        this.hour = w[0].hour();
        this.description = description;
    }
    public Watch(){
        AWatch[] w = this.getClass().getAnnotationsByType(AWatch.class);
        this.hour = w[0].hour();
        this.description = w[0].description();
    }
    public int nextHour(){
        hour += 1;
        return hour;
    }
    public String getWatchByOleg(){
        AWatch[] w = this.getClass().getAnnotationsByType(AWatch.class);
        return w[0].description();
    }
    public String getWatchByOlegM(){
        AWatch[] w = this.getClass().getAnnotationsByType(AWatch.class);
        return w[1].description();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getHour() {
        return hour;
    }

    public String getDescription() {
        return description;
    }

}
