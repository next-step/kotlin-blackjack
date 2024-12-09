package blackjack.step2.domain

class Dealer private constructor(
    override val name: String,
    override val cards: Cards,
) : Participant {
    override fun pickCard(card: Card): Dealer {
        return Dealer(name, cards.add(card))
    }

    companion object {
        private const val DEALER_NAME = "딜러"

        fun of(cards: List<Card> = emptyList()): Dealer {
            return Dealer(name = DEALER_NAME, cards = Cards.of(cards))
        }
    }
}
