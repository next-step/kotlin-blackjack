package blackjack.utils

import blackjack.Card
import blackjack.CardNumber
import blackjack.Suit

fun prepareAllPossibleUniqueCards() = CardNumber
    .values()
    .flatMap { number ->
        Suit.values().map { suit ->
            Card(suit, number)
        }
    }
    .shuffled()
    .toSet()

fun arrangeCardsForTest(
    vararg preArrangedCard: Card,
): Set<Card> {

    val cardsExceptPreArrangedCard = prepareAllPossibleUniqueCards()
        .filterNot(preArrangedCard::contains)

    return preArrangedCard.toSet() + cardsExceptPreArrangedCard
}

