package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayingCardsTest {
    @Test
    fun `플레잉 카드를 생성하면 각각 4문양 및 13개의 숫자로 조합된 52장의 카드가 만들어진다`() {
        val cards = PlayingCards.createCards().cards
        assertThat(cards.size).isEqualTo(52)
        assertThat(cards.count { playingCard -> playingCard.suit == Suits.DIAMOND }).isEqualTo(13)
        assertThat(cards.count { playingCard -> playingCard.number == CardNumber.ACE }).isEqualTo(4)
    }
}
