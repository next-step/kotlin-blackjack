package blackjack.view

import blackjack.business.Card
import blackjack.business.Player
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
    fun `플래이어의 카드를 출력한다`() {
        // given
        val playerCards: MutableList<Card> = mutableListOf()
        playerCards.add(Card(Suit.SPADE, Rank.ACE))
        playerCards.add(Card(Suit.SPADE, Rank.EIGHT))
        val player = Player("test", playerCards)

        // when
        OutputHandler.printPlayer(player)

        // then
        outputStreamCaptor.toString().trim() shouldBe "test카드: A스페이드, 8스페이드"
    }

    @Test
    fun `플래이어의 결과를 출력한다`() {
        // given
        val playerCards: MutableList<Card> = mutableListOf()
        playerCards.add(Card(Suit.SPADE, Rank.ACE))
        playerCards.add(Card(Suit.SPADE, Rank.EIGHT))
        val player = Player("test", playerCards)

        // when
        OutputHandler.printResult(player)

        // then
        outputStreamCaptor.toString().trim() shouldBe "test카드: A스페이드, 8스페이드 - 결과: 19"
    }
}
