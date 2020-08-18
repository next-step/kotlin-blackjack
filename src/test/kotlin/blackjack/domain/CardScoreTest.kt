package blackjack.domain

import blackjack.domain.CardScore.Companion.scoreOfCard
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class CardScoreTest {

    @DisplayName("각 카드 숫자만큼 점수를 반환하되, 마지막 3가지 JACK, QUEEN, KING은 10을 반환한다")
    @ParameterizedTest
    @EnumSource(value = CardScore::class)
    fun `card score`(cardScore: CardScore) {
        // given
        var expectedScores = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10)
        val index = CardScore.values().indexOf(cardScore)

        // when
        val scoreActual = scoreOfCard(cardScore)

        // then
        assertTrue(scoreActual == expectedScores[index])
    }

    @Test
    fun `initial of card`() {
        // when
        val initialOfAce = CardScore.initialOfCard(CardScore.ACE)
        val initialOfTen = CardScore.initialOfCard(CardScore.TEN)

        // then
        assertThat(initialOfAce).isEqualTo("A")
        assertThat(initialOfTen).isEqualTo("10")
    }

    @Test
    fun `sum with ace`() {
        // when
        val sumWithAce = CardScore.sumWithAce(10, hasAce = true)
        val sumWithoutAce = CardScore.sumWithAce(10, hasAce = false)

        // then
        assertThat(sumWithAce).isEqualTo(20)
        assertThat(sumWithoutAce).isEqualTo(10)
    }
}
