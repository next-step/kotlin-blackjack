package blackjack.bet.domain

import blackjack.bet.service.BetCalculator
import blackjack.bet.service.BetGameResultChecker
import blackjack.bet.view.BetPlayerStatus
import blackjack.common.service.DeckManager

class BlackJackBetTable(private val players: Array<BetPlayer>) {

    private val dealer: BetDealer = BetDealer()
    private val deckManager: DeckManager = DeckManager()

    fun beginRound() {
        dealer.beginRound(deckManager, players)
        players.forEach {
            val optimalValue = it.optimalValue()
            if (it.isInitialBlackjack(optimalValue)) {
                BetCalculator.initialBlackjack(it, dealer)
            }
        }
    }

    fun getPlayersName(): String {
        return players.filter { it.name != "딜러" }.joinToString { it.name }
    }

    fun getAllStatusWithDealer(): List<BetPlayerStatus> {
        return dealer.getParticipantInitialStatus(players)
    }

    fun executePlayerTurns(
        player: Array<BetPlayer>,
        wantToHit: (String) -> Boolean,
        handNotice: (BetPlayer) -> Unit
    ) {
        player.forEach {
            while (wantToHit.invoke(it.name)) {
                it.drawPhase(deckManager = deckManager, handNotice = handNotice)
            }
        }
    }

    fun executeDealerTurn(handNotice: (BetPlayer) -> Unit) {
        if (dealer.needToDraw()) dealer.drawPhase(deckManager = deckManager, handNotice = handNotice)
    }

    fun checkGameResult() {
        val checker = BetGameResultChecker(dealer)
        checker.determineGameResult(players)
    }
}
