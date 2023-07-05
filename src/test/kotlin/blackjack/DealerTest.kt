package blackjack

import blackjack.domain.Dealer
import io.kotest.matchers.nulls.shouldNotBeNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class DealerTest {

    @DisplayName("딜러는 CardDeck을 가지고 있다.")
    @Test
    fun dealerHasCardDeck() {
        val dealer = Dealer()

        dealer.cardDeck.shouldNotBeNull()
    }
}