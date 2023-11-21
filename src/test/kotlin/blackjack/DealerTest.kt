package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러는 17을 넘지 않으면 계속 카드를 받아야하는 상태다`() {
        val dealer = Dealer()
        dealer.getInitialCards(
            listOf(
                PlayingCard(Suits.DIAMOND, CardNumber.THREE),
                PlayingCard(Suits.CLOVER, CardNumber.KING)
            )
        )
        assertThat(dealer.canGetCard).isEqualTo(true)
        dealer.hit(PlayingCard(Suits.CLOVER, CardNumber.THREE))
        assertThat(dealer.canGetCard).isEqualTo(true)
        dealer.hit(PlayingCard(Suits.CLOVER, CardNumber.QUEEN))
        assertThat(dealer.canGetCard).isEqualTo(false)
    }
}
