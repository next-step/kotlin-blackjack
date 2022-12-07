package blackjack.domain

import blackjack.domain.CardNumber.*
import blackjack.domain.Suit.*
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class GamerTest {

    @DisplayName("참여자는 이름을 가지고 있다")
    @Test
    fun hasName() {
        val gamer = Gamer("hjw")

        gamer.name shouldBe "hjw"
    }

    @DisplayName("받은 카드 목록을 가지고 있다.")
    @Test
    fun hasCard() {
        val gamer = Gamer("hjw")
        val cardKD = Card(King, Diamond)
        val cardQS = Card(Queen, Spade)

        gamer.addCard(cardKD)
        gamer.addCard(cardQS)

        gamer.hasCard(cardKD) shouldBe true
        gamer.hasCard(cardQS) shouldBe true
    }

    @DisplayName("카드 숫자 합계를 계산해서 알고 있다.")
    @Test
    fun calculate() {
        val gamer = Gamer("hjw")
        val card5D = Card(Five, Diamond)
        val card9C = Card(Nine, Clover)

        gamer.addCard(card5D)
        gamer.addCard(card9C)

        gamer.getTotalScore() shouldBe 14
    }

    @DisplayName("Ace 카드는 1과 11로 합계를 계산할 수 있다.")
    @Test
    fun calculateAce() {
        val gamer = Gamer("hjw")
        val cardAD = Card(Ace, Diamond)
        val card9C = Card(Nine, Clover)

        gamer.addCard(cardAD)
        gamer.addCard(card9C)

        gamer.getTotalScore(isAceEleven = false) shouldBe 10
        gamer.getTotalScore(isAceEleven = true) shouldBe 20
    }
}
