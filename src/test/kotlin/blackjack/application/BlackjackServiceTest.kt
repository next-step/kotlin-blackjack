package blackjack.application

import blackjack.domain.Rank
import blackjack.domain.StubDeck
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

@Suppress("NonAsciiCharacters")
class BlackjackServiceTest {
    @Test
    fun `게임을 초기화한다`() {
        val sut = BlackjackService()
        val names = listOf("black", "jack")
        val deck = StubDeck.from(Rank.ACE, Rank.TWO, Rank.THREE, Rank.KING, Rank.QUEEN, Rank.JACK)

        val game = sut.initializeGame(names, deck)

        game.players[0].value shouldBe 21
        game.players[1].value shouldBe 12
        game.dealer.value shouldBe 13
    }
}
