package blackjack.domain.player

import blackjack.AnswerYesThird
import blackjack.FinishedHand
import blackjack.NotFinishHand
import blackjack.domain.card.*
import blackjack.domain.game.Hand
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
    fun `Gamer의 Hand가 finish 되었다면면 카드를 뽑지 못한다`() {
        val gamer = TestGamer(FinishedHand)

        assertThrows<IllegalStateException> {
            gamer.hit(deck)
        }
    }

    @Test
    fun `카드를 뽑을 수 있다면 대답이 No일때까지 카드를 뽑는다`() {
        val hand = NotFinishHand()
        val player = TestGamer(hand)

        player.hitWhileWant(deck, AnswerYesThird())

        assertThat(hand.drawCount).isEqualTo(AnswerYesThird.YES_COUNT)
    }

    @Test
    fun `대답이 No이면, 카드를 뽑지 않는다`() {
        val player = TestGamer(NotFinishHand())

        player.hitWhileWant(deck) { PlayerAnswer.NO }

        assertThat(player.hand.cards)
            .usingRecursiveComparison()
            .isEqualTo(Cards.createEmpty())
    }
}

class TestGamer(hand: Hand) : Gamer(PlayerName("test"), hand) {

    override fun wantHit(answerProvider: AnswerProvider) = answerProvider.getAnswer(this).hit

    override val afterHitCallBack: AfterHitWhileCallback? = null

    override fun firstOpenCardsCount(): Int = Int.MAX_VALUE
}
