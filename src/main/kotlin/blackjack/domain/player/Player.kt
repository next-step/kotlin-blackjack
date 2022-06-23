package blackjack.domain.player

class Player(
    val playerName: PlayerName,
) {
    companion object {
        fun from(nameValue: String): Player = Player(playerName = PlayerName(value = nameValue))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Player

        if (playerName != other.playerName) return false

        return true
    }

    override fun hashCode(): Int {
        return playerName.hashCode()
    }
}
