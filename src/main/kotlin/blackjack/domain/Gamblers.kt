package blackjack.domain

class Gamblers(private val gamblers: List<Gambler>) : Collection<Gambler> by gamblers {
    companion object {
        fun from(names: List<String>): Gamblers {
            val gamblers = names.map { name -> Gambler(name) }
            return Gamblers(gamblers)
        }
    }
}