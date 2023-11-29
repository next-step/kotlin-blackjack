package blackJack.domain.player

import blackJack.domain.card.CardDeck
import blackJack.domain.card.Cards
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ParticipantsTest {

    private lateinit var cardDeck: Cards

    @BeforeEach
    fun setUp() {
        cardDeck = CardDeck.createShuffledDeck()
    }

    @Test
    fun `리스트로 플레이어 이름을 넘기면 플레이어와 딜러가 생성된다`() {
        val playerList = listOf("Player1", "Player2")
        val participants = Participants.createParticipants(playerList)

        assertEquals(2, participants.players.players.size)
        assertEquals("Player1", participants.players.players[0].name)
        assertEquals("Player2", participants.players.players[1].name)
        assertEquals("딜러", participants.dealer.name)
    }

    @Test
    fun `딜러와 플레이어들에게 2장씩 카드를 나눠준다`() {
        val playerList = listOf("Player1", "Player2")
        val participants = Participants.createParticipants(playerList)

        participants.receiveInitialCards { cardDeck.initialCards() }

        assertEquals(2, participants.players.players[0].cards.cardSize)
        assertEquals(2, participants.players.players[1].cards.cardSize)
        assertEquals(2, participants.dealer.cards.cardSize)
    }
}
