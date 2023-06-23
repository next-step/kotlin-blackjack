package blackjack.domain.player

fun playerNames(vararg names: String): PlayerNames {
    return PlayerNames(names.map { PlayerName(it) })
}
