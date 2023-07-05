package step2.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CurrentScoreTest {

    @Test
    fun `스코어 계산에 성공한다`() {
        // given
        val cards = setOf(
            Card(CardShape.CLOVER, score = CardScore.TWO),
            Card(CardShape.CLOVER, score = CardScore.THREE)
        )

        val currentScore = CurrentScore()

        // when
        currentScore.calculateScore(cards)

        // then
        assertAll({
            assertThat(currentScore.maxScore).isSameAs(5)
            assertThat(currentScore.minScore).isSameAs(5)
        })
    }

    @Test
    fun `Ace가 포함된 경우스코어 계산에 성공한다`() {
        // given
        val cards = setOf(
            Card(CardShape.CLOVER, score = CardScore.ACE),
            Card(CardShape.CLOVER, score = CardScore.TWO)
        )

        val currentScore = CurrentScore()

        // when
        currentScore.calculateScore(cards)

        // then
        assertAll({
            assertThat(currentScore.maxScore).isSameAs(13)
            assertThat(currentScore.minScore).isSameAs(3)
        })
    }

    @Test
    fun `버스트 시 버스트 여부 확인에 성공한다`() {
        // given
        val currentScore = CurrentScore()

        // when
        currentScore.minScore = 22
        currentScore.maxScore = 22
        val result = currentScore.isBurst()

        // then
        assertThat(result).isTrue()
    }

    @Test
    fun `버스트 아닌 경우 버스트 여부 확인에 성공한다`() {
        // given
        val currentScore = CurrentScore()

        // when
        currentScore.minScore = 1
        currentScore.maxScore = 1
        val result = currentScore.isBurst()

        // then
        assertThat(result).isFalse()
    }
}
