package blackjack.domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CardTest {

    @Test
    fun `카드가 Ace인지 확인`() {
        // given
        val card = Card.spadeOf("A")

        // then
        assertThat(card.isAce()).isTrue()
    }

    @Test
    fun `카드의 점수 확인`() {
        // given
        val card = Card.spadeOf("7")

        // then
        assertThat(card.getScore()).isEqualTo(7)
    }

    @Test
    fun `모든 카드의 개수 확인`() {
        // then
        assertThat(Card.ALL).hasSize(52)
    }
}
