package blackjack

class Dealer(cards: MutableList<Card> = mutableListOf(), round: Round = Round()) : Player(NAME, cards, round) {
    override fun canGetCard(): Boolean {
        return sum < Rule.DEALER_MINIMUM_SCORE
    }

    companion object {
        const val NAME = "딜러"
    }
}
