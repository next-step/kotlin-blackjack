import blackjack.CardDraw
import blackjack.ResultCalculator
import blackjack.domains.GameRule
import blackjack.domains.deck.Deck
import blackjack.domains.deck.PokerNumber
import blackjack.domains.deck.PokerShape
import blackjack.views.Input.getPlayerNames
import blackjack.views.Output.printFirstDrawCards
import blackjack.views.Output.printResultEarningRate
import blackjack.views.Output.printSummedCards

fun main() {
    val playerNames: List<String> = getPlayerNames()

    val deck = Deck(pokerNumbers = PokerNumber.values().toList(), pokerShapes = PokerShape.values().toList())
    val gameRule = GameRule(deck = deck)
    val cardDrawService = CardDraw(deck = deck)
    val gamers = gameRule.initGamers(dealerName = "딜러", playerNames = playerNames)
    val players = gamers.getPlayers()
    players.forEach { gameRule.makeABet(it) }
    players.forEach { gameRule.playGame(it) }

    printFirstDrawCards(playerNames, GameRule.FIRST_DRAW_COUNT)
    gamers.drawCard(cardDrawService)

    gamers.values.forEach {
        printSummedCards(it.name, it.cards, it.summedCardNumbers)
    }

    ResultCalculator(gamers).setUserRanks()
    printResultEarningRate(gamers)
}
