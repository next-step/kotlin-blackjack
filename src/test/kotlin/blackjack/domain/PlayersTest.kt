package blackjack.domain

import blackjack.domain.StubDeck.Companion.DUMMY_SUIT
import blackjack.support.Fixtures.createBustedPlayer
import blackjack.support.Fixtures.createStandingPlayer
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
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

        players[0].hand[0] shouldBe Card(DUMMY_SUIT, Rank.ACE)
        players[1].hand[0] shouldBe Card(DUMMY_SUIT, Rank.TWO)
    }

    @Test
    fun `카드를 지급해서 플레이어의 턴이 종료되면 다음 플레이어로 넘어간다`() {
        val players = Players.from("black", "jack")
        val deck = StubDeck.from(Rank.ACE, Rank.TWO, Rank.KING, Rank.THREE)

        players.dealRoundOfCardsFrom(deck)
        players.dealRoundOfCardsFrom(deck)

        players.currentPlayer shouldBe players[1]
    }

    @Test
    fun `모든 플레이어들이 턴 종료하면 종료 상태이다`() {
        val players = Players(createStandingPlayer("black"), createBustedPlayer("jack"))
        players.isDone shouldBe true
    }

    @Test
    fun `모든 플레이어들이 종료하기 전까지는 종료 상태가 아니다`() {
        val deck = StubDeck.from(Rank.TWO, Rank.THREE)
        val players =
            Players(
                createBustedPlayer("black"),
                Player("jack").apply {
                    initialDrawFrom(deck)
                    initialDrawFrom(deck)
                },
            )

        players.isDone shouldBe false
    }

    @Test
    fun `현재 플레이어가 힛을 한다`() {
        val deck = StubDeck.from(Rank.ACE, Rank.TWO, Rank.THREE, Rank.FOUR, Rank.FIVE)
        val players = createPlayersFrom(listOf("black", "jack"), deck)

        players.hit(deck)

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
        val players = createPlayersFrom(listOf("black", "jack"), deck)

        players.hit(deck)
        players.hit(deck)

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
        val players = createPlayersFrom(listOf("black", "jack"), deck)

        players.hit(deck)

        players.currentPlayer shouldBe players[1]
    }

    @Test
    fun `게임이 종료되면 힛할 수 없다`() {
        val deck = StubDeck.from(Rank.KING, Rank.QUEEN, Rank.JACK, Rank.TEN, Rank.NINE, Rank.EIGHT)
        val players = createPlayersFrom(listOf("black", "jack"), deck)
        players.hit(deck)
        players.hit(deck)

        assertThrows<IllegalStateException> { players.hit(deck) }
    }

    @Test
    fun `현재 플레이어가 스탠드할 수 있다`() {
        val deck = StubDeck.from(Rank.ACE, Rank.TWO, Rank.THREE, Rank.FOUR, Rank.FIVE)
        val players = createPlayersFrom(listOf("black", "jack"), deck)

        players.stand()

        players[0].reasonDone shouldBe PlayerReasonDone.PLAYER_STANDS
    }

    @Test
    fun `스탣드하면 다음 플레이어로 차례가 넘어간다`() {
        val deck = StubDeck.from(Rank.ACE, Rank.TWO, Rank.THREE, Rank.FOUR, Rank.FIVE)
        val players = createPlayersFrom(listOf("black", "jack"), deck)

        players.stand()

        players.currentPlayer shouldBe players[1]
    }

    @Test
    fun `게임이 종료된 상태에서 스탠드할 수 없다`() {
        val deck = StubDeck.from(Rank.ACE, Rank.TWO, Rank.THREE, Rank.FOUR, Rank.FIVE)
        val players = createPlayersFrom(listOf("black", "jack"), deck)

        players.stand()
        players.stand()

        assertThrows<IllegalStateException> { players.stand() }
    }

    @Test
    fun `딜러가 블랙잭인 경우 각 플레이어들의 턴을 종료한다`() {
        val players = Players.from("black", "jack")

        players.dealerDealtBlackjack()

        players.isDone shouldBe true
        players[0].reasonDone shouldBe PlayerReasonDone.DEALER_DEALT_BLACKJACK
        players[1].reasonDone shouldBe PlayerReasonDone.DEALER_DEALT_BLACKJACK
    }

    @Test
    fun `플레이어의 결과가 미정인지 리턴한다`() {
        val deck = StubDeck.from(Rank.ACE, Rank.TWO, Rank.KING, Rank.FOUR)
        val players = createPlayersFrom(listOf("black", "jack"), deck).apply { stand() }
        players.isOutcomeUnknown shouldBe true
    }

    private fun createPlayersFrom(
        names: List<String>,
        deck: Deck,
    ): Players =
        Players.from(names).apply {
            dealRoundOfCardsFrom(deck)
            dealRoundOfCardsFrom(deck)
        }
}
