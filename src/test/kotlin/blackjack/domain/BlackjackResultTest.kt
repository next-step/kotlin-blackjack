package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class BlackjackResultTest {
    @Test
    fun `BlackjackResult는 블랙잭 게임 참가자의 결과를 보관한다`() {
        val name = PlayerName("이름")
        val hands = Hands.from(PlayingCards.empty())
        val matchStatus = MatchStatus.Lose

        val blackjackResult = BlackjackResult(name, hands, matchStatus)
        assertAll(
            { assertThat(blackjackResult.name).isEqualTo(name) },
            { assertThat(blackjackResult.hands).isEqualTo(hands) },
            { assertThat(blackjackResult.matchStatus).isEqualTo(matchStatus) }
        )
    }
}
