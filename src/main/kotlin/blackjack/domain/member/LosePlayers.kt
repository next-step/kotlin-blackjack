package blackjack.domain.member

class LosePlayers(
    val items: List<Player>
) {
    val size: Int
        get() = items.size
}
