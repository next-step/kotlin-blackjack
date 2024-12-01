package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

@Suppress("NonAsciiCharacters")
class GameTest {
    @Test
    fun `두 장의 카드를 플레이어들에게 지급한다`() {
        val players = Players.from("black", "jack", "game")
        val deck = StubDeck.from(Rank.ACE, Rank.TWO, Rank.THREE, Rank.FOUR, Rank.FIVE, Rank.SIX)
        val game = Game(players, deck)

        game.initialDeal()

        // black
        game.players[0].hand[0] shouldBe Card.of(StubDeck.DUMMY_SUIT, Rank.ACE)
        game.players[0].hand[1] shouldBe Card.of(StubDeck.DUMMY_SUIT, Rank.FOUR)
        // jack
        game.players[1].hand[0] shouldBe Card.of(StubDeck.DUMMY_SUIT, Rank.TWO)
        game.players[1].hand[1] shouldBe Card.of(StubDeck.DUMMY_SUIT, Rank.FIVE)
        // game
        game.players[2].hand[0] shouldBe Card.of(StubDeck.DUMMY_SUIT, Rank.THREE)
        game.players[2].hand[1] shouldBe Card.of(StubDeck.DUMMY_SUIT, Rank.SIX)
    }
}
