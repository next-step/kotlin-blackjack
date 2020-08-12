package blackjack.domain

import blackjack.view.REPLY_RECEIVE
import blackjack.view.REPLY_REJECT
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayerTest() {
    private lateinit var player: Player
    private lateinit var newCard: Card
    private lateinit var cards: List<Card>

    @BeforeEach
    fun `set up`() {
        player = Player(name = "mark")
        newCard = Card(Pair(CardScore.SEVEN, Suit.SPADE))
        cards = player.draw(newCard).toList()
    }

    @Test
    fun `draw a card`() {
        assertThat(cards[0]).isEqualTo(newCard)
    }

    @Test
    fun `get a chance to draw`() {
        // when
        player.getChanceToDraw(REPLY_RECEIVE)
        player.getChanceToDraw(REPLY_REJECT)

        // then
        assertThat(player.amountOfCards()).isEqualTo(2)
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
        // when
        val amount = player.amountOfCards()

        // then
        assertThat(amount).isEqualTo(1)
    }

    @Test
    fun `sum of scores`() {
        // when
        val sum = player.sumOfScores()

        // then
        assertThat(sum).isEqualTo(7)
    }
}
