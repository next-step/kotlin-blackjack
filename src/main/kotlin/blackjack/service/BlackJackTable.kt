package blackjack.service

import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.view.PlayerStatus

class BlackJackTable(private val players: Array<Player>) {

    private val dealer: Dealer = Dealer()
    private val deckManager: DeckManager = DeckManager()

    fun beginRound() {
        dealer.beginRound(deckManager, players)
    }

    fun getPlayersName(): String {
        return players.filter { it.name != "딜러" }.joinToString { it.name }
    }

    fun getAllStatusWithDealer(): List<PlayerStatus> {
        return dealer.getParticipantInitialStatus(players)
    }

    fun executePlayerTurns(
        player: Array<Player>,
        wantToHit: (String) -> Boolean,
        handNotice: (Player) -> Unit
    ) {
        player.forEach {
            while (wantToHit.invoke(it.name)) {
                it.drawPhase(deckManager = deckManager, handNotice = handNotice)
            }
        }
    }

    fun executeDealerTurn(handNotice: (Player) -> Unit) {
        if (dealer.needToDraw()) dealer.drawPhase(deckManager = deckManager, handNotice = handNotice)
    }

    fun checkGameResult() {
        val checker = GameResultChecker(dealer)
        checker.determineGameResult(players)
    }

    fun getDealerStatus(): PlayerStatus {
        return PlayerStatus.of(dealer)
    }
}
