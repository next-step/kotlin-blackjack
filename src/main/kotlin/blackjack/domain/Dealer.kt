package blackjack.domain

import blackjack.domain.CardScoreCalculator.isOverScore
import blackjack.domain.CardScoreCalculator.DEALER_SCORE_THRESHOLD

class Dealer(name: String) : Participant(name) {
    override fun receiveCard(card: Card) {
        state = state.draw(card)
        if (isOverScore(cards, DEALER_SCORE_THRESHOLD)) turnStand()
    }

    infix fun versus(players: Players): List<GameResult> {
        return GameResult.resultOfDealer(players, this)
    }
}

