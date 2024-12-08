package blackjack

import blackjack.CardTextFixtures.heartNineCard
import blackjack.CardTextFixtures.spadeAceCard
import blackjack.CardTextFixtures.spadeEightCard
import blackjack.CardTextFixtures.spadeKingCard
import blackjack.CardTextFixtures.spadeNineCard
import blackjack.CardTextFixtures.spadeQueenCard
import blackjack.CardTextFixtures.spadeSixCard
import blackjack.CardTextFixtures.spadeTenCard

object InitialCardsTestFixtures {
    val initial18Cards = listOf(spadeTenCard, spadeEightCard)

    val initial18Cards2 = listOf(spadeNineCard, heartNineCard)

    val initial20Cards = listOf(spadeKingCard, spadeQueenCard)

    val initial19Cards = listOf(spadeTenCard, spadeNineCard)

    val initial16Cards = listOf(spadeTenCard, spadeSixCard)

    val blackjackCards = listOf(spadeAceCard, spadeKingCard)

    val blackjackCards2 = listOf(spadeAceCard, spadeQueenCard)
}
