import blackjack.CardDraw
import blackjack.ResultCalculator
import blackjack.domains.GameRule
import blackjack.domains.deck.Deck
import blackjack.domains.deck.PokerNumber
import blackjack.domains.deck.PokerShape
import blackjack.domains.participants.Gamers
import blackjack.views.Input.getPlayerNames
import blackjack.views.Output.printFirstDrawCards
import blackjack.views.Output.printResultEarningRate
import blackjack.views.Output.printSummedCards

fun main() {
    val playerNames: List<String> = getPlayerNames()
    val (deck, gameRule, cardDrawService) = initGame()
    val gamers = initGamers(gameRule = gameRule, playerNames = playerNames)
    playGame(gameRule = gameRule, gamers = gamers, cardDrawService = cardDrawService)
    endGame(gamers = gamers)
}

private fun initGame(): Triple<Deck, GameRule, CardDraw> {
    val deck = Deck(pokerNumbers = PokerNumber.values().toList(), pokerShapes = PokerShape.values().toList())
    val gameRule = GameRule(deck = deck)
    val cardDrawService = CardDraw(deck = deck)
    return Triple(deck, gameRule, cardDrawService)
}

private fun initGamers(gameRule: GameRule, playerNames: List<String>): Gamers {
    val gamers = gameRule.initGamers(dealerName = "딜러", playerNames = playerNames)
    val players = gamers.getPlayers()
    players.forEach { gameRule.makeABet(it) }
    return gamers
}

private fun playGame(gameRule: GameRule, gamers: Gamers, cardDrawService: CardDraw) {
    val players = gamers.getPlayers()
    players.forEach { gameRule.playGame(it) }
    printFirstDrawCards(players.map { it.name }, GameRule.FIRST_DRAW_COUNT)
    gamers.drawCard(cardDrawService)
}

private fun endGame(gamers: Gamers) {
    gamers.values.forEach { printSummedCards(it.name, it.cards, it.summedCardNumbers) }
    ResultCalculator(gamers).setUserRanks()
    printResultEarningRate(gamers)
}
