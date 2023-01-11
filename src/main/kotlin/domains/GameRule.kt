package domains

import views.Input.answerDrawCard
import views.Output.printHasCards

class GameRule(private val deck: Deck) {
    fun createPlayers(playerNames: List<String>): List<Player> {
        return playerNames.map { playerName ->
            val cards = Cards((1..FIRST_DRAW_COUNT).map { deck.drawCard() }.toMutableList())
            val player = Player(name = playerName)
            player.startGame(cards)
            printHasCards(player.name, player.cards)
            player
        }
    }

    fun drawCard(player: Player) {
        while (true) {
            val isDrawable = player.cards.isDrawable(MAXIMUM_NUMBER_OF_DEFEAT)
            if (!isDrawable) break

            val isRequestDraw = answerDrawCard(player.name)
            if (!isRequestDraw) break

            player.drawCard(deck.drawCard())
            printHasCards(player.name, player.cards)
        }
    }

    fun sumOfCards(player: Player): Int {
        return ScoreCalculator(player.cards).sumOfNumbers(MAXIMUM_NUMBER_OF_DEFEAT)
    }

    companion object {
        const val MAXIMUM_NUMBER_OF_DEFEAT = 21
        const val FIRST_DRAW_COUNT = 2
    }
}
