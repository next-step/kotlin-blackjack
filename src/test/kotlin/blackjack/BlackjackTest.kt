package blackjack

import blackjack.model.Card
import blackjack.model.Player
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class BlackjackTest {
    @BeforeEach
    fun setUp() {
        Card.resetAllCards()
    }

    @DisplayName("게임에 참여할 사람의 이름을 입력받는다.")
    @ParameterizedTest
    @ValueSource(strings = ["aaa,bbb"])
    fun `parsing test1`(input: String) {
        val playerNames = input.split(",")
        playerNames shouldBe listOf("aaa", "bbb")
    }

    @DisplayName("이름은 쉼표를 기준으로 구분한다.")
    @ParameterizedTest
    @ValueSource(strings = ["aaa,bbb", "aaa, bbb", "aaa , bbb"])
    fun `parsing test2`(input: String) {
        val playerNames = input.split(",")
        playerNames shouldBe listOf("aaa", "bbb")
    }

    @DisplayName("이름은 쉼표가 아니면 예외를 던진다.")
    @ParameterizedTest
    @ValueSource(strings = ["aaa|bbb", "aaa;bbb"])
    fun `parsing test3`(input: String) {
        val playerNames = input.split(",")
        shouldThrow<IllegalArgumentException> {
            val players = playerNames.map { Player(name = it) }
            players
        }.message shouldBe "이름이 잘못 입력되었습니다."
    }

    @DisplayName("공백은 이름으로 인식하지 않는다.")
    @ParameterizedTest
    @ValueSource(strings = [",aaa,,bbb"])
    fun `parsing test4`(input: String) {
        val playerNames = input.split(",")
        val players = playerNames.map { Player(name = it) }
        players shouldBe listOf(Player("aaa"), Player("bbb"))
    }

    @DisplayName("플레이어는 처음 2장의 카드를 받는다")
    @Test
    fun `person test`() {
        val players = Player("aaa")
        players.cards.cards.size shouldBe 2
    }

    @DisplayName("카드는 중복하지 않는다.")
    @Test
    fun `card test1`() {
        val player = Player("aaa")
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
    fun `card test2`() {
        repeat(52) {
            Card.takeRandomCard()
        }
    }

    @DisplayName("카드는 총 52개 존재한다.")
    @Test
    fun `card test3`() {
        shouldThrow<IllegalStateException> {
            repeat(53) {
                Card.takeRandomCard()
            }
        }.message shouldBe "남는 카드가 없습니다."
    }

    @DisplayName("플레이어는 'y'혹은 'n'가 아닌 문자를 입력 시 예외가 발생한다.")
    @Test
    fun `game test2`() {
        val blackjackController = BlackjackController()
        val player = Player("aaa")
        shouldThrow<IllegalArgumentException> {
            blackjackController.checkPlayerRequest("nn", player)
        }.message shouldBe "'y'혹은 'n'로만 입력해주세요."
    }

    @DisplayName("플레이어가 카드를 받지 않을 때까지 계속 받을 수 있다.")
    @Test
    fun `game test3`() {
        val blackjackController = BlackjackController()
        val player = Player("aaa")
        // 처음에 2개를 가져가기 때문
        repeat(50) {
            blackjackController.checkPlayerRequest("y", player)
        }
    }
}
