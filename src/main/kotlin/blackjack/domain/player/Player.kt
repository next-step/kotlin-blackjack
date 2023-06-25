package blackjack.domain.player

class Player(val name: Name = Name()) : GamePlayer() {

    override fun isReceivable(): Boolean {
        return status.isReceivable
    }

    override fun afterEventOfReceiveCard() = Unit

    fun getStayStatus() {
        status = PlayerStatus.STAY
    }
}
