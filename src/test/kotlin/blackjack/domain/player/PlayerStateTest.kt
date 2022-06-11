package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardSuit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerStateTest {
    @Test
    fun `숫자 합이 21 미만이라면 게임 진행 상태가 된다`() {
        val player = Player("vivian")
        val cardsWithTotalBelowTwentyOne = listOf(
            Card.Ten(CardSuit.CLOVER),
            Card.Ten(CardSuit.HEART)
        )

        cardsWithTotalBelowTwentyOne.forEach { player.addCardToHand(it) }

        assertThat(PlayerState.of(player) is PlayerState.Playing).isTrue
    }

    @Test
    fun `숫자 합이 21 이라면 게임 진행 불가 상태가 된다`() {
        val player = Player("vivian")
        val cardsWithTotalBelowTwentyOne = listOf(
            Card.Ace(CardSuit.CLOVER),
            Card.Ten(CardSuit.HEART)
        )

        cardsWithTotalBelowTwentyOne.forEach { player.addCardToHand(it) }

        assertThat(PlayerState.of(player) is PlayerState.Done).isTrue
    }

    @Test
    fun `숫자 합이 21 초과라면 게임 진행 불가 상태가 된다`() {
        val player = Player("vivian")
        val cardsWithTotalBelowTwentyOne = listOf(
            Card.Ten(CardSuit.CLOVER),
            Card.Ten(CardSuit.HEART),
            Card.Ten(CardSuit.DIAMOND)
        )

        cardsWithTotalBelowTwentyOne.forEach { player.addCardToHand(it) }

        assertThat(PlayerState.of(player) is PlayerState.Done).isTrue
    }
}
