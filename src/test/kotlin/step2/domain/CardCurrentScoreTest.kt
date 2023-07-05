package step2.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CardCurrentScoreTest {

    @Test
    fun `스코어 계산에 성공한다`() {
        // given
        val cards = setOf(
            Card(CardShape.CLOVER, score = CardScore.TWO),
            Card(CardShape.CLOVER, score = CardScore.THREE)
        )

        val cardCurrentScore = CardCurrentScore()

        // when
        cardCurrentScore.calculateScore(cards)

        // then
        assertAll({
            assertThat(cardCurrentScore.maxScore).isSameAs(5)
            assertThat(cardCurrentScore.minScore).isSameAs(5)
        })
    }

    @Test
    fun `Ace가 포함된 경우스코어 계산에 성공한다`() {
        // given
        val cards = setOf(
            Card(CardShape.CLOVER, score = CardScore.ACE),
            Card(CardShape.CLOVER, score = CardScore.TWO)
        )

        val cardCurrentScore = CardCurrentScore()

        // when
        cardCurrentScore.calculateScore(cards)

        // then
        assertAll({
            assertThat(cardCurrentScore.maxScore).isSameAs(13)
            assertThat(cardCurrentScore.minScore).isSameAs(3)
        })
    }

    @Test
    fun `버스트 시 버스트 여부 확인에 성공한다`() {
        // given
        val cardCurrentScore = CardCurrentScore()

        // when
        cardCurrentScore.minScore = 22
        cardCurrentScore.maxScore = 22
        val result = cardCurrentScore.isBurst()

        // then
        assertThat(result).isTrue()
    }

    @Test
    fun `버스트 아닌 경우 버스트 여부 확인에 성공한다`() {
        // given
        val cardCurrentScore = CardCurrentScore()

        // when
        cardCurrentScore.minScore = 1
        cardCurrentScore.maxScore = 1
        val result = cardCurrentScore.isBurst()

        // then
        assertThat(result).isFalse()
    }
}
