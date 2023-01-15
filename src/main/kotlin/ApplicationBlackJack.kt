import blackjack.domains.Deck
import blackjack.domains.GameRule
import blackjack.domains.PokerNumber
import blackjack.domains.PokerShape
import blackjack.views.Input.getPlayerNames
import blackjack.views.Output.printFirstDrawCards
import blackjack.views.Output.printResult

fun main() {
    val playerNames = getPlayerNames()
    val deck = Deck(pokerNumbers = PokerNumber.values().toList(), pokerShapes = PokerShape.values().toList())

    val gameRule = GameRule(deck = deck)
    printFirstDrawCards(playerNames, GameRule.FIRST_DRAW_COUNT)
    val players = gameRule.createPlayers(playerNames)

    players.forEach { gameRule.drawCard(it) }
    players.forEach {
        val summed = gameRule.sumOfCards(it)
        printResult(it.name, it.cards, summed)
    }
}
