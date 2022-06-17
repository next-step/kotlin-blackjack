package blackjack.model.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("카드 점수 테스트")
class CardScoreTest {

    @Test
    fun `카드 점수 합 계산 기능이 정상 동작`() {
        // given
        val cardScore = CardScore(1, 2)
        val other = CardScore(3, 4)

        // when
        val sumOfScore = cardScore.plus(other)

        // then
        assertThat(sumOfScore.score).isEqualTo(4)
        assertThat(sumOfScore.otherScore).isEqualTo(6)
    }
}
