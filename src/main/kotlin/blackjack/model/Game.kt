package blackjack.model

import blackjack.model.candidate.Candidate
import blackjack.model.candidate.Candidates

class Game(
    private val gameHost: GameHost = GameHost(),
    val candidates: Candidates,
    var turnCandidate: Candidate = candidates.first
) {

    fun start() {
        gameHost.shuffleCards()
        gameHost.provideCardTo(candidates, INITIAL_PROVIDED_CARD_COUNT)
    }

    fun provideCardToTurnPlayer() = gameHost.provideOneCardTo(turnCandidate)

    fun changeTurn() {
        turnCandidate.needMoreCard = false
        turnCandidate = candidates.findNext(turnCandidate)
    }

    fun isNotEnd() = turnCandidate.needMoreCard

    companion object {
        private const val INITIAL_PROVIDED_CARD_COUNT = 2
    }
}
