package blackjack

import blackjack.domain.card.Card
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class CardTest : FreeSpec({

    "toString" - {

        "숫자+모양 형태로 출력되어야한다." {
            val card = Card("다이아몬드", "7")

            card.toString() shouldBe "7다이아몬드"
        }
    }
})
