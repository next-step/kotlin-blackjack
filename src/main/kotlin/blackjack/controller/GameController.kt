package blackjack.controller

import blackjack.domain.card.Deck
import blackjack.domain.player.Dealer
import blackjack.domain.player.Participant
import blackjack.domain.player.Player
import blackjack.domain.rule.DefaultScoringRule
import blackjack.view.ConsoleInput
import blackjack.view.ConsoleResult

fun main() {
    val names = ConsoleInput.inputNamesOfPlayer()
    val scoringRule = DefaultScoringRule()

    val dealer: Player = Dealer(scoringRule)
    val participants: List<Player> = names.map { name -> Participant(name, scoringRule) }
    val allPlayers = listOf(dealer, *participants.toTypedArray())
    val deck = Deck()

    ConsoleResult.drawAllFirstTwoCards(participants)
    allPlayers.forEach {
        it.draw(deck)
        it.draw(deck)
    }
    ConsoleResult.printCardsOfPlayers(allPlayers)

    val participantsEndedGame: MutableSet<Player> = mutableSetOf()
    while (participantsEndedGame.size < participants.size) {
        participants.forEach { playParticipants(it, participantsEndedGame, deck) }
    }

    ConsoleResult.printCardsAndTotalScoreOfPlayers(allPlayers)
}

private fun playParticipants(
    it: Player,
    playersOfWantedEndGame: MutableSet<Player>,
    deck: Deck
) {
    if (it.canDraw().not()) {
        ConsoleResult.notifyParticipantCannotDraw(it)
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
