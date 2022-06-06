package blackjack.domain.player.vo

data class PlayerStatus(
    val name: Name,
    var stay: Boolean = false
)
