package blackjack

import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `Player는 '카드 리스트'와 '카드 총합 점수' 정보를 가지고 있다`() {
        val player = Player()

        player.cards shouldNotBe null
        player.score shouldNotBe null
    }

    @Test
    fun `Player는 게임 시작시 랜덤한 2장의 카드를 가지며 0점으로 시작합니다`() {
        val deck = CardDeck()
        val cards = deck.getRandomCards(2)

        val player = Player(cards)

        player.cards.size shouldBe 2
        player.score shouldBe 0
    }
}
