package blackjack.domain.participant.state

import blackjack.domain.deck.Card
import blackjack.domain.deck.CardNumber
import blackjack.domain.deck.CardPattern
import blackjack.domain.participant.GameResult
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

internal class StayTest : FreeSpec({

    "스테이에서" - {
        // given
        val cards = mutableListOf(
            Card(pattern = CardPattern.CLOVER, number = CardNumber.TEN)
        )

        val stay = Stay(cards = Cards(values = cards))

        "카드 받기를 요청하면 예외가 발생한다." {
            shouldThrowExactly<IllegalStateException> {
                stay.receiveCard(
                    card = Card(
                        CardPattern.HEART,
                        CardNumber.TWO
                    )
                )
            }
        }

        "스테이로 상태변화를 요청하면 예외가 발생한다." {
            shouldThrowExactly<IllegalStateException> { stay.stay() }
        }

        "종료되었는지 물으면 True를 반환한다." {
            stay.isFinished() shouldBe true
        }

        "카드 목록을 반환할 수 있다." {
            stay.cards().shouldContainExactly(cards)
        }

        "점수를 반환할 수 있다." {
            stay.score().value shouldBe 10
        }

        "다른 점수와 승패를 비교할 때 " - {
            "상대점수가 블랙잭 점수면 무조건 패배한다." {
                stay.judgementGameResult(otherScore = Score(21)) shouldBe GameResult.LOSE
            }

            "상대점수가 버스트 점수면 무조건 승리한다." {
                stay.judgementGameResult(otherScore = Score(22)) shouldBe GameResult.WIN
            }

            "내 점수가 더 높으면 승리한다." {
                stay.judgementGameResult(otherScore = Score(9)) shouldBe GameResult.WIN
            }

            "내 점수가 더 낮으면 패배한다." {
                stay.judgementGameResult(otherScore = Score(11)) shouldBe GameResult.LOSE
            }

            "내 점수와 상대 점수가 같으면 무승부가 된다." {
                stay.judgementGameResult(otherScore = Score(10)) shouldBe GameResult.DRAW
            }
        }
    }
})
