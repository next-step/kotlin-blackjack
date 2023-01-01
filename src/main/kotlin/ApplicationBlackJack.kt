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
    val firstDrawCount = 2
    printFirstDrawCards(playerNames, firstDrawCount)

    val gameRule = GameRule(deck = deck)
    val players = gameRule.createPlayers(firstDrawCount, playerNames)

    players.forEach { gameRule.drawCard(it) }
    players.forEach {
        val summed = gameRule.sumOfCards(it)
        printResult(it.name, it.cards, summed)
    }
}
