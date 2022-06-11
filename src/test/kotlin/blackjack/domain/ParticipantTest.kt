package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ParticipantTest {

    @ParameterizedTest
    @ValueSource(strings = ["molly,jayce"])
    fun `참가자는 이름을 가진다`(input: String) {
        val players = input.split(",")
        val participants = players.map { Participant(it) }
        assertThat(participants.size).isEqualTo(2)
    }

    @ParameterizedTest
    @ValueSource(strings = ["molly"])
    fun `참가자 최초 생성 시 스코어는 0점`(input: String) {
        val participant = Participant(input)
        assertThat(participant.playerCards.score()).isEqualTo(0)
    }

    @ParameterizedTest
    @ValueSource(strings = ["molly"])
    fun `참가자 생성 직구 카드 갯수 0개`(input: String) {
        val participant = Participant(input)
        assertThat(participant.playerCards.playerCards.size).isEqualTo(0)
    }
}
