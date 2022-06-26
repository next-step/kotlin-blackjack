package blackjack.domain.player

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParticipantTest {
    @Test
    fun `사용자는 이름을 가진다`() {
        val participant = Participant.from("장재주")
        assertThat(participant.name).isEqualTo("장재주")
    }
}
