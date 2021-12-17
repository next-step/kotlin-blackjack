package blackjack.state

import blackjack.domain.card.Cards
import blackjack.domain.Score
import blackjack.domain.state.Blackjack
import blackjack.domain.state.Finished.Companion.UNSUPPORTED_DRAW_METHOD
import blackjack.domain.state.Finished.Companion.UNSUPPORTED_STAY_METHOD
import blackjack.domain.state.GameResultState
import blackjack.domain.state.Initial
import blackjack.domain.state.State
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BlackjackTest {

    private lateinit var blackjack: Blackjack

    @BeforeEach
    internal fun setUp() {
        blackjack = Blackjack(Cards(listOf(CARD_HEART_ACE, CARD_HEART_TEN)))
    }

    @Test
    fun `Blackjack 상태이면 Blackjack과의 match()에서 무승부이다`() {
        val blackjack2 = Blackjack(Cards(listOf(CARD_HEART_KING, CARD_HEART_ACE)))

        val match: GameResultState = blackjack.match(blackjack2)

        assertThat(match).isEqualTo(GameResultState.Draw)
    }

    @Test
    fun `Blackjack 상태이면 bust상태와의 match()에서 승리한다`() {
        var bust: State = Initial()
        bust = bust.draw(CARD_HEART_KING)
            .draw(CARD_HEART_KING)
            .draw(CARD_HEART_KING)

        val match: GameResultState = blackjack.match(bust)

        assertThat(match).isEqualTo(GameResultState.Win)
    }

    @Test
    fun `Blackjack의 score는 21이다`() {
        val score = blackjack.score

        assertThat(score).isEqualTo(Score(21))
    }

    @Test
    fun `Blackjack은 Finished 상태이다`() {
        val isFinished: Boolean = blackjack.isFinished()

        assertThat(isFinished).isTrue
    }

    @Test
    fun `Blackjack은 카드를 뽑을 수 없다`() {

        Assertions
            .assertThatExceptionOfType(UnsupportedOperationException::class.java)
            .isThrownBy {
                blackjack.draw(CARD_HEART_ACE)
            }
            .withMessage(UNSUPPORTED_DRAW_METHOD)
    }

    @Test
    fun `Blackjack은 stay할 수 없다`() {

        Assertions
            .assertThatExceptionOfType(UnsupportedOperationException::class.java)
            .isThrownBy {
                blackjack.stay()
            }.withMessage(UNSUPPORTED_STAY_METHOD)
    }
}
