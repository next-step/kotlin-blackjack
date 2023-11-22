package step2.blackjack.model

@JvmInline
value class Dealer private constructor(private val deck: Deck) {

    fun handsOut(gamblers: Gamblers) = gamblers.forEach { gambler -> handsOut(gambler) }
    private fun handsOut(gambler: Gambler) = gambler.receive(deck.peek())

    companion object {
        fun hire(deck: Deck = Deck.all()): Dealer = Dealer(deck)
    }
}