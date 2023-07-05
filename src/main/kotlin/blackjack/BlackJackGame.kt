package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.domain.card.CardDeck
import blackjack.view.DisplayView
import blackjack.view.InputView

class BlackJackGame {

    fun start() {
        val names = InputView.inputNameOfPlayer()
        val players = Players(names)
        DisplayView.dealOutCards(players)

        val dealer = Dealer(CardDeck())
        dealer.dealOutCards(players)
        DisplayView.cardsOfPlayers(players)

        dealOutAdditionalCards(dealer, players)
        DisplayView.result(players)
    }


    private fun dealOutAdditionalCards(dealer: Dealer, players: Players) {
        players.players.forEach {
            dealOutAdditionalCard(dealer, it)
        }
    }

    private fun dealOutAdditionalCard(dealer: Dealer, player: Player) {
        DisplayView.dealOutAdditionalCard(player)
        if (InputView.inputAdditionalCard() == "y") {
            dealer.dealOutCard(player)
            (player.getScore() >= MAX_SCORE).takeIf { it }?.let { dealOutAdditionalCard(dealer, player) }
        }
    }

    companion object {
        const val MAX_SCORE = 21
    }
}

fun main() {
    val game = BlackJackGame()
    game.start()
}
