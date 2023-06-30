package MyStoreShop;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/Cucumber/features", plugin = {"pretty", "html:out.html"}, tags = "@shop") //shopping
public final class StartShopTest {

//    @Test
//    @DisplayName("Testy aplikacji myStore")
//    @RepeatedTest(2)
//    public void testGoogleSearch() {
//    }
}
