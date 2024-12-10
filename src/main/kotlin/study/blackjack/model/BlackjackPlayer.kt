package study.blackjack.model

/**
 * @author 이상준
 */
class BlackjackPlayer(
    name: String,
    private var cards: Cards = Cards(listOf()),
) {
    var name = name
        private set
    private var match: Match = Match.WAIT

    fun addCard(card: Card) {
        cards = cards.add(card)
    }

    fun mergeCards(cards: Cards) {
        this.cards = cards.merge(cards)
    }

    fun cards(): Cards {
        return cards
    }

    fun result(): Match {
        return this.match
    }

    fun match(dealer: BlackjackPlayer) {
        this.match = Match.of(this.cards.calculateScore(), dealer.cards.calculateScore())
    }
}
