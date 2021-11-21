package blackjack.model

@JvmInline
value class Gamers(private val gamers: List<Gamer>) {

    fun players(): List<Player> = gamers.filterIsInstance<Player>()

    fun dealer(): Dealer? = gamers.filterIsInstance<Dealer>().firstOrNull()

    fun toList(): List<Gamer> = gamers

    fun drawUntilStarted(next: () -> Card?) = gamers
        .asSequence()
        .map { gamer ->
            var result = gamer
            while (result.isReady()) {
                val card = next() ?: break
                result = result.draw(card)
            }
            result
        }
        .let { Gamers(it.toList()) }

    fun drawWhile(next: (Gamer) -> Card?, onDraw: (Gamer) -> Unit) = gamers
        .asSequence()
        .map { gamer ->
            var result = gamer
            while (result.isRunning()) {
                val card = next(result) ?: break
                result = result.draw(card)
                onDraw(result)
            }
            result.stay()
        }
        .let { Gamers(it.toList()) }

    companion object {
        fun empty(): Gamers = Gamers(emptyList())

        fun from(dealer: Dealer, players: List<Player>) = Gamers(listOf(dealer) + players)

        fun players(players: List<Player>) = Gamers(players)
    }
}
