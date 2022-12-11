package blackjack.domain

data class Player(val name: String, val cards: Cards = Cards.empty()) {
    init {
        require(!name.isNullOrBlank()) { "이름은 빈값이 될 수 없습니다." }
    }

    fun initialCard(cards: Cards): Player = this.copy(cards = this.cards.addAll(cards))

    fun hit(card: Card): Player = this.copy(name = this.name, cards = this.cards.add(card))

    fun countingCard(): Int = cards.countingCard()

    fun isBurst(): Boolean = countingCard() > BLACKJACK_SCORE
}
