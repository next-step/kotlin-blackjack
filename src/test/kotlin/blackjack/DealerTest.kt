package blackjack

import blackjack.domain.CardDeck
import blackjack.domain.Dealer
import blackjack.domain.strategy.SequentialCardPickStrategy
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

internal class DealerTest : BehaviorSpec({
    Given("딜러는 ") {
        val cardDeck = CardDeck()
        val firstCard = cardDeck.cards.cards.first()
        When("전략에 따라 카드를 ") {
            val card = Dealer(SequentialCardPickStrategy()).pickCard(cardDeck)
            Then("가져온다.") {
                card shouldBe firstCard
            }
        }
    }
})
