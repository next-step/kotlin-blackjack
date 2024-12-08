package blackjack.domain

class Gamblers(private val gamblers: List<Gambler>) : Collection<Gambler> by gamblers {
    constructor(vararg names: String) : this(names.map { name -> Gambler(name) })
}