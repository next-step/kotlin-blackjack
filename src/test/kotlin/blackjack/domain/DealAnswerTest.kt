package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class DealAnswerTest {
    @Test
    fun `카드 수락 여부`() {
        val yes = DealAnswer.select("y")
        val no = DealAnswer.select("n")

        assertThat(yes).isEqualTo(DealAnswer.YES)
        assertThat(no).isEqualTo(DealAnswer.NO)
    }

    @ParameterizedTest
    @ValueSource(strings = ["yes", "no", "a", "b", "c", "d", "e", "f", "g", "y!", "n!"])
    fun `유효하지 않은 카드 수락 여부`(invalidAnswer: String) {

        assertThrows<IllegalArgumentException> {
            DealAnswer.select(invalidAnswer)
        }
    }
}