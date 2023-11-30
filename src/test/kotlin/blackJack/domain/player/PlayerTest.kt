package blackJack.domain.player

import blackJack.domain.card.Card
import blackJack.domain.card.CardDeck
import blackJack.domain.card.Cards
import blackJack.domain.enums.Rank.KING
import blackJack.domain.enums.Rank.QUEEN
import blackJack.domain.enums.Status
import blackJack.domain.enums.Status.BLACKJACK
import blackJack.domain.enums.Status.BUST
import blackJack.domain.enums.Status.HIT
import blackJack.domain.enums.Suit.DIAMOND
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PlayerTest {

    private lateinit var cardDeck: Cards

    @BeforeEach
    fun setUp() {
        cardDeck = CardDeck.createShuffledDeck()
    }

    @Test
    fun `쉼표를 기준으로 Player 들이 구분 되어 리스트로 반환된다`() {
        val inputNames = "pobi, jason"
        val names = Player.splitNames(inputNames)
        assertThat(names).containsExactly("pobi", "jason")
    }

    @Test
    fun `Status 가 HIT 이고 Answer 가 'y' 이면 카드를 계속해서 추가가 가능하다`() {
        val player = Player("pobi")
        player.cards = Cards(listOf())
        player.status = HIT

        val card = cardDeck.drawCard()
        player.addCard(card)
        assertEquals(1, player.cards.cards.size)
    }

    @Test
    fun `Status 가 BUST 이고 Answer 가 'y' 이면 카드를 계속해서 추가가 불가능하다`() {
        val player = Player("pobi")
        player.cards = Cards(listOf())
        player.status = BUST

        assertThrows<IllegalArgumentException> {
            val card = cardDeck.drawCard()
            player.addCard(card)
        }
    }

    @Test
    fun `Status 가 BLACKJACK 이고 Answer 가 'y' 이면 카드를 계속해서 추가가 불가능하다`() {
        val player = Player("pobi")
        player.cards = Cards(listOf())
        player.status = BLACKJACK

        assertThrows<IllegalArgumentException> {
            val card = cardDeck.drawCard()
            player.addCard(card)
        }
    }

    @Test
    fun `player 에게 초기 카드를 전달하면 정상적으로 player 손에 들어가야 한다`() {
        val player = Player("pobi")
        val initialCards = Cards(mutableListOf(Card(DIAMOND, KING), Card(DIAMOND, QUEEN)))

        player.receiveInitialCards(initialCards)

        assertEquals(2, player.cards.cardSize)
        assertEquals(HIT, player.status)
    }

    @Test
    fun `stop() 을 호출하면 status 가 STAND 로 바뀐다`() {
        val player = Player(name = "pobi")
        player.gameStop()
        assertEquals(Status.STAND, player.status)
    }
}
