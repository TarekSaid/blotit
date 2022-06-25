package org.blotit.rating.domain

import spock.lang.Specification

class RateRequestSpec extends Specification {

    def "creating a new rate request should calculate the total"() {
        expect:
        with(req.total) {
            type == Type.TOTAL

            with(location) {
                G == 3
                primaryP == 23
                secondaryP == 4
                E == 2
            }

            with(determinants) {
                FPlus == 16
                FMinus == 4
                FZero == 2
                mPrimary == 2
                mSecondary == 1
                lPrimary == 1
                lLine == 1
                cLine == 3
                FC == 1
                CF == 2
                C == 1
            }

            with(content) {
                A == 14
                pA == 2
                H == 6
                pH == 2
                al == 1
                an == 1
                art == 1
                bt == 1
                ml == 1
                obj == 1
                rl == 1
                vst == 1
            }

            popularity == 4

            with(summary) {
                Elab == 13.0
                zSquared == 6.0
                tri == 65
                T == 732
                R == 32
            }
        }

        where:
        req << new RateRequest(
            new DataSheet(Type.MONO,
                new Location(3, 7, 2, 0, 0, 0, 0, 0),
                new Determinants(7, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0),
                new Content(4, 2, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0),
                3,
                new Summary(10.0, 0.0, 32, 258, 12)
            ),

            new DataSheet(Type.COLOR,
                new Location(0, 16, 2, 2, 0, 0, 0, 0),
                new Determinants(9, 3, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 2, 1, 2, 1),
                new Content(10, 0, 2, 2, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1),
                1,
                new Summary(3.0, 6.0, 33, 474, 20)
            ))
    }
}
