package blackjack.domain

import blackjack.domain.CardNumber.*
import blackjack.domain.Suit.*
import io.kotest.matchers.collections.shouldContainInOrder
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class GamerTest {

    @DisplayName("참여자는 이름을 가지고 있다")
    @Test
    fun hasName() {
        val gamer = Gamer("hjw", listOf(Card(King, Diamond)))

        gamer.name shouldBe "hjw"
    }

    @DisplayName("받은 카드 목록을 가지고 있다.")
    @Test
    fun hasCardList() {
        val gamer = Gamer("hjw", listOf(Card(King, Diamond), Card(Queen, Spade)))

        gamer.cardList shouldContainInOrder (listOf(Card(King, Diamond), Card(Queen, Spade)))
    }

    @DisplayName("카드 숫자 합계를 계산해서 알고 있다.")
    @Test
    fun calculate() {
        val gamer = Gamer("hjw", listOf(Card(Five, Diamond), Card(Nine, Clover)))

        gamer.calculate() shouldBe 14
    }

    @DisplayName("Ace 카드는 1과 11로 합계를 계산할 수 있다.")
    @Test
    fun calculateAce() {
        val gamer = Gamer("hjw", listOf(Card(Ace, Diamond), Card(Nine, Clover)))

        gamer.calculate(isAceEleven = false) shouldBe 10
        gamer.calculate(isAceEleven = true) shouldBe 20
    }
}
