package ylc.sgp.dotcom;

public class DotComShip {
    private String url;

    public DotComShip(String name) {
        this.url = this.guaranteeURL(name);
    }

    //guarantee some sort of url naming convention (note that domains that are not valid will be exempted - i.e. zanga.fakedomain)
    private String guaranteeURL(String url) {
        if(url.contains(".")) {
            return url;
        }
        else {
            return url.concat(".com");
        }
    }

    //ensure that the number of cells required will always be greater or equal to 1
    private int getWordCellSpace() {
        return ((this.url.length() / 2) + 1);
    }
}