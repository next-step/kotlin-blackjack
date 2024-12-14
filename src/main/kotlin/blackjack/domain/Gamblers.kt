package blackjack.domain

class Gamblers(private val gamblers: List<Gambler>): Collection<Gambler> by gamblers {
    fun receive(deck: Deck) {
        gamblers.forEach { gambler ->
            gambler.receive(deck.draw(), deck.draw())
        }
    }

    companion object {
        fun from(names: List<String>): Gamblers {
            val gamblers = names.map { name -> Gambler(name) }
            return Gamblers(gamblers)
        }
    }
}