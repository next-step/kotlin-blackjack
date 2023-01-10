import domains.Deck
import domains.GameRule
import domains.PokerNumber
import domains.PokerShape
import views.Input.getPlayerNames
import views.Output.printFirstDrawCards
import views.Output.printResult

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
