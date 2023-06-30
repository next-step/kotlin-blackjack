package blackjack

import blackjack.domain.Denomination
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DenominationTest : StringSpec({

    "카드에서 King, Queen, Jack은 각각 10으로 계산한다" {
        val king = Denomination.KING
        val queen = Denomination.QUEEN
        val jack = Denomination.JACK

        king.score shouldBe 10
        queen.score shouldBe 10
        jack.score shouldBe 10
    }
})
