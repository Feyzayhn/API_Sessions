package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GoRestPojo {

    // BURASI OUTER POJO CLASS

    // 1) Tum keyler icin private variable'lar olusturuyoruz
    private Object meta;  // data turunu obje secmemin sebebi sonucu null oldugu icin
                           // Strıng,Integer vb ne oldugunu bilmedigim icin kapsayici girdim

    private GoRestDataPojo data;

    // 2) Tum parametrelerle ve parametresiz constroctorlar olusturuyoruz


    public GoRestPojo(Object meta, GoRestDataPojo data) {
        this.meta = meta;
        this.data = data;
    }

    public GoRestPojo() {
    }


    // 3) Public Getter ve Setter methodlarini olusturuyoruz

    public Object getMeta() {
        return meta;
    }

    public void setMeta(Object meta) {
        this.meta = meta;
    }

    public GoRestDataPojo getData() {
        return data;
    }

    public void setData(GoRestDataPojo data) {
        this.data = data;
    }


    // 4) toString() methodunu olusturuyoruz


    @Override
    public String toString() {
        return "GoRestMetaPojo{" +
                "meta=" + meta +
                ", data=" + data +
                '}';
    }
}
