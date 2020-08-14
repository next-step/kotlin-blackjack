package blackjack.domain

import blackjack.view.REPLY_HIT
import blackjack.view.REPLY_STAND
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class PlayerTest {
    private lateinit var player: Player
    private lateinit var deck: Deck

    @BeforeEach
    fun `set up`() {
        player = Player(name = "mark")
        deck = Deck(setOf(Card(Pair(CardScore.SEVEN, Suit.SPADE))))
    }

    @DisplayName("덱에서 카드를 뽑을 때마다 1장씩 반환되고, 덱이 비어있을 땐 null이 반환된다")
    @Test
    fun `draw a card`() {
        // when
        val cards = player.draw(deck)
        val nullCards = player.draw(deck)

        // then
        assertThat(cards?.size()).isEqualTo(1)
        assertThat(nullCards).isEqualTo(null)
    }

    @Test
    fun `get a chance to draw`() {
        // when
        player.chooseToDraw(REPLY_HIT, deck)
        player.chooseToDraw(REPLY_STAND, deck)

        // then
        assertThat(player.amountOfCards()).isEqualTo(1)
    }

    @Test
    fun `has score more than maximum score`() {
        // when
        val isMoreThanMax = player.hasScoreMoreThanMax()

        // then
        assertFalse(isMoreThanMax)
    }

    @Test
    fun `amount of cards`() {
        // given
        player.draw(deck)

        // when
        val amount = player.amountOfCards()

        // then
        assertThat(amount).isEqualTo(1)
    }

    @Test
    fun `sum of scores`() {
        // given
        player.draw(deck)

        // when
        val sum = player.sumOfScores()

        // then
        assertThat(sum).isEqualTo(7)
    }
}
