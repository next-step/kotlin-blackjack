package blackjack.domain.gamer

fun players(vararg names: String): Players {
    return Players(names.map { player(it) })
}
