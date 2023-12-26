package game.blackjack.v2.domain

import game.blackjack.v2.domain.card.CardNumber
import game.blackjack.v2.domain.card.CardNumber.ACE
import game.blackjack.v2.domain.card.CardNumber.EIGHT
import game.blackjack.v2.domain.card.CardNumber.FIVE
import game.blackjack.v2.domain.card.CardNumber.FOUR
import game.blackjack.v2.domain.card.CardNumber.JACK
import game.blackjack.v2.domain.card.CardNumber.KING
import game.blackjack.v2.domain.card.CardNumber.NINE
import game.blackjack.v2.domain.card.CardNumber.QUEEN
import game.blackjack.v2.domain.card.CardNumber.SEVEN
import game.blackjack.v2.domain.card.CardNumber.SIX
import game.blackjack.v2.domain.card.CardNumber.TEN
import game.blackjack.v2.domain.card.CardNumber.THREE
import game.blackjack.v2.domain.card.CardNumber.TWO
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class CardNumberTest : StringSpec({

    "ACE 카드가 아닌 경우, 모두 각각의 카드 점수를 그대로 반환한다." {
        forAll(
            row(TWO, 2),
            row(THREE, 3),
            row(FOUR, 4),
            row(FIVE, 5),
            row(SIX, 6),
            row(SEVEN, 7),
            row(EIGHT, 8),
            row(NINE, 9),
            row(TEN, 10),
            row(JACK, 10),
            row(QUEEN, 10),
            row(KING, 10)
        ) { cardNumber: CardNumber, expect: Int ->
            cardNumber.getScore() shouldBe expect
        }
    }

    "ACE 카드 점수는 현재 카드 패 점수가 10점 이하일 경우, 11점으로 반환한다." {
        forAll(
            row(1),
            row(2),
            row(3),
            row(4),
            row(5),
            row(6),
            row(7),
            row(8),
            row(9),
            row(10),
        ) { currentScore: Int ->
            ACE.getScore(currentScore) shouldBe 11
        }
    }

    "ACE 카드 점수는 현재 카드 패 점수가 11점 이상일 경우, 1점으로 반환한다." {
        forAll(
            row(11),
            row(12),
            row(13),
            row(14),
            row(15),
            row(16),
            row(17),
            row(18),
            row(19),
            row(20),
            row(21),
            row(22),
        ) { currentScore: Int ->
            ACE.getScore(currentScore) shouldBe 1
        }
    }
})
