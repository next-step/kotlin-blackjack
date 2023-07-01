package blackjack.domain.card

import blackjack.domain.shuffle.CardCustomShuffler
import blackjack.domain.shuffle.CardNotShuffler
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardDeckTest : StringSpec({

    "모든 카드를 보유한다" {
        val cardDeck = CardDeck.createAllCards(CardNotShuffler())
        cardDeck shouldBe CardDeck(Card.ALL_CARDS)
    }

    "순서가 섞인 카드 목록을 보유한다" {
        val originCards = Card.ALL_CARDS
        val newCards = originCards.toMutableList().apply { add(removeFirst()) }
        val mockShuffler = CardCustomShuffler { newCards }
        CardDeck.createAllCards(mockShuffler) shouldBe CardDeck(newCards)
    }
})
