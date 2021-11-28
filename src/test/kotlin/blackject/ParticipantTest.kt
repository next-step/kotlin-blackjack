package blackject

import blackject.model.Participant
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.NullAndEmptySource
import org.junit.jupiter.params.provider.ValueSource

class ParticipantTest {

    @ValueSource(strings = ["sohyun,hiii"])
    @ParameterizedTest
    @DisplayName("참가자 리스트 확인")
    fun `check participant list`(name: String) {
        val participant = Participant.addPerson(name)

        assertThat(participant.getPerson().size).isEqualTo(2)
        assertThat(participant.getPerson()[0].name).isEqualTo("sohyun")
        assertThat(participant.getPerson()[1].name).isEqualTo("hiii")
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("참가자 리스트의 빈칸 혹은 null 값이 들어온 경우")
    fun `check participant list null or empty`(name: String?) {
        assertThrows<IllegalArgumentException> {
            Participant.addPerson(name)
        }
    }
}
