package blackjack.model

sealed class Gamer(val name: Name, val cards: Cards) {

    val score: Int get() = cards.sum()

    abstract fun canReceive(): Boolean

    abstract fun copy(name: Name = this.name, cards: Cards = this.cards): Gamer

    fun receive(card: Card): Gamer = if (hasNext(card)) copy(cards = cards + card) else this

    fun receiveWhile(next: () -> Card?, onReceive: (Cards) -> Unit = {}): Gamer {
        var result = this
        var card: Card? = next()
        while (result.hasNext(card)) {
            result = result.receive(card!!)
            onReceive(result.cards)
            card = next()
        }
        return result
    }

    private fun hasNext(card: Card?): Boolean = card != null && canReceive() && !hasCard(card)

    private fun hasCard(card: Card): Boolean = card in cards

    companion object {
        fun player(name: Name, cards: Cards = Cards.empty()): Gamer = Player.from(name, cards)

        fun dealer(name: Name, cards: Cards = Cards.empty()): Gamer = Dealer.from(name, cards)
    }
}
