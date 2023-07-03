package blackjack

import blackjack.domain.Card
import blackjack.domain.GameCardsSet
import blackjack.domain.Ranks
import blackjack.domain.Suits
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class GameCardsSetTest {
    private lateinit var gameCardsSet: GameCardsSet

    @BeforeEach
    fun setUp() {
        gameCardsSet = GameCardsSet()
    }

    @Test
    fun `게임 카드 세트에서 카드를 뽑을 수 있다`() {
        val draw = gameCardsSet.drawRandomCard()
        assertThat(draw).isInstanceOf(Card::class.java)
    }

    @Test
    fun `게임 카드 세트에서 원하는 카드를 뽑을 수 있다`() {
        val card = Card.of(Ranks.TWO, Suits.HEARTS)
        val drawnCard = gameCardsSet.drawSpecificCard(card)
        assertThat(card).isEqualTo(drawnCard)
    }

    @Test
    fun `게임 카드 세트에서 이미 뽑은 카드를 다시 뽑을 경우 IllegalStateException`() {
        val card = Card.of(Ranks.TWO, Suits.HEARTS)
        val drawnCard = gameCardsSet.drawSpecificCard(card)

        assertThrows<IllegalStateException> {
            val drawnCard2 = gameCardsSet.drawSpecificCard(card)
        }
    }

    @Test
    fun `게임 카드 세트에서 카드를 뽑을 때 남은 카드가 없다면 IllegalStateException`() {
        repeat(52) {
            val draw = gameCardsSet.drawRandomCard()
        }

        assertThrows<IllegalStateException> {
            val draw = gameCardsSet.drawRandomCard()
        }
    }
}
