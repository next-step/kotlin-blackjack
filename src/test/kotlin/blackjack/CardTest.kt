package blackjack

import blackjack.domain.Card
import blackjack.domain.Ranks
import blackjack.domain.Suits
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `카드 한 장에는 숫자와 기호가 있다`() {
        val rank = Ranks.EIGHT
        val suit = Suits.CLUBS
        val card = Card.createCard(rank, suit)

        assertThat(card.numbers[0]).isEqualTo(8)
        assertThat(card.suit).isEqualTo(Suits.CLUBS)
    }
}
