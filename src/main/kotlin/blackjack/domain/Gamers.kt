package blackjack.domain

data class Gamers(val gamer: List<Gamer>) {
    init {
        require(gamer.size >= MINIMUM_GAMER)
    }

    companion object {
        private const val MINIMUM_GAMER = 2

        fun createGamers(names: Names): Gamers {
            return Gamers(names.names.map { Gamer(it) })
        }
    }
}
