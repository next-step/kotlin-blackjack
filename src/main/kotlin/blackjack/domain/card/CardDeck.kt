package blackjack.domain.card

import blackjack.domain.shuffle.Shuffler
import java.util.LinkedList

@JvmInline
value class CardDeck private constructor(val cards: LinkedList<Card>) {

    constructor(
        shuffler: Shuffler<Card>
    ) : this(
        LinkedList(shuffler.shuffled(Card.ALL_CARDS))
    )
}
