package blackjack.domain.card

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class CardScoreTest : FunSpec({
    test("두 개의 카드의 점수를 더할 수 있다.") {
        val cardScore1 = CardScore(10)
        val cardScore2 = CardScore(11)
        cardScore1 + cardScore2 shouldBe CardScore(21)
    }
})
