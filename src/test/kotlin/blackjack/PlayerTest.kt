package blackjack

import blackjack.model.Deck
import blackjack.model.Player
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class PlayerTest {

    private lateinit var deck: Deck

    @BeforeEach
    fun setUp() {
        deck = Deck()
        deck.resetAllCards()
    }

    @DisplayName("유효한 이름을 가진 플레이어를 생성해야 한다.")
    @Test
    fun `유효한 이름을 가진 플레이어를 생성해야 한다`() {
        val player = Player("Alice", deck)
        player.name shouldBe "Alice"
        player.cards.cards.size shouldBe 2
    }

    @DisplayName("유효하지 않은 이름을 가진 플레이어 생성 시 예외가 발생해야 한다.")
    @Test
    fun `유효하지 않은 이름을 가진 플레이어 생성 시 예외가 발생해야 한다`() {
        shouldThrow<IllegalArgumentException> {
            Player("Alice123", deck)
        }.message shouldBe "이름이 잘못 입력되었습니다."
    }

    @DisplayName("플레이어가 새로운 카드를 추가하면 카드 수가 증가해야 한다.")
    @Test
    fun `플레이어가 새로운 카드를 추가하면 카드 수가 증가해야 한다`() {
        val player = Player("Bob", deck)
        val initialSize = player.cards.cards.size
        player.takeNewCard()
        player.cards.cards.size shouldBe (initialSize + 1)
    }
}
