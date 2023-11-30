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
    val participants: List<Participant> = names.map { name -> Participant(name, scoringRule) }
    val allPlayers = listOf(dealer, *participants.toTypedArray())
    val deck = Deck()

    ConsoleResult.drawAllFirstTwoCards(participants)
    allPlayers.forEach { it.beginGame(deck) }
    ConsoleResult.printCardsOfPlayers(allPlayers)

    val participantsEndedGame = Players(participants)
    while (!participantsEndedGame.isAllFinished()) {
        participants
            .filter { it.state.isFinished.not() }
            .forEach {
                val inputState = ConsoleInput.inputHitAndStay(it)
                it.nextTurn(inputState, deck)
                ConsoleResult.printCardsOfPlayer(it)
            }
    }

    if (dealer.canDraw()) {
        dealer.draw(deck)
        ConsoleResult.notifyDealerMoreOneCard(dealer)
    }
    ConsoleResult.printCardsAndTotalScoreOfPlayers(allPlayers)

    val gameResults = GameResults.results(dealer as Dealer, participants.map { it })
    ConsoleResult.printGameResults(dealer, gameResults)
}
