package blackjack.domain.player

import blackjack.domain.card.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

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
    fun `Player는 스코어가 블랙잭 스코어보다 작다면 hit할 수 있다`() {
        val underBlackJackHand = Hand(listOf(
            Card(Symbol.TEN, Type.CLUB),
            Card(Symbol.TEN, Type.CLUB),
        ))
        val player = Player(name, underBlackJackHand)

        val result = player.canHit()

        assertThat(result).isTrue
    }

    @Test
    fun `Player는 스코어가 블랙잭 스코어와 같다면 hit할 수 없다`() {
        val blackJackScoreHand = Hand(listOf(
            Card(Symbol.TEN, Type.CLUB),
            Card(Symbol.TEN, Type.CLUB),
            Card(Symbol.ACE, Type.CLUB),
        ))
        val player = Player(name, blackJackScoreHand)

        val result = player.canHit()

        assertThat(result).isFalse
    }

    @Test
    fun `Player는 스코어가 블랙잭 스코어보다 크다면 hit할 수 없다`() {
        val blackJackScoreHand = Hand(listOf(
            Card(Symbol.TEN, Type.CLUB),
            Card(Symbol.TEN, Type.CLUB),
            Card(Symbol.ACE, Type.CLUB),
            Card(Symbol.ACE, Type.CLUB),
        ))
        val player = Player(name, blackJackScoreHand)

        val result = player.canHit()

        assertThat(result).isFalse
    }
}
