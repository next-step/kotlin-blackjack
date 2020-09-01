package blackjack.domain.player

import blackjack.domain.game.Dealer

class Players(input: String) {
    val participants = parseNames(input)

    init {
        require(input.isNotBlank())
    }

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

        private fun parseNames(input: String) = input.split(DELIMITER)
            .filter { it.isNotBlank() }
            .mapIndexed { index, name -> Player(index, name.trim()) }
    }
}
