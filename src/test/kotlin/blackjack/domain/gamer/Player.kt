package blackjack.domain.gamer

fun player(name: String): Player {
    return Player(PlayerName(name))
}
