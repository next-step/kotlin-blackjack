package blackjack

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class BlackjackTest {

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

    }

    @DisplayName("카드는 중복하지 않는다.")
    @Test
    fun `card test1`() {

    }

    @DisplayName("카드는 램덤하게 정해진다.")
    @Test
    fun `card test2`() {

    }

    @DisplayName("플레이어는 카드를 더 받을지 덜 받을지 선택할 수 있다.")
    @Test
    fun `game test1`() {

    }

    @DisplayName("플레이어가 카드를 받지 않을 때까지 계속 받을 수 있다.")
    @Test
    fun `game test2`() {

    }

    @DisplayName("카드가 의미하는 수의 합이 21이 넘은 플레이어는 패배한다.")
    @Test
    fun `game test3`() {

    }

    @DisplayName("카드가 의미하는 수의 합이 21에 제일 가까운 플레이어가 승리한다.")
    @Test
    fun `game test4`() {

    }
}