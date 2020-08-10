package blackjack.domain.player

data class Players(private val input: String) {
    val list = parseNames(input)

    init {
        require(input.isNotBlank()) { "1명 이상의 참가자가 필요합니다." }
    }

    fun findWinners() = findWinnerCandidates().filter { it.getScore() == findMaxPosition() }

    private fun findMaxPosition() = findWinnerCandidates().max()?.getScore() ?: 0

    operator fun invoke(index: Int) = list[index]

    override fun toString() = list.joinToString("\n")

    private fun findWinnerCandidates(): List<Player> = list.filter { it.isWinnerCandidate() }

    companion object {
        private const val DELIMITER = ","

        private fun parseNames(input: String) = input.split(DELIMITER)
            .filter { it.isNotBlank() }
            .mapIndexed { index, name -> Player(index, name.trim()) }
    }
}
