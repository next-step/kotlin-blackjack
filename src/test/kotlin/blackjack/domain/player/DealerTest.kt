package blackjack.domain.player

import blackjack.Hand
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

@Suppress("NonAsciiCharacters")
class DealerTest {

    @Test
    fun `Dealer는 스코어가 16 이하라면 hit할 수 있다`() {
        val underHitHand = Hand(16)
        val dealer = Dealer(hand = underHitHand)

        val result = dealer.canHit()

        assertThat(result).isTrue
    }

    @Test
    fun `Dealer는 스코어가 17 이상이라면 hit할 수 없다`() {
        val overHitHand = Hand(17)
        val dealer = Dealer(hand = overHitHand)

        val result = dealer.canHit()

        assertThat(result).isFalse
    }
}
