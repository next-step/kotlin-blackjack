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
    @DisplayName("참가자 ")
    fun `check participant list`(name: String) {
        val participant = Participant.addPerson(name)
        val expectedList = listOf("sohyun", "hiii")

        assertThat(participant.persons.size).isEqualTo(2)
        assertThat(participant.persons).isEqualTo(expectedList)
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("")
    fun `check participant list null or empty`(name: String?) {
        assertThrows<IllegalArgumentException> {
            Participant.addPerson(name)
        }
    }
}
