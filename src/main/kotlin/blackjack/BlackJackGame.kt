package blackjack

import blackjack.domain.Cards
import blackjack.domain.deck.Deck
import blackjack.domain.deck.DeckFactory
import blackjack.domain.player.Player
import blackjack.dto.Dto
import blackjack.dto.ResultDto
import blackjack.userinterface.Console
import blackjack.userinterface.UserInterface

fun main() {
    val console = Console()
    val game = BlackJackGame(console)
    game.run()
}

class BlackJackGame(private val userInterface: UserInterface) {

    private val deck: Deck = DeckFactory.create()

    fun run() {
        val players = run {
            val userNames = userInterface.inputPlayerNames()
            userNames.map { Player(it, Cards(deck.draw(), deck.draw())) }
        }

        userInterface.outputPlayerCards(players.map(::Dto))
        players.forEach { takeCardsIfNecessary(it) }
        userInterface.outputGameResult(players.map(::ResultDto))
    }

    private tailrec fun takeCardsIfNecessary(player: Player) {
        if (player.isNotTakeable()) {
            return
        }

        when (userInterface.inputCardTakenWhether(player.name)) {
            false -> return
            true -> {
                player.takeCard(deck.draw())
                return takeCardsIfNecessary(player)
            }
        }
    }
}
