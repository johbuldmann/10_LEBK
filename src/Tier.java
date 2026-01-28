public class Tier {

    private String art;
    private int groesse;


    public Tier(String art, int groesse) {
        this.art = art;
        this.groesse = groesse;
    }


    public void wachsen(int cm) {
        this.groesse += cm;
        System.out.println("Die " + art + " ist " + groesse + " cm groß");
    }

    public double fresse() {
        if (groesse < 20) {
            return 0.5;
        } else if (groesse >= 40) {
            return 1.5;
        } else {
            return 1.0;
        }
    }

    public String getArt() {
        return this.art;
    }

    public void setArt(String art) {
        this.art = art;
    }

    public int getGroesse() {
        return this.groesse;
    }

    public void setGroesse(int groesse) {
        this.groesse = groesse;
    }


}