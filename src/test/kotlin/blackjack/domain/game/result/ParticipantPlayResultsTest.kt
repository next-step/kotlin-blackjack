package blackjack.domain.game.result

import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player
import blackjack.domain.player.mockPlayer
import blackjack.domain.state.mockBlackjackState
import blackjack.domain.state.mockBustState
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ParticipantPlayResultsTest : StringSpec({

    "참가자 플레이 결과를 통해 매치 결과 리스트를 알 수 있다." {
        val participantPlayResults = ParticipantPlayResults(
            dealerPlayResult = ParticipantPlayResult(
                participant = Dealer(state = mockBlackjackState),
                finishState = mockBlackjackState,
            ),

            playerPlayResults = listOf(
                ParticipantPlayResult(
                    participant = mockPlayer(name = "진원"),
                    finishState = mockBustState,
                ),
                ParticipantPlayResult(
                    participant = mockPlayer(name = "테스트"),
                    finishState = mockBustState,
                ),
            ),
        )

        val totalMatchResult = participantPlayResults.totalMatchResult()
        val dealerMatchResult = totalMatchResult.find { it.participant is Dealer }

        dealerMatchResult?.winScore shouldBe 2
        dealerMatchResult?.loseScore shouldBe 0

        val playerMatchResult = totalMatchResult.filter { it.participant is Player }

        playerMatchResult.forEach {
            it.winScore shouldBe 0
            it.loseScore shouldBe 1
        }
    }
})
