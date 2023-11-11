package edu.hw5;

import edu.hw5.task5.Task5;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Task5Test {

    private Task5 task5;

    @BeforeEach
    void setUp() {
        task5 = new Task5();
    }

    @ParameterizedTest
    @CsvSource({"'А001АА01', true",
        "'В002ВВ02', true",
        "'Е102ЕЕ03', true",
        "'К702КК04', true",
        "'М003ММ05', true",
        "'Н103НН06', true",
        "'О004ОО07', true",
        "'Р005РР08', true",
        "'С006СС09', true",
        "'Т007ТТ10', true",
        "'У008УУ11', true",
        "'Х009ХХ111', true",
        "'А010АА12', true",
        "'В011ВВ13', true",
        "'Е111ЕЕ14', true",
        "'К012КК15', true",
        "'М013ММ16', true",
        "'Н113НН116', true",
        "'О014ОО17', true",
        "'Р015РР18', true",
        "'С016СС188', true",
        "'Т017ТТ19', true",
        "'У018УУ21', true",
        "'Х019ХХ121', true",
        "'А020АА22', true",
        "'В021ВВ122', true",
        "'Е022ЕЕ23', true",
        "'К093КК123', true",
        "'М023ММ193', true",
        "'Н024НН24', true",
        "'О084ОО124', true",
        "'Р088РР25', true",
        "'С024СС125', true",
        "'Т025ТТ725', true",
        "'У026УУ26', true",
        "'Х027ХХ27', true",
        "'А028АА127', true",
        "'В029ВВ28', true",
        "'Е030ЕЕ29', true",
        "'К130КК30', true",
        "'М031ММ130', true",
        "'Н032НН31', true",
        "'О033ОО32', true",
        "'Р034РР134', true",
        "'С035СС35', true",
        "'Т036ТТ36', true",
        "'У037УУ136', true",
        "'Х038ХХ37', true",
        "'А039АА38', true",
        "'В091ВВ138', true",
        "'Е022ЕЕ2333', false",
        "'К093ГК123', false",
        "'М023ЙМ193', false",
        "'Н024НЖ24', false",
        "'О084ОД124', false",
        "'Р088РР7777', false",
        "'С02СС125', false",
        "'Т5ТТ725', false",
        "'026УУ2', false",
        "'Х0', false",
        "'А028АА', false",
        "'В029В77', false",
        "'Е030ЦЕ29', false",
        "'К130ДК30', false",
        "'М031МЗ130', false",
        "'Н032НН00', false",
        "'О033ОО0', false",
        "'Р034РРРР134', false",
        "'СА035СС35', false",
        "'ТТ036ТТ36', false",
        "'У037УТУ136', false",
        "'Х03ХХ37', false",
        "'', false",
        ", false"})
    void isThisARussianLicensePlateTest(String licensePlate, boolean excepted) {
        var actual = task5.isThisARussianLicensePlate(licensePlate);
        Assertions.assertEquals(excepted, actual);
    }
}
