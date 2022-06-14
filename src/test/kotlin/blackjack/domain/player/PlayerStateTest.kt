package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardSuit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerStateTest {
    @Test
    fun `숫자 합이 21 이하 라면 게임 진행 상태가 된다`() {
        val player = Player("vivian")
        val cardsWithTotalBelowTwentyOne = listOf(
            Card.Ace(CardSuit.CLOVER),
            Card.Ten(CardSuit.HEART)
        )

        cardsWithTotalBelowTwentyOne.forEach { player.addCardToHand(it) }

        assertThat(PlayerState.of(player)).isExactlyInstanceOf(PlayerState.Playing::class.java)
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

        assertThat(PlayerState.of(player)).isEqualTo(PlayerState.Done)
    }

    @Test
    fun `사용자가 게임을 진행할 수 있는 상태이고 hit 를 해 카드를 받은 후 총 숫자합이 21 미만일 경우 계속 게임 진행 상태로 유지된다`() {
        val player = Player("vivian")

        val cards = listOf(
            Card.Four(CardSuit.CLOVER),
            Card.Six(CardSuit.HEART),
        )
        val additionalCard = Card.King(CardSuit.CLOVER)

        cards.forEach { player.addCardToHand(it) }

        val playerState = PlayerState.of(player)
        assertThat(playerState).isExactlyInstanceOf(PlayerState.Playing::class.java)

        val newPlayerState = (playerState as PlayerState.Playing).hit(additionalCard)
        assertThat(newPlayerState).isExactlyInstanceOf(PlayerState.Playing::class.java)
    }

    @Test
    fun `사용자가 게임을 진행할 수 있는 상태이고 hit 를 해 카드를 받은 후 총 숫자합이 21 이상일 경우 게임 불가 상태가 된다`() {
        val player = Player("vivian")

        val cards = listOf(
            Card.Six(CardSuit.CLOVER),
            Card.Six(CardSuit.HEART),
        )
        val additionalCard = Card.King(CardSuit.CLOVER)

        cards.forEach { player.addCardToHand(it) }

        val playerState = PlayerState.of(player)
        assertThat(playerState).isExactlyInstanceOf(PlayerState.Playing::class.java)

        val newPlayerState = (playerState as PlayerState.Playing).hit(additionalCard)
        assertThat(newPlayerState).isEqualTo(PlayerState.Done)
    }

    @Test
    fun `사용자가 게임을 진행할 수 있는 상태이고 stand 를 했을 경우 게임 불가 상태가 된다`() {
        val player = Player("vivian")

        val cards = listOf(
            Card.Six(CardSuit.CLOVER),
            Card.Six(CardSuit.HEART),
        )

        cards.forEach { player.addCardToHand(it) }

        val playerState = PlayerState.of(player)
        assertThat(playerState).isExactlyInstanceOf(PlayerState.Playing::class.java)

        val newPlayerState = (playerState as PlayerState.Playing).stand()
        assertThat(newPlayerState).isEqualTo(PlayerState.Done)
    }
}
