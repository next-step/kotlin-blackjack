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

        participantResult.players.forEach {
            hitAndStay(it)
        }

        ResultView.getTotalScore(participantResult.players)
    }

    private fun hitAndStay(player: Player) {
        while (true) {
            val stayResult = InputView.stay(player.name)
            if (!stayResult.isStay || BlackJack.isOverScore(player)) break
            val card = BlackJack.pick()
            player.cards.add(card)
            ResultView.getCurrentStatus(listOf(player))
        }
        player.score = BlackJack.getScore(player)
    }
}
