package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.RepetitionInfo
import org.junit.jupiter.api.Test

@DisplayName("점수(Score)")
internal class ScoreTest {

    @DisplayName("점수 생성(ScoreInitializer)")
    @Nested
    internal class ScoreInitializerTest {
        @Test
        fun `0의 숫자로 Score 를 만들 수 있다`() {
            val score = Score.from(0)

            assertAll(
                { assertThat(score).isNotNull },
                { assertThat(score).isExactlyInstanceOf(Score::class.java) },
            )
        }

        @RepeatedTest(value = 32, name = "현재, {currentRepetition}/{totalRepetitions}")
        fun `1~32 사이의 숫자로 Score 를 만들 수 있다`(repetitionInfo: RepetitionInfo) {
            val score = Score.from(repetitionInfo.currentRepetition)

            assertAll(
                { assertThat(score).isNotNull },
                { assertThat(score).isExactlyInstanceOf(Score::class.java) },
            )
        }
    }
}