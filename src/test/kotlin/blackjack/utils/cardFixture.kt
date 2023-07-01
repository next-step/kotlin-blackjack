package blackjack.utils

import blackjack.Card
import blackjack.prepareAllPossibleRandomUniqueCards

fun arrangeCardsForTest(
    vararg preArrangedCard: Card,
): Set<Card> {

    val cardsExceptPreArrangedCard = prepareAllPossibleRandomUniqueCards()
        .filterNot(preArrangedCard::contains)

    return preArrangedCard.toSet() + cardsExceptPreArrangedCard
}

