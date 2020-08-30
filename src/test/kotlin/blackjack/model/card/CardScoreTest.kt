package blackjack.model.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class CardScoreTest {

    @ParameterizedTest
    @EnumSource(value = CardScore::class)
    fun `카드 이니셜`(cardScore: CardScore) {
        // given
        val expected: List<String> = listOf("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K")

        // when
        val actual = CardScore.values().map(CardScore::initial)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @EnumSource(value = CardScore::class)
    fun `카드 점수 (마지막 JACK, QUEEN, KING은 10을 반환한다)`(cardScore: CardScore) {
        // given
        val expected: List<Int> = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10)

        // when
        val actual: List<Int> = CardScore.values().map(CardScore::score)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `ACE 계산 (점수가 11 이하면 Ace는 11점으로 사용 가능)`() {
        // when
        val score11WithAce = CardScore.sumWithAce(11, hasAce = true)
        val score13WithAce = CardScore.sumWithAce(13, hasAce = true)
        val score11WithoutAce = CardScore.sumWithAce(11, hasAce = false)

        // then
        assertThat(score11WithAce).isEqualTo(21)
        assertThat(score13WithAce).isEqualTo(13)
        assertThat(score11WithoutAce).isEqualTo(11)
    }
}
