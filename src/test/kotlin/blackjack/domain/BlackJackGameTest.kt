package blackjack.domain

import org.junit.jupiter.api.Test

internal class BlackJackGameTest {

    @Test
    internal fun 테스트() {
        val deck = MockCardDeck()
        val players = listOf("molly", "jayce")
        BlackJackGame.of(players, deck)
    }
}