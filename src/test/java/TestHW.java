import data.Result;
import org.junit.BeforeClass;
import org.junit.Test;
import spacifications.SpacificationRest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static spacifications.SpacificationRest.setReqSpec;
import static spacifications.SpacificationRest.setResSpec;

public class TestHW {
    static List<Result> resultList;

    @BeforeClass
    static public void bTest() {
        SpacificationRest.setForRickAndMorty(setReqSpec("https://rickandmortyapi.com/api/"), setResSpec());
        resultList = given().when()
                .get("character")
                .then()
                .extract().body().jsonPath().getList("results", Result.class);
    }
    @Test
    public void testNames() {
        List<String> errorUrl = new ArrayList<>();
        for (Result l : resultList) {
            if (Objects.isNull(l.name)){
                errorUrl.add(String.valueOf(l.id));
                continue;
            }
            System.out.println("name:"+" " + l.name + "\n" + "status:"+" " + l.status+"\n"+"species"+" "+l.species);
        }


    }

}

