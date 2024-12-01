package blackjack.domain

import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@Suppress("NonAsciiCharacters")
class PlayersTest {
    @Test
    fun `이름들로 플레이어들을 생성할 수 있다`() {
        val names = listOf("black", "jack", "game")

        val players = Players.from(names)

        players.roster.map { it.name } shouldContainExactlyInAnyOrder names
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
        val players = Players.from("black", "jack", "game")
        val deck = StubDeck.from(Rank.ACE, Rank.TWO, Rank.THREE)

        players.dealRoundOfCardsFrom(deck)

        players[0].hand[0] shouldBe Card.of(StubDeck.DUMMY_SUIT, Rank.ACE)
        players[1].hand[0] shouldBe Card.of(StubDeck.DUMMY_SUIT, Rank.TWO)
        players[2].hand[0] shouldBe Card.of(StubDeck.DUMMY_SUIT, Rank.THREE)
    }
}
