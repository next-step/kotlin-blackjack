package blackjack.domain

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

    @DisplayName("카드 숫자 합계를 계산해서 알고 있다.")
    @Test
    fun calculate() {
        val player = Player("hjw")
        val card5D = Card(CardNumber.Five, Suit.Diamond)
        val card9C = Card(CardNumber.Nine, Suit.Clover)

        player.receive(card5D)
        player.receive(card9C)

        player.totalScore shouldBe 14
    }

    @DisplayName("Ace 카드가 포함된 경우 21에 가장 가까운 값으로 점수가 계산된다.")
    @Test
    fun calculateAce() {
        val player = Player("hjw")
        val cardAD = Card(CardNumber.Ace, Suit.Diamond)
        val card9C = Card(CardNumber.Nine, Suit.Clover)

        player.receive(cardAD)
        player.receive(card9C)

        player.totalScore shouldBe 20
    }
}
