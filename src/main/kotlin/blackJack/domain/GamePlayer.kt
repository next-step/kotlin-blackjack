package blackJack.domain

open class GamePlayer(
    val name: String,
    var status: PlayerStatus = PlayerStatus.of(),
) {

    fun receiveCard(card: Card) {
        status = status.update(card)
    }

    fun noReceiveCard() {
        status = status.changeContinueStatus(false)
    }

    fun isDealer(): Boolean = this is Dealer

    open fun getAbleReceivedCard(): Boolean = status.ableGetACard()

    fun getCards() = status.cards

    fun isBlackJackPlayer(): Boolean = status.isBlackJack()

    fun isBustPlayer(): Boolean = status.isBustStatus()

    fun getScore() = status.sumScore()
}
