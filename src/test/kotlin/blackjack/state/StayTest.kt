package blackjack.state

import blackjack.domain.card.Cards
import blackjack.domain.Score
import blackjack.domain.state.Blackjack
import blackjack.domain.state.Bust
import blackjack.domain.state.Finished.Companion.UNSUPPORTED_DRAW_METHOD
import blackjack.domain.state.Finished.Companion.UNSUPPORTED_STAY_METHOD
import blackjack.domain.state.GameResultState
import blackjack.domain.state.Stay
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class StayTest {

    private lateinit var stay: Stay

    @BeforeEach
    internal fun setUp() {
        stay = Stay(Cards(listOf(CARD_HEART_KING, CARD_HEART_FOUR)))
    }

    @Test
    fun `Stay상태와 Bust가 match()하면 승리한다`() {
        val bust = Bust(Cards(listOf(CARD_HEART_KING, CARD_HEART_TWO, CARD_HEART_TEN)))

        val match = stay.match(bust)

        assertThat(match).isEqualTo(GameResultState.Win)
    }

    @Test
    fun `Stay상태와 Blackjack이 match()하면 패배한다`() {
        val blackjack = Blackjack(Cards(listOf(CARD_HEART_ACE, CARD_HEART_KING)))

        val match = stay.match(blackjack)

        assertThat(match).isEqualTo(GameResultState.Lose)
    }

    @Test
    fun `Stay상태와 Stay상태가 match()하면 점수가 높으면 승리한다`() {
        val blackjack = Stay(Cards(listOf(CARD_HEART_ACE, CARD_HEART_TWO)))

        val match = stay.match(blackjack)

        assertThat(match).isEqualTo(GameResultState.Lose)
    }

    @Test
    fun `Stay상태와 Stay상태가 match()하면 점수가 같으면 무승부이다`() {
        val blackjack = Stay(Cards(listOf(CARD_HEART_TEN, CARD_HEART_FOUR)))

        val match = stay.match(blackjack)

        assertThat(match).isEqualTo(GameResultState.Draw)
    }

    @Test
    fun `Stay상태와 Stay상태가 match()하면 점수가 낮으면 패배한다`() {
        val blackjack = Stay(Cards(listOf(CARD_HEART_TEN, CARD_HEART_KING)))

        val match = stay.match(blackjack)

        assertThat(match).isEqualTo(GameResultState.Lose)
    }

    @Test
    fun `Stay상태는 Finished 상태이다`() {
        val isFinished = stay.isFinished()

        assertThat(isFinished).isTrue
    }

    @Test
    fun `Stay상태에서는 draw()할 수 없다`() {

        Assertions.assertThatExceptionOfType(UnsupportedOperationException::class.java).isThrownBy {
            stay.draw(CARD_HEART_ACE)
        }.withMessage(UNSUPPORTED_DRAW_METHOD)
    }

    @Test
    fun `Stay상태에서는 stay()할 수 없다`() {

        Assertions
            .assertThatExceptionOfType(UnsupportedOperationException::class.java)
            .isThrownBy { stay.stay() }
            .withMessage(UNSUPPORTED_STAY_METHOD)
    }

    @Test
    fun `Stay상태에서 score를 알 수 있다`() {
        assertThat(stay.score).isEqualTo(Score(14))
    }
}
