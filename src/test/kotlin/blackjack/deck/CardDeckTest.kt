package blackjack.deck

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CardDeckTest {

    @Test
    fun `카드 덱에 카드 52개가 생성되는지 확인한다`() {
        assertThat(CardDeck.cards.size).isEqualTo(CARD_DECK_SIZE)
    }

    @Test
    fun `카드덱에서 카드 한장을 받는다`() {
        assertThat(CardDeck.draw()).isNotNull
    }

    @Test
    fun `카드 덱에서 카드를 전부 발급 받으면 Illegalargumentexception 에러가 발생한다`() {
        (0 until CARD_DECK_SIZE)
            .forEach { _ -> CardDeck.draw() }

        assertThrows<IllegalArgumentException> { CardDeck.draw() }
    }

    companion object {
        private const val CARD_DECK_SIZE = 52
    }
}
