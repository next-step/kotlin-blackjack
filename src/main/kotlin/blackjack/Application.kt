import blackjack.domain.Cards
import blackjack.domain.deck.Deck
import blackjack.domain.deck.DeckFactory
import blackjack.domain.player.Name
import blackjack.domain.player.Player
import blackjack.view.inputAnswer
import blackjack.view.inputPlayerNames
import blackjack.view.printPlayerCards
import blackjack.view.printPlayersResult
import blackjack.view.printStartMessage

fun main() {
    val deck = DeckFactory.create()
    val playerNames = inputPlayerNames().map { Name(it) }
    val players = playerNames.map { Player(name = it, cards = Cards(deck.draw(), deck.draw())) }
    printStartMessage(players)

    players.forEach {
        askTakeCard(deck, it)
    }

    printPlayersResult(players)
}

private fun askTakeCard(deck: Deck, it: Player) {
    while (it.score < Cards.BLACKJACK_SCORE && inputAnswer(it)) {
        it.take(deck.draw())
        printPlayerCards(it)
    }
}
