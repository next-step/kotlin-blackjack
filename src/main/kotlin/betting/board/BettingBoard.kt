package betting.board

import betting.Bet
import betting.BetResult
import betting.Winning
import blackjack.dealer.Dealer
import blackjack.participant.Participant

class BettingBoard(
    val participantBets: MutableMap<Participant<*>, BetResult> = mutableMapOf(),
) {
    fun setup(
        playerBets: Map<Participant<*>, BetResult>,
        dealer: Dealer,
    ) {
        playerBets.forEach { (player, betResult) ->
            participantBets[player] = betResult
        }
        participantBets[dealer] = BetResult(bet = Bet(), winning = Winning())
    }
}
