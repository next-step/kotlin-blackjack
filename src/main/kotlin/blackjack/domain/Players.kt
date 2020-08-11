package blackjack.domain

object Players {
    fun from(names: List<String>) = names.map { Player(it) }
}
