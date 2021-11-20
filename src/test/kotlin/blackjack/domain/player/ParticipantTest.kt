package blackjack.domain.player

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ParticipantTest {

    @ValueSource(strings = ["", " "])
    @ParameterizedTest
    fun `참가자 이름 빈값, 공백일때 exception`(name: String) {
        assertThrows<IllegalArgumentException> {
            Player(Participant(""), 0)
        }
    }
}
