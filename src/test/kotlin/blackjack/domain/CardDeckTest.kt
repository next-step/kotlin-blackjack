package blackjack.domain

import domain.CardDeck
import domain.factory.DefaultCardsFactory
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.style.FreeSpec

class CardDeckTest : FreeSpec({
    "카드덱은 순서를 바꿀 수 있다" {
        val deck = CardDeck(cardsFactory = DefaultCardsFactory)
        shouldNotThrowAny { deck.shuffle() }
    }
})
