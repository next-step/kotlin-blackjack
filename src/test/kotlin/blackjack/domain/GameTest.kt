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

    @Test
    fun `모든 플레이어들의 턴이 종료했으면 게임도 종료 상태이다`() {
        val players =
            Players(
                listOf(
                    Player("black", Hand()).apply { stand() },
                    Player("jack", Hand()).apply { stand() },
                ),
            )

        val game = Game(players, StubDeck.from())

        game.isDone shouldBe true
    }
}
