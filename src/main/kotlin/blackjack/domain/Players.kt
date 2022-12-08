package blackjack.domain

class Players(
    val items: List<Player>
) {

    init {
        require(items.size >= MIN_SIZE) { "참가자는 최소 두명이상 이어야 해요." }
    }

    fun winner(dealer: Dealer) = items.filter { it.isWin(dealer) }
    fun loser(dealer: Dealer) = items.filter { it.isLose(dealer) }

    companion object {
        private const val MIN_SIZE = 2

        fun init(usersNames: List<String>, deck: Deck): Players {
            return usersNames.map { name ->
                val cards = deck.drawInitAssignCards()
                Player(name, cards)
            }.toPlayers()
        }

        private fun List<Player>.toPlayers() = Players(this)
    }
}
