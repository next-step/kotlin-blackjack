package blackjack.domain.card

class Card private constructor(
    val rank: CardRank,
    val shape: CardShape
) {

    fun getPoint(): Int {
        return rank.point[0] ?: throw IllegalArgumentException("포인트가 세팅되지 않았습니다")
    }

    companion object {
        val CACHE: Map<Pair<CardRank, CardShape>, Card> = makeCardCache()

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
