package blackjack

import blackjack.card.Deck
import blackjack.participant.Dealer
import blackjack.participant.Players
import blackjack.policy.InputMoreCardPolicy
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val playerNames = InputView.inputPlayerNames()

    val dealer = Dealer()
    val gamePlayer = Players.of(playerNames)
    val deck = Deck()
    val game = BlackJackGame(dealer, gamePlayer, deck)

    val startGame = game.start()
    OutputView.printPlayersStartCardPack(startGame)

    val results = startGame.play(InputMoreCardPolicy)
    OutputView.printParticipantCardsResult(gamePlayer.player)
    OutputView.printBlackJackResult(results)

}
