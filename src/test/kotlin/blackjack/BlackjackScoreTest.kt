package blackjack

import blackjack.domain.model.BlackjackScore
import blackjack.domain.model.Card
import blackjack.domain.model.Cards
import blackjack.domain.model.Pattern
import blackjack.domain.model.Sign
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

fun BlackjackScore(cards: Set<Card> = emptySet()): BlackjackScore {
    return BlackjackScore.create(Cards.from(cards))
}

class BlackjackScoreTest : StringSpec({

    "숫자로만 이루어진 카드들은 숫자의 합이 점수이다." {
        val cards = Cards.from(
            setOf(
                Card.of(Pattern.CLOVER, Sign.TWO),
                Card.of(Pattern.CLOVER, Sign.THREE),
                Card.of(Pattern.CLOVER, Sign.SEVEN)
            )
        )
        cards.score().value shouldBe 12
    }

    "J, K, Q 카드는 10점으로 합산된다." {
        val cards = Cards.from(
            setOf(
                Card.of(Pattern.CLOVER, Sign.JACK),
                Card.of(Pattern.CLOVER, Sign.QUEEN),
                Card.of(Pattern.CLOVER, Sign.KING)
            )
        )
        cards.score().value shouldBe 30
    }

    "A 카드는 1점 또는 11점으로 합산 할 수 있다." {
        val cards1 = Cards.from(
            setOf(
                Card.of(Pattern.CLOVER, Sign.QUEEN),
                Card.of(Pattern.CLOVER, Sign.KING),
                Card.of(Pattern.CLOVER, Sign.ACE)
            )
        )
        cards1.score().value shouldBe 21

        val cards2 = Cards.from(
            setOf(
                Card.of(Pattern.CLOVER, Sign.QUEEN),
                Card.of(Pattern.CLOVER, Sign.ACE)
            )
        )
        cards2.score().value shouldBe 21
    }

    "빈 카드들을 점수로 바꿀 경우 카드점수의 합계가 1보다 작아 IllegalArgumentException 예외를 던진다." {
        shouldThrow<IllegalArgumentException> {
            BlackjackScore()
        }
    }

    "[Q하트, Q클로버, Q스페이스, 2스페이스]를 점수로 바꿀 경우 카드점수의 합계가 31을 넘어 IllegalArgumentException 예외를 던진다." {
        shouldThrow<IllegalArgumentException> {
            BlackjackScore(
                setOf(
                    Card(Pattern.HEART, Sign.QUEEN),
                    Card(Pattern.CLOVER, Sign.QUEEN),
                    Card(Pattern.SPACE, Sign.QUEEN),
                    Card(Pattern.SPACE, Sign.TWO)
                )
            )
        }
    }

    "[A하트, 2하트]를 점수로 바꿀 경우 13점을 가진 스코어가 생성되어야한다." {
        BlackjackScore(
            setOf(
                Card(Pattern.HEART, Sign.ACE),
                Card(Pattern.HEART, Sign.TWO)
            )
        ).value shouldBe 13
    }

    "[A하트, 2하트, J하트]를 점수로 바꿀 경우 13점을 가진 스코어가 생성되어야한다." {
        BlackjackScore(
            setOf(
                Card(Pattern.HEART, Sign.ACE),
                Card(Pattern.HEART, Sign.TWO),
                Card(Pattern.HEART, Sign.JACK)
            )
        ).value shouldBe 13
    }

    "[A하트, J하트]를 점수로 바꿀 경우 21점을 가진 스코어가 생성되어야한다." {
        BlackjackScore(
            setOf(
                Card(Pattern.HEART, Sign.ACE),
                Card(Pattern.HEART, Sign.JACK)
            )
        ).value shouldBe 21
    }

    "[7클로버, J하트]를 가지고 있을 경우 카드의 합계가 21점을 넘지 않아 버스트가 아니다." {
        val blackjackScore = BlackjackScore(
            setOf(
                Card.of(Pattern.CLOVER, Sign.SEVEN),
                Card.of(Pattern.HEART, Sign.JACK)
            )
        )
        blackjackScore.isBust() shouldBe false
    }

    "[7클로버, J하트, 4스페이스]를 점수로 바꿀 경우 카드의 합계가 21점을 넘어 버스트다." {
        val blackjackScore = BlackjackScore(
            setOf(
                Card.of(Pattern.CLOVER, Sign.SEVEN),
                Card.of(Pattern.HEART, Sign.JACK),
                Card.of(Pattern.SPACE, Sign.EIGHT)
            )
        )
        blackjackScore.isBust() shouldBe true
    }
})
