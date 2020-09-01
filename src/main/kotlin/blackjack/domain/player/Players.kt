package blackjack.domain.player

class Players(input: String) {
    val participants: List<Player> = parseNames(input)
    val size: Int
        get() = participants.size

    init {
        require(input.isNotBlank())
    }

    operator fun invoke(index: Int) = participants[index]

    override fun toString() = participants.joinToString("\n")

    companion object {
        private const val DELIMITER = ","

        private fun parseNames(input: String): List<Player> = input.split(DELIMITER)
            .filter { it.isNotBlank() }
            .mapIndexed { index, name -> Player(index, name.trim()) }
    }
}
