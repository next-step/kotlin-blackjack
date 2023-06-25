package blackjack.ui

import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardSymbol
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe

class OutViewConstMapTest : StringSpec({
    "카드 숫자 표현 상수를 지원하는 타입" {
        table(
            headers("카드 숫자", "표현 상수"),
            row(CardNumber.ACE, "1"),
            row(CardNumber.TWO, "2"),
            row(CardNumber.THREE, "3"),
            row(CardNumber.FOUR, "4"),
            row(CardNumber.FIVE, "5"),
            row(CardNumber.SIX, "6"),
            row(CardNumber.SEVEN, "7"),
            row(CardNumber.EIGHT, "8"),
            row(CardNumber.NINE, "9"),
            row(CardNumber.TEN, "10"),
            row(CardNumber.JACK, "J"),
            row(CardNumber.QUEEN, "Q"),
            row(CardNumber.KING, "K"),
        ).forAll { number, expect ->
            OutViewConstMap[number] shouldBe expect
        }
    }

    "카드 모양 표현 상수를 지원하는 타입" {
        table(
            headers("카드 모양", "표현 상수"),
            row(CardSymbol.CLUB, "클로바"),
            row(CardSymbol.DIAMOND, "다이아"),
            row(CardSymbol.SPADE, "스페이드"),
            row(CardSymbol.HEART, "하트")
        ).forAll { symbol, expect ->
            OutViewConstMap[symbol] shouldBe expect
        }
    }
})
