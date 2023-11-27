package blackjack.domain.model

data class PlayerInfo(
    val name: String,
    val cards: List<String>
) {
    companion object {
        fun from(player: Player): PlayerInfo {
            return PlayerInfo(
                player.name.name,
                player.cards().cards.map { "${it.number.displayName}${it.shape.shape}" }
            )
        }
    }
}
