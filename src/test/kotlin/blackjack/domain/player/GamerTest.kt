package blackjack.domain.player

import blackjack.domain.card.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@Suppress("NonAsciiCharacters")
class GamerTest {
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
    fun `Gamer가 Deck에서 카드를 뽑으면 Hand에 카드가 한 장 추가된다`() {
        val gamer = TestGamer(canHit = true)

        gamer.hit(deck)

        assertThat(gamer.hand)
            .usingRecursiveComparison()
            .isEqualTo(Hand(listOf(topOfDeck)))
    }

    @Test
    fun `Gamer의 Hand가 hit할 수 없으면 카드를 뽑지 못한다`() {
        val gamer = TestGamer(canHit = false)

        assertThrows<IllegalStateException> {
            gamer.hit(deck)
        }
    }
}

class TestGamer(private val canHit: Boolean) : Gamer(PlayerName("test"), Hand.createEmpty()) {

    override fun canHit() = canHit

    override fun firstOpenCardsCount(): Int {
        throw UnsupportedOperationException()
    }
}
