package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardPattern
import blackjack.domain.card.Cards
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.equality.shouldBeEqualToComparingFields

class StartTest : BehaviorSpec({

    Given("카드 2장이 주어졌을 때 - 1") {
        val firstCard = Card(number = CardNumber.TEN, pattern = CardPattern.CLOVER)
        val secondCard = Card(number = CardNumber.ACE, pattern = CardPattern.CLOVER)
        val cards = Cards().apply {
            add(firstCard)
            add(secondCard)
        }

        When("두 점수의 합이 블랙잭이면") {
            val result = Start.handCard(firstCard, secondCard)

            Then("블랙잭을 반환한다.") {
                result shouldBeEqualToComparingFields Blackjack(cards)
            }
        }
    }

    Given("카드 2장이 주어졌을 때 - 2") {
        val firstCard = Card(number = CardNumber.TEN, pattern = CardPattern.CLOVER)
        val secondCard = Card(number = CardNumber.JACK, pattern = CardPattern.CLOVER)
        val cards = Cards().apply {
            add(firstCard)
            add(secondCard)
        }

        When("두 점수의 합이 블랙잭이 아니면") {
            val result = Start.handCard(firstCard, secondCard)

            Then("히트를 반환한다.") {
                result shouldBeEqualToComparingFields Hit(cards)
            }
        }
    }
})
