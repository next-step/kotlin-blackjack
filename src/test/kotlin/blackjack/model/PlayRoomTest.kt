package blackjack.model

import blackjack.model.card.Card
import blackjack.model.card.State
import blackjack.model.player.CardRecipient
import blackjack.model.player.HitDecisionMaker
import blackjack.model.player.Player
import blackjack.model.player.Players.Companion.toPlayers
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class PlayRoomTest {

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
    fun `playRoom startGame Test`() {

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

        val initialCardCountOfEachPlayer = 2
        val playRoom = PlayRoom(cardDistributor, players, initialCardCountOfEachPlayer)

        // when
        playRoom.startNewGame()

        // then
        assertAll(
            {
                Assertions.assertThat(players.map { it.cardCount }.distinct())
                    .containsOnly(initialCardCountOfEachPlayer)
            },
            { Assertions.assertThat(players[0].state).isEqualTo(State.Running(12)) },
            { Assertions.assertThat(players[1].state).isEqualTo(State.Running(12)) },
            { Assertions.assertThat(players[2].state).isEqualTo(State.Running(4)) },
            { Assertions.assertThat(players[3].state).isEqualTo(State.Running(4)) }
        )
    }
}
