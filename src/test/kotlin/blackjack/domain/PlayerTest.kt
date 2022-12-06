package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage

class PlayerTest : StringSpec({
    "참여자 이름은 공백이면 예외가 발생한다." {
        val message = shouldThrow<IllegalArgumentException> { Player("") }
        message shouldHaveMessage "Player 이름은 필수 입력입니다."
    }

    "참여자의 카드 포인트가 21을 넘기면 Bust 이다." {
        forAll(
            row(
                listOf(
                    Card(Card.Suit.CLUB, Card.Number.SIX),
                    Card(Card.Suit.DIAMOND, Card.Number.SIX),
                    Card(Card.Suit.HEART, Card.Number.SIX),
                    Card(Card.Suit.SPADE, Card.Number.SIX)
                ),
                true
            ),
            row(
                listOf(
                    Card(Card.Suit.CLUB, Card.Number.SIX),
                    Card(Card.Suit.DIAMOND, Card.Number.SIX)
                ),
                false
            )
        ) { cards, isBust ->
            val player = Player("테스터")

            cards.forEach { card ->
                player.cardDeck.hit(card)
            }

            player.isBust() shouldBe isBust
        }
    }

    "참여자의 카드 포인트가 총합을 계산하다." {
        forAll(
            row(
                listOf(
                    Card(Card.Suit.CLUB, Card.Number.SIX),
                    Card(Card.Suit.DIAMOND, Card.Number.SIX),
                    Card(Card.Suit.HEART, Card.Number.SIX),
                    Card(Card.Suit.SPADE, Card.Number.SIX)
                ),
                24
            ),
            row(
                listOf(
                    Card(Card.Suit.CLUB, Card.Number.SIX),
                    Card(Card.Suit.DIAMOND, Card.Number.SIX)
                ),
                12
            )
        ) { cards, point ->
            val player = Player("테스터")

            cards.forEach { card ->
                player.cardDeck.hit(card)
            }

            player.point() shouldBe point
        }
    }

    "Ace 는 블랙잭 숫자보다 합산이 작을 경우엔 포인트가 11로 계산된다." {
        val player = Player("테스터")
        val card = Card(Card.Suit.SPADE, Card.Number.ACE)

        player.cardDeck.hit(card)

        player.point() shouldBe 11
    }

    "Ace 는 블랙잭 숫자보다 합산이 클 경우엔 포인트가 1로 계산된다." {
        val player = Player("테스터")

        val cards = listOf(
            Card(Card.Suit.HEART, Card.Number.NINE),
            Card(Card.Suit.DIAMOND, Card.Number.NINE),
            Card(Card.Suit.SPADE, Card.Number.ACE)
        )

        cards.forEach { card ->
            player.cardDeck.hit(card)
        }

        player.point() shouldBe 19
    }
})
