package blackjack.model

sealed class Gamer(val name: Name, val cards: Cards) {

    abstract fun canReceive(): Boolean

    abstract fun copy(name: Name = this.name, cards: Cards = this.cards): Gamer

    fun receive(card: Card): Gamer = if (hasNext(card)) copy(cards = cards + card) else this

    private fun hasNext(card: Card?): Boolean = card != null && canReceive() && !hasCard(card)

    private fun hasCard(card: Card): Boolean = card in cards

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

    companion object {
        fun player(name: Name): Gamer = Player.from(name, Cards.empty())

        fun dealer(name: Name): Gamer = Dealer.from(name, Cards.empty())
    }
}
