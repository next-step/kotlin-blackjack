package blackjack.domain

import blackjack.domain.StubDeck.Companion.DUMMY_SUIT
import blackjack.support.Fixtures.createBustedPlayer
import blackjack.support.Fixtures.createPlayer
import blackjack.support.Fixtures.createPlayers
import blackjack.support.Fixtures.createStandingPlayer
import blackjack.support.Fixtures.playersOf
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@Suppress("NonAsciiCharacters")
class PlayersTest {
    @Test
    fun `이름들로 플레이어들을 생성할 수 있다`() {
        val players = Players.from("black", "jack", "game")

        val names = players.roster.map { it.name }
        names shouldContainExactly listOf("black", "jack", "game")
    }

    @Test
    fun `플레이어 목록이 비어 있으면 예외를 던진다`() {
        assertThrows<IllegalArgumentException> { Players(emptyList()) }
    }

    @Test
    fun `중복된 이름이 있으면 예외를 던진다`() {
        val names = listOf("black", "jack", "black")
        assertThrows<IllegalArgumentException> { Players.from(names) }
    }

    @Test
    fun `플레이어들에게 카드 한 장씩 지급한다`() {
        val players = Players.from("black", "jack")
        val deck = StubDeck.from(Rank.ACE, Rank.TWO)

        players.dealRoundOfCardsFrom(deck)
        // black: A
        // jack:  2

        players[0].hand[0] shouldBe Card(DUMMY_SUIT, Rank.ACE)
        players[1].hand[0] shouldBe Card(DUMMY_SUIT, Rank.TWO)
    }

    @Test
    fun `카드를 지급해서 플레이어의 턴이 종료되면 다음 플레이어로 넘어간다`() {
        val players = Players.from("black", "jack")
        val deck = StubDeck.from(Rank.ACE, Rank.TWO, Rank.KING, Rank.THREE)

        players.dealRoundOfCardsFrom(deck)
        players.dealRoundOfCardsFrom(deck)
        // black: A, K (블랙잭)
        // jack:  2, 3

        players.currentPlayer shouldBe players[1]
    }

    @Test
    fun `모든 플레이어들이 턴 종료하면 종료 상태이다`() {
        val players =
            playersOf(
                createStandingPlayer("black"),
                createBustedPlayer("jack"),
            )
        // black: 스탠드
        // jack:  바스트
        players.isDone shouldBe true
    }

    @Test
    fun `모든 플레이어들이 종료하기 전까지는 종료 상태가 아니다`() {
        val deck = StubDeck.from(Rank.TWO, Rank.THREE)
        val players =
            playersOf(
                createBustedPlayer("black"),
                createPlayer(deck, "jack"),
            )
        // black: 바스트
        // jack:  2, 3
        players.isDone shouldBe false
    }

    @Test
    fun `현재 플레이어가 힛을 한다`() {
        val deck = StubDeck.from(Rank.ACE, Rank.TWO, Rank.THREE, Rank.FOUR, Rank.FIVE)
        val players = createPlayers(listOf("black", "jack"), deck)
        // black: A, 3
        // jack:  2, 4

        players.hit(deck)
        // black: A, 3, 5

        players[0].hand.cards shouldContainExactly
            listOf(
                Card(DUMMY_SUIT, Rank.ACE),
                Card(DUMMY_SUIT, Rank.THREE),
                Card(DUMMY_SUIT, Rank.FIVE),
            )
    }

    @Test
    fun `연속해서 힛 할 있다`() {
        val deck = StubDeck.from(Rank.ACE, Rank.TWO, Rank.THREE, Rank.FOUR, Rank.FIVE, Rank.SIX)
        val players = createPlayers(listOf("black", "jack"), deck)
        // black: A, 3
        // jack:  2, 4

        players.hit(deck)
        // black: A, 3, 5
        players.hit(deck)
        // black: A, 3, 5, 6

        players[0].hand.cards shouldContainExactly
            listOf(
                Card(DUMMY_SUIT, Rank.ACE),
                Card(DUMMY_SUIT, Rank.THREE),
                Card(DUMMY_SUIT, Rank.FIVE),
                Card(DUMMY_SUIT, Rank.SIX),
            )
    }

    @Test
    fun `힛을 해서 버스트 상태가 되면 다음 플레이어로 넘어간다`() {
        val deck = StubDeck.from(Rank.KING, Rank.TWO, Rank.QUEEN, Rank.FOUR, Rank.JACK)
        val players = createPlayers(listOf("black", "jack"), deck)
        // black: K, Q
        // jack:  2, 4

        players.hit(deck)
        // black: K, Q, J (버스트)

        players.currentPlayer shouldBe players[1]
    }

    @Test
    fun `게임이 종료되면 힛할 수 없다`() {
        val deck = StubDeck.from(Rank.KING, Rank.QUEEN, Rank.JACK, Rank.TEN, Rank.NINE, Rank.EIGHT)
        val players = createPlayers(listOf("black", "jack"), deck)
        // black: K, J
        // jack:  Q, 10
        players.hit(deck)
        // black: K, J, 9 (버스트)
        players.hit(deck)
        // jack:  Q, 10, 8 (버스트)

        assertThrows<IllegalStateException> { players.hit(deck) }
    }

