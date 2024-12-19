package blackjack.application

import blackjack.domain.Bet
import blackjack.domain.Rank
import blackjack.domain.StubDeck
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.math.BigDecimal

@Suppress("NonAsciiCharacters")
class BlackjackServiceTest {
    @Test
    fun `게임을 생성한다`() {
        val service = BlackjackService()
        val deck = StubDeck.from(Rank.ACE, Rank.TWO, Rank.THREE, Rank.KING, Rank.QUEEN, Rank.JACK)
        val command =
            CreateGameCommand(
                setOf("black", "jack"),
                listOf(BigDecimal(1_000L), BigDecimal(2_000L)),
                deck,
            )
        // black:  A, K
        // jack:   2, Q
        // dealer: 3, J

        val game = service.createGame(command)

        game.players[0].value shouldBe 21
        game.players[0].bet shouldBe Bet(1_000L)
        game.players[1].value shouldBe 12
        game.players[1].bet shouldBe Bet(2_000L)
        game.dealer.value shouldBe 13
    }
}
