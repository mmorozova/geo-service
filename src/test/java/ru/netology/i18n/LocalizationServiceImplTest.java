package ru.netology.i18n;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalizationServiceImplTest {
    @Test
    public void localeTestRUS() {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        Country country = Country.RUSSIA;
        String expected = "Добро пожаловать";

        String actual = localizationService.locale(country);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @EnumSource(value = Country.class, names = {"USA", "GERMANY", "BRAZIL"})
    public void testLocaleWithParams(Country country) {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String expected = "Welcome";

        String actual = localizationService.locale(country);

        assertEquals(expected, actual);
    }
}
