package blackjack.fixture

import blackjack.domain.trump.Card
import blackjack.domain.trump.Cards
import blackjack.domain.trump.Number
import blackjack.domain.trump.Pattern

class CardFixture {

    companion object {
        val HEART_ACE = Card(Pattern.HEART, Number.ACE)
        val SPADE_THREE = Card(Pattern.SPADE, Number.THREE)
        val CLOB_FOUR = Card(Pattern.CLUB, Number.FOUR)
        val DIAMOND_KING = Card(Pattern.DIAMOND, Number.KING)

        val CARDS_WITH_SUM_18 = Cards(listOf(HEART_ACE, SPADE_THREE, CLOB_FOUR, DIAMOND_KING))
    }
}
