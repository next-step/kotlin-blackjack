package blackjack.domain

data class GameStatus(
    val dealer: Dealer,
    val players: List<Player>,
)
