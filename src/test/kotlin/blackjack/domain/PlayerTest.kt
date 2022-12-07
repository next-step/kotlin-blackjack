package blackjack.domain

import blackjack.domain.CardNumber.*
import blackjack.domain.Suit.*
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class PlayerTest {

    @DisplayName("참여자는 이름을 가지고 있다")
    @Test
    fun hasName() {
        val player = Player("hjw")

        player.name shouldBe "hjw"
    }

    @DisplayName("받은 카드 목록을 가지고 있다.")
    @Test
    fun hasCard() {
        val player = Player("hjw")
        val cardKD = Card(King, Diamond)
        val cardQS = Card(Queen, Spade)

        player.addCard(cardKD)
        player.addCard(cardQS)

        player.hasCard(cardKD) shouldBe true
        player.hasCard(cardQS) shouldBe true
    }

    @DisplayName("카드 숫자 합계를 계산해서 알고 있다.")
    @Test
    fun calculate() {
        val player = Player("hjw")
        val card5D = Card(Five, Diamond)
        val card9C = Card(Nine, Clover)

        player.addCard(card5D)
        player.addCard(card9C)

        player.totalScore shouldBe 14
    }

    @DisplayName("Ace 카드가 포함된 경우 21에 가장 가까운 값으로 점수가 계산된다.")
    @Test
    fun calculateAce() {
        val player = Player("hjw")
        val cardAD = Card(Ace, Diamond)
        val card9C = Card(Nine, Clover)

        player.addCard(cardAD)
        player.addCard(card9C)

        player.totalScore shouldBe 20
    }
}
