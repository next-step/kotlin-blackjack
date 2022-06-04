package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ParticipantTest {

    @ParameterizedTest
    @ValueSource(strings = ["molly,jayce"])
    fun `참가자 생성 테스트`(input: String) {
        val players = input.split(",")
        val participants = players.map { Participant.of(it, CardDeck(emptyList())) }
        assertThat(participants.count()).isEqualTo(2)
    }

    @ParameterizedTest
    @ValueSource(strings = ["molly"])
    fun `스코어 테스트`(input: String) {
        val participant = Participant.of(input, CardDeck(emptyList()))
        assertThat(participant.score()).isEqualTo(0)
    }

    @ParameterizedTest
    @ValueSource(strings = ["molly"])
    fun `카드 갯수 테스트`(input: String) {
        val participant = Participant.of(input, CardDeck(emptyList()))
        assertThat(participant.cards.count()).isEqualTo(0)
    }
}