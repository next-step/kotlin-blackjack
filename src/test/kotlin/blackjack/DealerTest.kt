package blackjack

import blackjack.domain.Card
import blackjack.domain.CardDeck
import blackjack.domain.CardNumber
import blackjack.domain.CardShape
import blackjack.domain.Cards
import blackjack.domain.Dealer
import blackjack.domain.strategy.SequentialCardPickStrategy
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

internal class DealerTest : BehaviorSpec({
    Given("딜러는 ") {
        val cardDeck = CardDeck()
        val firstCard = cardDeck.cards.cards.first()
        When("전략에 따라 카드를 ") {
            val card = Dealer(name = "딜러", cardPickStrategy = SequentialCardPickStrategy()).pickCard(cardDeck)
            Then("가져온다.") {
                card shouldBe firstCard
            }
        }

        When("카드를 추가하면 ") {
            val dealer = Dealer(name = "딜러", cardPickStrategy = SequentialCardPickStrategy())
            dealer.addCard(Card(CardShape.CLOVER, CardNumber.QUEEN))
            Then("정상적으로 카드를 추가한다.") {
                dealer.cards.cards.first().cardShape shouldBe CardShape.CLOVER
                dealer.cards.cards.first().cardNumber shouldBe CardNumber.QUEEN
            }
        }

        When("점수를 계산하면 ") {
            val dealer = Dealer(name = "딜러", cardPickStrategy = SequentialCardPickStrategy(), cards = Cards(mutableListOf(Card(CardShape.CLOVER, CardNumber.QUEEN))))
            val score = dealer.getScore()
            Then("정상적으로 점수를 계산한다.") {
                score shouldBe 10
            }
        }
    }
})
