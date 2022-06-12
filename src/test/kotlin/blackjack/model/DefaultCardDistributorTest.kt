package blackjack.model

import blackjack.model.card.Card
import blackjack.model.player.CardRecipient
import blackjack.model.player.HitDecisionMaker
import blackjack.model.player.Player
import blackjack.model.player.Players.Companion.toPlayers
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class DefaultCardDistributorTest {

    @Test
    fun `카드 배분 테스트+더미플레이어`() {

        // Given
        val dummyPlayer = object : CardRecipient {
            val cardList = mutableListOf<Card>()
            override fun addCard(card: Card) {
                cardList.add(card)
            }
        }

        val defaultCardDistributor = DefaultCardDistributor().apply {
            this.resetCardSet()
        }
        val countOfCardToDistribute = 2

        // When
        defaultCardDistributor.giveCardsTo(dummyPlayer, countOfCardToDistribute)

        // Then
        assertAll(
            { assertThat(dummyPlayer.cardList).hasSize(countOfCardToDistribute) },
            { assertThat(dummyPlayer.cardList.distinct()).hasSize(countOfCardToDistribute) } // 중복없이
        )
    }

    @Test
    fun `카드 배분 테스트+플레이어`() {

        // Given
        val countOfCardToDistribute = 3
        val dummyHitDecisionMaker = HitDecisionMaker { player, _ ->
            (player.cardCount < countOfCardToDistribute) // 3장까지 카드를 받을 수 있도록 함.
        }

        val player1 = Player.Guest("김", dummyHitDecisionMaker)
        val player2 = Player.Guest("이", dummyHitDecisionMaker)
        val players = listOf(player1, player2).toPlayers()

        val defaultCardDistributor = DefaultCardDistributor().apply {
            this.resetCardSet()
        }

        // When : hit 할 수 있을 말큼 hit
        while (players.find { it.canHit(defaultCardDistributor) } != null) {
            defaultCardDistributor.giveCardsTo(players, 1)
        }

        // Then
        assertAll(
            { assertThat(player1.cards).hasSize(countOfCardToDistribute) },
            { assertThat(player1.cards.distinct()).hasSize(countOfCardToDistribute) }, // 중복없이

            { assertThat(player2.cards).hasSize(countOfCardToDistribute) },
            { assertThat(player2.cards.distinct()).hasSize(countOfCardToDistribute) } // 중복없이
        )
    }
}
