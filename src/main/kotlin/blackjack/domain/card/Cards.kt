package blackjack.domain.card

data class Cards(private val _cardList: MutableList<Card>) {
    val cardList get() = _cardList.toList()
    val size get() = _cardList.size

    fun shuffle() {
        _cardList.shuffle()
    }

    fun drawTop(): Card = _cardList.removeAt(0)

    fun add(card: Card) {
        _cardList.add(card)
    }

    companion object {
        fun fullCards(): Cards {
            val mutableList = mutableListOf<Card>()
            Suit.values().forEach { suit ->
                Character.values().forEach { character ->
                    mutableList.add(Card(suit, character))
                }
            }
            return Cards(mutableList)
        }
    }
}
