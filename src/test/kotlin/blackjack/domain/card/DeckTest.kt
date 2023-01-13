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
        Deck.setShuffleStrategy(StraightShuffleStrategy())

        // when, then
        assertThat(Deck.getCard()).isEqualTo(SpadeAce)
        assertThat(Deck.getCard()).isEqualTo(SpadeTwo)
        assertThat(Deck.getCard()).isEqualTo(SpadeThree)
        assertThat(Deck.getCards(2)).isEqualTo(PlayingCards(SpadeFour, SpadeFive))
    }
}
