package blackjack

class Dealer(round: Round = Round()) : Player(NAME, round) {
    override fun canGetCard(): Boolean {
        return sum < Round.DEALER_MINIMUM_SCORE
    }

    companion object {
        const val NAME = "딜러"
    }
}
