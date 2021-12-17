package blackjack.state

import blackjack.domain.card.Cards
import blackjack.domain.Score
import blackjack.domain.state.Blackjack
import blackjack.domain.state.Bust
import blackjack.domain.state.Finished
import blackjack.domain.state.GameResultState
import blackjack.domain.state.Stay
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BustTest {

    private lateinit var bust: Bust

    @BeforeEach
    internal fun setUp() {
        bust = Bust(Cards(listOf(CARD_HEART_TEN, CARD_HEART_TEN, CARD_HEART_KING)))
    }

    @Test
    fun `Bust 상태이면 Bust와의 match()에서 무승부이다`() {
        val bust2 = Bust(Cards(listOf(CARD_HEART_KING, CARD_HEART_TEN, CARD_HEART_KING)))

        val match: GameResultState = bust.match(bust2)

        assertThat(match).isEqualTo(GameResultState.Draw)
    }

    @Test
    fun `Bust 상태이면 stay상태와의 match()에서 진다`() {
        val stay = Stay(Cards(listOf(CARD_HEART_ACE, CARD_HEART_TWO)))
        val match: GameResultState = bust.match(stay)

        assertThat(match).isEqualTo(GameResultState.Lose)
    }

    @Test
    fun `Bust 상태이면 Blackjack 상태와의 match()에서 진다`() {
        val blackjack = Blackjack(Cards(listOf(CARD_HEART_KING, CARD_HEART_ACE)))
        val match1: GameResultState = bust.match(blackjack)

        assertThat(match1).isEqualTo(GameResultState.Lose)
    }

    @Test
    fun `Bust는 finished 상태이다`() {
        val isFinished: Boolean = bust.isFinished()

        assertThat(isFinished).isTrue
    }

    @Test
    fun `Bust는 카드를 뽑을 수 없다`() {

        Assertions.assertThatExceptionOfType(UnsupportedOperationException::class.java).isThrownBy {
            bust.draw(CARD_HEART_ACE)
        }.withMessage(Finished.UNSUPPORTED_DRAW_METHOD)
    }

    @Test
    fun `Bust는 stay할 수 없다`() {

        Assertions.assertThatExceptionOfType(UnsupportedOperationException::class.java).isThrownBy {
            bust.stay()
        }.withMessage(Finished.UNSUPPORTED_STAY_METHOD)
    }

    @Test
    fun `Bust된 score를 알 수 있다`() {
        assertThat(bust.score).isEqualTo(Score(30))
    }
}
