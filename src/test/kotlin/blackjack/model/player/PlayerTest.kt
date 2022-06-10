package blackjack.model.player

import blackjack.model.CardDistributor
import blackjack.model.card.Card
import blackjack.model.card.CardShape
import blackjack.model.card.Denomination
import blackjack.model.card.State
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class PlayerTest {

    private lateinit var alwaysHitDecisionMaker: HitDecisionMaker
    private lateinit var sequentialCardDistributor: CardDistributor

    @BeforeEach
    fun setUp() {
        this.alwaysHitDecisionMaker = object : HitDecisionMaker {
            override fun shouldHit(player: Player) = true
        }

        this.sequentialCardDistributor = object : CardDistributor {
            private var offset = 0
            override fun resetCardSet() {
                this.offset = 0
            }

            override fun giveCardsTo(recipient: CardRecipient, count: Int) {
                repeat(count) { recipient.addCard(Card.of(offset++)) }
            }
        }
    }

    @Test
    fun `player add Card Test`() {

        // given
        val cardAceClubs = Card.of(Denomination.ACE, CardShape.CLUBS)
        val scoreOfAce = 11

        // when
        val player = Player.Guest("aa", alwaysHitDecisionMaker)
        if (player.canHit) {
            player.addCard(cardAceClubs)
        }

        // then
        assertAll(
            { assertThat(player.cards).containsOnly(cardAceClubs) },
            { assertThat(player.cardCount).isEqualTo(1) },
            { assertThat(player.state).isInstanceOf(State.Running::class.java) },
            { assertThat(player.state.finalScore).isEqualTo(scoreOfAce) }
        )
    }

    @Test
    fun `player clear Card Test`() {

        // given
        val cardAceClubs = Card.of(Denomination.ACE, CardShape.CLUBS)
        val player = Player.Guest("aa", alwaysHitDecisionMaker)
        if (player.canHit) {
            player.addCard(cardAceClubs)
        }

        // when
        player.clearCard()

        // then
        assertAll(
            { assertThat(player.cardCount).isEqualTo(0) },
            { assertThat(player.state).isInstanceOf(State.Running::class.java) },
            { assertThat(player.state.finalScore).isEqualTo(0) }
        )
    }

    @Test
    fun `player hit Test`() {

        // given
        val player = Player.Guest("aa", alwaysHitDecisionMaker)
        val cardDistributor = sequentialCardDistributor.apply {
            this.resetCardSet()
        }

        // when :   기대 카드번호:  A,A,A,A 2,2,2,2 3,3,3 11장
        //          기대 상태 : 21점 BlackJack
        player.hitWhileWants(cardDistributor)
        val expectedCardCount = 11
        val expectedState = State.BlackJack()

        // then
        assertAll(
            { assertThat(player.cardCount).isEqualTo(expectedCardCount) },
            { assertThat(player.state).isEqualTo(expectedState) }
        )
    }
}
