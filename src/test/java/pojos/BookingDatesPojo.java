package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingDatesPojo {

    // BURASI INNER POJO


    // 1) Tum keyler icin private variable'lar olusturuyoruz
    private String checkin;
    private String checkout;

    // 2) Tum parametrelerle ve parametresiz constroctorlar olusturuyoruz


    public BookingDatesPojo(String checkin, String checkout) {  // parametreli constroctor
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public BookingDatesPojo() {  // parametresiz  constroctor
    }

    // 3) Public Getter ve Setter methodlarini olusturuyoruz


    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }


    // 4) toString() methodunu olusturuyoruz

    @Override
    public String toString() {
        return "BookingDatesPojo{" +
                "checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';
    }
}
