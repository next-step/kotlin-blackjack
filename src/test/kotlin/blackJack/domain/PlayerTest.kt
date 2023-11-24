package blackJack.domain

import blackJack.domain.Rank.KING
import blackJack.domain.Rank.QUEEN
import blackJack.domain.Status.BLACKJACK
import blackJack.domain.Status.BUST
import blackJack.domain.Status.HIT
import blackJack.domain.Suit.DIAMOND
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PlayerTest {

    private lateinit var cardDeck: Cards
    private lateinit var dealer: Dealer

    @BeforeEach
    fun setUp() {
        cardDeck = CardDeck.createShuffledDeck()
        dealer = Dealer(cardDeck)
    }

    @Test
    fun `쉼표를 기준으로 Player 들이 구분 되어 리스트로 반환된다`() {
        val inputNames = "pobi, jason"
        val names = Player.splitNames(inputNames)
        assertThat(names).containsExactly("pobi", "jason")
    }

    @Test
    fun `이름을 입력하면 정상적으로 Player 가 만들어 진다`() {
        val name = "pobi"
        val player = Player.createPlayer(name)
        assertThat(player.name).isEqualTo(name)
    }

    @Test
    fun `Status 가 HIT 이고 Answer 가 'y' 이면 카드를 계속해서 추가가 가능하다`() {
        val player = Player("pobi", dealer.initialCards(), HIT)
        val card = dealer.drawCard()
        player.addCard(card)
        assertEquals(3, player.cards.cards.size)
    }

    @Test
    fun `Status 가 BUST 이고 Answer 가 'y' 이면 카드를 계속해서 추가가 불가능하다`() {
        val player = Player("pobi", dealer.initialCards(), BUST)
        assertThrows<IllegalArgumentException> {
            val card = dealer.drawCard()
            player.addCard(card)
        }
    }

    @Test
    fun `Status 가 BLACKJACK 이고 Answer 가 'y' 이면 카드를 계속해서 추가가 불가능하다`() {
        val player = Player("pobi", dealer.initialCards(), BLACKJACK)
        assertThrows<IllegalArgumentException> {
            val card = dealer.drawCard()
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
        val player = Player(name = "pobi", status = HIT)
        player.gameStop()
        assertEquals(Status.STAND, player.status)
    }
}
