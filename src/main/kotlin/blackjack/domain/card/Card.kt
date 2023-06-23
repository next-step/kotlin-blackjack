package blackjack.domain.card

class Card private constructor(
    val rank: CardRank,
    val shape: CardShape
) {
    fun getPoints(): List<Int> {
        return rank.point
    }

    override fun toString(): String = "${rank.mark}${shape.mark}"

    companion object {
        private val CACHE = makeCardCache()

        fun createCard(rank: CardRank, shape: CardShape): Card {
            return CACHE[Pair(rank, shape)] ?: throw IllegalArgumentException("존재하지 않는 카드입니다.")
        }

        private fun makeCardCache(): Map<Pair<CardRank, CardShape>, Card> {
            return CardRank.values().flatMap { rank ->
                CardShape.values().map { suit -> Card(rank, suit) }
            }.associateBy {
                Pair(it.rank, it.shape)
            }
        }
    }
}
