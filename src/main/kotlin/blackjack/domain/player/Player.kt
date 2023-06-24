package blackjack.domain.player

class Player(val name: Name = Name()) : GamePlayer() {

    override fun isReceivable(): Boolean {
        return status == PlayerStatus.RECEIVABLE || status == PlayerStatus.BLACK_JACK
    }

    override fun getDefaultStatus(): PlayerStatus {
        return PlayerStatus.RECEIVABLE
    }

    override fun afterEventOfReceiveCard() = Unit

    fun getStayStatus() {
        status = PlayerStatus.STAY
    }
}
