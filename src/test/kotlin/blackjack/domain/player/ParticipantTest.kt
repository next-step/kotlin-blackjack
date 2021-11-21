package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardDenomination
import blackjack.domain.card.CardPattern
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ParticipantTest {

    @ValueSource(strings = ["", " "])
    @ParameterizedTest
    fun `참가자 이름 빈값, 공백일때 exception`(name: String) {
        assertThrows<IllegalArgumentException> {
            Player(Participant(""))
        }
    }

    @Test
    fun `카드의 합이 21일때 Blackjack`() {
        val player = Player(Participant("one")).apply {
            addCard(Card(CardPattern.CLOVER, CardDenomination.TEN))
            addCard(Card(CardPattern.CLOVER, CardDenomination.ACE))
        }

        val actual = player.isBlackjack()
        assertTrue(actual)
    }

    @Test
    fun `카드의 합이 21이 아닐때는 Not Blackjack`() {
        val player = Player(Participant("one")).apply {
            addCard(Card(CardPattern.CLOVER, CardDenomination.TEN))
            addCard(Card(CardPattern.HEART, CardDenomination.TEN))
        }

        val actual = player.isBlackjack()
        assertFalse(actual)
    }

    @Test
    fun `수익금에 5000원을 더할시 기존 수익금에 5000 원이 추가된다`() {
        val player = Player(Participant("one"))

        player.addRevenue(5000.0)

        assertEquals(5000.0, player.getRevenue())
    }

    @Test
    fun `수익금에 5000원을 빼면 기존 수익금에 5000 원이 줄어든다`() {
        val player = Player(Participant("one"))

        player.minusRevenue(5000.0)

        assertEquals(-5000.0, player.getRevenue())
    }
}
