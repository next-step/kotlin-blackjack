package blackjack.service

import blackjack.domain.Card
import java.util.Stack

interface DeckCardsBuilder {
    fun build(): Stack<Card>
}
