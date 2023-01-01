package domains

import views.Input.answerDrawCard
import views.Output.printHasCards

class GameRule(private val deck: Deck) {
    fun createPlayers(firstDrawCount: Int, playerNames: List<String>): List<Player> {
        return playerNames.map { playerName ->
            val cards = Cards((1..firstDrawCount).map { deck.drawCard() }.toMutableList())
            val player = Player(name = playerName, cards = cards)
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

            player.addCard(deck.drawCard())
            printHasCards(player.name, player.cards)
        }
    }

    fun sumOfCards(player: Player): Int {
        return player.cards.sumOfNumbers(MAXIMUM_NUMBER_OF_DEFEAT)
    }

    companion object {
        const val MAXIMUM_NUMBER_OF_DEFEAT = 21
    }
}
