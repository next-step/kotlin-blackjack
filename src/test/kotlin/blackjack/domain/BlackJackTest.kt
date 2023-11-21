package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackJackTest {
    @Test
    fun `PreparedPlayer는 카드 두 장을 받은 OngoingPlayer가 된다`() {
        val deck = Deck()
        val blackJack = BlackJack(deck)

        val preparedPlayers = listOf(PreparedPlayer("a"))
        val onGoingPlayers = blackJack.play(preparedPlayers)

        val actualCardsCount = onGoingPlayers.first().cards.value.size
        val expectedCardsCount = 2

        assertThat(actualCardsCount).isEqualTo(expectedCardsCount)
    }

    @Test
    fun `PreparedPlayer는 OngoingPlayer가 되더라도 이름이 유지된다`() {
        val deck = Deck()
        val blackJack = BlackJack(deck)

        val preparedPlayers = listOf(PreparedPlayer("a"))
        val onGoingPlayers = blackJack.play(preparedPlayers)

        val actualPlayerName = onGoingPlayers.first().name
        val expectedPlayerName = "a"

        assertThat(actualPlayerName).isEqualTo(expectedPlayerName)
    }

    @Test
    fun `진행중인 플레이어는 카드를 더 뽑을 수 있다`() {
        val deck = Deck()
        val blackJack = BlackJack(deck)
        val player = OnGoingPlayer("a", Cards(listOf()))

        val onGoingPlayer = blackJack.hit(player)

        val actualCardsCount = onGoingPlayer.cards.value.size
        val expectedCardsCount = 1

        assertThat(actualCardsCount).isEqualTo(expectedCardsCount)
    }

    @Test
    fun `카드를 뽑았을 때 21을 초과하면 버스트된 플레이어가 된다`() {
        val deck = Deck()
        val blackJack = BlackJack(deck)
        val cards = Cards(
            listOf(
                Card(Suit.Spade, Rank.Ten),
                Card(Suit.Spade, Rank.Ten),
                Card(Suit.Spade, Rank.Ace),
            )
        )
        val player = OnGoingPlayer("a", cards)

        val bustedPlayer = blackJack.hit(player)

        assertThat(bustedPlayer).isInstanceOf(BustedPlayer::class.java)
    }

    @Test
    fun `스테이 하면 종료한 플레이어가 된다`() {
        val deck = Deck()
        val blackJack = BlackJack(deck)
        val player = object : Player {
            override val name: String = ""
            override val cards: Cards = Cards(emptyList())
        }

        val finishedPlayer = blackJack.stay(player)

        assertThat(finishedPlayer).isInstanceOf(FinishedPlayer::class.java)
    }
}
