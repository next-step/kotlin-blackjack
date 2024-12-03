package blackjack.domain

class Player(
    name: String,
    cards: Cards = Cards(),
    val bettingMoney: BettingMoney,
) : Participant(name, cards) {
    override fun canDrawCard(): Boolean {
        return !cards.isOverMaxScore()
    }

    fun isWinner(dealer: Dealer): Boolean {
        if (cards.isBust()) {
            return false
        }

        if (dealer.isBust()) {
            return true
        }

        return cards.calculateScore() > dealer.cards.calculateScore()
    }

    fun calculateEarningMoney(dealer: Dealer): Long {
        if (isBlackJack) {
            return calcBlackJackEarningMoney(dealer)
        }
        return if (isWinner(dealer)) bettingMoney.getMoney() else bettingMoney.getMinusMoney()
    }

    private fun Player.calcBlackJackEarningMoney(dealer: Dealer): Long {
        if (dealer.isBlackJack) {
            return 0L
        }
        return (bettingMoney.getBlackJackMultipleMoney())
    }
}
