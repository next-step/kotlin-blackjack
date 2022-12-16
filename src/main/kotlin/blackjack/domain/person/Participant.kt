package blackjack.domain.person

import blackjack.domain.WinOrLose
import blackjack.domain.card.Cards

class Participant(
    name: String,
    cards: Cards = Cards()
) : Player(name, cards) {
    fun getGameResult(dealer: Dealer): WinOrLose {
        val dealerScore = dealer.getScore()
        val participantScore = this.getScore()
        if (dealerScore > MAXIMUM_SCORE || participantScore in dealerScore..MAXIMUM_SCORE) {
            return WinOrLose.WIN
        }
        return WinOrLose.LOSE
    }

    companion object {
        private const val MAXIMUM_SCORE = 21
    }
}
