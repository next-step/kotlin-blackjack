package blackjack.domain.participant.state

import blackjack.domain.deck.Card
import blackjack.domain.deck.CardNumber
import blackjack.domain.deck.CardPattern
import blackjack.domain.participant.GameResult
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

internal class BlackjackTest : FreeSpec({

    "블랙잭에서" - {
        // given
        val cards = mutableListOf(
            Card(pattern = CardPattern.CLOVER, number = CardNumber.TEN),
            Card(pattern = CardPattern.DIAMOND, number = CardNumber.ACE),
        )

        val blackjack = Blackjack(cards = Cards(values = cards))

        "카드 받기를 요청하면 예외가 발생한다." {
            shouldThrowExactly<IllegalStateException> {
                blackjack.receiveCard(
                    card = Card(
                        CardPattern.HEART,
                        CardNumber.TWO
                    )
                )
            }
        }

        "스테이로 상태변화를 요청하면 예외가 발생한다." {
            shouldThrowExactly<IllegalStateException> { blackjack.stay() }
        }

        "종료되었는지 물으면 True를 반환한다." {
            blackjack.isFinished() shouldBe true
        }

        "카드 목록을 반환할 수 있다." {
            blackjack.cards().shouldContainExactly(cards)
        }

        "점수를 반환할 수 있다." {
            blackjack.score().value shouldBe 21
        }

        "다른 점수와 승패를 비교할 때" - {
            "같은 블랙잭 점수면 무승부 결과가 나온다." {
                blackjack.judgementPlayerResult(otherScore = Score(21)) shouldBe GameResult.DRAW
            }

            "블랙잭이 아니면 무조건 승리한다." {
                blackjack.judgementPlayerResult(otherScore = Score(22)) shouldBe GameResult.WIN
                blackjack.judgementPlayerResult(otherScore = Score(20)) shouldBe GameResult.WIN
            }
        }
    }
})
