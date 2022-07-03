package blackjack.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class DealerTest {
    @Test
    internal fun `2장의 카드 숫자의 총합이 16이하이면 canNotHit=false이다`() {
        val dealer = Dealer()
        listOf(
            SPADE_JACK,
            SPADE_SIX,
        ).map {
            dealer.receiveCard(it)
        }

        Assertions.assertThat(dealer.sumOfPoints()).isEqualTo(16)
        Assertions.assertThat(dealer.canNotHit).isFalse
    }

    @Test
    internal fun `2장의 카드 숫자의 총합이 17이상이면 canNotHit=true이다`() {
        val dealer = Dealer()
        listOf(
            SPADE_JACK,
            HEART_JACK,
        ).map {
            dealer.receiveCard(it)
        }

        Assertions.assertThat(dealer.sumOfPoints()).isEqualTo(20)
        Assertions.assertThat(dealer.canNotHit).isTrue
    }
}
