package blackJack.domain

import blackJack.domain.Status.BLACKJACK
import blackJack.domain.Status.BUST
import blackJack.domain.Status.HIT
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
    fun `Player 이름을 입력하면 정상적으로 카드를 가져 온다`() {
        val name = "pobi"
        val player = Player.createPlayer(name, dealer)
        assertThat(player.name).isEqualTo(name)
        assertThat(player.cards.cards.size).isEqualTo(2)
    }

    @Test
    fun `Status 가 HIT 이고 Answer 가 'y' 이면 카드를 계속해서 추가가 가능하다`() {
        val player = Player("pobi", dealer.initialCards(), HIT)
        player.addCard(dealer, "y")
        assertEquals(3, player.cards.cards.size)
    }

    @Test
    fun `Answer 가 'n' 이면 카드를 계속해서 추가가 되면 안된다`() {
        val player = Player("pobi", dealer.initialCards(), HIT)
        player.addCard(dealer, "n")
        assertEquals(2, player.cards.cards.size)
    }

    @Test
    fun `Status 가 BUST 이고 Answer 가 'y' 이면 카드를 계속해서 추가가 불가능하다`() {
        val player = Player("pobi", dealer.initialCards(), BUST)
        assertThrows<IllegalArgumentException> {
            player.addCard(dealer, "y")
        }
    }

    @Test
    fun `Status 가 BLACKJACK 이고 Answer 가 'y' 이면 카드를 계속해서 추가가 불가능하다`() {
        val player = Player("pobi", dealer.initialCards(), BLACKJACK)
        assertThrows<IllegalArgumentException> {
            player.addCard(dealer, "y")
        }
    }
}
