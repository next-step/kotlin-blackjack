package blackjack.domain.participant.state

import blackjack.domain.deck.Card
import blackjack.domain.deck.CardNumber.TEN
import blackjack.domain.deck.CardNumber.TWO
import blackjack.domain.deck.CardPattern.CLOVER
import blackjack.domain.deck.CardPattern.DIAMOND
import blackjack.domain.deck.CardPattern.HEART
import blackjack.domain.deck.CardPattern.SPADE
import blackjack.domain.participant.GameResult
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

internal class BustTest : FreeSpec({

    "버스트에서" - {
        // given
        val cards = mutableListOf(
            Card(pattern = CLOVER, number = TEN),
            Card(pattern = DIAMOND, number = TEN),
            Card(pattern = SPADE, number = TEN),
        )

        val bust = Bust(cards = Cards(values = cards))

        "카드 받기를 요청하면 예외가 발생한다." {
            shouldThrowExactly<IllegalStateException> { bust.receiveCard(card = Card(HEART, TWO)) }
        }

        "스테이로 상태변화를 요청하면 예외가 발생한다." {
            shouldThrowExactly<IllegalStateException> { bust.stay() }
        }

        "종료되었는지 물으면 True를 반환한다." {
            bust.isFinished() shouldBe true
        }

        "카드 목록을 반환할 수 있다." {
            bust.cards().shouldContainExactly(cards)
        }

        "점수를 반환할 수 있다." {
            bust.score().value shouldBe 30
        }

        "다른 점수와 승패를 비교할 때 무조건 패배한다." {
            bust.judgementGameResult(otherScore = Score(22)) shouldBe GameResult.LOSE
            bust.judgementGameResult(otherScore = Score(21)) shouldBe GameResult.LOSE
            bust.judgementGameResult(otherScore = Score(20)) shouldBe GameResult.LOSE
            bust.judgementGameResult(otherScore = Score(1)) shouldBe GameResult.LOSE
        }
    }
})
