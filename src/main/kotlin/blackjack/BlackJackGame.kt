package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Players
import blackjack.domain.card.CardSet
import blackjack.view.DisplayView
import blackjack.view.InputView

class BlackJackGame {

    fun start() {
        val names = InputView.inputNameOfPlayer()
        val players = Players(names)
        DisplayView.dealOutCards(players)

        val allCard = CardSet()
        val dealer = Dealer()
        dealer.dealOutCards(allCard, players)

        DisplayView.cardsOfPlayers(players)
    }
}

fun main() {
    val game = BlackJackGame()
    game.start()
}
