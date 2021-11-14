package blackJack.domain

class Player(val playerName: String) {
    private var _status: PlayerStatus = PlayerStatus.of()

    val status: PlayerStatus
        get() = _status

    init {
        require(playerName.isNotEmpty()) { IS_PLAYER_NAME_BLACK }
    }

    fun receiveCard(card: Card) {
        _status = status.update(card)
    }

    fun noReceiveCard() {
        _status = status.noWantReceiveCard()
    }

    fun getPlayerDecisionStatus(): PlayerDecision = status.decisionStatus

    fun getAbleReceivedCard(): Boolean = status.ableGetACard()

    fun getCards() = status.cards

    companion object {
        fun of(playerName: String): Player {
            return Player(playerName)
        }

        private const val IS_PLAYER_NAME_BLACK = "해당 플레이어 이름이 빈값입니다."
    }
}
