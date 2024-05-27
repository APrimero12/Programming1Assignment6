import org.example.Address;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class AddressTest {

    @Test
    public void testIsPostalCodeValid1() {
        String postalCode = "H4L3X9";
        boolean expectedResult = true;
        boolean result = Address.isPostalCodeValid(postalCode);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void testIsPostalCodeValid2() {
        String postalCode = "H4L 3X9";
        boolean expectedResult = true;
        boolean result = Address.isPostalCodeValid(postalCode);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void testIsPostalCodeValid3() {
        String postalCode = null;
        boolean expectedResult = false;
        boolean result = Address.isPostalCodeValid(postalCode);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void testIsPostalCodeValid4() {
        String postalCode = "H4L3X 9";
        boolean expectedResult = false;
        boolean result = Address.isPostalCodeValid(postalCode);

        Assertions.assertEquals(expectedResult, result);
    }
}
