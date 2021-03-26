import blackjack.domain.Result
import blackjack.domain.card.state.StateFactory
import blackjack.domain.deck.Deck
import blackjack.domain.deck.DeckFactory
import blackjack.domain.player.Dealer
import blackjack.domain.player.Name
import blackjack.domain.player.Player
import blackjack.view.inputAnswer
import blackjack.view.inputPlayerNames
import blackjack.view.printDealerTakeCardMessage
import blackjack.view.printParticipantsResult
import blackjack.view.printPlayerCards
import blackjack.view.printResult
import blackjack.view.printStartMessage

fun main() {
    val deck = DeckFactory.create()
    val dealer = Dealer(StateFactory.create(deck.draw(), deck.draw()))

    val playerNames = inputPlayerNames().map { Name(it) }
    val players = playerNames.map { Player(name = it, state = StateFactory.create(deck.draw(), deck.draw())) }
    printStartMessage(dealer, players)

    players.forEach {
        askTakeCard(deck, it)
    }

    while (dealer.isMustTakeCard()) {
        printDealerTakeCardMessage()
        dealer.take(deck.draw())
    }

    val result = Result(
        players.map { it to it.match(dealer) }
            .toMap()
    )

    printParticipantsResult(listOf(dealer) + players)
    printResult(result)
}

private fun askTakeCard(deck: Deck, player: Player) {
    while (player.canHit()) {
        if (inputAnswer(player)) {
            player.take(deck.draw())
            printPlayerCards(player)
        } else {
            player.stay()
        }
    }
}
