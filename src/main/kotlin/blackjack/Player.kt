package blackjack

data class Player(
    val name: String,
) {
    val cardList: MutableList<Card> = mutableListOf()

    val score: Int
        get() {
            var score = 0
            val aceRankGroup = cardList.groupBy { it.rank == Rank.ACE }
            score += aceRankGroup[false]?.sumOf { it.rank.point } ?: 0

            val aceCount = aceRankGroup[true]?.count() ?: 0
            score += if (aceCount > 0) {
                if (score + aceCount + 10 > 21) {
                    aceCount
                } else {
                    aceCount + 10
                }
            } else {
                0
            }
            return score
        }

    val canReceiveCard: Boolean
        get() {
            return score < 21
        }

    fun receiveCard(card: Card) {
        cardList.add(card)
    }

    companion object {
        fun of(playerString: String): List<Player> {
            return playerString.split(",")
                .map { Player(it.trim()) }
        }
    }

}
