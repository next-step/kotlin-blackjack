package blackjack.domain.person

import blackjack.domain.card.Cards
import blackjack.domain.enums.WinOrLose

class Participant(
    name: String,
    money: Long,
    cards: Cards = Cards()
) : Player(name, money, cards) {
    fun getGameResult(dealer: Dealer): WinOrLose {
        if (dealer.isBurst() || this.getScore() in dealer.getScore()..MAXIMUM_SCORE) {
            return WinOrLose.WIN
        }
        return WinOrLose.LOSE
    }

    companion object {
        private const val MAXIMUM_SCORE = 21
        private const val LOSE_DIVIDEND_RATE = -1.0
    }
}
