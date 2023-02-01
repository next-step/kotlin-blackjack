import blackjack.GameRule
import blackjack.ResultCalculator
import blackjack.domains.deck.Deck
import blackjack.domains.deck.PokerNumber
import blackjack.domains.deck.PokerShape
import blackjack.views.Input.getPlayerNames
import blackjack.views.Output.printFirstDrawCards
import blackjack.views.Output.printResultEarningRate

fun main() {
    val playerNames: List<String> = getPlayerNames()

    val deck = Deck(pokerNumbers = PokerNumber.values().toList(), pokerShapes = PokerShape.values().toList())
    val gameRule = GameRule(deck = deck)

    val gamers = gameRule.initGamers(dealerName = "딜러", playerNames = playerNames)
    val players = gamers.getPlayers()
    players.forEach { gameRule.makeABet(it) }
    players.forEach { gameRule.playGame(it) }

    printFirstDrawCards(playerNames, GameRule.FIRST_DRAW_COUNT)

    gamers.drawCard(gameRule = gameRule)
    gamers.printScores()
    ResultCalculator(gamers).setUserRanks()
    printResultEarningRate(gamers)
}
