
import java.util.ArrayList;
        import java.util.List;
        import javax.annotation.Generated;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class AllComAndSec {

    @SerializedName("companies")
    @Expose
    private List<Company> companies = new ArrayList<Company>();

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    @Override
    public String toString() {
        return "AllComAndSec{" +
                "companies=" + companies +
                '}';
    }
}