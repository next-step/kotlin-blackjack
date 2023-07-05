package blackjack.domain

@JvmInline
value class Players(
    val players: List<Player>,
) : List<Player> by players
