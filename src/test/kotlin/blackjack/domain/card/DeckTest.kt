package blackjack.domain.card

import blackjack.SpadeAce
import blackjack.SpadeFive
import blackjack.SpadeFour
import blackjack.SpadeThree
import blackjack.SpadeTwo
import blackjack.domain.card.strategy.StraightShuffleStrategy
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `카드 덱 - 카드 분배 테스트`() {
        // given
        val deck = Deck(StraightShuffleStrategy())

        // when, then
        assertThat(deck.getCard()).isEqualTo(SpadeAce)
        assertThat(deck.getCard()).isEqualTo(SpadeTwo)
        assertThat(deck.getCard()).isEqualTo(SpadeThree)
        assertThat(deck.getCards(2)).isEqualTo(PlayingCards(SpadeFour, SpadeFive))
    }
}
