package blackjack.domain

class PlayerResult(private val player: Player, private val result: Result) {
    fun playerName(): String {
        return player.name
    }

    fun resultDisplayName(): String {
        return result.displayName
    }
}
