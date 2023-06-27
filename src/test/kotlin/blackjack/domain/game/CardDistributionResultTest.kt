package blackjack.domain.game

import blackjack.domain.card.Card
import blackjack.domain.card.CardHolder
import blackjack.domain.gamer.PlayerCards
import blackjack.domain.gamer.PlayerName
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec

class CardDistributionResultTest : StringSpec({

    "플레이어 카드들의 사이즈가 분배한 카드 수와 같지 않다면 RuntimeException 예외 처리를 한다" {
        val dealerCards = listOf(
            CardHolder.Open(Card.ALL_CARDS[0]),
            CardHolder.Hide,
        )
        val playerCards = listOf(
            PlayerCards(
                playerName = PlayerName("1"),
                cards = Card.ALL_CARDS.take(2),
            ),
            PlayerCards(
                playerName = PlayerName("1"),
                cards = Card.ALL_CARDS.take(1),
            ),
        )
        shouldThrow<RuntimeException> {
            CardDistributionResult(
                distributionCardSize = 2,
                dealerCards = dealerCards,
                playerCards = playerCards,
            )
        }
    }

    "딜러의 카드 사이즈가 분배한 카드 수와 다르다면 RuntimeException 예외 처리를 한다" {
        val dealerCards = listOf(
            CardHolder.Open(Card.ALL_CARDS[0]),
        )
        val playerCards = listOf(
            PlayerCards(
                playerName = PlayerName("1"),
                cards = Card.ALL_CARDS.take(2),
            ),
            PlayerCards(
                playerName = PlayerName("1"),
                cards = Card.ALL_CARDS.take(2),
            ),
        )
        shouldThrow<RuntimeException> {
            CardDistributionResult(
                distributionCardSize = 2,
                dealerCards = dealerCards,
                playerCards = playerCards,
            )
        }
    }

    "딜러와 플레이어 카드들의 사이즈가 분배한 카드 수와 같다면 결과물이 만들어진다" {
        val dealerCards = listOf(
            CardHolder.Open(Card.ALL_CARDS[0]),
            CardHolder.Hide,
        )
        val playerCards = listOf(
            PlayerCards(
                playerName = PlayerName("1"),
                cards = Card.ALL_CARDS.take(2),
            ),
            PlayerCards(
                playerName = PlayerName("1"),
                cards = Card.ALL_CARDS.take(2),
            ),
        )
        shouldNotThrow<Throwable> {
            CardDistributionResult(
                distributionCardSize = 2,
                dealerCards = dealerCards,
                playerCards = playerCards,
            )
        }
    }
})
