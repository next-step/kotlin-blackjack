package blackjack.domain.player

data class Players private constructor(private val input: String) {
    val participants = parseNames(input)

    fun findWinners() = findWinnerCandidates().filter { it.getScore() == findMaxPosition() }

    operator fun invoke(index: Int) = participants[index]

    override fun toString() = participants.joinToString("\n")

    private fun findMaxPosition() = findWinnerCandidates().max()?.getScore() ?: 0

    private fun findWinnerCandidates(): List<Player> = participants.filter { it.isWinnerCandidate() }

    companion object {
        private const val DELIMITER = ","

        fun getOrNull(input: String): Players? {
            if (input.isNotBlank()) {
                return Players(input)
            }
            return null
        }

        private fun parseNames(input: String) = input.split(DELIMITER)
            .filter { it.isNotBlank() }
            .mapIndexed { index, name -> Player(index, name.trim()) }
    }
}
