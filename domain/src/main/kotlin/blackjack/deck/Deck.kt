package blackjack.deck

import blackjack.card.Card
import java.util.Stack

class Deck(
    cardProvider: CardProvider = StandardCardProvider(),
    cardShuffler: CardShuffler = RandomCardShuffler(),
) {
    private val cards: Stack<Card> = Stack<Card>().apply {
        addAll(cardShuffler.shuffle(cardProvider.provideCards()))
    }

    val remainingCards: List<Card>
        get() = cards.toList()

    val size
        get() = cards.size

    fun drawCard(): Card {
        check(cards.isNotEmpty()) { "덱에 카드가 없으면 카드를 뽑을 수 없습니다." }
        return cards.pop()
    }
}
