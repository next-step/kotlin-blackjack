package blackjack.domain.player

import blackjack.Hand
import blackjack.domain.card.Score.Companion.BLACK_JACK_SCORE
import blackjack.domain.game.Money
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

@Suppress("NonAsciiCharacters")
class PlayerTest {

    private val name = PlayerName("aaj")

    @Test
    fun `Player는 스코어가 블랙잭 스코어보다 작다면 hit할 수 있다`() {
        val underBlackJackHand = Hand(BLACK_JACK_SCORE.value - 1)
        val player = Player(name, Money(1000), hand = underBlackJackHand)

        val result = player.canHit()

        assertThat(result).isTrue
    }

    @Test
    fun `Player는 스코어가 블랙잭 스코어와 같다면 hit할 수 없다`() {
        val blackJackScoreHand = Hand(BLACK_JACK_SCORE.value)
        val player = Player(name, Money(1000), hand = blackJackScoreHand)

        val result = player.canHit()

        assertThat(result).isFalse
    }

    @Test
    fun `Player는 스코어가 블랙잭 스코어보다 크다면 hit할 수 없다`() {
        val blackJackScoreHand = Hand(BLACK_JACK_SCORE.value + 1)
        val player = Player(name, Money(1000), hand = blackJackScoreHand)

        val result = player.canHit()

        assertThat(result).isFalse
    }
}
