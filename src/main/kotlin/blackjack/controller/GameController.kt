package blackjack.controller

import blackjack.domain.card.Deck
import blackjack.domain.player.Participant
import blackjack.domain.rule.DefaultScoringRule
import blackjack.view.ConsoleInput
import blackjack.view.ConsoleResult

fun main() {
    val names = ConsoleInput.inputNamesOfPlayer()
    val scoringRule = DefaultScoringRule()

    val participants = names.map { name -> Participant(name, scoringRule) }
    val deck = Deck()

    ConsoleResult.drawAllFirstTwoCards(participants)
    participants.forEach {
        it.draw(deck)
        it.draw(deck)
    }
    ConsoleResult.printCardsOfPlayers(participants)

    val participantsEndedGame: MutableSet<Participant> = mutableSetOf()
    while (participantsEndedGame.size < participants.size) {
        participants.forEach { playParticipants(it, participantsEndedGame, deck) }
    }

    ConsoleResult.printCardsAndTotalScoreOfPlayers(participants)
}

private fun playParticipants(
    it: Participant,
    playersOfWantedEndGame: MutableSet<Participant>,
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
