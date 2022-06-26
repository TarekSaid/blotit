package org.blotit.rating.domain


import spock.lang.Specification

class DataSheetSpec extends Specification {

    def "creating a new data sheet should calculate the total"() {
        expect: "each total category should contain mono and color's sum"
        sheet.total == expectedTotal

        where:
        sheet << new DataSheet(
            new Inkblot(Type.MONO,
                new Location(3, 7, 2, 0, 0, 0, 0, 0),
                new Determinants(7, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0),
                new Content(4, 2, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0),
                3,
                new Summary(10, 0, 32, 258, 12)
            ),

            new Inkblot(Type.COLOR,
                new Location(0, 16, 2, 2, 0, 0, 0, 0),
                new Determinants(9, 3, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0.5, 2.5, 1.5, 2, 1),
                new Content(10, 0, 2, 2, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1),
                1,
                new Summary(3, 6, 33, 474, 20)
            ))

        expectedTotal << new Inkblot(Type.TOTAL,
        new Location(3, 23, 4, 2, 0, 0, 0, 0),
        new Determinants(16, 4, 2, 2, 1, 0, 0, 0, 0, 1, 0, 0.5, 3.5, 1.5, 2, 1),
        new Content(14, 2, 6, 2, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 1),
        4,
        new Summary(13, 6, 65, 732, 32))
    }
}
