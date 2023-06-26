package blackjack.scorerule.domain

import blackjack.scorerule.view.ScorePlayerStatus
import blackjack.common.service.DeckManager
import blackjack.scorerule.service.ScoreGameResultChecker

class BlackJackScoreTable(private val players: Array<ScorePlayer>) {

    private val dealer: ScoreDealer = ScoreDealer()
    private val deckManager: DeckManager = DeckManager()

    fun beginRound() {
        dealer.beginRound(deckManager, players)
    }

    fun getPlayersName(): String {
        return players.filter { it.name != "딜러" }.joinToString { it.name }
    }

    fun getAllStatusWithDealer(): List<ScorePlayerStatus> {
        return dealer.getParticipantInitialStatus(players)
    }

    fun executePlayerTurns(
        player: Array<ScorePlayer>,
        wantToHit: (String) -> Boolean,
        handNotice: (ScorePlayer) -> Unit
    ) {
        player.forEach {
            while (wantToHit.invoke(it.name)) {
                it.drawPhase(deckManager = deckManager, handNotice = handNotice)
            }
        }
    }

    fun executeDealerTurn(handNotice: (ScorePlayer) -> Unit) {
        if (dealer.needToDraw()) dealer.drawPhase(deckManager = deckManager, handNotice = handNotice)
    }

    fun checkGameResult() {
        val checker = ScoreGameResultChecker(dealer)
        checker.determineGameResult(players)
    }

    fun getDealerStatus(): ScorePlayerStatus {
        return ScorePlayerStatus.of(dealer)
    }
}
