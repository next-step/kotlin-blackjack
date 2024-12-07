package blackjack.domain

data class CardDeck(val deck: MutableList<Card>) {
    init {
        val defaultDeck = defaultDeckGenerator()
        require(deck.containsAll(defaultDeck)) { "블랙잭 게임을 하기 위해서는 모든 카드가 준비되어야합니다" }
    }

    fun drawCard(): Card {
        require(deck.isNotEmpty()) { "더이상 카드가 남아있지 않습니다" }
        return deck.removeAt(deck.lastIndex)
    }
}
