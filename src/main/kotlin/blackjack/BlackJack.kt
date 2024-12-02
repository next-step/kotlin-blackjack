package blackjack

object BlackJack {
    fun start(playerNames: List<String>, deck: Deck) {
        println(deck.toString())
        println(deck.cards.size)
    }
}