package betting.board

import betting.Bet
import betting.BetResult
import betting.Winning
import blackjack.dealer.Dealer
import blackjack.participant.Participant
import blackjack.player.Player

class BettingBoard(
    val participantBets: MutableMap<Participant<*>, BetResult> = mutableMapOf()
) {
    private fun getDealer(): Participant<*> =
        participantBets.filter { it.key == Dealer() }
            .keys
            .first()

    fun setup(
        playerBets: Map<Participant<*>, BetResult>,
        dealer: Dealer,
    ) {
        playerBets.forEach { (player, betResult) ->
            participantBets[player] = betResult
        }
        participantBets[dealer] = BetResult(bet = Bet(), winning = Winning())
    }

    fun bust(player: Player) {
        val playerBet = participantBets[player] ?: return
        val dealerBet = participantBets[getDealer()] ?: return

        participantBets[getDealer()] = dealerBet.win(playerBet.bet.amount)
        participantBets[player] = playerBet.lose()
    }
}
