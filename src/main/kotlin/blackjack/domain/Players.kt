package blackjack.domain

class Players(
    private val players: List<Player>,
) : List<Player> by players
