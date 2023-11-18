package blackJack.domain

class Dealer {
    fun betting(): Cards {
        return Cards(List(INIT_CARD_COUNT) { drawCard() })
    }

    fun drawCard(): Card {
        return Card(Suit.randomizeSuit(), Rank.randomizeRank())
    }

    companion object {
        private const val INIT_CARD_COUNT = 2
    }
}
