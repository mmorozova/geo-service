package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.function.Executable;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class GeoServiceImplTest {
    public static Stream<Arguments> testByIpWithParams() {
        return Stream.of(
                Arguments.of(
                        GeoServiceImpl.LOCALHOST,
                        new Location(null,null,null,0)),
                Arguments.of(
                        GeoServiceImpl.NEW_YORK_IP,
                        new Location("New York", Country.USA, " 10th Avenue", 32)),
                Arguments.of(
                        GeoServiceImpl.MOSCOW_IP,
                        new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of(
                        "172.",
                        new Location("Moscow", Country.RUSSIA, null, 0)),
                Arguments.of(
                        "96.",
                        new Location("New York", Country.USA, null,  0)
                )
        );
    }

    @ParameterizedTest
    @MethodSource
    public void testByIpWithParams(String ip, Location expectedLoc){
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location actual = geoService.byIp(ip);

        Assertions.assertEquals(expectedLoc.getCountry(), actual.getCountry());
        Assertions.assertEquals(expectedLoc.getStreet(), actual.getStreet());
        Assertions.assertEquals(expectedLoc.getCity(), actual.getCity());
    }


    @Test
    void testByCoordinatesException() {
        double latitude = 0;
        double longitude = 0;

        GeoServiceImpl geoService = new GeoServiceImpl();
        Executable executable = () -> geoService.byCoordinates(latitude, longitude);

        assertThrows(RuntimeException.class, executable);
    }
}
