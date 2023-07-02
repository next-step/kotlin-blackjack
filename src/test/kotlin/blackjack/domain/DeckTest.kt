package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.Cards
import blackjack.domain.card.Deck
import blackjack.domain.card.Suit
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class DeckTest : BehaviorSpec({

    Given("비어있는 카드 리스트가 있다") {
        val cards = Cards(emptyList())
        When("해당 리스트로 덱을 만들면") {
            Then("에러가 던져진다") {
                shouldThrow<IllegalArgumentException> { Deck(cards) }
            }
        }
    }

    Given("비어있지 않은 카드 리스트가 있다") {
        val cards = Cards(listOf(Card(Suit.SPADE, CardNumber.of(CardNumber.MIN_CARD_NUMBER))))
        When("해당 리스트로 덱을 만들면") {
            Then("생성이 된다") {
                shouldNotThrow<Throwable> { Deck(cards) }
            }
        }
    }

    Given("1장이 들어있는 덱이 있다") {
        val card = Card(Suit.SPADE, CardNumber.of(CardNumber.MIN_CARD_NUMBER))
        val cardList = Deck(Cards(listOf(card)))

        When("덱에서 한장을 꺼내면") {
            Then("카드가 꺼내진다") {
                cardList.drawCard() shouldBe card
                cardList.size shouldBe 0
            }
        }

        When("덱에서 한장을 더 꺼내면") {
            Then("에러가 던져진다") {
                shouldThrow<IllegalStateException> { cardList.drawCard() }
            }
        }
    }
})
