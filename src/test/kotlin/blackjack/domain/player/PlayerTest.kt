package blackjack.domain.player

import blackjack.domain.card.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@Suppress("NonAsciiCharacters")
class PlayerTest {

    private val name = PlayerName("aaj")
    private val hitImpossibleHand = Hand(listOf(Card(Symbol.TEN, Type.CLUB), Card(Symbol.ACE, Type.CLUB)))
    private lateinit var deck: Deck
    private lateinit var topOfDeck: Card

    @BeforeEach
    fun setUp() {
        deck = Deck(
            listOf(
                Card(Symbol.TEN, Type.CLUB),
                Card(Symbol.NINE, Type.CLUB),
                Card(Symbol.TWO, Type.CLUB),
                Card(Symbol.ACE, Type.CLUB),
            )
        )
        topOfDeck = deck.cards.first()
    }

    @Test
    fun `Player가 Deck에서 카드를 뽑으면 Hand에 카드가 한 장 추가된다`() {
        val player = Player(name)

        player.hit(deck)

        assertThat(player.hand)
            .usingRecursiveComparison()
            .isEqualTo(Hand(listOf(topOfDeck)))
    }

    @Test
    fun `Player의 Hand가 hit할 수 없으면 카드를 뽑지 못한다`() {
        val player = Player(name, hitImpossibleHand)

        assertThrows<IllegalStateException> {
            player.hit(deck)
        }
    }

    @Test
    fun `대답이 Yes고, 카드를 뽑으면 성공한다`() {
        val player = Player(name)

        val result = player.hitIfYes(deck, PlayerAnswer.YES)

        assertThat(result.success).isTrue
        assertThat(player.hand)
            .usingRecursiveComparison()
            .isEqualTo(Hand(listOf(topOfDeck)))
    }

    @Test
    fun `대답이 Yes지만, 카드를 뽑지 못하면 실패한다`() {
        val player = Player(name, hitImpossibleHand)

        val result = player.hitIfYes(deck, PlayerAnswer.YES)

        assertThat(result.success).isFalse
        assertThat(player.hand)
            .usingRecursiveComparison()
            .isEqualTo(hitImpossibleHand)
    }

    @Test
    fun `대답이 No이면, 카드를 뽑지 않고 실패한다`() {
        val player = Player(name)

        val result = player.hitIfYes(deck, PlayerAnswer.NO)

        assertThat(result.success).isFalse
        assertThat(player.hand)
            .usingRecursiveComparison()
            .isEqualTo(Hand.createEmpty())
    }
}
