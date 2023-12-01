package blackjack.controller

import blackjack.domain.Match
import blackjack.domain.card.Deck
import blackjack.domain.player.Dealer
import blackjack.domain.player.Participant
import blackjack.domain.player.Players
import blackjack.domain.rule.DefaultScoringRule
import blackjack.domain.card.State
import blackjack.view.ConsoleInput
import blackjack.view.ConsoleResult

fun main() {
    val names = ConsoleInput.inputNamesOfPlayer()
    val scoringRule = DefaultScoringRule()

    val dealer = Dealer(scoringRule)
    val participants: List<Participant> = names.map { name ->
        val inputBet = ConsoleInput.inputBet(name)
        Participant(name, inputBet, scoringRule)
    }
    val allPlayers = listOf(dealer, *participants.toTypedArray())
    val deck = Deck()

    ConsoleResult.drawAllFirstTwoCards(participants)
    allPlayers.forEach {
        it.draw(deck)
        it.draw(deck)
    }
    ConsoleResult.printCardsOfPlayers(allPlayers)

    val participantsEndedGame = Players(participants)
    while (!participantsEndedGame.isAllFinished()) {
        participants
            .filter { it.isFinished.not() }
            .forEach {
                val inputState = ConsoleInput.inputHitAndStay(it)
                nextTurn(it, inputState, deck)
                ConsoleResult.printCardsOfPlayer(it)
            }
    }

    if (dealer.canDraw()) {
        dealer.draw(deck)
        ConsoleResult.notifyDealerMoreOneCard(dealer)
    }
    ConsoleResult.printCardsAndTotalScoreOfPlayers(allPlayers)

    Match.applyAllResult(dealer, participants)
    ConsoleResult.printGameResults(dealer, participants)
}

fun nextTurn(participant: Participant, inputState: State, deck: Deck) {
    if (inputState == State.STAY) {
        participant.stay()
        return
    }

    participant.draw(deck)
}
