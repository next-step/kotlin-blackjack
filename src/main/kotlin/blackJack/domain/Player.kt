package blackJack.domain

data class Player(val playerName: String, val status: PlayerStatus) {

    fun receiveCard(card: Card): Player {
        return this.copy(playerName = playerName, status = status.update(card))
    }

    companion object {
        fun of(playerName: String): Player {
            return Player(playerName, PlayerStatus.of())
        }
    }
}
