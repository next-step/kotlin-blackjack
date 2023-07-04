package blackjack.domain.card

import blackjack.domain.card.Denomination.*
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class DenominationTest : StringSpec({

    "denomination은 정확한 점수를 반환해야한다." {
        forAll(
            row(ACE, 1),
            row(TWO, 2),
            row(THREE, 3),
            row(FOUR, 4),
            row(FIVE, 5),
            row(SIX, 6),
            row(SEVEN, 7),
            row(EIGHT, 8),
            row(NINE, 9),
            row(KING, 10),
            row(QUEEN, 10),
            row(JACK, 10)
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
            ACE.getScore(score) shouldBe expected
        }
    }
})
