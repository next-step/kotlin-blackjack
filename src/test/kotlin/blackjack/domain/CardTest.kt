package blackjack.domain

import blackjack.enums.Rank
import blackjack.enums.Symbol
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CardTest {

    @Test
    fun `카드의 심볼은 다이아몬드, 하트, 스페이드, 클로버 (4가지)를 가진다`() {
        val symbolList = Symbol.values()

        symbolList.size shouldBe 4
        symbolList shouldContainAll listOf(Symbol.HEARTS, Symbol.DIAMONDS, Symbol.CLUBS, Symbol.SPADES)
    }

    @Test
    fun `카드의 숫자는 Ace (1 or 11), 2 ~ 10, King(10), Queen(10), Jack(10) 총 13 종류를 가진다`() {
        val rankList = Rank.values()
        rankList.size shouldBe 13
    }

    @Test
    fun `King, Queen, Jack 카드의 총합은 30이다`() {
        val rankList = Rank.values().filter { it == Rank.KING || it == Rank.QUEEN || it == Rank.JACK }
        rankList.size shouldBe 3
        rankList.sumOf { it.value } shouldBe 30
    }

    @Test
    fun `Ace의 경우 숫자 1과 11을 가지고 있다`() {
        val aceNumbers = listOf(Rank.ACE.value, Rank.ACE.hiddenValue)
        aceNumbers.size shouldBe 2
        aceNumbers.sumOf { it } shouldBe 12
    }
}
