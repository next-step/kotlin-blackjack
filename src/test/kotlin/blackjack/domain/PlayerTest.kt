package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
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

        player.hand[0] shouldBe Card.of(StubDeck.DUMMY_SUIT, Rank.ACE)
    }

    @Test
    fun `두 장의 카드를 받아서 블랙젝인 경우, 턴이 종료 된다`() {
        val player = Player("jack")
        val deck = StubDeck.from(Rank.ACE, Rank.KING)

        player.initialDrawFrom(deck)
        player.initialDrawFrom(deck)

        player.isDone shouldBe true
        player.reasonDone shouldBe PlayerReasonDone.BLACKJACK
    }

    @Test
    fun `힛을 하여 덱에서 카드를 뽑을 수 있다`() {
        val player = Player("jack")
        val deck = StubDeck.from(Rank.TWO)

        player.hit(deck)

        player.hand[0] shouldBe Card.of(StubDeck.DUMMY_SUIT, Rank.TWO)
    }

    @ParameterizedTest
    @MethodSource("donePlayers")
    fun `턴이 종료된 경우, 힛을 할 수 없다`(player: Player) {
        val deck = StubDeck.from(Rank.TWO, Rank.THREE)
        assertThrows<IllegalStateException> { player.hit(deck) }
    }

    @Test
    fun `힛을 하여 21점을 넘으면 버스트된다`() {
        val hand =
            Hand.from(
                Card.of(Suit.CLUBS, Rank.KING),
                Card.of(Suit.CLUBS, Rank.QUEEN),
            )
        val player = Player("jack", hand)
        val deck = StubDeck.from(Rank.JACK)

        player.hit(deck)

        player.isDone shouldBe true
        player.reasonDone shouldBe PlayerReasonDone.BUSTED
    }

    @Test
    fun `스탠드를 하면 턴이 종료된다`() {
        val player = Player("jack")

        player.stand()

        player.isDone shouldBe true
        player.reasonDone shouldBe PlayerReasonDone.STANDS
    }

    @ParameterizedTest
    @MethodSource("donePlayers")
    fun `이미 턴이 끝난 상태에서는 스탠드 할 수 없다`(player: Player) {
        assertThrows<IllegalStateException> { player.stand() }
    }

    @Test
    fun `버스트되었는지 리턴한다`() {
        createBustedPlayer().isBusted shouldBe true
        Player("jack").isBusted shouldBe false
    }

    companion object {
        @JvmStatic
        fun donePlayers() =
            listOf(
                createBlackjackPlayer(),
                createBustedPlayer(),
                createStandingPlayer(),
            )

        private fun createBlackjackPlayer(): Player {
            val deck = StubDeck.from(Rank.ACE, Rank.KING)
            return Player("jack").apply {
                initialDrawFrom(deck)
                initialDrawFrom(deck)
            }
        }

        private fun createBustedPlayer(): Player {
            val deck = StubDeck.from(Rank.KING, Rank.QUEEN, Rank.JACK)
            return Player("jack").apply {
                initialDrawFrom(deck)
                initialDrawFrom(deck)
                hit(deck)
            }
        }

        private fun createStandingPlayer(): Player =
            Player("jack").apply {
                stand()
            }
    }
}
