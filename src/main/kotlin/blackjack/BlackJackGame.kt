package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Distributor
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

        val distributor = Distributor(CardDeck())
        val dealer = Dealer()

        distributor.dealOutCards(dealer, players)
        DisplayView.cardsOfPlayers(dealer, players)

        dealOutAdditionalCards(distributor, players)
        dealOutAdditionalCard(distributor, dealer)
        DisplayView.finalScore(dealer, players)

        GameResultCalculator.getResult(dealer, players)
        DisplayView.result(dealer, players)
    }

    private fun dealOutAdditionalCards(distributor: Distributor, players: Players) {
        players.players.forEach {
            dealOutAdditionalCard(distributor, it)
        }
    }

    private fun dealOutAdditionalCard(distributor: Distributor, player: Player) {
        DisplayView.dealOutAdditionalCard(player)
        if (InputView.inputAdditionalCard() == "y") {
            distributor.dealOutCard(player)
            takeAnotherCard(distributor, player)
        }
    }

    private fun dealOutAdditionalCard(distributor: Distributor, dealer: Dealer) {
        val receiveNewCard = dealer.getScore() < Dealer.LIMIT_SCORE
        if (receiveNewCard) {
            distributor.dealOutCard(dealer)
        }
        DisplayView.dealOutAdditionalCard(receiveNewCard)
    }

    private fun takeAnotherCard(dealer: Distributor, player: Player) {
        if (player.getScore() < MAX_SCORE) {
            dealOutAdditionalCard(dealer, player)
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
