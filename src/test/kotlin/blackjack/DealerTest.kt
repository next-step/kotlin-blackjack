package blackjack

import domain.card.Cards
import domain.dealer.Dealer
import domain.dealer.DealerState
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러가 가진 카드의 합계가 16 이하이면 카드를 받을 수 있다`() {
        assertThat(
            Dealer(DealerState(Cards(listOf(spadeTen, spadeSix)))).canReceiveMoreCard
        ).isTrue()
    }

    @Test
    fun `딜러가 가진 카드의 합계가 17 이상이면 카드를 받을 수 없다`() {
        assertThat(
            Dealer(DealerState(Cards(listOf(spadeTen, spadeSeven)))).canReceiveMoreCard
        ).isFalse()
    }
}
