package blackjack.domain

import blackjack.domain.CardScore.Companion.scoreOfCard
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class CardScoreTest {

    @ParameterizedTest
    @EnumSource(value = CardScore::class)
    fun `각 카드숫자만큼 점수를 반환하되, 마지막 JACK, QUEEN, KING은 10을 반환한다`(cardScore: CardScore) {
        // given
        var expectedScores = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10)
        val index = CardScore.values().indexOf(cardScore)

        // when
        val scoreActual = scoreOfCard(cardScore)

        // then
        assertTrue(scoreActual == expectedScores[index])
    }

    @Test
    fun `카드의 이니셜`() {
        // when
        val initialOfAce = CardScore.initialOfCard(CardScore.ACE)
        val initialOfTen = CardScore.initialOfCard(CardScore.TEN)

        // then
        assertThat(initialOfAce).isEqualTo("A")
        assertThat(initialOfTen).isEqualTo("10")
    }

    @Test
    fun `ACE 계산 방식이 다르다 (점수가 11 이하면 Ace 11 사용)`() {
        // when
        val sumOf13 = CardScore.sumWithAce(13, hasAce = true)
        val sumOf11 = CardScore.sumWithAce(11, hasAce = true)
        val sumWithoutAce = CardScore.sumWithAce(11, hasAce = false)

        // then
        assertThat(sumOf13).isEqualTo(13)
        assertThat(sumOf11).isEqualTo(21)
        assertThat(sumWithoutAce).isEqualTo(11)
    }
}
