package blackjack.controller

import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.domain.rule.DefaultScoringRule
import blackjack.view.ConsoleInput
import blackjack.view.ConsoleResult

fun main() {
    val names = ConsoleInput.inputNamesOfPlayer()
    val scoringRule = DefaultScoringRule()

    val players = names.map { name -> Player(name, scoringRule) }
    val deck = Deck()

    ConsoleResult.drawAllFirstTwoCards(players)
    players.forEach {
        it.draw(deck)
        it.draw(deck)
    }
    ConsoleResult.printCardsOfPlayers(players)

    val playersOfWantedEndGame: MutableSet<Player> = mutableSetOf()
    while (playersOfWantedEndGame.size < players.size) {
        players.forEach { playGame(it, playersOfWantedEndGame, deck) }
    }

    ConsoleResult.printCardsAndTotalScoreOfPlayers(players)
}

private fun playGame(
    it: Player,
    playersOfWantedEndGame: MutableSet<Player>,
    deck: Deck
) {
    if (it.canDraw().not()) {
        ConsoleResult.notifyPlayerCannotDraw(it)
        playersOfWantedEndGame.add(it)
    }

    if (playersOfWantedEndGame.contains(it)) {
        return
    }

    val drawOneMoreCard = ConsoleInput.inputGettingOneMoreCard(it)
    if (drawOneMoreCard && it.canDraw()) {
        it.draw(deck)
        ConsoleResult.printCardsOfPlayer(it)
    }

    if (!drawOneMoreCard) {
        playersOfWantedEndGame.add(it)
    }
}
