package blackjack.controller

import blackjack.domain.GameResults
import blackjack.domain.card.Deck
import blackjack.domain.player.Dealer
import blackjack.domain.player.Participant
import blackjack.domain.player.Player
import blackjack.domain.player.Players
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

    val participantsEndedGame = Players()
    while (participantsEndedGame.size < participants.size) {
        participants.forEach { playParticipants(it, participantsEndedGame, deck) }
    }

    if (dealer.canDraw()) {
        dealer.draw(deck)
        ConsoleResult.notifyDealerMoreOneCard(dealer)
    }
    ConsoleResult.printCardsAndTotalScoreOfPlayers(allPlayers)

    val gameResults = GameResults.results(dealer as Dealer, participants.map { it as Participant })
    ConsoleResult.printGameResults(dealer, gameResults)
}

private fun playParticipants(
    player: Player,
    playersOfWantedEndGame: Players,
    deck: Deck
) {
    if (player.canDraw().not()) {
        ConsoleResult.notifyParticipantCannotDraw(player)
        playersOfWantedEndGame.add(player)
    }

    if (playersOfWantedEndGame.contains(player)) {
        return
    }

    val drawOneMoreCard = ConsoleInput.inputGettingOneMoreCard(player)
    if (drawOneMoreCard && player.canDraw()) {
        player.draw(deck)
        ConsoleResult.printCardsOfPlayer(player)
    }

    if (!drawOneMoreCard) {
        playersOfWantedEndGame.add(player)
    }
}
