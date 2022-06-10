package balckjac.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ScoreCalculatorTest {

    @Test
    fun `ScoreCalculator 는 카드 목록을 전달 받아 total 점수를 계산한다`() {
        assertThat(
            ScoreCalculator.calculate(
                listOf(
                    Card(Suit.DIA, Denomination.FIVE),
                    Card(Suit.DIA, Denomination.SIX),
                    Card(Suit.DIA, Denomination.SEVEN),
                )
            )
        ).isEqualTo(18)
    }

    @Test
    fun `King, Queen, Jack은  10으로 계산한다`() {
        assertThat(ScoreCalculator.calculate(listOf(Card(Suit.DIA, Denomination.FIVE), Card(Suit.DIA, Denomination.KING)))).isEqualTo(15)
        assertThat(ScoreCalculator.calculate(listOf(Card(Suit.DIA, Denomination.FIVE), Card(Suit.DIA, Denomination.QUEEN)))).isEqualTo(15)
        assertThat(ScoreCalculator.calculate(listOf(Card(Suit.DIA, Denomination.FIVE), Card(Suit.DIA, Denomination.JACK)))).isEqualTo(15)
    }
}
