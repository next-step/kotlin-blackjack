package blackjack

import blackjack.business.CardFixture.SPACE_ACE
import blackjack.business.CardFixture.SPACE_FIVE
import blackjack.business.CardFixture.SPACE_NINE
import blackjack.business.CardFixture.SPACE_TEN
import blackjack.business.CardFixture.SPACE_THREE
import blackjack.business.card.Card
import blackjack.business.card.CardFactory

class GameManagerTestCardFactory : CardFactory {
    override fun getCards(): List<Card> {
        return listOf(
            // dealer
            SPACE_ACE, SPACE_THREE,
            // pobi
            SPACE_NINE, SPACE_THREE,
            // jason
            SPACE_TEN, SPACE_THREE,
            // pobi
            SPACE_NINE,
            // jason
            SPACE_TEN,
            // dealer
            SPACE_FIVE
        )
    }
}
