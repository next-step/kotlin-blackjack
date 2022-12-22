package blackjack.application

import blackjack.domain.card.Denomination
import blackjack.domain.card.PlayingCard
import blackjack.domain.card.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DeckTest {
    @Test
    fun `카드 덱 - 카드 분배 테스트`() {
        // given
        val deck = Deck(PlayingCard(Suit.CLUBS, Denomination.ACE))

        // when
        val expected = PlayingCard(Suit.CLUBS, Denomination.ACE)

        // then
        assertThat(deck.getCard()).isEqualTo(expected)
    }

    @Test
    fun `카드 덱 - 카드 분배 예외처리 테스트, 카드가 없는 경우에 카드를 뽑는 경우`() {
        // given
        val deck = Deck(mutableListOf())

        // when
        val exception = assertThrows<NoSuchElementException> {
            deck.getCard()
        }

        // then
        assertThat(exception.message).isEqualTo("카드가 없습니다.")
    }
}
