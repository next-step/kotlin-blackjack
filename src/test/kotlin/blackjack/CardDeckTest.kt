package blackjack

import blackjack.domain.card.CardDeck
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CardDeckTest {
    @Test
    fun `카드덱에서 카드를 랜덤하게 카드를 가져온 후 덱에서 가져온 카드를 제거합니다`() {
        val deck = CardDeck()

        val randomCards = deck.getRandomCards(2)
        val result = randomCards.any { deck.cards.contains(it) }

        result shouldBe false
    }
}
