package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {

    @Test
    fun `딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야한다`() {
        val playerCards = PlayerCards(
            setOf(
                Card(CardSuite.CLOVER, CardNumber.FIVE),
                Card(CardSuite.CLOVER, CardNumber.TWO)
            )
        )

        val dealer = Dealer(playerCards)

        val isSmaller = dealer.isSmallerThanMinimumScore()

        assertThat(isSmaller).isTrue()
    }
}
