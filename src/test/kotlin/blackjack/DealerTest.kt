package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러는 17을 넘지 않으면 카드를 받는다`() {
        val score16Dealer: Dealer = Dealer().apply {
            hit(PlayingCard(Suits.DIAMOND, CardNumber.KING))
            hit(PlayingCard(Suits.CLOVER, CardNumber.SIX))
        }

        assertThat(score16Dealer.canGetCard).isEqualTo(true)
    }

    @Test
    fun `딜러는 17을 넘으면 카드를 받지 않는다`() {
        val score17Dealer: Dealer = Dealer().apply {
            hit(PlayingCard(Suits.DIAMOND, CardNumber.KING))
            hit(PlayingCard(Suits.CLOVER, CardNumber.SIX))
            hit(PlayingCard(Suits.CLOVER, CardNumber.ACE))
        }
        assertThat(score17Dealer.canGetCard).isEqualTo(false)
    }

    @Test
    fun `딜러는 버스트되면 카드를 받지 않는다`() {
        val score23Dealer: Dealer = Dealer().apply {
            hit(PlayingCard(Suits.DIAMOND, CardNumber.KING))
            hit(PlayingCard(Suits.CLOVER, CardNumber.SIX))
            hit(PlayingCard(Suits.CLOVER, CardNumber.SEVEN))
        }
        assertThat(score23Dealer.canGetCard).isEqualTo(false)
    }
}
