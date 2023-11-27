package blackjack.domain

data class Player(val name: String)

@JvmInline
value class Players(val values: List<Player>) {

    constructor(vararg names: String) : this(names.map { Player(it) })

    init {
        require(values.size == values.map { it.name }.toSet().size) { "duplicate name has been used" }
    }
}
