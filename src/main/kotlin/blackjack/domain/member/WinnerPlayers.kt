package blackjack.domain.member

class WinnerPlayers(
    val items: List<Player>
) {
    val size: Int
        get() = items.size
}
