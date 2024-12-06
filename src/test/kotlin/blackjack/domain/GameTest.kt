package blackjack.domain

import blackjack.domain.StubDeck.Companion.DUMMY_SUIT
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@Suppress("NonAsciiCharacters")
class GameTest {
    private lateinit var deck: Deck

    @BeforeEach
    fun setUp() {
        deck =
            StubDeck.from(
                Rank.ACE,
                Rank.TWO,
                Rank.THREE,
                Rank.FOUR,
                Rank.FIVE,
                Rank.SIX,
                Rank.SEVEN,
                Rank.EIGHT,
            )
    }

    @Test
    fun `플레이어들에게 두 장의 카드를 지급한다`() {
        val players = Players.from("black", "jack")
        val game = Game(players, deck)

        game.initialDeal()

        // black
        game.players[0].hand[0] shouldBe Card(DUMMY_SUIT, Rank.ACE)
        game.players[0].hand[1] shouldBe Card(DUMMY_SUIT, Rank.FOUR)
        // jack
        game.players[1].hand[0] shouldBe Card(DUMMY_SUIT, Rank.TWO)
        game.players[1].hand[1] shouldBe Card(DUMMY_SUIT, Rank.FIVE)
    }

    @Test
    fun `딜러에게도 두 장의 카드를 지급한다`() {
        val players = Players.from("black", "jack")
        val game = Game(players, deck)

        game.initialDeal()

        game.dealer.hand[0] shouldBe Card(DUMMY_SUIT, Rank.THREE)
        game.dealer.hand[1] shouldBe Card(DUMMY_SUIT, Rank.SIX, Face.DOWN)
    }

    @Test
    fun `딜러의 첫번째 카드는 앞면이 보인다`() {
        val players = Players.from("black", "jack")
        val game = Game(players, deck)

        game.initialDeal()

        game.dealer.hand[0].isFaceUp shouldBe true
    }

    @Test
    fun `딜러의 두번째 카드는 뒷면이 보인다`() {
        val players = Players.from("black", "jack")
        val game = Game(players, deck)

        game.initialDeal()

        game.dealer.hand[1].isFaceUp shouldBe false
    }

    @Test
    fun `딜러가 블랙잭이면 바로 게임을 종료한다`() {
        val deck = StubDeck.from(Rank.TWO, Rank.THREE, Rank.ACE, Rank.FOUR, Rank.FIVE, Rank.KING)
        val players = Players.from("black", "jack")

        val game = Game(players, deck).apply { initialDeal() }

        game.players[0].reasonDone shouldBe PlayerReasonDone.DEALER_DEALT_BLACKJACK
        game.players[1].reasonDone shouldBe PlayerReasonDone.DEALER_DEALT_BLACKJACK
    }

    @Test
    fun `모든 플레이어들의 턴이 종료했으면 게임도 종료 상태이다`() {
        val players =
            Players(
                Player("black", Hand()).apply { stand() },
                Player("jack", Hand()).apply { stand() },
            )

        val game = Game(players, StubDeck.from())

        game.isDone shouldBe true
    }

    @Test
    fun `플레이어가 힛하면 덱에서 카드를 뽑는다`() {
        val players = Players.from("black", "jack")
        val game = Game(players, deck).apply { initialDeal() }

        game.playerHits()

        game.currentPlayer.hand[2] shouldBe Card(DUMMY_SUIT, Rank.SEVEN)
    }

    @Test
    fun `플레이어가 힛하면 플레이어의 턴이 계속된다`() {
        val players = Players.from("black", "jack")
        val game = Game(players, deck).apply { initialDeal() }

        game.playerHits()

        game.currentPlayer shouldBe game.players[0]
    }

    @Test
    fun `플레이어가 스탠드하면 플레이어의 턴이 종료한다`() {
        val players = Players.from("black", "jack")
        val game = Game(players, deck).apply { initialDeal() }

        game.playerStands()

        game.players[0].reasonDone shouldBe PlayerReasonDone.PLAYER_STANDS
    }

    @Test
    fun `플레이러가 스탠드하면 다음 선수로 턴이 넘어간다`() {
        val players = Players.from("black", "jack")
        val game = Game(players, deck).apply { initialDeal() }

        game.playerStands()

        game.currentPlayer shouldBe game.players[1]
    }
}
