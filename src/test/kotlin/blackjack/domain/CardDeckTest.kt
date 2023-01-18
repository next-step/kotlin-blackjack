package blackjack.domain

import domain.card.Card
import domain.card.CardDeck
import domain.card.CardNumber
import domain.card.CardShape
import domain.factory.CardsFactory
import domain.factory.DefaultCardsFactory
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class CardDeckTest : FreeSpec({
    "카드덱은 순서를 바꿀 수 있다" {
        val deck = CardDeck(cardsFactory = DefaultCardsFactory)
        shouldNotThrowAny { deck.shuffle() }
    }

    "카드덱은 가장 위에있는 카드를 꺼낼 수 있다" {
        val deck = CardDeck(
            object : CardsFactory {
                override fun generate(): MutableList<Card> = mutableListOf(
                    Card(CardShape.HEART, CardNumber.ACE),
                    Card(CardShape.HEART, CardNumber.TWO)
                )
            }
        )
        val firstPop = deck.pop()
        firstPop shouldBe Card(CardShape.HEART, CardNumber.ACE)
        deck.pop() shouldBe Card(CardShape.HEART, CardNumber.TWO)
    }
})
