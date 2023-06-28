package blackjack.domain.gamer

fun playerNames(vararg names: String): PlayerNames {
    return PlayerNames(names.map { PlayerName(it) })
}
