package blackjack

import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.CardShape
import blackjack.domain.ResultCalculator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ResultCalculatorTest {
    @Test
    fun `카드의 합계를 계산하는 결과가 맞는지 테스트`() {
        val cards = listOf(
            Card(CardNumber.ACE, CardShape.CLOVER),
            Card(CardNumber.THREE, CardShape.HEART),
            Card(CardNumber.QUEEN, CardShape.CLOVER),
        )

        val answer = ResultCalculator().calculateRecursive(cards)
        val expected = 14

        assertThat(answer).isEqualTo(expected)
    }
}
