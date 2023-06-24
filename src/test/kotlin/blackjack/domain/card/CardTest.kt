package blackjack.domain.card

import blackjack.domain.score.Score
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardTest : StringSpec({
    "카드가 에이스일 때 점수는 11 이다" {
        CardSymbol.values()
            .map { Card(CardNumber.ACE, it) }
            .forEach {
                it.toScore() shouldBe Score(11)
            }
    }
})
