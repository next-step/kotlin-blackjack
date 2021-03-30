package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class GameTest {
    private val HIT = 1

    @Test
    fun `블랙잭은 처음에 카드를 2장을 가져가면 현재 상태를 리턴한다`() {
        val game = Game(Card(CardSuite.HEART, CardNumber.ACE), Card(CardSuite.SPADE, CardNumber.TWO))
        val state = game.state

        assertThat(state).isEqualTo(HIT)
    }

    @ParameterizedTest
    @CsvSource("HEART,QUEEN")
    fun `카드의 숫자의 합이 21미만이면 Hit 이다`() {
        val game = Game(Card(CardSuite.HEART, CardNumber.QUEEN), Card(CardSuite.SPADE, CardNumber.KING)) // 20
        val state = game.state

        assertThat(state).isEqualTo(HIT)
    }
}
