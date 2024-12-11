package blackjack.app

import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Game
import blackjack.domain.GameParticipants
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val playerNames = InputView.getPlayerNames()
    val players =
        Players(
            playerNames.map { name ->
                val bettingAmount = InputView.getBettingAmount(name)
                Player(name, bettingAmount)
            },
        )
    val dealer = Dealer()
    val participants = GameParticipants(dealer, players)
    val deck = Deck()
    val game = Game(deck, participants)

    game.start()
    ResultView.showInitialCards(players.toResultDTO())
    ResultView.showDealerInitialCard(dealer.getCards().first())

    players.getPlayers().forEach { player ->
        while (InputView.askToHit(player.name)) {
            game.hit(player.name)
            ResultView.showPlayerCards(players.toResultDTO().find { it.name == player.name }!!)
        }
    }

    game.dealerPlayTurn()
    ResultView.showDealerCards(dealer.getCards())

    val profits = game.calculateFinalResults()
    ResultView.showFinalProfits(profits)
}
