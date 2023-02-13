package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class ScoreStateGeneratorTest {
    @Test
    fun `카드 값 합이 21 초과 시 버스트로 판단한다`() {
        assertAll(
            {
                assertThat(ScoreStateGenerator().generate(22)).isEqualTo(ScoreState.BUST)
            },
            {
                assertThat(ScoreStateGenerator().generate(23)).isEqualTo(ScoreState.BUST)
            }
        )
    }

    @Test
    fun `카드 값 합이 21일 경우 블랙잭으로 판단한다`() {
        assertThat(ScoreStateGenerator().generate(21)).isEqualTo(ScoreState.BLACKJACK)
    }

    @Test
    fun `카드 값 합이 21 미만 시 히트로 판단한다`() {
        assertAll(
            {
                assertThat(ScoreStateGenerator().generate(20)).isEqualTo(ScoreState.HIT)
            },
            {
                assertThat(ScoreStateGenerator().generate(19)).isEqualTo(ScoreState.HIT)
            }
        )
    }
}
