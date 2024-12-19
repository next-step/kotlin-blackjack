package blackjack.domain

import blackjack.domain.StubDeck.Companion.DUMMY_SUIT
import blackjack.support.Fixtures.createBlackjackPlayer
import blackjack.support.Fixtures.createBustedPlayer
import blackjack.support.Fixtures.createDealer
import blackjack.support.Fixtures.createHand
import blackjack.support.Fixtures.createPlayer
import blackjack.support.Fixtures.createStandingPlayer
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

@Suppress("NonAsciiCharacters")
class PlayerTest {
    @Test
    fun `이름이 빈 문자열인 경우 예외를 던진다`() {
        assertThrows<IllegalArgumentException> { Player("") }
    }

    @Test
    fun `이름을 지정할 수 있다`() {
        val player = Player("jack")
        player.name shouldBe "jack"
    }

    @Test
    fun `플레이어 생성 시 카드가 없다`() {
        val player = Player("jack")
        player.value shouldBe 0
    }

    @Test
    fun `플레이어 생성 시 턴이 종료되지 않았다`() {
        val player = Player("jack")
        player.isDone shouldBe false
    }

    @Test
    fun `초기화 라운드에 카드를 지급 받는다`() {
        val player = Player("jack")
        val deck = StubDeck.from(Rank.ACE)

        player.initialDrawFrom(deck)

        player.hand[0] shouldBe Card(DUMMY_SUIT, Rank.ACE)
    }

    @Test
    fun `두 장의 카드를 받아서 블랙젝인 경우, 턴이 종료 된다`() {
        val player = Player("jack")
        val deck = StubDeck.from(Rank.ACE, Rank.KING)

        player.initialDrawFrom(deck)
        player.initialDrawFrom(deck)

        player.isDone shouldBe true
        player.reasonDone shouldBe PlayerReasonDone.PLAYER_HAS_BLACKJACK
    }

    @Test
    fun `힛을 하여 덱에서 카드를 뽑을 수 있다`() {
        val player = Player("jack")
        val deck = StubDeck.from(Rank.TWO)

        player.hit(deck)

        player.hand[0] shouldBe Card(DUMMY_SUIT, Rank.TWO)
    }

    @ParameterizedTest(name = "{index} 플레이어 상태 = {1}")
    @MethodSource("donePlayers")
    fun `턴이 종료된 경우, 힛을 할 수 없다`(
        player: Player,
        description: String,
    ) {
        val deck = StubDeck.from(Rank.TWO, Rank.THREE)
        assertThrows<IllegalStateException> { player.hit(deck) }
    }

    @Test
    fun `힛을 하여 21점을 넘으면 버스트된다`() {
        val hand = createHand(Rank.KING, Rank.QUEEN)
        val player = Player("jack", hand)
        val deck = StubDeck.from(Rank.JACK)

        player.hit(deck)

        player.isDone shouldBe true
        player.reasonDone shouldBe PlayerReasonDone.PLAYER_BUSTED
    }

    @Test
    fun `힛을 해서 21점이 되었으면 자동으로 스탠드한다`() {
        val deck = StubDeck.from(Rank.FIVE, Rank.SEVEN, Rank.NINE)
        val player = createPlayer(deck)
        // 5, 7

        player.hit(deck)
        // 5 + 7 + 9 = 21

        player.reasonDone shouldBe PlayerReasonDone.PLAYER_STANDS
    }

    @Test
    fun `힛을 해서 21점 미만이면 턴이 종료되지 않았다`() {
        val deck = StubDeck.from(Rank.FIVE, Rank.SEVEN, Rank.EIGHT)
        val player = createPlayer(deck)
        // 5, 7

        player.hit(deck)
        // 5 + 7 + 8 = 20

        player.isDone shouldBe false
    }

    @Test
    fun `스탠드를 하면 턴이 종료된다`() {
        val player = Player("jack")

        player.stand()

        player.isDone shouldBe true
        player.reasonDone shouldBe PlayerReasonDone.PLAYER_STANDS
    }

    @ParameterizedTest(name = "{index} 플레이어 상태 = {1}")
    @MethodSource("donePlayers")
    fun `이미 턴이 끝난 상태에서는 스탠드 할 수 없다`(
        player: Player,
        description: String,
    ) {
        assertThrows<IllegalStateException> { player.stand() }
    }

    @Test
    fun `버스트되었는지 리턴한다`() {
        createBustedPlayer().isBusted shouldBe true
        Player("jack").isBusted shouldBe false
    }

