package blackjack.domain.model

data class PlayerInfo(
    val name: String,
    val cards: List<String>,
    val score: Int
) {
    companion object {
        fun from(player: Player): PlayerInfo {
            return PlayerInfo(
                player.name.name,
                player.getCardsString(),
                player.getFinalScore().score
            )
        }

        private fun Player.getCardsString(): List<String> {
            return cards()
                .cards
                .map { "${it.number.displayName}${it.shape.shape}" }
        }
    }
}
