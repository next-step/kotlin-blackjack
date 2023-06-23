package blackjack

import blackjack.domain.card.CardDeck
import blackjack.domain.card.CardType
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CardDeckTest {
    @Test
    fun `카드덱에서 카드를 가져온 후 가져온 카드는 카드덱에서 제거합니다`() {
        val deck = CardDeck()

        val randomCards = deck.getRandomCards(2)
        val result = randomCards.getValue().any { deck.cards.getValue().contains(it) }

        result shouldBe false
    }

    @Test
    fun `CardDeck은 다이아몬드, 하트, 클로버, 스페이드가 각각 13개의 끗수로 이루어져있다`() {
        val deck = CardDeck.cardDeck
        val validMap = mutableMapOf(*CardType.values().map { it to 0 }.toTypedArray())

        deck.forEach { card -> validMap[card.type] = validMap[card.type]!! + 1 }

        validMap.values shouldBe listOf(13, 13, 13, 13)
    }
}
