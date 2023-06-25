package blackjack.domain.card

import blackjack.domain.score.Score
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.matchers.shouldBe

class CardTest : StringSpec({
    "카드가 에이스일 때 점수는 11 이다" {
        forAll<CardSymbol> {
            Card(CardNumber.ACE, it).toScore() shouldBe Score(11)
        }
    }
})
