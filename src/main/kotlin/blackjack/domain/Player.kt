package blackjack.domain

open class Player(
    playerName: String,
    val battingAmount: Int,
    playerCards: PlayerCards = PlayerCards()
) : Participant(name = playerName, participantCards = playerCards) {

    override fun isDealer(): Boolean {
        return false
    }

    fun getEarnAmount(dealer: Dealer): Int {
        return GameResult.earnMoney(this, dealer)
    }
}
