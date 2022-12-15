package blackjack.controller

import blackjack.domain.BlackJack
import blackjack.domain.Player
import blackjack.domain.Score
import blackjack.view.InputView
import blackjack.view.ResultView

object BlackjackController {

    fun start() {
        val participantResult = InputView.enterNameOfParticipant()

        participantResult.players.forEach {
            BlackJack.firstPick(it)
        }

        ResultView.handingOutCards(participantResult.players)

        participantResult.players.forEach {
            ResultView.getCurrentStatus(it)
        }

        participantResult.players.forEach {
            hitAndStay(it)
        }

        participantResult.players.forEach {
            ResultView.getTotalScore(it, Score.getFinalScore(it))
        }
    }

    private fun hitAndStay(player: Player) {
        while (true) {
            val stayResult = InputView.stay(player.name)
            if (BlackJack.stay(stayResult)) break
            if (BlackJack.bust(player)) break
            BlackJack.hit(player)
            ResultView.getCurrentStatus(player)
        }
    }
}
