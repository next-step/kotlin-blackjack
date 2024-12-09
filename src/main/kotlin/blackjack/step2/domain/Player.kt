package blackjack.step2.domain

class Player private constructor(
    override val name: String,
    override val cards: Cards,
) : Participant {
    override fun pickCard(card: Card): Player {
        return Player(name, cards.add(card))
    }

    companion object {
        fun of(
            playerName: String,
            cards: List<Card> = emptyList(),
        ): Player {
            return Player(playerName, Cards.of(cards))
        }
    }
}
