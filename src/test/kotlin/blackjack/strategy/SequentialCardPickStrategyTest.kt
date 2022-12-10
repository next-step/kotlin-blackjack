package blackjack.strategy

import blackjack.domain.CardDeck
import blackjack.domain.strategy.SequentialCardPickStrategy
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

internal class SequentialCardPickStrategyTest : BehaviorSpec({
    Given("카드 생성 전략이 ") {
        val cardDeck = CardDeck()
        val firstCard = cardDeck.cards.cards.first()
        When("순차적으로 뽑는 전략이면 ") {
            val card = SequentialCardPickStrategy().pick(cardDeck)
            Then("처음 카드를 가져온다.") {
                card shouldBe firstCard
            }
        }
    }
})
