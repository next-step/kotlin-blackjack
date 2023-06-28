package blackjack.bet.domain

import blackjack.bet.service.BetGameResultChecker
import blackjack.bet.view.BetPlayerStatus
import blackjack.common.service.DeckManager

class BlackJackBetTable(private val players: List<BetPlayer>) {

    private val dealer: BetDealer = BetDealer()
    private val deckManager: DeckManager = DeckManager()

    fun beginRound() {
        dealer.beginRound(deckManager, players)
    }

    fun getPlayersName(): String {
        return players.joinToString { it.name }
    }

    fun getInitialStatus(): List<BetPlayerStatus> {
        val betPlayerStatus = mutableListOf(BetPlayerStatus.dealerUpCard(dealer))
        for (player in players) {
            betPlayerStatus.add(BetPlayerStatus.of(player))
        }

        return betPlayerStatus
    }

    fun getAllStatus(): List<BetPlayerStatus> {
        return dealer.getAllStatus(players)
    }

    fun executePlayerTurns(
        player: List<BetPlayer>,
        wantToHit: (String) -> Boolean,
        handNotice: (BetPlayer) -> Unit,
        cantDrawException: (String) -> Unit
    ) {
        player.forEach {
            while (it.canDraw() && wantToHit(it.name)) {
                try {
                    it.drawPhase(deckManager = deckManager, handNotice = handNotice)
                } catch (ex: IllegalStateException) {
                    cantDrawException(it.name)
                    break
                }
            }
        }
    }

    fun executeDealerTurn(handNotice: (BetPlayer) -> Unit) {
        dealer.drawCardIfNeeded(deckManager, handNotice)
    }

    fun checkGameResult() {
        val checker = BetGameResultChecker(dealer)
        checker.determine(players)
    }
}
