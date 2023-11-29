package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ParticipantTest {
    val participant = Participant("참가자")

    @Test
    fun `카드 받기`() {
        participant.isReceiveCard("y")
        assertThat(participant.receive).isEqualTo(true)
    }

    @Test
    fun `카드 안받기`() {
        participant.isReceiveCard("n")
        assertThat(participant.receive).isEqualTo(false)
    }

    @Test
    fun `카드 응답 오류`() {
        assertThrows(IllegalArgumentException::class.java) {
            participant.isReceiveCard("A")
        }
    }

    @Test
    fun `카드 추가`() {
        participant.addCard()
        assertThat(participant.cards.size).isEqualTo(1)
    }
}