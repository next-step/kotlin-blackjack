package blackjack.model.player

import blackjack.model.CardDistributor
import blackjack.model.card.Card
import blackjack.model.card.State
import blackjack.model.player.Players.Companion.toPlayers
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class PlayersTest {

    private lateinit var alwaysHitDecisionMaker: HitDecisionMaker
    private lateinit var sequentialCardDistributor: CardDistributor

    @BeforeEach
    fun setUp() {
        this.alwaysHitDecisionMaker = object : HitDecisionMaker {
            override fun doYouWantToHit(player: Player) = true
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
    fun `players clear Card Test`() {

        // given
        val cardDistributor = sequentialCardDistributor.apply {
            this.resetCardSet()
        }
        val players = listOf(
            Player("a", alwaysHitDecisionMaker),
            Player("b", alwaysHitDecisionMaker),
            Player("c", alwaysHitDecisionMaker),
            Player("d", alwaysHitDecisionMaker),
        ).toPlayers()
            .onEach { it.hitWhileWants(cardDistributor = cardDistributor) }

        // when
        players.clearCard()

        // then
        assertAll(
            { Assertions.assertThat(players.blackJackPlayer).isNull() },
            { Assertions.assertThat(players.map { it.cardCount }.distinct()).containsOnly(0) }
        )
    }

    @Test
    fun `players blackJackPlayer Test`() {

        // given
        val players = listOf(Player("aa", alwaysHitDecisionMaker)).toPlayers()
        val cardDistributor = sequentialCardDistributor.apply {
            this.resetCardSet()
        }

        // when :   기대 카드번호:  A,A,A,A 2,2,2,2 3,3,3 11장
        //          기대 상태 : 21점 BlackJack
        players.forEach { player ->
            player.hitWhileWants(cardDistributor = cardDistributor)
        }

        val expectedCardCount = 11
        val expectedState = State.BlackJack()

        // then
        val player = players.blackJackPlayer
        assertAll(
            { Assertions.assertThat(player).isNotNull },
            { Assertions.assertThat(player?.cardCount).isEqualTo(expectedCardCount) },
            { Assertions.assertThat(player?.state).isEqualTo(expectedState) }
        )
    }
}
