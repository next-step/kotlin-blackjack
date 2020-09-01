package blackjack.domain.player

import blackjack.domain.game.Dealer

class Players private constructor(input: String) {
    val participants = parseNames(input)

    fun findWinners(dealer: Dealer): List<Player> {
        if (!dealer.isWinnerCandidate()) {
            return findWinnerCandidates()
        }
        return findWinnerCandidates().filter { it > dealer }
    }

    operator fun invoke(index: Int) = participants[index]

    override fun toString() = participants.joinToString("\n")

    private fun findWinnerCandidates() = participants.filter { it.isWinnerCandidate() }

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
