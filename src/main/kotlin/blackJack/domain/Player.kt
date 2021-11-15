package blackJack.domain

class Player(val playerName: String) {
    var status: PlayerStatus = PlayerStatus.of()
        private set

    init {
        require(playerName.isNotEmpty()) { IS_PLAYER_NAME_BLACK }
    }

    fun receiveCard(card: Card) {
        status = status.update(card)
    }

    fun noReceiveCard() {
        status = status.changeContinueStatus(false)
    }

    fun getAbleReceivedCard(): Boolean = status.ableGetACard()

    fun getCards() = status.cards

    fun isBlackJackPlayer(): Boolean = status.isBlackJack()

    fun getScore() = status.sumScore()

    companion object {
        fun of(playerName: String): Player {
            return Player(playerName)
        }

        private const val IS_PLAYER_NAME_BLACK = "해당 플레이어 이름이 빈값입니다."
    }
}
