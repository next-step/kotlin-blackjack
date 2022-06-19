package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러의 점수가 16점 이하일 경우 카드를 더 받을 수 있다`() {
        val receivableDealer = Participant.Dealer(
            "딜러",
            PlayingCard(Suit.DIAMONDS, CardNumber.KING),
            PlayingCard(Suit.DIAMONDS, CardNumber.TWO)
        )
        assertThat(receivableDealer.isReceivable()).isTrue

        val nonReceivableDealer = Participant.Dealer(
            "딜러",
            PlayingCard(Suit.CLUBS, CardNumber.JACK),
            PlayingCard(Suit.CLUBS, CardNumber.ACE)
        )
        assertThat(nonReceivableDealer.isReceivable()).isFalse
    }
}
