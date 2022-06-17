package blackjack.domain

abstract class Participant(
    val name: String,
    val participantCards: Cards
) {
    var blackJackStatus = participantCards.getBlackJackStatus()
        private set

    abstract fun isDealer(): Boolean

    abstract fun getEarnAmount(participants: List<Player>, dealer: Dealer): Int

    fun addCard(card: Card) {
        this.participantCards.addCard(card)
        blackJackStatus = participantCards.getBlackJackStatus()
    }

    fun setFirstDistributionBlackJack() {
        blackJackStatus = participantCards.getBlackJackStatus()
    }

    fun setBlackJackStatusStay() {
        blackJackStatus = BlackJackStatus.STAY
    }

    fun isHit(): Boolean {
        return this.blackJackStatus == BlackJackStatus.HIT
    }

    fun isBust(): Boolean {
        return this.blackJackStatus == BlackJackStatus.BUST
    }
}
