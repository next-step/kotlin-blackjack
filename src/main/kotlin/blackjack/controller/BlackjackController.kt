package blackjack.controller

import blackjack.domain.BlackJack
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.ResultView

object BlackjackController {

    fun start() {
        val participantResult = InputView.enterNameOfParticipant()

        BlackJack.firstPick(participantResult.players)

        ResultView.handingOutCards(participantResult.players)
        ResultView.getCurrentStatus(participantResult.players)

        hitAndStay(participantResult.players)

        ResultView.getTotalScore(participantResult.players)
    }

    private fun hitAndStay(players: List<Player>) {
        players.forEach {
            while (true) {
                val stayResult = InputView.stay(it.name)
                if (!stayResult.isStay || BlackJack.isOverScore(it)) break
                val card = BlackJack.pick()
                it.cards.add(card)
                ResultView.getCurrentStatus(listOf(it))
            }
            it.score = BlackJack.getScore(it)
        }
    }
}
