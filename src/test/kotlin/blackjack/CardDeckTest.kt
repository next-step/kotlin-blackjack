package blackjack

import blackjack.domain.card.CardDeck
import blackjack.domain.card.ShuffledCardDeck
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardDeckTest {
    @Test
    fun `CardDeck에서 섞인 카드를 요청할 경우 ShuffledCardDeck을 리턴한다`() {
        val cardDeck = CardDeck
        val shuffledCardDeck = cardDeck.getShuffledCardDeck()
        assertThat(shuffledCardDeck).isInstanceOf(ShuffledCardDeck::class.java)
    }
}
