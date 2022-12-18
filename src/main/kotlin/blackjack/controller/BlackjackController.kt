package blackjack.controller

import blackjack.domain.BlackJack
import blackjack.domain.Player
import blackjack.domain.Score
import blackjack.domain.card.Deck
import blackjack.view.InputView
import blackjack.view.ResultView

object BlackjackController {

    private val deck = Deck()
    private val blackJack = BlackJack(deck)

    fun start() {
        val participantResult = InputView.enterNameOfPlayer()

        participantResult.players.forEach {
            blackJack.firstPick(it)
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
            if (blackJack.stay(stayResult)) break
            if (blackJack.bust(player)) break
            blackJack.hit(player)
            ResultView.getCurrentStatus(player)
        }
    }
}
