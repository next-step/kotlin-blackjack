package blackjack.domain

class Deck {
    var cards = Cards().getFull()
        private set

    val cardSize
        get() = cards.value.size

    init {
        require(cards.value.size == Cards.TOTAL_SIZE) { "52장의 카드가 준비되어야 게임을 시작할 수 있습니다" }
    }

    fun ready() {
        cards.shuffle()
    }

    fun draw(): Card {
        return cards.dec()
    }
}
