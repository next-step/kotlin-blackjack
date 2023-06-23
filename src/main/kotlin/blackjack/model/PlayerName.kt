package blackjack.model

@JvmInline
value class PlayerName(private val name: String) {

    init {
        require(name.isNotBlank()) { "name must not be empty or blank" }
    }

    override fun toString(): String {
        return name
    }
}
