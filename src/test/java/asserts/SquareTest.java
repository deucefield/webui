package asserts;

import homeworks.lesson4.Triangle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class SquareTest {

    @ParameterizedTest
    @CsvSource({"3, 4, 5"})
    void squareTest(int a, int b, int c) {
        double result = new Triangle().calculateSquare(a, b, c);
        Assertions.assertEquals(6, result);
    }

}