    @Test
    fun `딜러가 블랙잭이라 플레이어 턴이 종료된다`() {
        val player = Player("jack")

        player.dealerDealtBlackjack()

        player.reasonDone shouldBe PlayerReasonDone.DEALER_DEALT_BLACKJACK
    }

    @ParameterizedTest(name = "{index} {3}")
    @MethodSource
    fun `딜러와 비기는지 (푸시) 리턴한다`(
        player: Player,
        dealer: Dealer,
        expected: Boolean,
        description: String,
    ) {
        player.pushes(dealer) shouldBe expected
    }

    @ParameterizedTest(name = "{index} {3}")
    @MethodSource
    fun `딜러를 이기는지 리턴한다`(
        player: Player,
        dealer: Dealer,
        expected: Boolean,
        description: String,
    ) {
        player.beats(dealer) shouldBe expected
    }

    @Test
    fun `베팅을 걸 수 있다`() {
        val player = Player("jack")

        player.placeBet(Bet(1_000L))

        player.bet shouldBe Bet(1_000L)
    }

    @Test
    fun `베팅이 있기 전에 승부 결과를 요청하면 예외를 던진다`() {
        val player =
            createPlayer(StubDeck.from(Rank.FOUR, Rank.FIVE)).apply {
                stand()
            }
        val dealer = createDealer(StubDeck.from(Rank.TWO, Rank.THREE))
        assertThrows<IllegalStateException> { player.result(dealer) }
    }

    @Test
    fun `턴이 끝나기 전에 결과를 요청하면 예외를 던진다`() {
        val player =
            createPlayer(StubDeck.from(Rank.FOUR, Rank.FIVE)).apply {
                placeBet(Bet(10_000L))
            }
        val dealer = createDealer(StubDeck.from(Rank.TWO, Rank.THREE))
        assertThrows<IllegalStateException> { player.result(dealer) }
    }

    @Test
    fun `딜러와의 승부 결과를 리턴한다`() {
        val player =
            createPlayer(StubDeck.from(Rank.FOUR, Rank.FIVE)).apply {
                placeBet(Bet(10_000L))
                stand()
            }
        val dealer = createDealer(StubDeck.from(Rank.TWO, Rank.THREE))

        val expected = PlayerResult("jack", Bet(10_000L), PlayerOutcome.WIN)

        val result = player.result(dealer)

        result shouldBe expected
    }

    companion object {
        @JvmStatic
        fun donePlayers() =
            listOf(
                Arguments.of(createBlackjackPlayer(), "블랙잭"),
                Arguments.of(createBustedPlayer(), "버스트"),
                Arguments.of(createStandingPlayer(), "스탠드"),
            )

        @JvmStatic
        fun `딜러와 비기는지 (푸시) 리턴한다`() =
            listOf(
                Arguments.of(
                    createPlayer(StubDeck.from(Rank.FIVE, Rank.TEN)),
                    createDealer(StubDeck.from(Rank.SEVEN, Rank.EIGHT)),
                    true,
                    "플레이어와 딜러의 점수가 같은 경우",
                ),
                Arguments.of(
                    createPlayer(StubDeck.from(Rank.TWO, Rank.THREE)),
                    createDealer(StubDeck.from(Rank.FOUR, Rank.FIVE)),
                    false,
                    "플레이어 점수가 딜러의 점수보다 낮은 경우",
                ),
                Arguments.of(
                    createPlayer(StubDeck.from(Rank.FOUR, Rank.FIVE)),
                    createDealer(StubDeck.from(Rank.TWO, Rank.THREE)),
                    false,
                    "플레이어 점수가 딜러의 점수보다 높은 경우",
                ),
            )

        @JvmStatic
        fun `딜러를 이기는지 리턴한다`() =
            listOf(
                Arguments.of(
                    createPlayer(StubDeck.from(Rank.FOUR, Rank.FIVE)),
                    createDealer(StubDeck.from(Rank.TWO, Rank.THREE)),
                    true,
                    "플레이어의 점수가 딜러의 점수보다 높은 경우",
                ),
                Arguments.of(
                    createPlayer(StubDeck.from(Rank.TWO, Rank.THREE)),
                    createDealer(StubDeck.from(Rank.FOUR, Rank.FIVE)),
                    false,
                    "플레이어의 점수가 딜러의 점수보다 낮은 경우",
                ),
                Arguments.of(
                    createPlayer(StubDeck.from(Rank.FIVE, Rank.TEN)),
                    createDealer(StubDeck.from(Rank.SEVEN, Rank.EIGHT)),
                    false,
                    "플레이어의 점수가 딜러의 점수와 같은 경우",
                ),
            )
    }
}
