package blackjack.domain.model

@JvmInline
value class Dealer private constructor(private val deck: Deck) {

    fun handsOutAll(gamblers: Gamblers) = gamblers.forEach { gambler -> handsOut(gambler) }

    fun handsOut(gambler: Gambler) = gambler.receive(deck.peek())

    companion object {
        fun hire(deck: Deck = Deck.all()): Dealer = Dealer(deck)
    }
}
