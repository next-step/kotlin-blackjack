package blackjack.application

import blackjack.SpadeAce
import blackjack.SpadeFour
import blackjack.SpadeThree
import blackjack.SpadeTwo
import blackjack.domain.card.PlayingCard
import blackjack.domain.card.PlayingCards
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `카드 덱 - 카드 분배 테스트`() {
        // given
        val deck = Deck(SpadeAce)

        // when, then
        assertThat(deck.getCard()).isEqualTo(SpadeAce)
    }

    @Test
    fun `카드 덱 - 카드가 없는 경우에 카드를 뽑는 경우, 덱 내부에서 카드를 자동 생성`() {
        // given
        val deck = Deck(mutableListOf())

        // when
        val actual = deck.getCard()

        // then
        assertThat(actual).isInstanceOf(PlayingCard::class.java)
    }

    @Test
    fun `카드 덱 - ShuffleStrategy 테스트`() {
        val cards = PlayingCards.shuffle { listOf(SpadeAce, SpadeTwo, SpadeThree, SpadeFour) }
        val deck = Deck(cards.toMutableList())

        assertThat(deck.getCard()).isEqualTo(SpadeAce)
        assertThat(deck.getCard()).isEqualTo(SpadeTwo)
        assertThat(deck.getCard()).isEqualTo(SpadeThree)
        assertThat(deck.getCard()).isEqualTo(SpadeFour)
    }
}
