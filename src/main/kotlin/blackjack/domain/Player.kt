package blackjack.domain

class Player(
    playerName: String,
    val battingAmount: Int,
    playerCards: PlayerCards = PlayerCards(),
    private val drawable: Drawable
) : Participant(name = playerName, participantCards = playerCards) {
    override fun isDealer(): Boolean {
        return false
    }

    override fun canDrawable(): Boolean {
        return participantCards.isHit() && drawable.canDraw(this)
    }

    fun getEarnAmount(dealer: Dealer): Int {
        return GameResult.earnMoney(this, dealer)
    }
}
