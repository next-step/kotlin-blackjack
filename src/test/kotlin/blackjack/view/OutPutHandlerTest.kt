package blackjack.view

import blackjack.business.Card
import blackjack.business.Player
import blackjack.business.PlayerCards
import blackjack.business.Rank
import blackjack.business.Suit
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class OutPutHandlerTest {

    private val outputStreamCaptor: ByteArrayOutputStream = ByteArrayOutputStream()

    @BeforeEach
    fun setUp() {
        System.setOut(PrintStream(outputStreamCaptor))
    }

    @Test
    fun `플래이어를 출력한다`() {
        // given
        val playerCards = PlayerCards()
        playerCards.add(Card(Suit.SPADE, Rank.ACE))
        playerCards.add(Card(Suit.SPADE, Rank.EIGHT))
        val player = Player("test", playerCards)

        // when
        OutputHandler.printPlayer(player)

        // then
        outputStreamCaptor.toString().trim() shouldBe "test카드: A스페이드, 8스페이드"
    }
}
