package blackjack.domain.game

import blackjack.domain.card.Card
import blackjack.domain.card.PlayerCardDeckCapture
import blackjack.domain.player.PlayerName
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardDistributionResultTest : StringSpec({

    "캡쳐된 카드들의 사이즈가 모두 같지 않다면 RuntimeException 예외 처리를 한다" {
        val captures = listOf(
            PlayerCardDeckCapture(
                playerName = PlayerName("1"),
                cards = Card.ALL_CARDS.take(2),
            ),
            PlayerCardDeckCapture(
                playerName = PlayerName("1"),
                cards = Card.ALL_CARDS.take(1),
            ),
        )
        shouldThrow<RuntimeException> {
            CardDistributionResult(captures)
        }
    }

    "캡쳐된 카드들의 사이즈가 모두 같다면 결과물이 만들어진다" {
        val captures = listOf(
            PlayerCardDeckCapture(
                playerName = PlayerName("1"),
                cards = Card.ALL_CARDS.take(2),
            ),
            PlayerCardDeckCapture(
                playerName = PlayerName("1"),
                cards = Card.ALL_CARDS.take(2),
            ),
        )
        shouldNotThrow<Throwable> {
            CardDistributionResult(captures)
        }
    }

    "분배된 카드의 개수를 반환한다" {
        val cards = Card.ALL_CARDS.take(2)
        val captures = listOf(
            PlayerCardDeckCapture(
                playerName = PlayerName("1"),
                cards = cards,
            )
        )
        CardDistributionResult(captures).countOfCardDistribution shouldBe cards.size
    }
})
