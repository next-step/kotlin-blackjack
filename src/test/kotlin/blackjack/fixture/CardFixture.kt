package blackjack.fixture

import blackjack.domain.trump.Card
import blackjack.domain.trump.Cards
import blackjack.domain.trump.Number
import blackjack.domain.trump.Pattern

class CardFixture {

    companion object {
        val HEART_ACE = Card(Pattern.HEART, Number.ACE)
        val SPADE_THREE = Card(Pattern.SPADE, Number.THREE)
        val CLUB_FOUR = Card(Pattern.CLUB, Number.FOUR)
        val DIAMOND_KING = Card(Pattern.DIAMOND, Number.KING)
        val HEART_QUEEN = Card(Pattern.HEART, Number.QUEEN)
        val CLUB_JACK = Card(Pattern.CLUB, Number.JACK)

        val CARDS_WITH_SUM_18 = Cards(listOf(HEART_ACE, SPADE_THREE, CLUB_FOUR, DIAMOND_KING))
        val CARDS_WITH_SUM_30 = Cards(listOf(DIAMOND_KING, HEART_QUEEN, CLUB_JACK))
    }
}
