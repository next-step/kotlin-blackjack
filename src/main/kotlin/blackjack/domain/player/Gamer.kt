package blackjack.domain.player

data class Gamer(
    override val name: String
): Player(name)
