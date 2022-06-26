package blackjack.domain.player

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ParticipantTest {
    @ParameterizedTest
    @ValueSource(strings = ["", " ", "j", "j ", "jjjjjj"])
    fun `이름의 길이에는 제한이 있다`() {
        assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy { Participant("") }
    }

    @Test
    fun `사용자는 이름을 가진다`() {
        val participant = Participant("장재주")
        assertThat(participant.name).isEqualTo("장재주")
    }
}
