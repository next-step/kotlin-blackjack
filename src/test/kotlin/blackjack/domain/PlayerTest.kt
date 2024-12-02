package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

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
    fun `힛을 하여 덱에서 카드를 뽑을 수 있다`() {
        val player = Player("jack")
        val deck = StubDeck.from(Rank.TWO)

        player.hit(deck)

        player.hand[0] shouldBe Card.of(StubDeck.DUMMY_SUIT, Rank.TWO)
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
    fun `스테이, 스탠드 할 수 있다`() {
        val player = Player("jack")

        player.stand()

        player.isDone shouldBe true
        player.reasonDone shouldBe PlayerReasonDone.STANDS
    }

    @Test
    fun `이미 스탠드해서 턴이 끝난 상태에서는 스탠드 할 수 없다`() {
        val player = Player("jack")
        player.stand()

        assertThrows<IllegalStateException> { player.stand() }
    }

    @Test
    fun `카드를 지급 받는다`() {
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
}
