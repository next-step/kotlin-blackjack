package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class BlackjackParticipantResultTest {
    @Test
    fun `BlackjackParticipantResult는 블랙잭 게임 참가자의 결과를 보관한다`() {
        val name = PlayerName("이름")
        val hands = Hands.from(PlayingCards.empty())
        val matchStatus = MatchStatus.Lose

        val blackjackParticipantResult = BlackjackParticipantResult(name, hands, matchStatus)
        assertAll(
            { assertThat(blackjackParticipantResult.name).isEqualTo(name) },
            { assertThat(blackjackParticipantResult.hands).isEqualTo(hands) },
            { assertThat(blackjackParticipantResult.matchStatus).isEqualTo(matchStatus) }
        )
    }
}
