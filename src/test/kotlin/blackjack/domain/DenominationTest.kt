package blackjack.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class DenominationTest : FunSpec({

    context("isAce") {
        data class IsAce(val denomination: Denomination, val expected: Boolean)
        withData(
            IsAce(Denomination.ACE, true),
            IsAce(Denomination.TWO, false),
            IsAce(Denomination.THREE, false),
            IsAce(Denomination.FOUR, false),
            IsAce(Denomination.FIVE, false),
            IsAce(Denomination.SIX, false),
            IsAce(Denomination.SEVEN, false),
            IsAce(Denomination.EIGHT, false),
            IsAce(Denomination.NINE, false),
            IsAce(Denomination.TEN, false),
            IsAce(Denomination.JACK, false),
            IsAce(Denomination.QUEEN, false),
            IsAce(Denomination.KING, false),
        ) { (denomination, expected) ->
            denomination.isAce() shouldBe expected
        }
    }

    context("maxScore") {
        data class MaxScore(val denomination: Denomination, val maxScore: Int)
        withData(
            MaxScore(Denomination.ACE, 11),
            MaxScore(Denomination.TWO, 2),
            MaxScore(Denomination.THREE, 3),
            MaxScore(Denomination.FOUR, 4),
            MaxScore(Denomination.FIVE, 5),
            MaxScore(Denomination.SIX, 6),
            MaxScore(Denomination.SEVEN, 7),
            MaxScore(Denomination.EIGHT, 8),
            MaxScore(Denomination.NINE, 9),
            MaxScore(Denomination.TEN, 10),
            MaxScore(Denomination.JACK, 10),
            MaxScore(Denomination.QUEEN, 10),
            MaxScore(Denomination.KING, 10),
        ) { (denomination, maxScore) ->
            denomination.maxScore() shouldBe maxScore
        }
    }
})
