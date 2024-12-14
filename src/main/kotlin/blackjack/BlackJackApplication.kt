package blackjack

import blackjack.card.Deck
import blackjack.participant.Dealer
import blackjack.participant.Players
import blackjack.policy.InputMoreCardPolicy
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val playerNames = InputView.inputPlayerNames()
    val game = BlackJackGame.start(Dealer(), Players.of(playerNames), Deck(), InputMoreCardPolicy)
    OutputView.printPlayersStartCardPack(game)

    val results = game.play()
    OutputView.printParticipantCardsResult(game)
    OutputView.printBlackJackResult(results)
}
