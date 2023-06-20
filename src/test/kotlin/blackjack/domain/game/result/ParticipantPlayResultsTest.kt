package blackjack.domain.game.result

import blackjack.domain.bet.Bet
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.ParticipantName
import blackjack.domain.participant.Player
import blackjack.domain.state.mockBlackjackState
import blackjack.domain.state.mockBustState
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ParticipantPlayResultsTest : StringSpec({

    "참가자 플레이 결과를 통해 매치 결과 리스트를 알 수 있다." {
        val betAmount = 10000.0

        val participantPlayResults = ParticipantPlayResults(
            dealerPlayResult = ParticipantPlayResult(
                participant = Dealer(state = mockBlackjackState),
                finishState = mockBlackjackState,
            ),

            playerPlayResults = listOf(
                ParticipantPlayResult(
                    participant = Player(
                        participantName = ParticipantName(name = "진원"),
                        bet = Bet(amount = betAmount),
                        state = mockBustState,
                    ),
                    finishState = mockBustState,
                ),
                ParticipantPlayResult(
                    participant = Player(
                        participantName = ParticipantName(name = "테스트"),
                        bet = Bet(amount = betAmount),
                        state = mockBustState,
                    ),
                    finishState = mockBustState,
                ),
            ),
        )

        val totalMatchResult = participantPlayResults.totalMatchResult()
        val dealerMatchResult = totalMatchResult.find { it.participant is Dealer }
        val playerMatchResult = totalMatchResult.filter { it.participant is Player }

        dealerMatchResult?.betResultAmount shouldBe betAmount * playerMatchResult.size

        playerMatchResult.forEach {
            it.betResultAmount shouldBe betAmount.unaryMinus()
        }
    }
})
