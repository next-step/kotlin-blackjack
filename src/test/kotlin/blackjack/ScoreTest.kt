package blackjack

import blackjack.domain.Score
import blackjack.domain.strategy.hittable.DealerHittableStrategy
import blackjack.domain.strategy.hittable.GamePlayerHittableStrategy
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class ScoreTest {

    @ParameterizedTest(name = "Score는 더할 수 있다")
    @CsvSource(value = ["1, 2, 3", "2, 2, 4", "100, 10, 110"])
    fun `Score는 더할 수 있다`(score1: Int, score2: Int, sum: Int) {
        val firstScore = Score(score1)
        val secondScore = Score(score2)
        assertThat(firstScore + secondScore).isEqualTo(Score(sum))
    }

    @ParameterizedTest(name = "Score는 비교할 수 있다")
    @CsvSource(value = ["1, 2, false", "3, 2, true", "100, 100, false"])
    fun `Score는 비교할 수 있다`(score1: Int, score2: Int, expected: Boolean) {
        val firstScore = Score(score1)
        val secondScore = Score(score2)
        assertThat(firstScore > secondScore).isEqualTo(expected)
    }

    @ParameterizedTest(name = "Score는 Bust를 알 수 있다")
    @CsvSource(value = ["22, true", "4, false", "8, false", "32, true"])
    fun `Score는 Bust를 알 수 있다`(score: Int, expected: Boolean) {
        val score = Score(score)
        assertThat(score.isBust).isEqualTo(expected)
    }

    @Test
    fun `Score는 Blackjack을 알 수 있다`() {
        val score = Score(21)
        assertThat(score.isBlackjack).isTrue
    }

    @Test
    fun `Score는 음수일 수 없다`() {
        Assertions
            .assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy {
                Score(-2)
            }
    }

    @Test
    fun `Score는 11이상이면 Ace 스코어로 1을 반환한다`() {
        val score = Score(11)

        val aceScore = score.getAceScore()

        assertThat(aceScore).isEqualTo(Score(1))
    }

    @Test
    fun `Score는 11미만이면 Ace 스코어로 11을 반환한다`() {
        val score = Score(10)

        val aceScore = score.getAceScore()

        assertThat(aceScore).isEqualTo(Score(11))
    }

    @Test
    fun `Score의 canHitScore()가 dealerHittableStrategy를 따르면 16이하일 경우 canHit이다`() {
        val dealerHittableStrategy = DealerHittableStrategy

        val dealerCanNotHitScore = Score(17)
        val canHit1: Boolean = dealerCanNotHitScore.canHit(dealerHittableStrategy)
        assertThat(canHit1).isFalse

        val dealerCanHitScore = Score(16)
        val canHit2: Boolean = dealerCanHitScore.canHit(dealerHittableStrategy)
        assertThat(canHit2).isTrue
    }

    @Test
    fun `Score의 canHitScore()가 gamePlayerHittableStrategy를 따르면 21미만일 경우 canHit이다`() {
        val gamePlayerHittableStrategy = GamePlayerHittableStrategy

        val gamePlayerCanNotHitScore = Score(21)
        val canHit1: Boolean = gamePlayerCanNotHitScore.canHit(gamePlayerHittableStrategy)
        assertThat(canHit1).isFalse

        val gamePlayerCanHitScore = Score(16)
        val canHit2: Boolean = gamePlayerCanHitScore.canHit(gamePlayerHittableStrategy)
        assertThat(canHit2).isTrue
    }
}
