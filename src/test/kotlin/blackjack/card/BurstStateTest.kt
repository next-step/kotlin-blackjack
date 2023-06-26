package blackjack.card

import blackjack.card.helper.CardsTestFactory
import domain.card.Card
import domain.card.CardNumber
import domain.card.Suit
import domain.player.Dealer
import domain.player.PlayerGameResult
import domain.state.Burst
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class BurstStateTest {

    @Test
    fun `버스트 상태에서는 무조건 진다`() {
        val cards = CardsTestFactory.makeCards(
            Card(suit = Suit.SPADE, number = CardNumber.JACK),
            Card(suit = Suit.SPADE, number = CardNumber.JACK),
            Card(suit = Suit.SPADE, number = CardNumber.JACK),
        )

        val dealer = Burst(cards)

        val burstState = Burst(cards = cards)

        burstState.getPlayerGameResult(dealer) shouldBe PlayerGameResult.LOSE
    }
}
