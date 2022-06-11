package camp.nextstep.blackjack

object CardShuffler {

    fun shuffle(cardDeck: CardDeck): CardDeck {
        val shuffled = cardDeck.cards.toMutableList().shuffled()
        return CardDeck.of(shuffled)
    }
}