    @Test
    fun `힛해서 21점이 되면 플레이어의 턴이 종료된다`() {
        val deck = StubDeck.from(Rank.FIVE, Rank.TWO, Rank.SEVEN, Rank.THREE, Rank.NINE)
        val players = createPlayers(listOf("black", "jack"), deck)
        // black: 5, 7
        // jack:  2, 3

        players.hit(deck)
        // 5 + 7 + 9 = 21

        players.currentPlayer shouldBe players[1]
    }

    @Test
    fun `현재 플레이어가 스탠드할 수 있다`() {
        val deck = StubDeck.from(Rank.ACE, Rank.TWO, Rank.THREE, Rank.FOUR)
        val players = createPlayers(listOf("black", "jack"), deck)
        // black: A, 3
        // jack:  2, 4

        players.stand()
        // black: A, 3 (스탠드)

        players[0].state.shouldBeInstanceOf<Stand>()
    }

    @Test
    fun `스탣드하면 다음 플레이어로 차례가 넘어간다`() {
        val deck = StubDeck.from(Rank.ACE, Rank.TWO, Rank.THREE, Rank.FOUR)
        val players = createPlayers(listOf("black", "jack"), deck)
        // black: A, 3
        // jack:  2, 4

        players.stand()

        players.currentPlayer shouldBe players[1]
    }

    @Test
    fun `게임이 종료된 상태에서 스탠드할 수 없다`() {
        val deck = StubDeck.from(Rank.ACE, Rank.TWO, Rank.THREE, Rank.FOUR)
        val players = createPlayers(listOf("black", "jack"), deck)
        // black: A, 3
        // jack:  2, 4

        players.stand()
        players.stand()

        assertThrows<IllegalStateException> { players.stand() }
    }

    @Test
    fun `딜러가 블랙잭인 경우 각 플레이어들의 턴을 종료한다`() {
        val players =
            createPlayers(
                listOf("black", "jack"),
                StubDeck.from(Rank.TWO, Rank.THREE, Rank.FOUR, Rank.FIVE),
            )

        players.dealerDealtBlackjack()

        players.isDone shouldBe true
        players[0].state.shouldBeInstanceOf<Stand>()
        players[1].state.shouldBeInstanceOf<Stand>()
    }

    @Test
    fun `플레이어의 결과가 미정인지 리턴한다`() {
        val deck = StubDeck.from(Rank.ACE, Rank.TWO, Rank.KING, Rank.FOUR)
        val players =
            createPlayers(listOf("black", "jack"), deck)
                .apply { stand() }
        // black: A, K (블랙잭)
        // jack:  2, 4 (스탠드)
        players.isOutcomeUnknown shouldBe true
    }

    @Test
    fun `베팅을 걸 수 있다`() {
        val players = Players.from("black", "jack", "game")
        val bets = listOf(Bet(1_000L), Bet(2_000L), Bet(3_000L))

        players.placeBets(bets)

        players.roster.map { it.bet } shouldBe bets
    }

    @Test
    fun `베팅 숫자가 플레이어들의 숫자와 같아야 한다`() {
        val players = Players.from("black", "jack")
        val bets = listOf(Bet(1_000L))
        assertThrows<IllegalArgumentException> { players.placeBets(bets) }
    }

    @Test
    fun `종료 전에 결과를 요청할 수 없다`() {
        val players =
            Players.from("black", "jack")
        players.placeBets(
            listOf(
                Bet(1_000L),
                Bet(2_000L),
            ),
        )
        val dealer = Dealer()
        assertThrows<IllegalStateException> { players.results(dealer) }
    }

    @Test
    fun `결과를 리턴한다`() {
        val deck =
            StubDeck.from(
                Rank.ACE,
                Rank.QUEEN,
                Rank.JACK,
                Rank.KING,
                Rank.FOUR,
                Rank.EIGHT,
                Rank.TEN,
                Rank.SIX,
                Rank.TWO,
            )
        val players =
            createPlayers(listOf("black", "jack", "game"), deck)
                .apply {
                    stand()
                    stand()
                }
        // black: A, K = 21 (블랙잭)
        // jack:  Q, 4 = 14 (스탠드)
        // game:  J, 8 = 18 (스탠드)
        players.placeBets(
            listOf(
                Bet(1_000L),
                Bet(2_000L),
                Bet(3_000L),
            ),
        )
        val dealer =
            Dealer().apply {
                drawFrom(deck)
                drawFrom(deck)
                drawFrom(deck)
            }
        // dealer: 10, 6, 2 = 18
        val expected =
            listOf(
                PlayerResult("black", Bet(1_000L), PlayerOutcome.BLACKJACK),
                PlayerResult("jack", Bet(2_000L), PlayerOutcome.LOSE),
                PlayerResult("game", Bet(3_000L), PlayerOutcome.DRAW),
            )

        val results = players.results(dealer)

        results shouldBe expected
    }
}
