package blackjack.domain.card

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class DenominationTest : StringSpec({

    "denomination은 정확한 점수를 반환해야한다." {
        forAll(
            row(Denomination.ACE, 1),
            row(Denomination.TWO, 2),
            row(Denomination.THREE, 3),
            row(Denomination.FOUR, 4),
            row(Denomination.FIVE, 5),
            row(Denomination.SIX, 6),
            row(Denomination.SEVEN, 7),
            row(Denomination.EIGHT, 8),
            row(Denomination.NINE, 9),
            row(Denomination.KING, 10),
            row(Denomination.QUEEN, 10),
            row(Denomination.JACK, 10)
        ) { denomination, score ->
            denomination.getScore() shouldBe score
        }
    }

    "Ace는 누적 점수에 따라 1 또는 11로 계산할 수 있다." {
        forAll(
            row(5, 11),
            row(15, 1),
            row(11, 1),
            row(21, 1),
            row(10, 11),
            row(20, 1)
        ) { score, expected ->
            Denomination.ACE.getScore(score) shouldBe expected
        }
    }
})
