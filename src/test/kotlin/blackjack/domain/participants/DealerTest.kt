package blackjack.domain.participants

import blackjack.domain.CLUB_KING
import blackjack.domain.CLUB_TWO
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `17이상이면 카드를 드로우 하지 않는다`() {
        val dealer = Dealer(initCards = arrayListOf(CLUB_KING, CLUB_KING))
        val beforeDrawScore = dealer.getScore()
        dealer.drawCard()
        val afterDrawScore = dealer.getScore()
        assertThat(beforeDrawScore).isEqualTo(afterDrawScore)
    }

    @Test
    fun `16이하이면 카드를 드로우 하지 않는다`() {
        val dealer = Dealer(initCards = arrayListOf(CLUB_TWO, CLUB_KING))
        val beforeDrawScore = dealer.getScore()
        dealer.drawCard()
        val afterDrawScore = dealer.getScore()
        Assertions.assertThat(beforeDrawScore).isNotEqualTo(afterDrawScore)
    }
}
