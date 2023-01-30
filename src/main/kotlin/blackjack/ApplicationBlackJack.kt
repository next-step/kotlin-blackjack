import blackjack.GameRule
import blackjack.ResultCalculator
import blackjack.domains.deck.Deck
import blackjack.domains.deck.PokerNumber
import blackjack.domains.deck.PokerShape
import blackjack.views.Input.getPlayerNames
import blackjack.views.Output.printFirstDrawCards
import blackjack.views.Output.printVictoryOrDefeat

fun main() {
    val playerNames = getPlayerNames()
    val deck = Deck(pokerNumbers = PokerNumber.values().toList(), pokerShapes = PokerShape.values().toList())

    val gameRule = GameRule(deck = deck)
    printFirstDrawCards(playerNames, GameRule.FIRST_DRAW_COUNT)
    val gamers = gameRule.initGame(playerNames)

    gamers.drawCard(gameRule = gameRule)
    gamers.printScores()
    ResultCalculator(gamers).setUserRanks()
    printVictoryOrDefeat(gamers.getDealer(), gamers.getPlayers())
}
