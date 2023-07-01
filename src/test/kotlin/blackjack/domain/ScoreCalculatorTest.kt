package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.Cards
import blackjack.domain.card.Suit
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class ScoreCalculatorTest : BehaviorSpec({

    Given("카드가 들어 있는 플레이어 패가 있다") {
        val cards = Cards(
            listOf(
                Card(Suit.SPADE, CardNumber.ACE), // 이때는 11
                Card(Suit.SPADE, CardNumber.NINE),
                Card(Suit.HEART, CardNumber.ACE), // 이때는 1
            ),
        )
        When("해당덱의 점수를 구하면") {
            Then("합이 반환된다") {
                ScoreCalculator.calculateScore(cards) shouldBe 21
            }
        }
    }
})
