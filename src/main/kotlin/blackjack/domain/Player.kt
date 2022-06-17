package blackjack.domain

class Player(
    playerName: String,
    val battingAmount: Int,
    playerCards: PlayerCards = PlayerCards()
) : Participant(name = playerName, participantCards = playerCards) {

    private val canDrawable: Drawable = PlayerDrawable(this)
    override fun isDealer(): Boolean {
        return false
    }

    override fun canDrawable(): Boolean {
        return participantCards.isHit() && canDrawable.canDraw()
    }

    fun getEarnAmount(dealer: Dealer): Int {
        return GameResult.earnMoney(this, dealer)
    }
}
