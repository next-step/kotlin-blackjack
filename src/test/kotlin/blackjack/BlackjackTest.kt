package blackjack

import blackjack.model.Deck
import blackjack.model.Player
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.common.runBlocking
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class BlackjackTest {

    private lateinit var deck: Deck
    private lateinit var blackjackController: BlackjackController

    @BeforeEach
    fun setUp() {
        deck = Deck()
        deck.resetAllCards()
        blackjackController = BlackjackController(deck)
    }

    @DisplayName("이름은 쉼표를 기준으로 구분한다.")
    @ParameterizedTest
    @ValueSource(strings = ["aaa,bbb", "aaa, bbb", "aaa , bbb"])
    fun `이름은 쉼표로 구분한다`(input: String) {
        val players = blackjackController.createPlayers(input)
        players.players.map { it.name } shouldBe listOf("aaa", "bbb")
    }

    @DisplayName("이름을 쉼표가 아닌 다른 것으로 구분하면 예외를 던진다.")
    @ParameterizedTest
    @ValueSource(strings = ["aaa|bbb", "aaa;bbb"])
    fun `이름을 쉼표가 아닌 다른 것으로 구분하면 예외를 던진다`(input: String) {
        shouldThrow<IllegalArgumentException> {
            blackjackController.createPlayers(input)
        }.message shouldBe "이름이 잘못 입력되었습니다."
    }

    @DisplayName("공백은 이름으로 인식하지 않는다.")
    @ParameterizedTest
    @ValueSource(strings = [",aaa,,bbb"])
    fun `공백은 이름으로 인식하지 않는다`(input: String) {
        val players = blackjackController.createPlayers(input)
        players.players.map { it.name } shouldBe listOf("aaa", "bbb")
    }

    @DisplayName("플레이어는 처음 2장의 카드를 받는다")
    @Test
    fun `플레이어는 처음 2장의 카드를 받는다`() {
        val player = Player("aaa", deck)
        player.cards.cards.size shouldBe 2
    }

    @DisplayName("카드는 중복하지 않는다.")
    @Test
    fun `카드는 중복하지 않는다`() {
        val player = Player("aaa", deck)
        val initialCards = player.cards.cards

        initialCards.size shouldBe 2
        initialCards.size shouldBe initialCards.toSet().size

        player.takeNewCard()
        val newCards = player.cards.cards

        newCards.size shouldBe 3
        newCards.size shouldBe newCards.toSet().size
    }

    @DisplayName("카드는 총 52개 존재한다.")
    @Test
    fun `카드는 총 52개 존재한다`() {
        // 덱에서 모든 카드를 소진
        repeat(52) {
            deck.takeRandomCard()
        }
        deck.cardsRemaining() shouldBe 0
    }

    @DisplayName("52개를 초과하면 예외를 던진다.")
    @Test
    fun `52개를 초과하면 예외를 던진다`() {
        // 덱에서 52장 뽑기
        repeat(52) {
            deck.takeRandomCard()
        }
        // 53번째 카드 뽑기 시 예외 발생
        shouldThrow<IllegalArgumentException> {
            deck.takeRandomCard()
        }.message shouldBe "남는 카드가 없습니다."
    }

    @DisplayName("플레이어는 'y'혹은 'n'가 아닌 문자를 입력 시 예외가 발생한다.")
    @Test
    fun `플레이어는 'y'혹은 'n'가 아닌 문자를 입력 시 예외가 발생한다`() {
        val player = Player("aaa", deck)
        shouldThrow<IllegalArgumentException> {
            blackjackController.checkPlayerRequest("nn", player)
        }.message shouldBe "'y'혹은 'n'로만 입력해주세요."
    }

    @DisplayName("플레이어가 카드를 받지 않을 때까지 계속 받을 수 있다.")
    @Test
    fun `플레이어가 카드를 받지 않을 때까지 계속 받을 수 있다`(): Unit = runBlocking {
        val player = Player("aaa", deck)
        // 처음에 2장을 받았으므로, 최대 50장 추가 가능 (총 52장)
        repeat(50) {
            blackjackController.checkPlayerRequest("y", player)
        }
        // 이제 덱에 남은 카드가 0장
        deck.cardsRemaining() shouldBe 0

        // 추가로 카드를 받으려 하면 예외 발생
        shouldThrow<IllegalArgumentException> {
            blackjackController.checkPlayerRequest("y", player)
        }.message shouldBe "남는 카드가 없습니다."
    }
}