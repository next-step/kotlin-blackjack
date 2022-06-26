package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class BlackjackParticipantResultTest {
    @Test
    fun `BlackjackParticipantResult는 블랙잭 게임 참가자의 결과를 보관한다`() {
        val player = Player("이름", 10_000)
        val matchStatus = MatchStatus.Lose

        val blackjackParticipantResult = BlackjackParticipantResult(player, matchStatus)
        assertAll(
            { assertThat(blackjackParticipantResult.participant).isEqualTo(player) },
            { assertThat(blackjackParticipantResult.matchStatus).isEqualTo(matchStatus) }
        )
    }
}
