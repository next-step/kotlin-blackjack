package blackjack.domain.card

import blackjack.domain.shuffle.CardCustomShuffler
import blackjack.domain.shuffle.CardNotShuffler
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardDeckTest : StringSpec({

    "모든 카드를 보유한다" {
        val cardDeckCards = CardDeck(CardNotShuffler()).cards
        val allCards = Card.ALL_CARDS
        val intersectSize = cardDeckCards.intersect(allCards.toSet()).size
        intersectSize shouldBe allCards.size
    }

    "순서가 섞인 카드 목록을 보유한다" {
        val originCards = Card.ALL_CARDS
        val newCards = originCards.toMutableList().apply { add(removeFirst()) }
        val mockShuffler = CardCustomShuffler { newCards }
        CardDeck(mockShuffler).cards shouldBe newCards
    }
})
