package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardDenomination
import blackjack.domain.card.CardPattern
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PlayerTest {
    @Test
    fun `Ace 가 2장일때 Card 의 합은 12`() {
        val player = Player("one").player

        player.addCard(Card(CardPattern.HEART, CardDenomination.ACE))
        player.addCard(Card(CardPattern.DIAMOND, CardDenomination.ACE))

        val actual = player.getCardSum()

        assertEquals(12, actual)
    }

    @Test
    fun `Ace, King 일때 Card 의 합은 21`() {
        val player = Player("one").player

        player.addCard(Card(CardPattern.HEART, CardDenomination.ACE))
        player.addCard(Card(CardPattern.DIAMOND, CardDenomination.KING))

        val actual = player.getCardSum()

        assertEquals(21, actual)
    }

    @Test
    fun `9, ACE, ACE 일때 Card 의 합은 21`() {
        val player = Player("one").player

        player.addCard(Card(CardPattern.HEART, CardDenomination.NINE))
        player.addCard(Card(CardPattern.HEART, CardDenomination.ACE))
        player.addCard(Card(CardPattern.DIAMOND, CardDenomination.ACE))

        val actual = player.getCardSum()

        assertEquals(21, actual)
    }

    @ValueSource(strings = ["", " "])
    @ParameterizedTest
    fun `Player 이름 빈값, 공백일때 exception`(name: String) {
        assertThrows<IllegalArgumentException> {
            Player(name)
        }
    }

    @Test
    fun `카드를 추가하면 카드가 1장 추가된다`() {
        val player = Player("one").player

        player.addCard(Card(CardPattern.HEART, CardDenomination.NINE))

        val actual = player.cards

        assertEquals(1, actual.size)
    }

    @Test
    fun `승리로 값을 변경시 승리로 변경된다`() {
        val player = Player("one")

        player.determineWinOrLose(ResultStatus.WIN)

        assertEquals(ResultStatus.WIN, player.resultStatus)
    }

    @Test
    fun `패배로 값을 변경시 패배로 변경된다`() {
        val player = Player("one")

        player.determineWinOrLose(ResultStatus.LOSE)

        assertEquals(ResultStatus.LOSE, player.resultStatus)
    }

    @Test
    fun `무승부로 값을 변경시 무승부로 변경된다`() {
        val player = Player("one")

        player.determineWinOrLose(ResultStatus.TIE)

        assertEquals(ResultStatus.TIE, player.resultStatus)
    }
}
